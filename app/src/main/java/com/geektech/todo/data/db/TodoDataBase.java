package com.geektech.todo.data.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.geektech.todo.data.local.TodoDao;
import com.geektech.todo.model.TodoAction;

@Database(entities = {TodoAction.class},
        version = TodoDataBase.VERSION,
        exportSchema = false)

public abstract class TodoDataBase extends RoomDatabase {

    public static final int VERSION = 1;

    abstract public TodoDao todoDao();
}
