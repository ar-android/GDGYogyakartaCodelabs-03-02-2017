package com.ahmadrosid.olxlite.core.iklan_saya;

import android.support.v7.widget.RecyclerView;

import com.ahmadrosid.lib.baseapp.core.BaseView;

/**
 * Created by ocittwo on 2/3/17.
 *
 * @Author Ahmad Rosid
 * @Email ocittwo@gmail.com
 * @Github https://github.com/ar-android
 * @Web http://ahmadrosid.com
 */
public interface IklanSayaView extends BaseView{
    void showMessage(String messages);
    void setDataIklan(RecyclerView.Adapter adapter);
    void dismishrefresh();
}
