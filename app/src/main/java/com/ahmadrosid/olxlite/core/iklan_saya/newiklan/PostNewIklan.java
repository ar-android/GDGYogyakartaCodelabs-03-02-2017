package com.ahmadrosid.olxlite.core.iklan_saya.newiklan;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ocittwo on 2/3/17.
 *
 * @Author Ahmad Rosid
 * @Email ocittwo@gmail.com
 * @Github https://github.com/ar-android
 * @Web http://ahmadrosid.com
 */
@IgnoreExtraProperties
public class PostNewIklan {

    public String uid;
    public String judul;
    public String harga;
    public String lokasi;
    public String description;
    public String url_image;
    public int status;

    public PostNewIklan() {
    }

    public PostNewIklan(String uid, String judul,String harga, String lokasi, String description, String url_image) {
        this.uid = uid;
        this.judul = judul;
        this.harga = harga;
        this.lokasi = lokasi;
        this.description = description;
        this.url_image = url_image;
    }

    public String getUid() {
        return uid;
    }

    public String getJudul() {
        return judul;
    }

    public String getHarga() {
        return harga;
    }

    public String getLokasi() {
        return lokasi;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl_image() {
        return url_image;
    }

    public int getStatus() {
        return status;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("uid", uid);
        result.put("judul", judul);
        result.put("harga", harga);
        result.put("lokasi", lokasi);
        result.put("description", description);
        result.put("url_image", url_image);
        result.put("status", 0);

        return result;
    }
}
