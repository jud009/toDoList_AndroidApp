package com.example.todolist.data.database;

import static com.example.todolist.data.utils.SQLScripts.TaskTableScripts.*;
import static com.example.todolist.data.utils.Tables.TaskTable.*;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.todolist.data.entity.ToDoTask;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

public class TaskDao implements Dao<ToDoTask> {

    private final SQLiteDatabase writeInDb;
    private final SQLiteDatabase readInDb;
    private final ExecutorService executor;

    public TaskDao(ExecutorService executor, SQLiteOpenHelper database) {
        this.executor = executor;
        this.writeInDb = database.getWritableDatabase();
        this.readInDb = database.getReadableDatabase();
    }

    @Override
    public void insert(ToDoTask value) {
        executor.execute(() -> writeInDb.insert(TABLE_NAME, null, getTaskContentValue(value)));
    }

    @Override
    public LiveData<List<ToDoTask>> getAll() {
        MutableLiveData<List<ToDoTask>> liveData = new MutableLiveData<>();
        executor.execute(() -> {
            List<ToDoTask> tasks = new ArrayList<>();
            Cursor cursor = readInDb.rawQuery(SELECT_ALL, null);

            int idIndex = cursor.getColumnIndex(FIELD_ID);
            int nameIndex = cursor.getColumnIndex(FIELD_NAME);

            boolean hasItem = cursor.moveToFirst();
            while (hasItem) {
                int id = cursor.getInt(idIndex);
                String name = cursor.getString(nameIndex);
                tasks.add(new ToDoTask(id, name));
                hasItem = cursor.moveToNext();
            }

            liveData.postValue(tasks);
        });
        return liveData;
    }

    @Override
    public void delete(ToDoTask task) {
        executor.execute(() -> {
            String[] args = {task.getId().toString()};
            writeInDb.delete(TABLE_NAME, FIELD_ID + "=?", args);
        });
    }

    @Override
    public void update(ToDoTask task) {
        executor.execute(() -> {
            String[] args = {task.getId().toString()};
            writeInDb.update(TABLE_NAME, getTaskContentValue(task), FIELD_ID + "=?", args);
        });
    }

    private ContentValues getTaskContentValue(ToDoTask task) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(FIELD_ID, task.getId());
        contentValues.put(FIELD_NAME, task.getTask());
        return contentValues;
    }
}