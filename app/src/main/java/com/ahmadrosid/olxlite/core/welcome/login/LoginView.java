package com.ahmadrosid.olxlite.core.welcome.login;

import android.content.Intent;

import com.ahmadrosid.lib.baseapp.core.BaseView;

/**
 * Created by ocittwo on 2/2/17.
 *
 * @Author Ahmad Rosid
 * @Email ocittwo@gmail.com
 * @Github https://github.com/ar-android
 * @Web http://ahmadrosid.com
 */
public interface LoginView extends BaseView{
    void startResult(Intent signInIntent, int rcSignIn);
    void successLogin();
    void failedLogin();
}
