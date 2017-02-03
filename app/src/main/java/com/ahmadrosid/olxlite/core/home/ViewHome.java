package com.ahmadrosid.olxlite.core.home;

import android.support.v7.widget.RecyclerView;

import com.ahmadrosid.lib.baseapp.core.BaseView;

/**
 * Created by ocittwo on 1/30/17.
 *
 * @Author Ahmad Rosid
 * @Email ocittwo@gmail.com
 * @Github https://github.com/ar-android
 * @Web http://ahmadrosid.com
 */
public interface ViewHome extends BaseView{
    void setDataIklan(RecyclerView.Adapter adapter);
    void showMessage(String message);
    void dismishrefresh();
}
