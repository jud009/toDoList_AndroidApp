package com.example.todolist.screens.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.todolist.AppApplication;
import com.example.todolist.R;
import com.example.todolist.data.entity.ToDoTask;
import com.example.todolist.databinding.FragmentUpdateTaskBinding;
import com.example.todolist.viewModel.updateTaskFragment.UpdateTaskFragmentViewModel;
import com.example.todolist.viewModel.updateTaskFragment.UpdateTaskFragmentViewModelFactory;

public class UpdateTaskFragment extends Fragment {

    private FragmentUpdateTaskBinding binding;
    private UpdateTaskFragmentViewModel viewModel;
    private final boolean isUpdate;
    private ToDoTask toDoTask;

    public UpdateTaskFragment(boolean isUpdate) {
        this.isUpdate = isUpdate;
    }

    public void setToDoTask(ToDoTask toDoTask) {
        this.toDoTask = toDoTask;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        binding = FragmentUpdateTaskBinding.inflate(inflater, container, false);
        AppApplication appApplication = (AppApplication) requireActivity().getApplication();
        viewModel = new ViewModelProvider(this, new UpdateTaskFragmentViewModelFactory(appApplication.taskDao)).get(UpdateTaskFragmentViewModel.class);

        if (isToUpdate()) {
            Log.i("TAG", "update: " + toDoTask);
            binding.inputText.setText(toDoTask.getTask());
        }

        return binding.getRoot();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_check) {
            String typedValue = binding.inputText.getText().toString();
            if (!typedValue.isEmpty()) {
                saveOrUpdate(typedValue);
                getParentFragmentManager().popBackStack();
            } else {
                displayToast();
            }
        }
        return true;
    }

    private boolean isToUpdate() {
        return isUpdate && toDoTask != null;
    }

    private void saveOrUpdate(String value) {
        if (isToUpdate())
            viewModel.update(new ToDoTask(toDoTask.getId(), value));
        else
            viewModel.insert(new ToDoTask(value));
    }

    private void displayToast() {
        Toast.makeText(requireContext(), "Campo vazio", Toast.LENGTH_SHORT).show();
    }
}