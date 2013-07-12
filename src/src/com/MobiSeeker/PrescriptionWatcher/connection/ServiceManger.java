package com.MobiSeeker.PrescriptionWatcher.connection;

import java.util.List;

import com.MobiSeeker.PrescriptionWatcher.activities.BaseActivity;
import com.MobiSeeker.PrescriptionWatcher.connection.ChordApiService.ChordServiceBinder;
import com.samsung.chord.ChordManager;
import com.samsung.chord.IChordChannel;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;

public class ServiceManger {
	
	private ChordApiService mChordService;
	BaseActivity mainActivity;
	List<IChordChannel> listOfChannels;
	
	private boolean Connected ;
	
	public ServiceManger(BaseActivity activity)
	{
		this.mainActivity=activity;
	}	

	private ServiceConnection serviceConnection;
	
	public void Connect()
	{
		serviceConnection=new ServiceConnection() {
			
			@Override
			public void onServiceDisconnected(ComponentName name) {
				// TODO Auto-generated method stub
				mChordService=null;
				Connected=false;
				
			}
			
			@Override
			public void onServiceConnected(ComponentName name, IBinder service) {
				// TODO Auto-generated method stub
				ChordServiceBinder binder=(ChordServiceBinder)service;
				mChordService=binder.getService();
				try
				{
					mChordService.initialize(mainActivity);
					Connected=true;
				}catch(Exception EE)
				{
					EE.printStackTrace();
				}
				
				mChordService.start(ChordManager.INTERFACE_TYPE_WIFI);
				mChordService.joinChannel(NodeManager.CHORD_API_CHANNEL);
				listOfChannels=mChordService.getJoinedChannelList();
				
				System.out.println(listOfChannels);
		//		sendData("welcome");
			}
		};
		
	}
	
	

	
	public void reConnect()
	{
		if(!isConnected())
		{
			Connect();
		}
		
	}
	
	
	public boolean isConnected() {
		return Connected;
	}


	public void setConnected(boolean connected) {
		Connected = connected;
	}


	public ChordApiService getmChordService() {
		return mChordService;
	}


	public void setmChordService(ChordApiService mChordService) {
		this.mChordService = mChordService;
	}


	public void sendData(String data,String messageType,String nodeName)
	{
		if(mChordService!=null)
		{
			mChordService.sendData(NodeManager.CHORD_API_CHANNEL, data.getBytes(), nodeName, messageType);
		}
		
	}
	

	public List<IChordChannel> getListOfChannels() {
		return listOfChannels;
	}


	public void setListOfChannels(List<IChordChannel> listOfChannels) {
		this.listOfChannels = listOfChannels;
	}


	public void sendDataToAll(String data,String messageType)
	{
		if(mChordService!=null)
		{
			mChordService.sendDataToAll(NodeManager.CHORD_API_CHANNEL, data.getBytes(),messageType);
		}
		
	}
	
	 public void bindChordService() {
	        if (mChordService == null) {
	            Intent intent = new Intent(
	                    "com.MobiSeeker.PrescriptionWatcher.connection.ChordApiService.SERVICE_BIND");
	            mainActivity.bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
	            
	        }
	    }

	 public void startService() {
	        Intent intent = new Intent("com.MobiSeeker.PrescriptionWatcher.connection.ChordApiService.SERVICE_START");
	        mainActivity.startService(intent);
	    }

	    private void stopService() {
	        Intent intent = new Intent("com.MobiSeeker.PrescriptionWatcher.connection.ChordApiService.SERVICE_STOP");
	        mainActivity.stopService(intent);
	    }

	 
	public void initalize()
	{
		
		
	}
	

}
