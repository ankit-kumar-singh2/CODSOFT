package com.example.AlarmApp;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Calendar;
import java.util.Date;

@Entity(tableName = "alarm_table")
public class Alarm {

    @PrimaryKey(autoGenerate = true)
    private int alarm_id;
    private int hour;
    private int minute;
    private String title;
    private boolean started;

    public void setStarted(boolean started) {
        this.started = started;
    }

    public boolean isStarted() {
        return started;
    }

    public void setAlarm_id(int alarm_id) {
        this.alarm_id = alarm_id;
    }

    public int getAlarm_id() {
        return alarm_id;
    }

    public int getHour() {
        return hour;
    }


    public void setHour(int hour) {
        this.hour = hour;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getMinute() {
        return minute;
    }

    public String getTitle() {
        return title;
    }


    public void schedule(Context context){
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(context.ALARM_SERVICE);
        Intent intent = new Intent(context,AlarmReceiver.class);
        intent.putExtra("Title",title);

        PendingIntent pendingintent = PendingIntent.getBroadcast(context,alarm_id,intent,PendingIntent.FLAG_IMMUTABLE);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY,hour,hour);
        calendar.set(Calendar.MINUTE,minute);
        calendar.set(Calendar.MILLISECOND,0);
        calendar.set(Calendar.SECOND,0);

        Date customdate = calendar.getTime();

        alarmManager.set(AlarmManager.RTC_WAKEUP,customdate.getTime(),pendingintent);

        Toast.makeText(context,"Alarm set",Toast.LENGTH_SHORT).show();
    }
    public  void cancelAlarm(Context context){
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(context,AlarmReceiver.class);
        PendingIntent alarmpendingintent = PendingIntent.getBroadcast(context,alarm_id,intent,PendingIntent.FLAG_IMMUTABLE);
        alarmManager.cancel(alarmpendingintent);
        this.started =false;

        String toasttext = String.format("Alarm Cancelled for %02d:%02d",hour,minute);
        Toast.makeText(context,toasttext,Toast.LENGTH_SHORT).show();
    }
}

