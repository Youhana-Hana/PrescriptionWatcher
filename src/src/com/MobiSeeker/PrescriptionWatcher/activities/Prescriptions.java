package com.MobiSeeker.PrescriptionWatcher.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.MobiSeeker.PrescriptionWatcher.R;
import roboguice.activity.RoboListActivity;

public class Prescriptions extends RoboListActivity {

    public static void start(Context context) {
        Intent intent = new Intent(context, Prescriptions.class);
        context.startActivity(intent);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prescriptions);
    }

    public void createPrescription(View view){
        Prescription.start(this);
    }
}
