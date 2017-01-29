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
public class UserProfile implements Parcelable{

    public String name;
    public String email;
    public String photo;

    public UserProfile(String name, String email, String photo) {
        this.name = name;
        this.email = email;
        this.photo = photo;
    }

    protected UserProfile(Parcel in) {
        name = in.readString();
        email = in.readString();
        photo = in.readString();
    }

    public static final Creator<UserProfile> CREATOR = new Creator<UserProfile>() {
        @Override
        public UserProfile createFromParcel(Parcel in) {
            return new UserProfile(in);
        }

        @Override
        public UserProfile[] newArray(int size) {
            return new UserProfile[size];
        }
    };

    @Override public int describeContents() {
        return 0;
    }

    @Override public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(email);
        parcel.writeString(photo);
    }
}
