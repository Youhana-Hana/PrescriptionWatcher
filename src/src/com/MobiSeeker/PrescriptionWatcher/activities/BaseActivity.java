package com.MobiSeeker.PrescriptionWatcher.activities;

import com.MobiSeeker.PrescriptionWatcher.connection.IChordServiceListener;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import roboguice.activity.RoboFragmentActivity;

public abstract class BaseActivity extends RoboFragmentActivity implements IChordServiceListener {
   
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
}
