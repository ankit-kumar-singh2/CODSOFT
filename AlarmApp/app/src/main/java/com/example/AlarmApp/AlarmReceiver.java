package com.example.AlarmApp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;


public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        startAlarmService(context,intent);
    }

    private void startAlarmService(Context context,Intent intent){
        Intent intentservice = new Intent(context,AlarmService.class);
        intentservice.putExtra("Title",intent.getStringExtra("Title"));
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            context.startForegroundService(intentservice);
        }else{
            context.startService(intentservice);
        }
    }
}
