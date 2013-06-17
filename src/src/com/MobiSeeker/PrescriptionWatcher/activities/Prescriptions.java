package com.MobiSeeker.PrescriptionWatcher.activities;

import android.os.Bundle;
import android.view.View;
import com.MobiSeeker.PrescriptionWatcher.R;
import roboguice.activity.RoboListActivity;

public class Prescriptions extends RoboListActivity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prescriptions);
    }

    public void createPrescription(View view){
        Prescription.start(this);
    }
}
