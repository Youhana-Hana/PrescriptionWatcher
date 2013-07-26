package com.MobiSeeker.PrescriptionWatcher.activities;

import java.sql.Time;
import java.util.Date;
import java.util.List;

import android.os.Bundle;
import android.view.View;

import com.MobiSeeker.PrescriptionWatcher.R;
import com.MobiSeeker.PrescriptionWatcher.connection.ConnectionConstant;
import com.MobiSeeker.PrescriptionWatcher.connection.NodeManager;
import com.MobiSeeker.PrescriptionWatcher.connection.ServiceManger;
import com.MobiSeeker.PrescriptionWatcher.data.AlarmSetterObject;
import com.MobiSeeker.PrescriptionWatcher.data.Entry;
import com.MobiSeeker.PrescriptionWatcher.data.Utilites;
import com.samsung.chord.IChordChannel;

public class PrescriptionWatcher extends BaseActivity {

	ServiceManger manger;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        manger=ServiceManger.getInstance(this,true);
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
   
    	
    	Date date=new Date();
    	Entry entery=new Entry(this,"Panadoool",date,date,new Time(date.getTime()),new Time(date.getTime()),2d,2,"Panadool",ConnectionConstant.MY_PRESCRIPTION,Utilites.getDeviceImei(this));
    	AlarmSetterObject.setAlaram(this, entery, date, date, "");
    	
    	manger.sendDataToAll("welcome",ConnectionConstant.SEND_MESSAGE);
    	List<IChordChannel> ioChennels=	manger.getListOfChannels();
    	List<String> NoteList=manger.getmChordService().getJoinedNodeList(NodeManager.CHORD_API_CHANNEL);
    	manger.sendPrescriptionToWatcher(entery, null);
    	System.out.println(NoteList);
    	
    	
    }

    
    
	@Override
	public void onReceiveMessage(String node, String channel, String message,String MessageType) {
		// TODO Auto-generated method stub
		
		super.onReceiveMessage(node, channel, message,MessageType);

	}

	@Override
	public void onFileWillReceive(String node, String channel, String fileName,
			String exchangeId) {
		// TODO Auto-generated method stub
		super.onFileWillReceive(node, channel, fileName, exchangeId);
	}

	@Override
	public void onFileProgress(boolean bSend, String node, String channel,
			int progress, String exchangeId) {
		// TODO Auto-generated method stub
		super.onFileProgress(bSend, node, channel, progress, exchangeId);
	}

	@Override
	public void onFileCompleted(int reason, String node, String channel,
			String exchangeId, String fileName) {
		// TODO Auto-generated method stub
		super.onFileCompleted(reason, node, channel, exchangeId, fileName);
	}

	@Override
	public void onNodeEvent(String node, String channel, boolean bJoined) {
		// TODO Auto-generated method stub
		super.onNodeEvent(node, channel, bJoined);
	}

	@Override
	public void onNetworkDisconnected() {
		// TODO Auto-generated method stub
		super.onNetworkDisconnected();
	}

	@Override
	public void onUpdateNodeInfo(String nodeName, String ipAddress) {
		// TODO Auto-generated method stub
		super.onUpdateNodeInfo(nodeName, ipAddress);
	}

	@Override
	public void onConnectivityChanged() {
		// TODO Auto-generated method stub
		super.onConnectivityChanged();
	}
    
}
