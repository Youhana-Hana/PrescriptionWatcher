package com.MobiSeeker.PrescriptionWatcher.main;

import com.MobiSeeker.PrescriptionWatcher.PrescriptionWatcher;
import com.MobiSeeker.PrescriptionWatcher.R;

import roboguice.activity.RoboFragmentActivity;
import roboguice.inject.InjectView;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.widget.SeekBar;

public class Splash extends BaseActivity {

	FragmentManager manager;
	FragmentTransaction transaction;
	int progress=0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		loadingStart();

	}

	
	
	public void loading()
	{
		this.runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				progress++;
			}
		});
		
		
	}
	
	
	public void loadingStart()
	{
		Thread thread=new Thread()
		{
				public void run()
				{
					while (progress<100) {
						loading();
					try {
						sleep(100);
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
	
	
	public void goToHome()
	{
		this.runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				Intent intent=new Intent(Splash.this,PrescriptionWatcher.class);
				startActivity(intent);

			}
		});

			
		
	}
	
	/*
	 * initalize Transactions
	 */

	private void initalizemangerAndTransaction() {
		manager = this.getSupportFragmentManager();
		transaction = manager.beginTransaction();
	}

}
