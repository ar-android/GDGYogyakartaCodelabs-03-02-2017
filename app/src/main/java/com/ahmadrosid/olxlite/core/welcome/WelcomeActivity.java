package com.ahmadrosid.olxlite.core.welcome;

import android.os.Bundle;

import com.ahmadrosid.lib.baseapp.core.BaseActivity;
import com.ahmadrosid.olxlite.R;
import com.ahmadrosid.olxlite.core.home.HomeActivity;

public class WelcomeActivity extends BaseActivity {

    @Override protected int getResourceLayout() {
        return R.layout.activity_welcome;
    }

    @Override protected void onViewReady(Bundle savedInstanceState) {
        open(HomeActivity.class);
    }
}
