package com.MobiSeeker.PrescriptionWatcher.activities;

import java.util.HashMap;

import com.MobiSeeker.PrescriptionWatcher.connection.ConnectionConstant;
import com.MobiSeeker.PrescriptionWatcher.connection.IChordServiceListener;
import com.MobiSeeker.PrescriptionWatcher.connection.ServiceManger;

import android.R;
import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import roboguice.activity.RoboFragmentActivity;

public abstract class BaseActivity extends RoboFragmentActivity implements IChordServiceListener {

	
	public static HashMap<String, String> Nodes=new HashMap<String, String>();
	
	
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

	@Override
	public void onReceiveMessage(String node, String channel, String message,String MessageType) {
		// TODO Auto-generated method stub

		if(MessageType.equalsIgnoreCase(ConnectionConstant.SEND_MESSAGE))
		{
			
		}
		else
		if(MessageType.equalsIgnoreCase(ConnectionConstant.GET_DEVICE_NAME))			
		{
			try
			{
				ServiceManger.getInstance(this,false).sendData(AccountManager.get(this).getAccounts()[0].name, ConnectionConstant.MY_DEVICE_NAME, node);
			
			}catch(Exception ee)
			{
				ServiceManger.getInstance(this,false).sendData(android.os.Build.MODEL, ConnectionConstant.MY_DEVICE_NAME, node);
			}			
		}
		else
		if(MessageType.equalsIgnoreCase(ConnectionConstant.REQUEST_FOR_REGISTER_ALARAM))	
		{
			
			
		}		
		else
		if(MessageType.equalsIgnoreCase(ConnectionConstant.ALARAM_REGISTERED))	
		{
			
			
		}		
		else
		if(MessageType.equalsIgnoreCase(ConnectionConstant.CONFIRMED_TAKEN_MIDICEN))	
		{

		}	
		else
		if(MessageType.equalsIgnoreCase(ConnectionConstant.CANCEL_TAKEN_MIDICEN))	
		{

		}		
		else
		if(MessageType.equalsIgnoreCase(ConnectionConstant.REQUEST_FOR_TAKE_MEDICIEN))	
		{
			confirmForTakenMedicin(node,message);
		}		
		else
		if(MessageType.equalsIgnoreCase(ConnectionConstant.GET_MY_LOCATION))	
		{
			
			
		}		
		
		Toast toast=	Toast.makeText(this, message, 1000*60);
		toast.show();

	}

	
	private void confirmForTakenMedicin(final String node,final String messageContent)
	{
		AlertDialog.Builder alertBuilder=new AlertDialog.Builder(this);
		alertBuilder.setPositiveButton(getString(R.string.ok), new OnClickListener() {

		@Override
		public void onClick(DialogInterface dialog, int which)
		{
			ServiceManger.getInstance(BaseActivity.this, false).sendData(messageContent, ConnectionConstant.CONFIRMED_TAKEN_MIDICEN, node);
		}
		});
		
		alertBuilder.setNegativeButton(getString(R.string.cancel), new OnClickListener()
		{
		@Override
		public void onClick(DialogInterface dialog, int which)
		{
				ServiceManger.getInstance(BaseActivity.this, false).sendData(messageContent, ConnectionConstant.CANCEL_TAKEN_MIDICEN, node);
		}
		});
		alertBuilder.show();
	}
	
	
	@Override
	public void onFileWillReceive(String node, String channel, String fileName,
			String exchangeId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFileProgress(boolean bSend, String node, String channel,
			int progress, String exchangeId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFileCompleted(int reason, String node, String channel,
			String exchangeId, String fileName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onNodeEvent(String node, String channel, boolean bJoined) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onNetworkDisconnected() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUpdateNodeInfo(String nodeName, String ipAddress) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onConnectivityChanged() {
		// TODO Auto-generated method stub
		
	}
    
    
}
