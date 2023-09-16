package com.example.TodolistApp;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class TaskViewModel extends AndroidViewModel {
    private todolistRepository todolistrepository;
    private LiveData<List<Task>> tasklist;

    public TaskViewModel(@NonNull Application application) {
        super(application);
        todolistrepository = new todolistRepository(application);
        tasklist = todolistrepository.getalltask();
    }

    public void insert(Task task) {
        todolistrepository.insertData(task);
    }
    public void delete(Task task) {
        todolistrepository.deleteData(task);
    }
    public void update(Task task) {
        todolistrepository.updateData(task);
    }

    public LiveData<List<Task>> getalldata(){
        return tasklist;

    }
}
