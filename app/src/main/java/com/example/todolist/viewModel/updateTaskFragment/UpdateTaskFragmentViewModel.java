package com.example.todolist.viewModel.updateTaskFragment;

import androidx.lifecycle.ViewModel;

import com.example.todolist.data.database.TaskDao;
import com.example.todolist.data.entity.ToDoTask;

public class UpdateTaskFragmentViewModel extends ViewModel {

    private final TaskDao dao;

    public UpdateTaskFragmentViewModel(TaskDao dao) {
        this.dao = dao;
    }

    public void insert(ToDoTask task) {
        dao.insert(task);
    }

    public void update(ToDoTask task){
        dao.update(task);
    }
}
