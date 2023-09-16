package com.example.AlarmApp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.example.AlarmApp.databinding.ActivityInsertAlarmBinding;

public class insert_alarm extends AppCompatActivity {

    ActivityInsertAlarmBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInsertAlarmBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.ScheduleAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    intent.putExtra("Hour",binding.newAlarmTimepicker.getHour());
                    intent.putExtra("Minute",binding.newAlarmTimepicker.getMinute());

                }else{
                    intent.putExtra("Hour",binding.newAlarmTimepicker.getCurrentHour());
                    intent.putExtra("Minute",binding.newAlarmTimepicker.getCurrentMinute());
                }
                intent.putExtra("Title",binding.newAlarmTitle.getText().toString());
                setResult(RESULT_OK,intent);
                finish();

            }
        });
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        startActivity(new Intent(insert_alarm.this,MainActivity.class));
    }
}