package com.ahmadrosid.olxlite;

import android.os.Bundle;
import android.util.Log;

import com.ahmadrosid.lib.baseapp.core.BaseActivity;
import com.ahmadrosid.lib.baseapp.helper.NetworkHelper;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ocittwo on 2/3/17.
 *
 * @Author Ahmad Rosid
 * @Email ocittwo@gmail.com
 * @Github https://github.com/ar-android
 * @Web http://ahmadrosid.com
 */
public class TestActivity extends BaseActivity {
    private static final String TAG = "TestActivity";

    private DatabaseReference mIklanReference;
    @Override protected int getResourceLayout() {
        return R.layout.blank_activity;
    }

    @Override protected void onViewReady(Bundle savedInstanceState) {
    }
}
