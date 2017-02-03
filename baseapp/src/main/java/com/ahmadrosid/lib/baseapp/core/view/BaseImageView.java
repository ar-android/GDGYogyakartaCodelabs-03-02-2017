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

package com.ahmadrosid.lib.baseapp.core.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.ahmadrosid.lib.baseapp.helper.ImageViewHelper;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

/**
 * Created by ocittwo on 1/28/17.
 *
 * @Author Ahmad Rosid
 * @Email ocittwo@gmail.com
 * @Github https://github.com/ar-android
 * @Web http://ahmadrosid.com
 */
public class BaseImageView extends ImageView{
    private String imageUrl;

    public BaseImageView(Context context) {
        super(context);
    }

    public BaseImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setImageUrl(String url) {
        imageUrl = url;
        Glide.with(getContext())
                .load(url)
                .into(this);
    }

    public void setImageUrl(String url, int errorResourceId) {
        imageUrl = url;
        Glide.with(getContext())
                .load(url)
                .error(errorResourceId)
                .into(this);
    }

    public void setImageUrl(String url, int placeHolderResourceId, int errorResourceId) {
        imageUrl = url;
        Glide.with(getContext())
                .load(url)
                .placeholder(placeHolderResourceId)
                .error(errorResourceId)
                .into(this);
    }

    public void setImageUrl(String url, int placeHolderDrawable, Drawable errorDrawable) {
        imageUrl = url;
        Glide.with(getContext())
                .load(url)
                .placeholder(placeHolderDrawable)
                .error(errorDrawable)
                .into(this);
    }

    public void setImageUrl(String url, final ProgressBar progressBar) {
        imageUrl = url;
        progressBar.setVisibility(VISIBLE);
        Glide.with(getContext())
                .load(url)
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        progressBar.setVisibility(GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        progressBar.setVisibility(GONE);
                        return false;
                    }
                })
                .into(this);
    }

    public void setImageUrl(String url, final ProgressBar progressBar, int errorResourceId) {
        imageUrl = url;
        progressBar.setVisibility(VISIBLE);
        Glide.with(getContext())
                .load(url)
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        progressBar.setVisibility(GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        progressBar.setVisibility(GONE);
                        return false;
                    }
                })
                .error(errorResourceId)
                .into(this);
    }

    public void setImageUrl(String url, final ProgressBar progressBar, Drawable errorDrawable) {
        imageUrl = url;
        progressBar.setVisibility(VISIBLE);
        Glide.with(getContext())
                .load(url)
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        progressBar.setVisibility(GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        progressBar.setVisibility(GONE);
                        return false;
                    }
                })
                .error(errorDrawable)
                .into(this);
    }

    public void setImageUrlCircle(String imageUrl){
        ImageViewHelper.createRounded(getContext(), imageUrl, this);
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
