package com.example.TodolistApp;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = Task.class,version = 1)
public abstract class todolistDatabase extends RoomDatabase {

    private static todolistDatabase object;

    public abstract taskDao taskDao();
    public static synchronized todolistDatabase getInstance(Context context){
        if(object==null){
            object = Room.databaseBuilder(context.getApplicationContext()
                    ,todolistDatabase.class,"to_do_list_database").fallbackToDestructiveMigration()
                    .build();
        }
        return object;
    }
}
