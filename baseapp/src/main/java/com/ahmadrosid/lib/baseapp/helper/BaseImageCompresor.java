package com.ahmadrosid.lib.baseapp.helper;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;

import java.io.File;

import id.zelory.compressor.Compressor;

/**
 * Created by ocittwo on 2/3/17.
 *
 * @Author Ahmad Rosid
 * @Email ocittwo@gmail.com
 * @Github https://github.com/ar-android
 * @Web http://ahmadrosid.com
 */

public class BaseImageCompresor {

    private static BaseImageCompresor instance;

    public static BaseImageCompresor pluck() {
        if (instance == null)
            instance = new BaseImageCompresor();
        return instance;
    }

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
