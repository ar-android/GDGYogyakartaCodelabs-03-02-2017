package com.ahmadrosid.olxlite.data;

import com.ahmadrosid.lib.baseapp.data.PreferencesManager;

/**
 * Created by ocittwo on 1/29/17.
 *
 * @Author Ahmad Rosid
 * @Email ocittwo@gmail.com
 * @Github https://github.com/ar-android
 * @Web http://ahmadrosid.com
 */
public class UserProfile{

    private static UserProfile instance;

    public String name;
    public String email;
    public String photo;
    public String accessToken;
    public boolean loginWith;

    public static UserProfile getInstance() {
        if (instance == null)
            instance = new UserProfile();
        return instance;
    }

    public static final void login(String data){
        PreferencesManager.saveString(OlxLiteConstants.LOGIN_CACHE, data);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public boolean isLoginWith() {
        return loginWith;
    }

    public void setLoginWith(boolean loginWith) {
        this.loginWith = loginWith;
    }
}
