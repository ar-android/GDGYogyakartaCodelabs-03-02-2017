package com.ahmadrosid.olxlite.core.iklan_saya.newiklan;

import android.app.Activity;

import com.ahmadrosid.lib.baseapp.core.BaseView;

/**
 * Created by ocittwo on 2/3/17.
 *
 * @Author Ahmad Rosid
 * @Email ocittwo@gmail.com
 * @Github https://github.com/ar-android
 * @Web http://ahmadrosid.com
 */

public interface NewIklanView extends BaseView{
    Activity getActivity();
    void finishAddNewIklan();
}
