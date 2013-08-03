package com.MobiSeeker.PrescriptionWatcher.activities;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.DialogFragment;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.MobiSeeker.PrescriptionWatcher.Fragments.DatePickerFragment;
import com.MobiSeeker.PrescriptionWatcher.Fragments.TimePickerFragment;
import com.MobiSeeker.PrescriptionWatcher.R;
import com.MobiSeeker.PrescriptionWatcher.connection.ConnectionConstant;
import com.MobiSeeker.PrescriptionWatcher.data.AlarmSetterObject;
import com.MobiSeeker.PrescriptionWatcher.data.Entry;
import com.MobiSeeker.PrescriptionWatcher.data.PrescriptionRepository;
import com.MobiSeeker.PrescriptionWatcher.data.Utilites;

import org.joda.time.LocalTime;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import roboguice.inject.InjectResource;
import roboguice.inject.InjectView;

public class PrescriptionViewer extends BaseActivity  {

    protected final static String TAG = "com.MobiSeeker.PrescriptionWatcher.activities.Prescription";

    public static void start(Context context) {
        Intent intent = new Intent(context, PrescriptionViewer.class);
        context.startActivity(intent);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.entryviewer);

        init();
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
    
    private void init() {
       
    }

    private void initFromEntry(Entry entry) {
       
    }

   
}