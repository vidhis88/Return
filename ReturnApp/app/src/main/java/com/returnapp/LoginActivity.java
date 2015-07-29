package com.returnapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.parse.ParseObject;

/**
 * Created by vidhi on 6/29/15.
 */
public class LoginActivity extends AppCompatActivity {
    private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();

        setContentView(R.layout.login_activity);

        LoginButton loginBtn = (LoginButton) findViewById(R.id.login_button);
        loginBtn.setReadPermissions("public_profile", "email");
        loginBtn.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                AccessToken token = loginResult.getAccessToken();
                Log.v("Vidhi", "login successful: access token is " + token);
                Log.w("Vidhi", "permissions are " + loginResult.getRecentlyGrantedPermissions());

	            if (!TextUtils.isEmpty(token.getToken())) {

		            ParseObject userObj = new ParseObject("User");
		            userObj.put("user_id", token.getUserId());
		            userObj.put("fb_token", token.getToken());
		            userObj.saveInBackground();
	            }
                // TODO: take them to landing page
            }

            @Override
            public void onCancel() {
                Log.d("Vidhi", "login cancelled");
                // TODO: take them to a screen explaining why we cannot move forward without it
            }

            @Override
            public void onError(FacebookException e) {
                Log.w("Vidhi", "login error");
                // TODO: request them to retry
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
