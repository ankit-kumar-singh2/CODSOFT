package com.example.AlarmApp;

import android.app.Application;

import androidx.annotation.NonNull;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;





public class AlarmViewModel extends AndroidViewModel {

    private AlarmRepository alarmRepository;
    private LiveData<List<Alarm>> alarmlist;

    public AlarmViewModel(@NonNull Application application) {
        super(application);
        alarmRepository = new AlarmRepository(application);
        alarmlist = alarmRepository.getAlarmlist();
    }

    public void insert(Alarm alarm){
        alarmRepository.insertData(alarm);
    }

    public void delete(Alarm alarm){
        alarmRepository.deleteData(alarm);
    }

    public LiveData<List<Alarm>> getAlarmlist(){
        return alarmlist;
    }
}
