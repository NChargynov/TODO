package com.geektech.todo;

import android.app.Application;

import com.geektech.todo.data.AppPreference;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        new AppPreference(this);
    }
}
