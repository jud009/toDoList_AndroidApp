package com.example.todolist.screens.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.todolist.AppApplication;
import com.example.todolist.R;
import com.example.todolist.data.entity.ToDoTask;
import com.example.todolist.databinding.FragmentTasksBinding;
import com.example.todolist.screens.adapters.TaskAdapter;
import com.example.todolist.viewModel.taskFragment.TaskFragmentViewModel;
import com.example.todolist.viewModel.taskFragment.TaskFragmentViewModelFactory;

public class TasksFragment extends Fragment {

    private TaskAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        FragmentTasksBinding view = FragmentTasksBinding.inflate(inflater, container, false);
        AppApplication appApplication = (AppApplication) requireActivity().getApplication();
        TaskFragmentViewModel viewModel = new ViewModelProvider(this, new TaskFragmentViewModelFactory(appApplication.taskDao)).get(TaskFragmentViewModel.class);

        adapter = new TaskAdapter(new TaskAdapter.onClickListener() {
            @Override
            public void onTaskClicked(ToDoTask task) {
                UpdateTaskFragment fragment = new UpdateTaskFragment(true);
                fragment.setToDoTask(task);
                navToFragment(fragment);
            }

            @Override
            public void onLogClicked(ToDoTask task) {
                viewModel.delete(task);
                updateAdapterValues(viewModel);
            }
        });

        updateAdapterValues(viewModel);

        view.recyclerTask.setLayoutManager(new LinearLayoutManager(requireContext()));
        view.recyclerTask.setHasFixedSize(true);
        view.recyclerTask.setAdapter(adapter);

        view.floatingActionButton.setOnClickListener(v -> {
            navToFragment(new UpdateTaskFragment(false));
        });

        return view.getRoot();
    }

    private void updateAdapterValues(TaskFragmentViewModel viewModel) {
        viewModel.getAll().observe(getViewLifecycleOwner(), adapter::submitList);
    }

    private void navToFragment(Fragment fragment) {
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
