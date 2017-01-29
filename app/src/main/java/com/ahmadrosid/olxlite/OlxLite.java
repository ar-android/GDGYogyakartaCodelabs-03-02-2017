package com.ahmadrosid.olxlite;

import android.app.Application;

import com.ahmadrosid.lib.baseapp.BaseApp;

/**
 * Created by ocittwo on 1/29/17.
 *
 * @Author Ahmad Rosid
 * @Email ocittwo@gmail.com
 * @Github https://github.com/ar-android
 * @Web http://ahmadrosid.com
 */
public class OlxLite extends Application{

    @Override public void onCreate() {
        super.onCreate();
        BaseApp.init(this);
    }

}
