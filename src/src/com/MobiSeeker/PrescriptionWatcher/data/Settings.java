package com.MobiSeeker.PrescriptionWatcher.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Settings {

    private Context context = null;

    public Settings(Context context) {
        this.context = context;
    }

    public String getUserName() {
        SharedPreferences sharedPrefs = PreferenceManager
                .getDefaultSharedPreferences(this.context);

        return sharedPrefs.getString("prefUsername", "Guest");
    }

    public boolean isVibrate() {
        SharedPreferences sharedPrefs = PreferenceManager
                .getDefaultSharedPreferences(this.context);

        return sharedPrefs.getBoolean("prefVibrate", false);
    }
}
