package com.example.wbollock.notifications;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationCompatExtras;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    NotificationCompat.Builder notification;
    private static final int uniqueID = 45612;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notification = new NotificationCompat.Builder(this);
        NotificationCompat.Builder mBuilder;

        notification.setAutoCancel(true); // this method gets called whenever mainactivity is opened
        // makes sense because when user opens app you want to cancel the notification

    }

    public void buckysButtonClicked(View view){


        // The id of the channel.
        String id = "my_channel_01";

    // The user-visible name of the channel.
        CharSequence name = getString(R.string.channel_name);

    // The user-visible description of the channel.
        String description = getString(R.string.channel_description);

        int importance = NotificationManager.IMPORTANCE_LOW;

        NotificationChannel mChannel = new NotificationChannel(id, name,importance);

    // Configure the notification channel.
        mChannel.setDescription(description);

        mChannel.enableLights(true);
    // Sets the notification light color for notifications posted to this
    // channel, if the device supports this feature.
        mChannel.setLightColor(Color.RED);

        mChannel.enableVibration(true);
        mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});



        // building the notification
        notification.setSmallIcon(R.mipmap.ic_launcher);// typically the logo of your app
        notification.setTicker("This is the ticker");
        notification.setWhen(System.currentTimeMillis()); // passes in current time
        notification.setContentTitle("Here is the title");
        notification.setContentText("I am the main body ContentText");
        notification.setOngoing(true);
        notification.setChannelId(id);

        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        // this line of code gives phone access to intents in our app

        notification.setContentIntent(pendingIntent);

        //issuing notification
        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        nm.notify(uniqueID, notification.build()); // object that sends out notifications
        nm.createNotificationChannel(mChannel);

    }
}
