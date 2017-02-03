/*
 * Copyright (c) 2017 Ahmad Rosid.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.ahmadrosid.lib.baseapp.core;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import butterknife.ButterKnife;

/**
 * Created by ocittwo on 1/8/17.
 *
 * @Author Ahmad Rosid
 * @Email ocittwo@gmail.com
 * @Github https://github.com/ar-android
 * @Web http://ahmadrosid.com
 */
public abstract class BaseActivity extends AppCompatActivity {

    private static final String TAG = "BaseActivity";

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getResourceLayout());
        ButterKnife.bind(this);
        onViewReady(savedInstanceState);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
    }

    protected abstract int getResourceLayout();

    protected abstract void onViewReady(Bundle savedInstanceState);

    public void setToolbar(int toolbarResId) {
        try {
            Toolbar toolbar = (Toolbar) findViewById(toolbarResId);
            setSupportActionBar(toolbar);
        } catch (Exception e) {
            Log.e(TAG, "setToolbar: ", e);
        }
    }

    protected void showLoading() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Loading...");
            progressDialog.show();
        } else {
            progressDialog.show();
        }
    }

    protected void hideLoading() {
        progressDialog.dismiss();
    }

    public void setActiveFragment(BaseFragment fragment, int containerRes) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(containerRes, fragment)
                .commit();
    }

    public void setActiveFragmentBackstack(BaseFragment fragment, int containerRes) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(containerRes, fragment)
                .addToBackStack(null)
                .commit();
    }

    public void alert(String message) {
        new AlertDialog.Builder(this)
                .setTitle("Message")
                .setMessage(message)
                .setPositiveButton("ok", (dialog, which) -> {
                    dialog.dismiss();
                }).show();
    }

    public void open(Class clazz) {
        startActivity(new Intent(this, clazz));
    }

    public void setOnBackToolbar() {
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}
