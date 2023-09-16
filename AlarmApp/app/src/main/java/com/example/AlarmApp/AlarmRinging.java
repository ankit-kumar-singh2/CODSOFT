package com.example.AlarmApp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.AlarmApp.databinding.ActivityAlarmRingingBinding;


import java.util.Calendar;


public class AlarmRinging extends AppCompatActivity {

    AlarmViewModel alarmViewModel;
    ActivityAlarmRingingBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_ringing);


        binding.Dismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentService = new Intent(getApplicationContext(),AlarmService.class);
                getApplicationContext().stopService(intentService);
                finish();
            }
        });

        binding.Snooze.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(System.currentTimeMillis());
                calendar.add(Calendar.MINUTE,10);

                Alarm alarm = new Alarm();
                alarm.setTitle("Snooze");
                alarm.setMinute(calendar.MINUTE);
                alarm.setHour(calendar.HOUR_OF_DAY);
                alarm.setStarted(true);
                alarm.schedule(getApplicationContext());

                Intent intentService = new Intent(getApplicationContext(),AlarmService.class);
                getApplicationContext().stopService(intentService);
                finish();



            }
        });
    }
}