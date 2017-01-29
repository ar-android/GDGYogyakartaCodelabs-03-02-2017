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

package com.ahmadrosid.lib.baseapp.helper;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

import java.io.File;

/**
 * Created by ocittwo on 1/8/17.
 *
 * @Author Ahmad Rosid
 * @Email ocittwo@gmail.com
 * @Github https://github.com/ar-android
 * @Web http://ahmadrosid.com
 */

public class ImageViewHelper {
    public static void createRounded(Context context, int imgRes, ImageView imageView) {
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), imgRes);
        RoundedBitmapDrawable rounded = RoundedBitmapDrawableFactory.create(context.getResources(), bitmap);
        rounded.setCornerRadius(bitmap.getWidth());
        imageView.setImageDrawable(rounded);
    }

    public static void createRounded(Context context, String url, ImageView imageView) {
        try {
            Glide.with(context).load(url).asBitmap().centerCrop().into(new BitmapImageViewTarget(imageView) {
                @Override
                protected void setResource(Bitmap resource) {
                    RoundedBitmapDrawable rounded =
                            RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                    rounded.setCircular(true);
                    imageView.setImageDrawable(rounded);
                    Log.d(TAG, "setResource: " + url);
                }
            });
        } catch (Exception e) {
            Log.e(TAG, "createRounded: ", e);
        }
    }

    public static void createRounded(Context context, File file, ImageView imageView) {
        Glide.with(context).load(file).asBitmap().centerCrop().into(new BitmapImageViewTarget(imageView) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable rounded =
                        RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                rounded.setCircular(true);
                imageView.setImageDrawable(rounded);
                Log.d(TAG, "setResource: " + file.getAbsoluteFile());
            }
        });
    }

    private static final String TAG = "ImageViewHelper";
}
