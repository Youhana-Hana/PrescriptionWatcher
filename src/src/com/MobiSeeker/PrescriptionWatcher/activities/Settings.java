package com.MobiSeeker.PrescriptionWatcher.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceActivity;

import com.MobiSeeker.PrescriptionWatcher.R;

public class Settings extends PreferenceActivity {

    public static void start(Context context) {
        Intent intent = new Intent(context, Settings.class);
        context.startActivity(intent);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.settings);
        
    }
    
    
}