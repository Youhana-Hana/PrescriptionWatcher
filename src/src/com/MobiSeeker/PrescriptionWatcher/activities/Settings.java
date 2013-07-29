package com.MobiSeeker.PrescriptionWatcher.activities;

import android.app.Activity;
import android.os.Bundle;
import android.preference.PreferenceActivity;

import com.MobiSeeker.PrescriptionWatcher.R;

public class Settings extends PreferenceActivity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.settings);
    }
}