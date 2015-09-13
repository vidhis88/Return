package com.returnapp;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;

import com.google.android.gms.location.ActivityRecognitionResult;
import com.google.android.gms.location.DetectedActivity;


public class ActivityRecognitionService extends IntentService {

    public static final String TAG = "ActivityRecognitionService";

    public ActivityRecognitionService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (ActivityRecognitionResult.hasResult(intent)) {
            ActivityRecognitionResult result = ActivityRecognitionResult.extractResult(intent);
            DetectedActivity detectedActivity = result.getMostProbableActivity();

            int confidence = detectedActivity.getConfidence();
            int type = detectedActivity.getType();
            String name = getActivityName(type);

            Intent notifyIntent = new Intent(this, MainActivity.class);
            PendingIntent pIntent = PendingIntent.getActivity(this, (int) System.currentTimeMillis(), notifyIntent, 0);

            Notification notification = new Notification.Builder(this)
                    .setContentTitle("ReturnApp")
                    .setContentText("Detected activity " + name + " with confidence " + confidence)
                    .setSmallIcon(R.drawable.ic_stat_action_star_rate)
                    .setContentIntent(pIntent)
                    .setGroup(TAG)
                    .build();

            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            // hide the notification after its selected
            notification.flags |= Notification.FLAG_AUTO_CANCEL;

            notificationManager.notify(type, notification);
        }
    }

    private String getActivityName(int type) {
        switch (type) {
            case DetectedActivity.IN_VEHICLE:
                return "In Vehicle";
            case DetectedActivity.ON_BICYCLE:
                return "On Bicycle";
            case DetectedActivity.ON_FOOT:
                return "On Foot";
            case DetectedActivity.WALKING:
                return "Walking";
            case DetectedActivity.STILL:
                return "Still";
            case DetectedActivity.TILTING:
                return "Tilting";
            case DetectedActivity.RUNNING:
                return "Running";
            case DetectedActivity.UNKNOWN:
                return "Unknown";
        }

        return "Unable to detect type of activity";
    }
}
