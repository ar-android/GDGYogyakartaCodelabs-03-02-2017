package com.ahmadrosid.olxlite.core.iklan_saya;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ahmadrosid.lib.baseapp.core.BaseFragment;
import com.ahmadrosid.olxlite.R;

import butterknife.BindView;

/**
 * Created by ocittwo on 1/29/17.
 *
 * @Author Ahmad Rosid
 * @Email ocittwo@gmail.com
 * @Github https://github.com/ar-android
 * @Web http://ahmadrosid.com
 */
public class IklanSayaFragment extends BaseFragment {

    @BindView(R.id.loader) ProgressBar loader;
    @BindView(R.id.list_iklan_saya) RecyclerView list_iklan_saya;
    @BindView(R.id.empty) TextView empty;
    @BindView(R.id.logout) TextView logout;

    @Override protected int getResourceLayout() {
        return R.layout.fragment_iklan_saya;
    }

    @Override protected void onViewReady(@Nullable Bundle savedInstanceState) {
        loader.setVisibility(View.VISIBLE);
        new Handler().postDelayed(this::finishLoad, 2500);
    }

    private void finishLoad() {
        loader.setVisibility(View.GONE);
        empty.setVisibility(View.VISIBLE);
    }


}
