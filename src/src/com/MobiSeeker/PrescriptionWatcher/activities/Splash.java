package com.MobiSeeker.PrescriptionWatcher.activities;

import roboguice.activity.RoboFragmentActivity;
import android.content.Intent;
import android.os.Bundle;

import com.MobiSeeker.PrescriptionWatcher.R;

public class Splash extends RoboFragmentActivity {

    int progress = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        loadingStart();
    }

    public void loading() {
        this.runOnUiThread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                progress++;
            }
        });
    }

    public void loadingStart() {
        Thread thread = new Thread() {
            public void run() {
                while (progress < 1) {
                    loading();
                    try {
                        sleep(1);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }

                goToHome();
            }

        };

        thread.start();
    }

    public void goToHome() {
        this.runOnUiThread(new Runnable() {

            @Override
            public void run() {
                Intent intent = new Intent(Splash.this, PrescriptionWatcher.class);
                startActivity(intent);
            }
        });
    }
}
