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

	public static final String ALARAM_FOR_PRESCRIPTION="ALARAM_FOR_PRESCRIPTION";
	
	public static final String ALARM_FOR_REQUEST_CONFIRMATION="ALARM_FOR_REQUEST_CONFIRMATION";
	
	
	
	public static void  setAlaram(Context context,Entry prescription_entry,Date startDate,Date endDate,String alarmType)
	{
		
		if(alarmType.equalsIgnoreCase(ALARAM_FOR_PRESCRIPTION))
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
		calendar.setTime(startDate);
		calendar.setTimeInMillis(System.currentTimeMillis());
		        AlarmManager alarm = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
		alarm.cancel(pendingIntent);
		alarm.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis()+1000, AlarmManager.INTERVAL_DAY, pendingIntent);
	}
	
}
