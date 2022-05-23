package com.example.todolist.viewModel.updateTaskFragment;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.todolist.data.database.TaskDao;

public class UpdateTaskFragmentViewModelFactory implements ViewModelProvider.Factory {

    private final TaskDao taskDao;

    public UpdateTaskFragmentViewModelFactory(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {

        if (modelClass.isAssignableFrom(UpdateTaskFragmentViewModel.class)){
            return (T) new UpdateTaskFragmentViewModel(taskDao);
        }

        throw new IllegalArgumentException("Unknown class.");
    }
}
