package com.ahmadrosid.olxlite.core.welcome;

import android.os.Bundle;
import android.os.Handler;

import com.ahmadrosid.lib.baseapp.core.BaseActivity;
import com.ahmadrosid.olxlite.R;
import com.ahmadrosid.olxlite.core.home.HomeActivity;
import com.ahmadrosid.olxlite.core.welcome.login.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class WelcomeActivity extends BaseActivity {

    @Override protected int getResourceLayout() {
        return R.layout.activity_welcome;
    }

    @Override protected void onViewReady(Bundle savedInstanceState) {
        new Handler().postDelayed(this::checkAuth, 2500);
    }

    private void checkAuth() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            open(HomeActivity.class);
        } else {
            open(LoginActivity.class);
        }
    }

    @Override protected void onStop() {
        super.onStop();
        finish();
    }

}
