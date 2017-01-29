package com.ahmadrosid.olxlite.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ocittwo on 1/29/17.
 *
 * @Author Ahmad Rosid
 * @Email ocittwo@gmail.com
 * @Github https://github.com/ar-android
 * @Web http://ahmadrosid.com
 */
public class UserIklan implements Parcelable{

    public String title;
    public String image;
    public int price;

    public UserIklan(String title, String image, int price) {
        this.title = title;
        this.image = image;
        this.price = price;
    }

    protected UserIklan(Parcel in) {
        title = in.readString();
        image = in.readString();
        price = in.readInt();
    }

    public static final Creator<UserIklan> CREATOR = new Creator<UserIklan>() {
        @Override
        public UserIklan createFromParcel(Parcel in) {
            return new UserIklan(in);
        }

        @Override
        public UserIklan[] newArray(int size) {
            return new UserIklan[size];
        }
    };

    @Override public int describeContents() {
        return 0;
    }

    @Override public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(image);
        parcel.writeInt(price);
    }
}
