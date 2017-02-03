package com.ahmadrosid.olxlite.core.detail;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.ahmadrosid.lib.baseapp.core.BaseActivity;
import com.ahmadrosid.lib.baseapp.helper.CurrencyHelper;
import com.ahmadrosid.olxlite.R;
import com.ahmadrosid.olxlite.core.home.IklanModel;
import com.bumptech.glide.Glide;
import com.google.firebase.crash.FirebaseCrash;

import butterknife.BindView;

public class DetailIklanActivity extends BaseActivity {

    @BindView(R.id.img)ImageView img;
    @BindView(R.id.judul)TextView judul;
    @BindView(R.id.harga)TextView harga;
    @BindView(R.id.description)TextView description;

    @Override protected int getResourceLayout() {
        return R.layout.activity_detail_iklan;
    }

    @Override protected void onViewReady(Bundle savedInstanceState) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setOnBackToolbar();

        try {
            IklanModel data = getIntent().getParcelableExtra("data");
            String[] mImage = data.getUrl_image().split(",");
            Glide.with(this).load(mImage[0]).centerCrop().into(img);
            judul.setText(data.getJudul());
            harga.setText(CurrencyHelper.format(data.getHarga()));
            description.setText(data.getDescription());
            getSupportActionBar().setTitle(data.getJudul());
        }catch (Exception e){
            FirebaseCrash.logcat(Log.DEBUG, "Error set data detail.", e.getMessage());
        }
    }

}
