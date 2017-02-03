package com.ahmadrosid.olxlite.core.iklan_saya.newiklan;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.ahmadrosid.olxlite.R;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ocittwo on 2/3/17.
 *
 * @Author Ahmad Rosid
 * @Email ocittwo@gmail.com
 * @Github https://github.com/ar-android
 * @Web http://ahmadrosid.com
 */
public class ItemHolderImage extends RecyclerView.ViewHolder {

    @BindView(R.id.image) ImageView images;

    public ItemHolderImage(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void onBindView(String image) {
        Glide.with(itemView.getContext()).load(image).centerCrop().into(images);
    }
}
