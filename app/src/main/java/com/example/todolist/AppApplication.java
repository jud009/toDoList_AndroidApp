package com.example.todolist;

import android.app.Application;

import com.example.todolist.data.database.TaskDao;
import com.example.todolist.data.database.TaskDatabase;

import java.util.concurrent.Executors;

public class AppApplication extends Application {

    public TaskDao taskDao;

    @Override
    public void onCreate() {
        super.onCreate();
        TaskDatabase database = new TaskDatabase(getApplicationContext());
        taskDao = new TaskDao(Executors.newFixedThreadPool(4), database);
    }
}
