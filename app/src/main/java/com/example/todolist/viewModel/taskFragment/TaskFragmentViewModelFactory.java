package com.example.todolist.viewModel.taskFragment;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.todolist.data.database.TaskDao;

public class TaskFragmentViewModelFactory implements ViewModelProvider.Factory {
    private final TaskDao taskDao;

    public TaskFragmentViewModelFactory(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {

        if (modelClass.isAssignableFrom(TaskFragmentViewModel.class)){
            return (T) new TaskFragmentViewModel(taskDao);
        }

        throw new IllegalArgumentException("Unknown class.");
    }
}
