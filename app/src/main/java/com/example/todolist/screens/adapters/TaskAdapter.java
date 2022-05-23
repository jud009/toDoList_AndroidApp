package com.example.todolist.screens.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolist.data.entity.ToDoTask;
import com.example.todolist.databinding.AdapterTaskLayoutBinding;

public class TaskAdapter extends ListAdapter<ToDoTask, TaskAdapter.ViewHolder> {

    public interface onClickListener {
        void onTaskClicked(ToDoTask task);
        void onLogClicked(ToDoTask task);
    }

    private final onClickListener listener;

    public TaskAdapter(onClickListener listener) {
        super(new Callback());
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return ViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(getItem(position), listener);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private final AdapterTaskLayoutBinding view;

        private ViewHolder(AdapterTaskLayoutBinding itemView) {
            super(itemView.getRoot());
            this.view = itemView;
        }

        public void bind(ToDoTask task, onClickListener listener) {
            view.taskValue.setOnClickListener(v -> {
                listener.onTaskClicked(task);
            });
            view.taskValue.setOnLongClickListener(v -> {
                listener.onLogClicked(task);
                return true;
            });
            view.taskValue.setText(task.getTask());
            view.executePendingBindings();
        }

        public static ViewHolder create(ViewGroup viewGroup) {
            LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
            AdapterTaskLayoutBinding binding = AdapterTaskLayoutBinding.inflate(layoutInflater, viewGroup, false);
            return new ViewHolder(binding);
        }

    }
}

class Callback extends DiffUtil.ItemCallback<ToDoTask> {

    @Override
    public boolean areItemsTheSame(@NonNull ToDoTask oldItem, @NonNull ToDoTask newItem) {
        return oldItem.getId().equals(newItem.getId());
    }

    @Override
    public boolean areContentsTheSame(@NonNull ToDoTask oldItem, @NonNull ToDoTask newItem) {
        return oldItem.getTask().equals(newItem.getTask());
    }
}