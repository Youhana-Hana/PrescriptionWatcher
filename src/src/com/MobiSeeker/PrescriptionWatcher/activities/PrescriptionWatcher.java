package com.MobiSeeker.PrescriptionWatcher.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.MobiSeeker.PrescriptionWatcher.R;
import com.MobiSeeker.PrescriptionWatcher.connection.ConnectionConstant;
import com.MobiSeeker.PrescriptionWatcher.connection.ServiceManger;

import roboguice.activity.RoboActivity;

public class PrescriptionWatcher extends BaseActivity {

	ServiceManger manger;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        manger=new ServiceManger(this);
        manger.startService();
        manger.bindChordService();
    }

    public void viewPrescriptions(View view) {
        Prescriptions.start(this);
    }

    public void viewContacts(View view) {

    }

    public void viewSettings(View view) {

    }
    
    public void testConnection(View view)
    {
        manger.sendDataToAll("welcome",ConnectionConstant.SEND_MESSAGE);
    	
    }

	@Override
	public void onReceiveMessage(String node, String channel, String message) {
		// TODO Auto-generated method stub
		Toast toast=	Toast.makeText(this, message, 1000*60);
		toast.show();

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
