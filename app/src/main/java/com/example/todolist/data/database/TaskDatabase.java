package com.example.todolist.data.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.todolist.data.utils.SQLScripts;

public class TaskDatabase extends SQLiteOpenHelper {

    public static final int VERSION = 1;

    public TaskDatabase(@Nullable Context context) {
        super(context, TaskDatabase.class.getSimpleName(), null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        try {
            database.execSQL(SQLScripts.TaskTableScripts.CREATE_TABLE);
            Log.e("DATABASE ERROR", "SUCCESS CREATED");
        } catch (Exception e) {
            Log.e("DATABASE ERROR", e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {}
}
