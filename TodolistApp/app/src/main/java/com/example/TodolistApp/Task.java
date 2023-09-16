package com.example.TodolistApp;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "todolist")
public class Task {
    private String title;
    private String task;

    @PrimaryKey(autoGenerate = true)
    private int id;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public Task(String title, String task) {
        this.title = title;
        this.task = task;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }
}
