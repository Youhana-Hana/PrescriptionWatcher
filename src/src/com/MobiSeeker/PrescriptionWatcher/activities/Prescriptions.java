package com.MobiSeeker.PrescriptionWatcher.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.MobiSeeker.PrescriptionWatcher.R;
import com.MobiSeeker.PrescriptionWatcher.data.Adapter;
import com.MobiSeeker.PrescriptionWatcher.data.Entry;
import com.MobiSeeker.PrescriptionWatcher.data.PrescriptionRepository;

import java.util.List;

import roboguice.activity.RoboListActivity;

public class Prescriptions extends RoboListActivity {

    private final static String TAG = "com.MobiSeeker.PrescriptionWatcher.activities.Prescriptions";

    protected PrescriptionRepository prescriptionRepository;

    protected Adapter adapter;

    public static void start(Context context) {
        Intent intent = new Intent(context, Prescriptions.class);
        context.startActivity(intent);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.prescriptions);
        this.prescriptionRepository = new PrescriptionRepository();
    }

    @Override
    protected void onStart() {
        super.onStart();

        try {
        List<Entry> entries = this.prescriptionRepository.getEntries(this);
        this.adapter = new Adapter(this, 0, entries);
        this.setListAdapter(adapter);
        }
        catch(Exception exception){
            Log.e(Prescriptions.TAG, "Prescriptions onStart", exception);
        }
    }

    public void createPrescription(View view) {
        Prescription.start(this);
    }
}
