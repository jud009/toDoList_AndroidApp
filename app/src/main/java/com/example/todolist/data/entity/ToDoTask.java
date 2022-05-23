package com.example.todolist.data.entity;

public class ToDoTask {

    private Integer id;
    private String task;

    public ToDoTask(String task) {
        this.task = task;
    }

    public ToDoTask(Integer id, String task) {
        this.id = id;
        this.task = task;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    @Override
    public String toString() {
        return "ToDoTask{" +
                "id=" + id +
                ", task='" + task + '\'' +
                '}';
    }
}
