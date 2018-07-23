package com.example.dell.jaimaaapp.messaging;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.example.dell.jaimaaapp.R;
import com.example.dell.jaimaaapp.activity.MainActivity;
import com.example.dell.jaimaaapp.activity.MeetingsActivity;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService{

    private static final String TAG = "MyFirebaseMsgService";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.d(TAG,"From : " + remoteMessage.getFrom());
        if(remoteMessage.getData().size() > 0){
            Log.d(TAG,"Message Data : " + remoteMessage.getData());
        }

        if(remoteMessage.getNotification() != null){
            Log.d(TAG,"Message body : " + remoteMessage.getNotification().getBody());
            sendNotification(remoteMessage.getNotification().getBody());
        }

    }

    /**
     * Display the notification
     * @param body
     */

    private void sendNotification(String body){

        Intent intent = new Intent(this, MeetingsActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_ONE_SHOT);
        Uri notificationSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this,getString(R.string.default_notification_channel_id));

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            notificationBuilder.setSmallIcon(R.mipmap.scholorship_icon);
            notificationBuilder.setColor(getResources().getColor(R.color.colorPrimary))
                    .setContentTitle("Meeting Schedule Updated")
                    .setContentText(body)
                    .setAutoCancel(true)
                    .setSound(notificationSound)
                    .setContentIntent(pendingIntent);
        } else {
            notificationBuilder.setSmallIcon(R.drawable.logo1)
                    .setContentTitle("Meeting Schedule Updated")
                    .setContentText(body)
                    .setAutoCancel(true)
                    .setSound(notificationSound)
                    .setContentIntent(pendingIntent);
        }

        NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0,notificationBuilder.build());


    }
}
