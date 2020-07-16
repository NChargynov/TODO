package com.geektech.todo.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.geektech.todo.R;
import com.geektech.todo.data.preference.AppPreference;
import com.geektech.todo.presentation.intro.IntroActivity;
import com.geektech.todo.presentation.main.MainActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        chooseScreen();
    }

    private void chooseScreen() {
        if (AppPreference.isFirstLaunch()){
            AppPreference.setLaunched(true);
            startActivity(new Intent(this, MainActivity.class));
        } else {
            startActivity(new Intent(this, IntroActivity.class));
        }
        finish();
    }
}
