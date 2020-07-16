package com.geektech.todo;

import android.app.Application;

import com.geektech.todo.data.TodoApiClient;
import com.geektech.todo.data.preference.AppPreference;

public class App extends Application {


    public static TodoApiClient todoApiClient;

    @Override
    public void onCreate() {
        super.onCreate();

        todoApiClient = new TodoApiClient();
        new AppPreference(this);
    }
}
