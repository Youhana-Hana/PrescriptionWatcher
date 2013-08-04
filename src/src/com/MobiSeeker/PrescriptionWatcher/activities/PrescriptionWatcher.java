package com.MobiSeeker.PrescriptionWatcher.activities;

import java.sql.Time;
import java.util.Date;
import java.util.List;

import org.w3c.dom.NodeList;

import android.content.Intent;
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
        setCurrentRoboActivity(this);
    }
    @Override
    protected void onRestart() {
    	// TODO Auto-generated method stub
    	
    	super.onRestart();
    	setCurrentRoboActivity(this);
    }

    @Override
    protected void onResume() {
    	// TODO Auto-generated method stub
    	super.onResume();
    	setCurrentRoboActivity(this);
    }

    public void viewPrescriptions(View view) {
        Prescriptions.start(this);
    }

    public void viewContacts(View view) {

    }

    public void viewSettings(View view) {
        Settings.start(this);
    }
    
    public void testConnection(View view)
    {
//    	Date date=new Date();
//    	Entry entery=new Entry(this,"Panadoool",date,date,new Time(date.getTime()),new Time(date.getTime()),2d,2,"Panadool",ConnectionConstant.MY_PRESCRIPTION,Utilites.getDeviceImei(this));
//    	AlarmSetterObject.setAlaram(this, entery);
//    	
//    	manger.sendDataToAll("welcome",ConnectionConstant.SEND_MESSAGE);
//    	List<IChordChannel> ioChennels=	manger.getListOfChannels();
//    	List<String> NoteList=manger.getmChordService().getJoinedNodeList(NodeManager.CHORD_API_CHANNEL);
//    	manger.sendPrescriptionToWatcher(entery, null);
//    	System.out.println(NoteList);
//    	Date date=new Date();
//    	Entry entery=new Entry(this,"Panadoool",date,date,new Time(date.getTime()),new Time(date.getTime()),2d,2,"Panadool",ConnectionConstant.MY_PRESCRIPTION,Utilites.getDeviceImei(this), null);
//    	AlarmSetterObject.setAlaram(this, entery);
    	
    }

        
}
