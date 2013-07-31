package com.MobiSeeker.PrescriptionWatcher.data;

import com.MobiSeeker.PrescriptionWatcher.activities.Splash;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AlarmRecieverBroadCast extends BroadcastReceiver{

	@Override
	public void onReceive(Context arg0, Intent alarmIntent) {
		// TODO Auto-generated method stub
		System.out.println("Alaram recieved ");
		Intent intent=new Intent(arg0,Splash.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);                     
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		arg0.startActivity(intent);
		
		
		
		
	}

}
