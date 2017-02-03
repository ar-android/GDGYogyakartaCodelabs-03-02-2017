package com.ahmadrosid.olxlite.core.profile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.ahmadrosid.lib.baseapp.core.BaseFragment;
import com.ahmadrosid.lib.baseapp.helper.ImageViewHelper;
import com.ahmadrosid.olxlite.R;
import com.ahmadrosid.olxlite.core.welcome.login.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by ocittwo on 1/29/17.
 *
 * @Author Ahmad Rosid
 * @Email ocittwo@gmail.com
 * @Github https://github.com/ar-android
 * @Web http://ahmadrosid.com
 */
public class ProfileFragment extends BaseFragment{

    @BindView(R.id.name)TextView name;
    @BindView(R.id.image_profile)ImageView image_profile;
    @BindView(R.id.email)TextView email;

    @Override protected int getResourceLayout() {
        return R.layout.fragment_profile;
    }

    @Override protected void onViewReady(@Nullable Bundle savedInstanceState) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        name.setText(user.getDisplayName());
        ImageViewHelper.createRounded(getContext(), user.getPhotoUrl().toString(), image_profile);
        email.setText(user.getEmail());
    }

    @OnClick(R.id.logout) void clickLogout(){
        FirebaseAuth.getInstance().signOut();
        open(LoginActivity.class);
        finish();
    }
}
