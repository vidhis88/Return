package com.returnapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.FacebookSdk;

/**
 * Created by vidhi on 9/12/15.
 */
public class RouterActivity extends AppCompatActivity {

	private AccessTokenTracker tokenTracker;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		FacebookSdk.sdkInitialize(getApplicationContext());

		final Context context = this;
		tokenTracker = new AccessTokenTracker() {
			@Override
			protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken newAccessToken) {
				if (newAccessToken != null) {
					updateAccessToken(context, newAccessToken);
				}
			}
		};

		String fbAccessToken = PreferenceManager.getDefaultSharedPreferences(this).getString(SharedPrefKeys.FB_ACCESS_TOKEN, null);
		Class classToLaunch = TextUtils.isEmpty(fbAccessToken) ? LoginActivity.class : TabsActivity.class;
		Intent intent = new Intent(this, classToLaunch);
		startActivity(intent);
		finish();
	}

	public static void updateAccessToken(Context context, AccessToken token) {
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
		prefs.edit().putString(SharedPrefKeys.FB_ACCESS_TOKEN, token.getToken()).apply();
	}

	@Override
	protected void onDestroy() {
		tokenTracker.stopTracking();
		super.onDestroy();
	}
}
