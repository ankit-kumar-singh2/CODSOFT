package com.example.AlarmApp;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class AlarmRepository {

    private AlarmDao alarmDao;
    private LiveData<List<Alarm>> alarmlist;

    public AlarmRepository(Application application){
        AlarmDatabase alarmDb = AlarmDatabase.getInstance(application);
        alarmDao = alarmDb.alarmDao();
        alarmlist = alarmDao.getAllAlarms();
    }

    public void insertData(Alarm alarm){
        new InsertData(alarmDao).execute(alarm);
    }
    public void updateData(Alarm alarm){
        new UpdateData(alarmDao).execute(alarm);
    }
    public void deleteData(Alarm alarm){
        new DeleteData(alarmDao).execute(alarm);
    }

    public LiveData<List<Alarm>> getAlarmlist(){
        return alarmlist;
    }

    public static  class InsertData extends AsyncTask<Alarm,Void,Void>{

        private AlarmDao alarmDao;

        private InsertData(AlarmDao alarmDao){
            this.alarmDao=alarmDao;
        }
        @Override
        protected Void doInBackground(Alarm... alarms) {
            alarmDao.insertAlarm(alarms[0]);
            return null;
        }
    }
    public static  class UpdateData extends AsyncTask<Alarm,Void,Void>{

        private AlarmDao alarmDao;

        private UpdateData(AlarmDao alarmDao){
            this.alarmDao=alarmDao;
        }
        @Override
        protected Void doInBackground(Alarm... alarms) {
            alarmDao.updateAlarm(alarms[0]);
            return null;
        }
    }public static  class DeleteData extends AsyncTask<Alarm,Void,Void>{

        private AlarmDao alarmDao;

        private DeleteData(AlarmDao alarmDao){
            this.alarmDao=alarmDao;
        }
        @Override
        protected Void doInBackground(Alarm... alarms) {
            alarmDao.deleteAlarm(alarms[0]);
            return null;
        }
    }
}
