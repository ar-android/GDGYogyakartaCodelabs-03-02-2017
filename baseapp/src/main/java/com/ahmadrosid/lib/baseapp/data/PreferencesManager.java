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

package com.ahmadrosid.lib.baseapp.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.ahmadrosid.lib.baseapp.BaseApp;

/**
 * Created by ocittwo on 1/8/17.
 *
 * @Author Ahmad Rosid
 * @Email ocittwo@gmail.com
 * @Github https://github.com/ar-android
 * @Web http://ahmadrosid.com
 */
public class PreferencesManager {

    private static final String TAG = "PreferenceManager";

    private static SharedPreferences getPref() {
        Context context = BaseApp.getContext();
        return android.preference.PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static void saveString(String key, String value) {
        Log.d(TAG, "saveString: key :" + key + " value : " + value);
        getPref().edit()
                .putString(key, value)
                .apply();
    }

    public static String getString(String key) {
        Log.d(TAG, "getString: key :" + key + " value : " + getString(key));
        return getPref().getString(key, null);
    }


    public static void deleteString(String key) {
        getPref().edit()
                .remove(key)
                .apply();
    }

    public static void saveInt(String key, int value) {
        Log.d(TAG, "saveInt: " + value);
        getPref().edit()
                .putInt(key, value)
                .apply();
    }

    public static int getInt(String key) {
        return getPref().getInt(key, 0);
    }

    public static void saveBol(String key, boolean value) {
        getPref().edit()
                .putBoolean(key, value)
                .apply();
    }

    public static boolean getBol(String key) {
        return getPref().getBoolean(key, false);
    }

    public static void deleteBol(String key) {
        getPref().edit()
                .remove(key)
                .apply();
    }

    public static void clear() {
        getPref().edit().clear().apply();
    }
}
