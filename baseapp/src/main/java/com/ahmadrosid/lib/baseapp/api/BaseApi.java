/*
 * Copyright (c) 2017 Ahmad Rosid.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.ahmadrosid.lib.baseapp.api;

import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;
import rx.subscriptions.Subscriptions;

/**
 * Created by ocittwo on 1/8/17.
 *
 * @Author Ahmad Rosid
 * @Email ocittwo@gmail.com
 * @Github https://github.com/ar-android
 * @Web http://ahmadrosid.com
 */
public class BaseApi {
    private static Observable<Response> request(OkHttpClient client, Request request) {
        return Observable.create((Subscriber<? super Response> subj) -> {
            final Call call = client.newCall(request);

            subj.add(Subscriptions.create(call::cancel));

            call.enqueue(new Callback() {
                @Override public void onFailure(Call call, IOException e) {
                    subj.onError(e);
                }

                @Override public void onResponse(Call call, Response response) throws IOException {
                    Throwable error = getFailureExceptionOnBadStatus(response);
                    if (error != null) {
                        subj.onError(error);
                        return;
                    }

                    subj.onNext(response);
                    subj.onCompleted();
                }
            });
        });
    }

    private static Observable<byte[]> streamBytes(OkHttpClient client, Request request) {
        return request(client, request)
                .flatMap(response -> Observable.create((Subscriber<? super byte[]> subj) -> {
                    AsyncTask t = new AsyncTask() {
                        @Override
                        protected Void doInBackground(Object[] objects) {
                            InputStream stream;
                            byte[] buffer = new byte[65536];
                            int bytesRead = 0;

                            stream = response.body().byteStream();
                            try {
                                while (bytesRead > -1 && !subj.isUnsubscribed()) {
                                    bytesRead = stream.read(buffer, 0, 65536);
                                    if (bytesRead < 1) continue;

                                    subj.onNext(Arrays.copyOfRange(buffer, 0, bytesRead));
                                }

                                if (!subj.isUnsubscribed()) subj.onCompleted();
                                stream.close();
                            } catch (IOException ex) {
                                subj.onError(ex);
                            }

                            return null;
                        }
                    };

                    subj.add(Subscriptions.create(() -> t.cancel(false)));
                    t.execute();
                }));
    }

    private static Observable<String> streamStrings(OkHttpClient client, Request request) {
        return streamBytes(client, request)
                .map(bytes -> {
                    try {
                        return new String(bytes, "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        throw new RuntimeException("UTF8 isn't supported this will never happen");
                    }
                });
    }

    public static Observable<String> streamLines(OkHttpClient client, Request request) {
        return streamStrings(client, request)
                .concatWith(Observable.just("\n"))
                .flatMap(new Func1<String, Observable<? extends String>>() {
                    String remainingString = "";

                    @Override
                    public Observable<? extends String> call(String s) {
                        String[] lines = (remainingString + s).split("\n");
                        if (s.charAt(s.length() - 1) != '\n') {
                            remainingString = lines[lines.length - 1];
                            return Observable.from(Arrays.copyOfRange(lines, 0, lines.length - 1));
                        }

                        remainingString = "";
                        return Observable.from(lines);
                    }
                })
                .filter(x -> x.length() > 0);
    }


    static Request createRequestPost(RequestBody body, String endPoint) {
        return new Request.Builder()
                .url(endPoint)
                .post(body)
                .addHeader("content-type", "application/x-www-form-urlencoded")
                .build();
    }

    static Request createRequestGet(String endPoint) {
        return new Request.Builder().url(endPoint).get().build();
    }

    private static Throwable getFailureExceptionOnBadStatus(Response resp) {
        if (resp.code() < 399) return null;
        return new FailedResponseException(resp);
    }

    private static class FailedResponseException extends RuntimeException {
        public FailedResponseException(Response msg) {
            super(msg.toString());
        }
    }
}
