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

package com.ahmadrosid.lib.baseapp.core;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.bind.DateTypeAdapter;

import java.security.SecureRandom;
import java.util.Date;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by ocittwo on 1/8/17.
 *
 * @Author Ahmad Rosid
 * @Email ocittwo@gmail.com
 * @Github https://github.com/ar-android
 * @Web http://ahmadrosid.com
 */

public class BasePresenter<T extends BaseView> implements Presenter<T> {

    public T mvpView;
    private static Gson gson;
    private CompositeSubscription subscription = new CompositeSubscription();

    static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    static SecureRandom rnd = new SecureRandom();

    public BasePresenter(T mvpView) {
        this.mvpView = mvpView;
        gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .setPrettyPrinting()
                .registerTypeAdapter(Date.class, new DateTypeAdapter())
                .create();
    }

    public CompositeSubscription getSubscraiber() {
        return subscription;
    }

    @Override
    public void detachView() {
        mvpView = null;
        subscription.unsubscribe();
    }

    public boolean isViewAttached() {
        return mvpView != null;
    }

    public T getMvpView() {
        return mvpView;
    }

    public static Gson getParser() {
        return gson;
    }

    public void checkViewAttached() {
        if (!isViewAttached()) throw new MvpViewNotAttachedException();
    }

    public String randomString(){
        int len = 30;
        StringBuilder sb = new StringBuilder( len );
        for( int i = 0; i < len; i++ )
            sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
        return sb.toString();
    }

    public static class MvpViewNotAttachedException extends RuntimeException {
        public MvpViewNotAttachedException() {
            super(
                    "Please call Presenter.attachView(MvpView) before" + " requesting data to the Presenter"
            );
        }
    }
}
