package com.ahmadrosid.olxlite.core.welcome.login;

import android.content.Intent;
import android.util.Log;

import com.ahmadrosid.lib.baseapp.core.BaseActivity;
import com.ahmadrosid.lib.baseapp.core.BasePresenter;
import com.ahmadrosid.olxlite.R;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

/**
 * Created by ocittwo on 2/2/17.
 *
 * @Author Ahmad Rosid
 * @Email ocittwo@gmail.com
 * @Github https://github.com/ar-android
 * @Web http://ahmadrosid.com
 */

public class LoginPresenter extends BasePresenter<LoginView> {
    private static final String TAG = "LoginPresenter";

    private BaseActivity activity;
    private GoogleApiClient mGoogleApiClient;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private static final int RC_SIGN_IN = 9001;

    public LoginPresenter(LoginView mvpView, BaseActivity activity) {
        super(mvpView);
        this.activity = activity;
    }

    public void loadConfig() {
        
    }

    public void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        
    }

    private void showProgressDialog() {
        mvpView.startLoading();
    }

    private void hideProgressDialog() {
        mvpView.stopLoading();
    }

    public void signIn() {
        
    }

    @Override public void detachView() {
        super.detachView();

        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
}
