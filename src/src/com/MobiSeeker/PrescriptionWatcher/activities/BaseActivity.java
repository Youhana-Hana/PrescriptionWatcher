package com.MobiSeeker.PrescriptionWatcher.activities;

import java.util.HashMap;
import roboguice.activity.RoboFragmentActivity;
import android.R;
import android.accounts.AccountManager;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toast;

import com.MobiSeeker.PrescriptionWatcher.connection.ConnectionConstant;
import com.MobiSeeker.PrescriptionWatcher.connection.IChordServiceListener;
import com.MobiSeeker.PrescriptionWatcher.connection.ServiceManger;
import com.MobiSeeker.PrescriptionWatcher.data.AlarmSetterObject;
import com.MobiSeeker.PrescriptionWatcher.data.Entry;
import com.MobiSeeker.PrescriptionWatcher.data.EntryMangement;
import com.MobiSeeker.PrescriptionWatcher.data.Settings;
import com.google.gson.Gson;

public abstract class BaseActivity extends RoboFragmentActivity implements
		IChordServiceListener {

	static RoboFragmentActivity currentRoboActivity;

	public static void setCurrentRoboActivity(
			RoboFragmentActivity _currentRoboActivity) {
		currentRoboActivity = _currentRoboActivity;
	}

	public static HashMap<String, String> Nodes = new HashMap<String, String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
//		this.requestWindowFeature(Window.FEATURE_NO_TITLE);

		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
	}

	@Override
	public void onReceiveMessage(String node, String channel, String message,
			String MessageType) {
		// TODO Auto-generated method stub

		if (MessageType.equalsIgnoreCase(ConnectionConstant.SEND_MESSAGE)) {

		} else

		if (MessageType.equalsIgnoreCase(ConnectionConstant.GET_DEVICE_NAME)) {
			try {
				ServiceManger.getInstance(this, false,null).sendData(new Settings(this).getUserName()+" ( "+
						AccountManager.get(this).getAccounts()[0].name+" )",
						ConnectionConstant.MY_DEVICE_NAME, node);

			} catch (Exception ee) {
				ServiceManger.getInstance(this, false,null).sendData(
						android.os.Build.MODEL,
						ConnectionConstant.MY_DEVICE_NAME, node);
			}
		} else

		if (MessageType.equalsIgnoreCase(ConnectionConstant.MY_DEVICE_NAME)) {
			if(currentRoboActivity instanceof NodesList)
			{
				((NodesList) currentRoboActivity).onReceiveMessage(node, channel, message, MessageType);
			}
		}

		else if (MessageType
				.equalsIgnoreCase(ConnectionConstant.REQUEST_FOR_REGISTER_ALARAM)) {
			Intent intent=new Intent(this,PrescriptionWatcher.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			intent.putExtra("takenmedcine", false);
			intent.putExtra("requestforalaram", true);
			intent.putExtra("node", node);
			intent.putExtra("message", message);
			startActivity(intent);
		

		//	confirmToRegisterAlarmForPrescription(message);
		} else if (MessageType.equalsIgnoreCase(ConnectionConstant.ALARAM_REGISTERED))
		{
			
		} else if (MessageType
				.equalsIgnoreCase(ConnectionConstant.PLEAECONFIRME_TAKEN_MIDICEN)) {
			Intent intent=new Intent(this,PrescriptionWatcher.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			intent.putExtra("takenmedcine", true);
			intent.putExtra("requestforalaram", false);

			intent.putExtra("node", node);
			intent.putExtra("message", message);
			startActivity(intent);
		
			confirmForTakenMedicin(node, message);
			
		} else if (MessageType
				.equalsIgnoreCase(ConnectionConstant.CONFIRMED_TAKEN_MIDICEN))
		{
			Toast toast = Toast.makeText(this, this.getString(com.MobiSeeker.PrescriptionWatcher.R.string.thanksforinterest), 10000 * 60);
			toast.show();

		}else if (MessageType
				.equalsIgnoreCase(ConnectionConstant.CANCEL_TAKEN_MIDICEN)) {
			cancelForTakenMedicin(node, message);
		} else if (MessageType
				.equalsIgnoreCase(ConnectionConstant.REQUEST_FOR_TAKE_MEDICIEN)) {
			confirmForTakenMedicin(node, message);
		} else if (MessageType
				.equalsIgnoreCase(ConnectionConstant.GET_MY_LOCATION)) {

		}
		
//		Toast toast = Toast.makeText(this, message, 1000 * 60);
//		toast.show();

	}
	
	
	public void checkActivity()
	{
		try{
		if(currentRoboActivity!=null&&currentRoboActivity.isFinishing())
		{
			Intent intent=new Intent(this,PrescriptionWatcher.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(intent);
			
		}

		}catch(Exception ee){ee.printStackTrace();}
	}
	Ringtone r;
	Uri notification;
	public void runNotification()
	{
		if(notification==null)
		 notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
		if(r==null)
		r = RingtoneManager.getRingtone(getApplicationContext(), notification);
		if(!r.isPlaying())
		r.play();
		
		
	}
	
	public void stopAlaram()
	{
		
	}
	
	public void confirmToRegisterAlarmForPrescription(
			final String prescriptionEntryString) {

		checkActivity();
		if(!new Settings(currentRoboActivity).isWatchingPrescriptions())
			return;
		runNotification();
		final Entry prescriptionEntry = new Gson().fromJson(
				prescriptionEntryString, Entry.class);

		AlertDialog.Builder alertBuilder = new AlertDialog.Builder(
				currentRoboActivity);
		alertBuilder.setTitle(prescriptionEntry.getUsername());
	alertBuilder.setMessage(currentRoboActivity.getString(com.MobiSeeker.PrescriptionWatcher.R.string.acceptmedience));
		alertBuilder.setPositiveButton(getString(R.string.ok),
				new OnClickListener() {
			
					@Override
					public void onClick(DialogInterface dialog, int which) {
						prescriptionEntry.setPrescriptionType(ConnectionConstant.PRESCRIPTION_WATCHER);
						new EntryMangement().saveEntry(prescriptionEntry,
								currentRoboActivity);
						AlarmSetterObject.setAlaram(currentRoboActivity,
								prescriptionEntry);
						
					}
				});

		alertBuilder.setNegativeButton(getString(R.string.cancel),
				new OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						stopAlaram();
					}
				});
		alertBuilder.show();

	}

	public void confirmForTakenMedicin(final String node,
			final String messageContent) {
		checkActivity();
		runNotification();
		AlertDialog.Builder alertBuilder = new AlertDialog.Builder(
				currentRoboActivity);
		alertBuilder.setMessage(currentRoboActivity.getString(com.MobiSeeker.PrescriptionWatcher.R.string.dearcustomerconfirm));

		alertBuilder.setPositiveButton(getString(R.string.ok),
				new OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						stopAlaram();
						ServiceManger
								.getInstance(BaseActivity.this, false,null)
								.sendData(
										messageContent,
										ConnectionConstant.CONFIRMED_TAKEN_MIDICEN,
										node);
						
					}
				});

		alertBuilder.setNegativeButton(getString(R.string.cancel),
				new OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						stopAlaram();
						ServiceManger
								.getInstance(BaseActivity.this, false,null)
								.sendData(
										messageContent,
										ConnectionConstant.CANCEL_TAKEN_MIDICEN,
										node);
					}
				});
		alertBuilder.show();
	}

	

	private void cancelForTakenMedicin(final String node,
			final String messageContent) {
		checkActivity();

		AlertDialog.Builder alertBuilder = new AlertDialog.Builder(
				currentRoboActivity);
		alertBuilder.setMessage(currentRoboActivity.getString(com.MobiSeeker.PrescriptionWatcher.R.string.cancelmedicine));

		alertBuilder.setPositiveButton(getString(R.string.ok),
				new OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						stopAlaram();
					}
				});

		alertBuilder.show();
	}

	
	@Override
	public void finish() {
		// TODO Auto-generated method stub
		super.finish();
		stopAlaram();
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
