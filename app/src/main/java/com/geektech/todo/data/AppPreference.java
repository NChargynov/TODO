package com.geektech.todo.data;

import android.content.Context;
import android.content.SharedPreferences;

public class AppPreference {

    private static SharedPreferences preferences;
    private static final String PREFERENCE_NAME = "bored_app_prefs";
    private static final String PREF_IS_FIRST_LAUNCH = "is_first_launch";

    public AppPreference(Context context) {
        preferences = context.getSharedPreferences(
                PREFERENCE_NAME,
                Context.MODE_PRIVATE);
    }


    public static boolean isFirstLaunch(){
        return preferences.getBoolean(PREF_IS_FIRST_LAUNCH, false);
    }

    public static void setLaunched(boolean isFirstLaunch){
        preferences.edit().putBoolean(PREF_IS_FIRST_LAUNCH, isFirstLaunch).apply();
    }
}
