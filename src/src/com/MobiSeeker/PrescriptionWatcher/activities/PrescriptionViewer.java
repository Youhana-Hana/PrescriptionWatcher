package com.MobiSeeker.PrescriptionWatcher.activities;

import java.text.DateFormat;

import org.joda.time.LocalTime;

import roboguice.inject.InjectView;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.MobiSeeker.PrescriptionWatcher.R;
import com.MobiSeeker.PrescriptionWatcher.connection.ConnectionConstant;
import com.MobiSeeker.PrescriptionWatcher.data.Entry;

public class PrescriptionViewer extends BaseActivity  {

    protected final static String TAG = "com.MobiSeeker.PrescriptionWatcher.activities.PrescriptionViewer";

    protected
    @InjectView(R.id.drugName)
    TextView drugName;

    protected
    @InjectView(R.id.comment)
    TextView comment;

    protected
    @InjectView(R.id.timesPerDay)
    TextView timesPerDay;

    protected
    @InjectView(R.id.dosage)
    TextView dosage;

    protected
    @InjectView(R.id.startDate)
    TextView startDate;

    protected
    @InjectView(R.id.endDate)
    TextView endDate;

    protected
    @InjectView(R.id.startTime)
    TextView startTime;

    protected
    @InjectView(R.id.endTime)
    TextView endTime;

    protected
    @InjectView(R.id.image)
    ImageView image;
    
 
    public static void start(Context context, Entry entry) {
        Intent intent = new Intent(context, Prescription.class);
        intent.putExtra("entry", entry);
        context.startActivity(intent);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.entryviewer);
        Intent intent = getIntent();
        Entry entry = (Entry)intent.getSerializableExtra(ConnectionConstant.PRESCRIPTION_ENTRY);
        
        initFromEntry(entry);
        setCurrentRoboActivity(this);
        
        
    }
    
    @Override
    protected void onRestart() {
    	super.onRestart();
    	setCurrentRoboActivity(this);
    }

    @Override
    protected void onResume() {
    	super.onResume();
    	setCurrentRoboActivity(this);
    }

    private void initFromEntry(Entry entry) {
    	runNotification();
        this.drugName.setText(entry.getMedicineName());
        this.dosage.setText(String.valueOf(entry.getDosage()));
        this.timesPerDay.setText(String.valueOf(entry.getTimesPerDay()));

        String entryStartDate = DateFormat.getDateInstance(DateFormat.MEDIUM).format(entry.getStartDate());
        startDate.setText(entryStartDate);

        String entryEndDate = DateFormat.getDateInstance(DateFormat.MEDIUM).format(entry.getEndDate());
        startDate.setText(entryEndDate);

        LocalTime localStartTime = new LocalTime(entry.getStartTime());
        startTime.setText(localStartTime.toString("HH:mm:ss"));

        LocalTime localEndTime = new LocalTime(entry.getEndTime());
        endTime.setText(localEndTime.toString("HH:mm:ss"));

        String imagePath = entry.getPrescriptionImagePath();

        if (imagePath != null && !imagePath.isEmpty()) {
            image.setImageURI(Uri.parse(imagePath));
        }
    }
    
    Ringtone r ;
    
	public void runNotification()
	{
		Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
		r = RingtoneManager.getRingtone(getApplicationContext(), notification);
		r.play();
		
	}

	@Override
	public void finish() {
		// TODO Auto-generated method stub
		super.finish();
		stop();
	}
	
	public void stop()
	{
		if(r!=null&&r.isPlaying())
		r.stop();
	}
	
	
}