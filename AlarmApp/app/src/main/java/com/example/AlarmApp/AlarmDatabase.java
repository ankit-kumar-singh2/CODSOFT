package com.example.AlarmApp;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = Alarm.class, version=1)
public abstract class AlarmDatabase extends RoomDatabase {

    private static AlarmDatabase instance;

    public abstract AlarmDao alarmDao();

    public static synchronized  AlarmDatabase getInstance(Context context){
        if(instance==null){
            instance= Room.databaseBuilder(context.getApplicationContext(),
                    AlarmDatabase.class,"Alarm_Database").fallbackToDestructiveMigration().build();
        }
        return instance;
    }
}
