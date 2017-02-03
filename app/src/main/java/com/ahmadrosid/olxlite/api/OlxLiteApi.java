package com.ahmadrosid.olxlite.api;

import com.ahmadrosid.lib.baseapp.api.BaseApi;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import rx.Observable;

/**
 * Created by ocittwo on 2/2/17.
 *
 * @Author Ahmad Rosid
 * @Email ocittwo@gmail.com
 * @Github https://github.com/ar-android
 * @Web http://ahmadrosid.com
 */

public class OlxLiteApi {

    private static final OkHttpClient client = new OkHttpClient();

    public static Observable<String> getIklan(){
        Request request = new Request.Builder()
                .url(Endpoint.IKLAN)
                .get()
                .build();
        return BaseApi.streamLines(client, request);
    }

    public static Observable<String> getIklanSaya(){
        Request request = new Request.Builder()
                .url(Endpoint.IKLAN_SAYA)
                .get()
                .build();
        return BaseApi.streamLines(client, request);
    }
}
