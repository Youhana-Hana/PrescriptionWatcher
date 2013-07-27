package com.MobiSeeker.PrescriptionWatcher.data;

import android.content.Context;
import android.telephony.TelephonyManager;

public class Utilites {
	
	public static String getDeviceImei(Context context)
	{
		TelephonyManager manger=(TelephonyManager)(context.getSystemService(Context.TELEPHONY_SERVICE));
		return manger.getDeviceId();
		
	}

}
