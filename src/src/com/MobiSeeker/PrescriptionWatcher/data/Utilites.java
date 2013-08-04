package com.MobiSeeker.PrescriptionWatcher.data;

import android.content.Context;
import android.telephony.TelephonyManager;

public class Utilites {
	
	public static String getDeviceImei(Context context)
	{
		 String id = android.provider.Settings.System.getString(context.getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);
	        return id;
//		TelephonyManager manger=(TelephonyManager)(context.getSystemService(Context.TELEPHONY_SERVICE));
//		return manger.getDeviceId();	
	}

}
