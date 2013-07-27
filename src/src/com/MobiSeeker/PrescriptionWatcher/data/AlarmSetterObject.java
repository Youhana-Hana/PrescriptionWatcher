package com.MobiSeeker.PrescriptionWatcher.data;

import java.util.Calendar;
import java.util.Date;

import com.MobiSeeker.PrescriptionWatcher.activities.Prescription;
import com.MobiSeeker.PrescriptionWatcher.connection.ConnectionConstant;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

public class AlarmSetterObject {
	
	
	
	public static void  setAlaram(Context context,Entry prescription_entry)
	{
		
		if(prescription_entry.getPrescriptionType().equalsIgnoreCase(ConnectionConstant.PRESCRIPTION_WATCHER))
		{
			
			
		}else
		{
			
		}
		
		Intent intent = new Intent(context, AlarmRecieverBroadCast.class);
		intent.putExtra(ConnectionConstant.PRESCRIPTION_ENTRY,prescription_entry);
		intent.setAction("packagename.ACTION");
		PendingIntent pendingIntent = PendingIntent.getBroadcast(context,
		            0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(prescription_entry.getStartDate());
		calendar.setTimeInMillis(System.currentTimeMillis());
		        AlarmManager alarm = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
		alarm.cancel(pendingIntent);
		alarm.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis()+1000, AlarmManager.INTERVAL_DAY, pendingIntent);
	}
	
}
