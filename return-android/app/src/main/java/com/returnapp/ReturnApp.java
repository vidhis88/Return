package com.returnapp;

import android.app.Application;

import com.parse.Parse;

/**
 * Created by vidhi on 6/29/15.
 */
public class ReturnApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // Enable Local Datastore.
//        Parse.enableLocalDatastore(this);

        Parse.initialize(this, "fMlfwyVdJ9SIYFZzvHwSSDTnLRzYIYjqNn6cHsxz", "9YDcrUiePSC3MDar8cCiU6SB2jbQNYfQZR6amlqV");
    }
}
