package com.example.se1603_ksapplication.service;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import com.example.se1603_ksapplication.NotificationHandleActivity;
import com.example.se1603_ksapplication.R;

public class MyDemoService extends Service {
    private MyBinder myBinder = new MyBinder();
    private class MyBinder extends Binder {
        public MyDemoService getMyDemoService() {
            return MyDemoService.this;
        }
    }
    public MyDemoService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return myBinder;
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        sendNotification();
        return START_NOT_STICKY;
    }
    private final static String CHANNEL_ID = "001";
    private void sendNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_action_setting)
                .setContentTitle("Demo Notification from service")
                .setContentText("This is a message sent from service .......");
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel =
                    new NotificationChannel(CHANNEL_ID, "Demo Service Notification Channel", NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(channel);
        } else {
            builder.setPriority(NotificationCompat.PRIORITY_HIGH);
        }
        //Add activity to handle action when user tap on notification
        Intent intent = new Intent(this, NotificationHandleActivity.class);
        PendingIntent pendingIntent= PendingIntent.getActivity(this, 0, intent, 0);
        builder.setContentIntent(pendingIntent);
        //Send notification
        notificationManager.notify(2, builder.build());
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("MyDemoService", "Service is destroying");
    }
}