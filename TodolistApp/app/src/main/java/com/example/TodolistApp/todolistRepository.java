package com.example.TodolistApp;


import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class todolistRepository {
    private taskDao taskDao;
    private LiveData<List<Task>> taskList;

    public todolistRepository(Application application) {
        todolistDatabase listDatabase = todolistDatabase.getInstance(application);
        taskDao = listDatabase.taskDao();
        taskList = taskDao.getalltask();
    }

    public void insertData(Task task){
        new InsertTask(taskDao).execute(task);
    }
    public void updateData(Task task){
        new UpdateTask(taskDao).execute(task);
    }
    public void deleteData(Task task){
        new DeleteTask(taskDao).execute(task);
    }

    public LiveData<List<Task>> getalltask(){
        return taskList;
    }

    private static class InsertTask extends AsyncTask<Task,Void,Void>{

        private taskDao taskDao;

        private InsertTask(taskDao taskdao) {

            this.taskDao=taskdao;
        }

        @Override
        protected Void doInBackground(Task... tasks) {
            taskDao.insert(tasks[0]);
            return null;
        }


    }
    private static class DeleteTask extends AsyncTask<Task,Void,Void>{

        private taskDao taskDao;

        private DeleteTask(taskDao taskdao)
        {
            this.taskDao=taskdao;
        }

        @Override
        protected Void doInBackground(Task... tasks) {
            taskDao.delete(tasks[0]);
            return null;
        }


    }private static class UpdateTask extends AsyncTask<Task,Void,Void>{

        private taskDao taskDao;

        private UpdateTask(taskDao taskdao) {

            this.taskDao=taskdao;
        }

        @Override
        protected Void doInBackground(Task... tasks) {
            taskDao.update(tasks[0]);
            return null;
        }


    }
}
