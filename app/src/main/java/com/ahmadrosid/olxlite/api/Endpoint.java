package com.ahmadrosid.olxlite.api;

import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by ocittwo on 2/2/17.
 *
 * @Author Ahmad Rosid
 * @Email ocittwo@gmail.com
 * @Github https://github.com/ar-android
 * @Web http://ahmadrosid.com
 */
public class Endpoint {
    private static String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
    public static final String MAIN_URL = "https://olxlite-422b0.firebaseio.com/";
    private static final String AUTH = "?auth=04MTOPRRZwGhomCRQ8arKG9MxFzgL9F9eTfjFHbb";
    public static final String IKLAN = MAIN_URL + "iklan.json" + AUTH;
    public static final String IKLAN_SAYA = MAIN_URL + "users/iklan/" + uid + ".json" + AUTH;
}
