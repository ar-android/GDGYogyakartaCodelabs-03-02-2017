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
public class CommonIklan implements Parcelable{

    public String title;
    public String image;
    public int price;

    public CommonIklan(String title, String image, int price) {
        this.title = title;
        this.image = image;
        this.price = price;
    }

    protected CommonIklan(Parcel in) {
        title = in.readString();
        image = in.readString();
        price = in.readInt();
    }

    public static final Creator<CommonIklan> CREATOR = new Creator<CommonIklan>() {
        @Override
        public CommonIklan createFromParcel(Parcel in) {
            return new CommonIklan(in);
        }

        @Override
        public CommonIklan[] newArray(int size) {
            return new CommonIklan[size];
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
