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
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import butterknife.ButterKnife;

/**
 * Created by ocittwo on 1/8/17.
 *
 * @Author Ahmad Rosid
 * @Email ocittwo@gmail.com
 * @Github https://github.com/ar-android
 * @Web http://ahmadrosid.com
 */
public abstract class BaseFragment extends Fragment{
    private ProgressDialog progressDialog;

    @Nullable @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(getResourceLayout(), container, false);
        progressDialog = new ProgressDialog(getContext());
        return view;
    }

    @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }

    protected abstract int getResourceLayout();

    protected abstract void onViewReady(@Nullable Bundle savedInstanceState);

    public void replace(int containerId, Fragment fragment, boolean addToBackStack) {
        if (addToBackStack) {
            getFragmentManager().beginTransaction()
                    .replace(containerId, fragment, fragment.getClass().getSimpleName())
                    .addToBackStack(null)
                    .commit();
        } else {
            getFragmentManager().beginTransaction()
                    .replace(containerId, fragment, fragment.getClass().getSimpleName())
                    .commit();
        }
    }

    public void replace(int containerId, Fragment fragment, boolean addToBackStack, int transitionStyle) {
        if (addToBackStack) {
            getFragmentManager().beginTransaction()
                    .replace(containerId, fragment, fragment.getClass().getSimpleName())
                    .setTransitionStyle(transitionStyle)
                    .commit();
        } else {
            getFragmentManager().beginTransaction()
                    .replace(containerId, fragment, fragment.getClass().getSimpleName())
                    .setTransitionStyle(transitionStyle)
                    .commit();
        }
    }

    protected ActionBar getSupportActionBar() {
        return ((AppCompatActivity) getActivity()).getSupportActionBar();
    }

    protected void setSupportActionBar(Toolbar toolbar) {
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
    }
    protected void showProgress() {
        if (progressDialog != null)
            if (!progressDialog.isShowing()) {
                progressDialog.setMessage("Loading...");
                progressDialog.show();
            }
    }

    protected void hideProgress() {
        if (progressDialog.isShowing())
            progressDialog.dismiss();
    }

    public void setActiveFragment(Fragment fragment, int containerRes) {
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(containerRes, fragment)
                .addToBackStack(null)
                .commit();
    }

    public void alert(String message){
        new AlertDialog.Builder(getActivity())
                .setTitle("Message")
                .setMessage(message)
                .setPositiveButton("ok", (dialog, which) -> {
                    dialog.dismiss();
                }).show();
    }

    public void snack(String snack){
        Snackbar.make(getView(), snack, Snackbar.LENGTH_SHORT).show();
    }

    public void open(Class clazz) {
        startActivity(new Intent(getActivity(), clazz));
    }

    public void finish() {
        getActivity().finish();
    }

    public View inflate(ViewGroup container, int layoutRes) {
        return LayoutInflater.from(container.getContext()).inflate(layoutRes, container, false);
    }

    public boolean isEmpty(String str) {
        return TextUtils.isEmpty(str);
    }

    public String getString(EditText editText) {
        return editText.getText().toString();
    }

    public void showSnack(String message) {
        Snackbar.make(getView(), message, Snackbar.LENGTH_SHORT).show();
    }

    public void setTitle(String title) {
        ((AppCompatActivity) getActivity()).setTitle(title);
    }
}
