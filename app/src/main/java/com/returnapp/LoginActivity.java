package com.returnapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

/**
 * Created by vidhi on 6/29/15.
 */
public class LoginActivity extends AppCompatActivity {
    private CallbackManager callbackManager;

	private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();

        setContentView(R.layout.login_activity);

        LoginButton loginBtn = (LoginButton) findViewById(R.id.login_button);
        loginBtn.setReadPermissions("public_profile", "email");
        loginBtn.registerCallback(callbackManager, new FbLoginCallback());

	    context = this;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

	public class FbLoginCallback implements FacebookCallback<LoginResult> {
		@Override
		public void onSuccess(LoginResult loginResult) {
			final AccessToken token = loginResult.getAccessToken();
			Log.v("Vidhi", "login successful: access token is " + token);
			Log.w("Vidhi", "permissions are " + loginResult.getRecentlyGrantedPermissions());

//	            GraphRequest request = GraphRequest.newMeRequest(
//			            token,
//			            new GraphRequest.GraphJSONObjectCallback() {
//				            @Override
//				            public void onCompleted(JSONObject object, GraphResponse response) {
//					            // Application code
//					            if (response.getError() == null) {
//						            try {
//							            ParseUser user = new ParseUser();
//							            user.setUsername("u_" + object.getString("id"));
//							            user.setPassword(object.getString("id"));
//							            user.setEmail(object.getString("email"));
//
//							            user.put("name", object.getString("name"));
//							            user.put("fb_token", token.getToken());
//
//							            user.signUpInBackground(new SignUpCallback() {
//								            public void done(ParseException e) {
//									            if (e == null) {
//										            // Hooray! Let them use the app now.
//									            } else {
//										            // Sign up didn't succeed. Look at the ParseException
//										            // to figure out what went wrong
//									            }
//								            }
//							            });
//						            } catch (JSONException e) {
//							            Log.e("Return", "Login: Error parsing JSON");
//						            }
//					            }
//				            }
//			            });
//	            Bundle parameters = new Bundle();
//	            parameters.putString("fields", "id,name,email");
//	            request.setParameters(parameters);
//	            request.executeAsync();

			Intent intent = new Intent(context, MainActivity.class);
			startActivity(intent);
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
	}
}
