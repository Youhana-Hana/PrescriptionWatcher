package com.MobiSeeker.PrescriptionWatcher.data;

import java.util.Calendar;
import java.util.Date;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

public class AlarmSetterObject {

	public void setAlaram(Context context,Date startDate,Date endDate)
	{
		Intent intent = new Intent(context, AlarmRecieverBroadCast.class);
		intent.setAction("packagename.ACTION");
		PendingIntent pendingIntent = PendingIntent.getBroadcast(context,
		            0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startDate);
		calendar.setTimeInMillis(System.currentTimeMillis());
		        AlarmManager alarm = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
		alarm.cancel(pendingIntent);
		alarm.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
		
	}
	
}
