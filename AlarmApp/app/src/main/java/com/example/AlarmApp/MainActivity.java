package com.example.AlarmApp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.AlarmApp.databinding.ActivityMainBinding;;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    AlarmAdapter adapter;
    ActivityMainBinding binding;
    private AlarmViewModel alarmViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        alarmViewModel = new ViewModelProvider(MainActivity.this,(ViewModelProvider.Factory)ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication()))
                .get(AlarmViewModel.class);

        binding.floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,insert_alarm.class);
                startActivityForResult(intent,1);
            }
        });

        binding.mainRecycle.setLayoutManager(new LinearLayoutManager(this));
        binding.mainRecycle.setHasFixedSize(true);
        adapter = new AlarmAdapter();
        binding.mainRecycle.setAdapter(adapter);


        alarmViewModel.getAlarmlist().observe(this, new Observer<List<Alarm>>() {
            @Override
            public void onChanged(List<Alarm> alarms) {
                adapter.submitList(alarms);
            }
        });



        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                alarmViewModel.delete(adapter.getAlarm(viewHolder.getAdapterPosition()));
                Toast.makeText(MainActivity.this,"Alarm deleted",Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(binding.mainRecycle);






    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1){
            String title = data.getStringExtra("Title");
            int hour = data.getIntExtra("Hour",0);
            int minute = data.getIntExtra("Minute",0);
            boolean started = true;
            Alarm alarm = new Alarm();
            alarm.setHour(hour);
            alarm.setMinute(minute);
            alarm.setStarted(started);
            alarm.setTitle(title);
            alarmViewModel.insert(alarm);
            alarm.schedule(getApplicationContext());
        }
    }
}