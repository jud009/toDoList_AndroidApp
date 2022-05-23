package com.example.todolist.data.utils;

import static com.example.todolist.data.utils.Tables.TaskTable.*;

public class SQLScripts {

    public static class TaskTableScripts {
        public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME +
                " (" + FIELD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + FIELD_NAME + " TEXT NOT NULL);";

        public static final String SELECT_ALL = "SELECT * FROM " + TABLE_NAME + ";";
    }
}
