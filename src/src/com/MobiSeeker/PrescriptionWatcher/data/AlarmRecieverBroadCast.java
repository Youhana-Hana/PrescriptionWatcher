package com.MobiSeeker.PrescriptionWatcher.data;


import java.util.List;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.MobiSeeker.PrescriptionWatcher.activities.PrescriptionWatcher;
import com.MobiSeeker.PrescriptionWatcher.connection.ConnectionConstant;

public class AlarmRecieverBroadCast extends BroadcastReceiver{

	@Override
	public void onReceive(Context arg0, Intent alarmIntent) {
		// TODO Auto-generated method stub
		try{
		System.out.println("Alaram recieved ");
		PrescriptionRepository prescriptionRepository = new PrescriptionRepository();

        List<Entry> entries = prescriptionRepository.getEntries(arg0);
        Entry selectedEntry=(Entry) alarmIntent.getSerializableExtra(ConnectionConstant.PRESCRIPTION_ENTRY);
        
		Intent intent=new Intent(arg0,PrescriptionWatcher.class);
		intent.putExtra(ConnectionConstant.PRESCRIPTION_ENTRY, selectedEntry);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		
		for(int i=0;i<entries.size();i++)
		{
			if(entries.get(i).getMedicineName().equalsIgnoreCase(selectedEntry.getMedicineName())
				&&	entries.get(i).getPrescriptionType().equalsIgnoreCase(selectedEntry.getPrescriptionType())
					&&	entries.get(i).getPrescriptionImagePath().equalsIgnoreCase(selectedEntry.getPrescriptionImagePath())
					&&	entries.get(i).getComment().equalsIgnoreCase(selectedEntry.getComment()))
				{
				arg0.startActivity(intent);
				break;
				}
				
		}
		
		
		
		}catch(Exception ee)
		{
			ee.printStackTrace();
		}
		
		
		
	}

}
