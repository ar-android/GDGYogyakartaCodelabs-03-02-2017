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
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import id.zelory.compressor.Compressor;
import id.zelory.compressor.FileUtil;
/**
 * Created by ocittwo on 1/8/17.
 *
 * @Author Ahmad Rosid
 * @Email ocittwo@gmail.com
 * @Github https://github.com/ar-android
 * @Web http://ahmadrosid.com
 */
public class PickImageHelper {

    private final Context context;
    private String mCurrentPhotoPath;

    public PickImageHelper(Context context) {
        this.context = context;
    }

    public void setImageFromFile(ImageView img, File file){
        ImageViewHelper.createRounded(img.getContext(), file, img);
    }

    public File createImageFile(int requestCode) {
        String timeStamp = new SimpleDateFormat("yyyyMMdd").format(new Date());
        String imageFileName = "IMG_" + timeStamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File image = null;
        try {
            image = File.createTempFile(
                    imageFileName,
                    ".jpeg",
                    storageDir
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert image != null;
        return image;
    }

    public void setImgFromGallery(Intent data, ImageView img, PickImageHelperImpl result) {
        Uri selectedImageUriLeft = data.getData();
        try {
            File file = FileUtil.from(context, selectedImageUriLeft);
            File img_compress = compressImage(context, file);
            setImageFromPath(img, "file:" + img_compress.getPath());
            result.resultImagePath("file:" + img_compress.getPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setImgFromCamera(ImageView imageView, PickImageHelperImpl result) {
        setImageFromPath(imageView, mCurrentPhotoPath);
        result.resultImagePath(mCurrentPhotoPath);
    }

    public void setImageFromPath(ImageView imageView, String path) {
        if (context != null) {
            try {
                Bitmap mImageBitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), Uri.parse(path));
                imageView.setImageBitmap(mImageBitmap);
                styleImage(imageView);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public interface PickImageHelperImpl {
        void resultImagePath(String path);
    }

    private void styleImage(ImageView imageView) {
        imageView.setPadding(0, 0, 0, 0);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
    }

    /**
     * Compress bitmap image from file
     *
     * @param context Context
     * @param actualImage File
     * @return File
     */
    public File compressImage(Context context, File actualImage) {
        return new Compressor.Builder(context)
                .setMaxWidth(640)
                .setMaxHeight(480)
                .setQuality(75)
                .setCompressFormat(Bitmap.CompressFormat.JPEG)
                .setDestinationDirectoryPath(Environment.getExternalStoragePublicDirectory(
                        Environment.DIRECTORY_PICTURES).getAbsolutePath())
                .build()
                .compressToFile(actualImage);
    }
}
