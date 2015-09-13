package com.returnapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

/**
 * Created by vidhi on 6/29/15.
 */
public class LoginActivity extends AppCompatActivity {
	private CallbackManager callbackManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_activity);
		setTitle("Get started");

		callbackManager = CallbackManager.Factory.create();

		LoginButton loginBtn = (LoginButton) findViewById(R.id.login_button);
		loginBtn.setReadPermissions("public_profile", "email");
		loginBtn.registerCallback(callbackManager, new FbLoginCallback(this));
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		callbackManager.onActivityResult(requestCode, resultCode, data);
	}

	private class FbLoginCallback implements FacebookCallback<LoginResult> {
		private Activity activity;

		public FbLoginCallback(Activity activity) {
			this.activity = activity;
		}

		@Override
		public void onSuccess(LoginResult loginResult) {
			AccessToken token = loginResult.getAccessToken();
			RouterActivity.updateAccessToken(activity, token);
			Log.v("Vidhi", "login successful: access token is " + token);
			Log.w("Vidhi", "permissions are " + loginResult.getRecentlyGrantedPermissions());

			Intent intent = new Intent(activity, TabsActivity.class);
			activity.startActivity(intent);
			activity.finish();
		}

		@Override
		public void onCancel() {
			Log.d("Vidhi", "login cancelled");
			// TODO: take them to a screen explaining why (to build trust) we need all that information before moving forward
		}

		@Override
		public void onError(FacebookException e) {
			Log.w("Vidhi", "login error");
			// TODO: request them to retry
		}
	}
}
