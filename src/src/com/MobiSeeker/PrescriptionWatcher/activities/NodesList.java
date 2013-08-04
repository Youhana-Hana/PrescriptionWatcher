package com.MobiSeeker.PrescriptionWatcher.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.MobiSeeker.PrescriptionWatcher.R;
import com.MobiSeeker.PrescriptionWatcher.connection.ConnectionConstant;
import com.MobiSeeker.PrescriptionWatcher.connection.NodeManager;
import com.MobiSeeker.PrescriptionWatcher.connection.ServiceManger;
import com.MobiSeeker.PrescriptionWatcher.data.Adapter;
import com.MobiSeeker.PrescriptionWatcher.data.CheckListAdapter;
import com.MobiSeeker.PrescriptionWatcher.data.Entry;
import com.MobiSeeker.PrescriptionWatcher.data.NodeObject;
import com.MobiSeeker.PrescriptionWatcher.data.PrescriptionRepository;

import java.util.ArrayList;
import java.util.List;

import roboguice.activity.RoboListActivity;
import roboguice.inject.InjectResource;
import roboguice.inject.InjectView;

public class NodesList extends BaseActivity {

	protected @InjectView(R.id.list)
	ListView list;
	
    private final static String TAG = "com.MobiSeeker.PrescriptionWatcher.activities.Prescriptions";

    protected PrescriptionRepository prescriptionRepository;

    protected CheckListAdapter adapter;
    
    ArrayList<NodeObject> listofNodes;
    @InjectView(R.id.newprescription_button)
    Button newprescription_button;
    
    @InjectResource(R.string.share)
    String share;
    
    Entry presCriptionEntry;
    
    public static void start(Context context) {
        Intent intent = new Intent(context, NodesList.class);
        context.startActivity(intent);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.prescriptions);
        newprescription_button.setText(share);
        this.prescriptionRepository = new PrescriptionRepository();
        listofNodes=new ArrayList<NodeObject>();
        this.adapter = new CheckListAdapter(this, 0, listofNodes);
        presCriptionEntry=(Entry)this.getIntent().getSerializableExtra(ConnectionConstant.PRESCRIPTION_ENTRY);
        
        setCurrentRoboActivity(this);
        
         }

    @Override
    protected void onStart() {
        super.onStart();

        try
        {
        List<String> nodeObjects = ServiceManger.getInstance(this, false,null).getmChordService().getJoinedNodeList(NodeManager.CHORD_API_CHANNEL);
        for(int i=0;i<nodeObjects.size();i++)
        {
        	ServiceManger.getInstance(this, false,null).sendData("", ConnectionConstant.GET_DEVICE_NAME, nodeObjects.get(i));        	
        }
        }
        catch(Exception exception){
            Log.e(NodesList.TAG, "Prescriptions onStart", exception);
        }
    }

    
    public void createPrescription(View view)
    {
    	for(int i=0;i<this.adapter.getCount();i++)
    	{
    		
    		if(this.adapter.getItem(i).isCheck)
    		{
    			ServiceManger.getInstance(this,false,null).sendData(presCriptionEntry.toString(),ConnectionConstant.REQUEST_FOR_REGISTER_ALARAM, this.adapter.getItem(i).nodeName);
    		}
    		
    	}
    	
    }
    

    @Override
    protected void onResume() {
    	// TODO Auto-generated method stub
    	super.onResume();
    	setCurrentRoboActivity(this);
    }
    
    @Override
    public void onReceiveMessage(String node, String channel, String message,String MessageType)
    {
    	NodeObject nodeObject=new NodeObject();
    	nodeObject.nodeName=node;
    	nodeObject.nodeDisplayedValue=message;
    	listofNodes.add(nodeObject);
        this.list.setAdapter(adapter);
  
    }
    
}
