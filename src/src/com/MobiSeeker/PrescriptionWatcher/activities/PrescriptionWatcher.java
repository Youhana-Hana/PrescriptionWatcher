package com.MobiSeeker.PrescriptionWatcher.activities;

import android.os.Bundle;
import android.view.View;

import com.MobiSeeker.PrescriptionWatcher.R;

import roboguice.activity.RoboActivity;

public class PrescriptionWatcher extends RoboActivity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    public void viewPrescriptions(View view) {
        Prescriptions.start(this);
    }

    public void viewContacts(View view) {

    }

    public void viewSettings(View view) {

    }
}
