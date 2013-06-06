package com.MobiSeeker.PrescriptionWatcher;

import roboguice.activity.RoboFragmentActivity;
import android.app.Activity;
import android.os.Bundle;

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
