package com.example.todolist.data.database;

import androidx.lifecycle.LiveData;

import java.util.List;

public interface Dao<T> {
    void insert(T value);

    LiveData<List<T>> getAll();

    void delete(T value);

    void update(T Value);
}
