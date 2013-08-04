package com.MobiSeeker.PrescriptionWatcher.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.MobiSeeker.PrescriptionWatcher.R;
import com.MobiSeeker.PrescriptionWatcher.connection.ConnectionConstant;
import com.MobiSeeker.PrescriptionWatcher.connection.ServiceManger;
import com.MobiSeeker.PrescriptionWatcher.data.Entry;

public class PrescriptionWatcher extends BaseActivity {

	 
	Entry prescriptionEntry;
	ServiceManger manger;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        manger=ServiceManger.getInstance(this,true);
        manger.startService();
        manger.bindChordService();
        setCurrentRoboActivity(this);
        prescriptionEntry=(Entry)this.getIntent().getSerializableExtra(ConnectionConstant.MY_PRESCRIPTION);
        if(prescriptionEntry!=null)
        {
    		Intent intent=new Intent(this,PrescriptionViewer.class);
    		intent.putExtra(ConnectionConstant.PRESCRIPTION_ENTRY, prescriptionEntry);
    		intent.setFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);                     
    		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    		startActivity(intent);

        }
        
        
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
