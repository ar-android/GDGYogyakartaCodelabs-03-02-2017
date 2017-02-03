package com.ahmadrosid.olxlite.core.home;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ocittwo on 2/2/17.
 *
 * @Author Ahmad Rosid
 * @Email ocittwo@gmail.com
 * @Github https://github.com/ar-android
 * @Web http://ahmadrosid.com
 */

public class IklanModel implements Parcelable{

    private String description;
    private String harga;
    private String judul;
    private int status;
    private String uid;
    private String url_image;

    public IklanModel() {
    }

    protected IklanModel(Parcel in) {
        description = in.readString();
        harga = in.readString();
        judul = in.readString();
        status = in.readInt();
        uid = in.readString();
        url_image = in.readString();
    }

    public static final Creator<IklanModel> CREATOR = new Creator<IklanModel>() {
        @Override
        public IklanModel createFromParcel(Parcel in) {
            return new IklanModel(in);
        }

        @Override
        public IklanModel[] newArray(int size) {
            return new IklanModel[size];
        }
    };

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUrl_image() {
        return url_image;
    }

    public void setUrl_image(String url_image) {
        this.url_image = url_image;
    }

    @Override public int describeContents() {
        return 0;
    }

    @Override public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(description);
        parcel.writeString(harga);
        parcel.writeString(judul);
        parcel.writeInt(status);
        parcel.writeString(uid);
        parcel.writeString(url_image);
    }
}
