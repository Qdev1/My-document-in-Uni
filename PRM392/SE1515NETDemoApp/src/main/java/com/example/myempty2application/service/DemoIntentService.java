package com.example.myempty2application.service;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.example.myempty2application.R;

public class DemoIntentService extends IntentService {

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     */
    public DemoIntentService() {
        super("DemoIntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        String serviceName = intent.getStringExtra("DemoIntentService");
        showNotification(serviceName);
    }
    private void showNotification(String serviceName) {
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentTitle("Service Notification")
                .setContentText("Intent Service "  + serviceName + " is started")
                .setSmallIcon(R.drawable.ic_errror)
                .setChannelId("001");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel =
                    new NotificationChannel("001", "Intent Service", NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(notificationChannel);
        }
        notificationManager.notify(1, builder.build());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Intent Service is destroyed", Toast.LENGTH_SHORT).show();
    }
}
