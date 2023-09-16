package com.example.AlarmApp;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.IBinder;
import android.os.Vibrator;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

public class AlarmService extends Service {
    private MediaPlayer mediaPlayer;
    private Vibrator vibrator;
    int NOTIFICATION_ID = 1;

    private String CHANNEL_ID = "Alarm_Service_channel";

    @Override
    public void onCreate() {
        super.onCreate();
        mediaPlayer = mediaPlayer.create(this, R.raw.alarm);

        mediaPlayer.setLooping(true);

        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
    }

    @Override
    public int onStartCommand(Intent intent,int flags,int startid){
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            Intent notificationIntent = new Intent(this, AlarmRinging.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_IMMUTABLE);


            NotificationChannel chan = new NotificationChannel(CHANNEL_ID,"CHANNERL", NotificationManager.IMPORTANCE_LOW);
            chan.setLightColor(Color.BLUE);
            chan.setLockscreenVisibility(Notification.VISIBILITY_SECRET);



            String alarmtitle = String.format("%s Alarm", intent.getStringExtra("Title"));

            NotificationManager manager = (NotificationManager) getSystemService(NotificationManager.class);//(Context.NOTIFICATION_SERVICE);
            assert  manager != null;
            manager.createNotificationChannel(chan);

            NotificationCompat.Builder notificationbuilder = new NotificationCompat.Builder(this,CHANNEL_ID);
            Notification notification = notificationbuilder.setOngoing(true)
                    .setContentTitle(alarmtitle)
                    .setContentText("Ring Ring .. Ring Ring")
                    .setSmallIcon(R.drawable.baseline_alarm_24)
                   .setContentTitle(alarmtitle)
                    .setContentIntent(pendingIntent)
                   .build();

            mediaPlayer.start();
            long pattern[] = {0, 100, 1000};
            vibrator.vibrate(pattern, 0);
            startForeground(NOTIFICATION_ID,notification);

        }

        return START_STICKY;

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        mediaPlayer.stop();
        vibrator.cancel();
    }
}
