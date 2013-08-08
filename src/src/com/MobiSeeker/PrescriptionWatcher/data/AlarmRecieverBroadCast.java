package com.MobiSeeker.PrescriptionWatcher.data;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.MobiSeeker.PrescriptionWatcher.activities.PrescriptionWatcher;
import com.MobiSeeker.PrescriptionWatcher.connection.ConnectionConstant;

public class AlarmRecieverBroadCast extends BroadcastReceiver{

	@Override
	public void onReceive(Context arg0, Intent alarmIntent) {
		// TODO Auto-generated method stub
		System.out.println("Alaram recieved ");
		Intent intent=new Intent(arg0,PrescriptionWatcher.class);
		intent.putExtra(ConnectionConstant.PRESCRIPTION_ENTRY, alarmIntent.getSerializableExtra(ConnectionConstant.PRESCRIPTION_ENTRY));
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		arg0.startActivity(intent);
		
		
		
		
	}

}
