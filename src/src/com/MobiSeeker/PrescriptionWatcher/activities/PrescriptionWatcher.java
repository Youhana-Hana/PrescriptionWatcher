package com.MobiSeeker.PrescriptionWatcher.activities;

import android.os.Bundle;

import com.MobiSeeker.PrescriptionWatcher.R;

import roboguice.activity.RoboFragmentActivity;

public class PrescriptionWatcher extends RoboFragmentActivity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
}
