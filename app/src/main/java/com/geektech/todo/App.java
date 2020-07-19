package com.geektech.todo;

import android.app.Application;

import androidx.room.Room;

import com.geektech.todo.data.TodoApiClient;
import com.geektech.todo.data.db.TodoDataBase;
import com.geektech.todo.data.local.TodoStorage;
import com.geektech.todo.data.preference.AppPreference;

public class App extends Application {

    private static TodoDataBase todoDataBase;
    public static TodoStorage todoStorage;
    public static TodoApiClient todoApiClient;

    @Override
    public void onCreate() {
        super.onCreate();

        todoApiClient = new TodoApiClient();
        new AppPreference(this);

        todoDataBase = Room.databaseBuilder(this, TodoDataBase.class, "todo_db")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries().build();

        todoStorage = new TodoStorage(todoDataBase.todoDao());


    }
}
