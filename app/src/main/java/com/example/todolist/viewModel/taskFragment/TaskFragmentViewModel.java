package com.example.todolist.viewModel.taskFragment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.todolist.data.database.TaskDao;
import com.example.todolist.data.entity.ToDoTask;

import java.util.List;

public class TaskFragmentViewModel extends ViewModel {

    private final TaskDao taskDao;

    public TaskFragmentViewModel(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    public LiveData<List<ToDoTask>> getAll() {
        return taskDao.getAll();
    }

    public void delete(ToDoTask task) {
        taskDao.delete(task);
    }
}