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

public class Prescription extends BaseActivity implements
        DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    protected final static String TAG = "com.MobiSeeker.PrescriptionWatcher.activities.Prescription";
    protected EditText selectedTextView = null;

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
    EditText startDate;

    protected
    @InjectView(R.id.endDate)
    EditText endDate;

    protected
    @InjectView(R.id.startTime)
    EditText startTime;

    protected
    @InjectView(R.id.endTime)
    EditText endTime;

    protected
    @InjectView(R.id.buttonDelete)
    Button buttonDelete;

    protected
    @InjectView(R.id.image)
    ImageView image;

    protected
    @InjectResource(R.string.defaultStartTime)
    String defaultStartTime;

    protected
    @InjectResource(R.string.defaultEndTime)
    String defaultEndTime;

    protected
    @InjectResource(R.string.addingPrescriptionSucceeded)
    String addingPrescriptionSucceeded;

    protected
    @InjectResource(R.string.addingPrescriptionFailed)
    String addingPrescriptionFailed;

    protected PrescriptionRepository prescriptionRepository;

    private final int REQ_CODE_PICK_IMAGE = 1;
    private String imagePath = null;

    public static void start(Context context) {
        Intent intent = new Intent(context, Prescription.class);
        context.startActivity(intent);
    }

    public static void start(Context context, Entry entry) {
        Intent intent = new Intent(context, Prescription.class);
        intent.putExtra("entry", entry);
        context.startActivity(intent);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.prescription);

        init();
        setCurrentRoboActivity(this);
        this.prescriptionRepository = new PrescriptionRepository();
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
        Intent intent = getIntent();
        Entry entry = (Entry)intent.getSerializableExtra("entry");
        if (entry == null) {
           this.initDefaults();
        } else {
            buttonDelete.setVisibility(View.VISIBLE);
            buttonDelete.setTag(entry);
            this.initFromEntry(entry);
        }
    }

    private void initFromEntry(Entry entry) {
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

        this.imagePath = entry.getPrescriptionImagePath();

        if (this.imagePath != null && !this.imagePath.isEmpty()) {
            image.setImageURI(Uri.parse(this.imagePath));
        }
    }

    private void initDefaults() {
        this.setStartDate();
        this.setEndtDate();
        this.setStartTime();
        this.setEndTime();
        this.dosage.setText("3");
        this.timesPerDay.setText("3");
    }

    public void showDatePicker(View view) {
        this.selectedTextView = (EditText) view;

        final Calendar calendar = this.getCalendarFromControl(this.selectedTextView);

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        DialogFragment datePickerFragment = new DatePickerFragment();
        Bundle bundle = new Bundle(3);
        bundle.putInt("year", year);
        bundle.putInt("month", month);
        bundle.putInt("dayOfMonth", dayOfMonth);

        datePickerFragment.setArguments(bundle);

        datePickerFragment.show(getSupportFragmentManager(), "DatePicker");
    }

    public void showTimePicker(View view) {
        this.selectedTextView = (EditText) view;

        LocalTime localTime = LocalTime.parse(this.selectedTextView.getText().toString());

        DialogFragment datePickerFragment = new TimePickerFragment();
        Bundle bundle = new Bundle(2);
        bundle.putInt("hour", localTime.getHourOfDay());
        bundle.putInt("minute", localTime.getMinuteOfHour());

        datePickerFragment.setArguments(bundle);
        datePickerFragment.show(getSupportFragmentManager(), "TimePicker");
    }

    public void save(View view) {
        PrescriptionRepository prescriptionRepository = new PrescriptionRepository();
        try {
            Entry entry =
                    new Entry(this, this.drugName.getText().toString(),
                            this.getDateFromControl(this.startDate),
                            this.getDateFromControl(this.endDate),
                            Time.valueOf(this.startTime.getText().toString()),
                            Time.valueOf(this.endTime.getText().toString()),
                            this.getDosage(),
                            this.getTimesPerDay(),
                            this.comment.getText().toString(),
                            ConnectionConstant.MY_PRESCRIPTION,
                            Utilites.getDeviceImei(this),
                            this.imagePath);

            this.prescriptionRepository.save(this, entry);
            AlarmSetterObject.setAlaram(this,entry);
            Toast.makeText(this, this.addingPrescriptionSucceeded, Toast.LENGTH_SHORT).show();
            this.LaunchPrescriptions();
        } catch (UnsupportedOperationException exception) {
            Toast.makeText(this, addingPrescriptionFailed + " " + exception.getMessage(), Toast.LENGTH_LONG).show();
            Log.e(Prescription.TAG, "Failed to save prescription", exception);
        } catch (Exception exception) {
            Log.e(Prescription.TAG, "Failed to save prescription", exception);
        }
    }

    private void LaunchPrescriptions() {
        Prescriptions.start(this);
        this.finish();
    }

    private int getTimesPerDay() {
        try {
            return Integer.parseInt(this.timesPerDay.getText().toString());
        } catch (NumberFormatException exception) {
            return 0;
        }
    }

    private double getDosage() {
        try {
            return Double.parseDouble(this.dosage.getText().toString());
        } catch (NumberFormatException exception) {
            return 0.0;
        }
    }


    public void cancel(View view) {
        this.LaunchPrescriptions();
    }

    public void delete(View view) {
        Entry entry = (Entry) buttonDelete.getTag();
        try {
        this.prescriptionRepository.delete(this, entry);
        } catch(Exception e)
        {
            Log.e(Prescription.TAG, e.getMessage(), e);
        }

        this.LaunchPrescriptions();
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        Calendar calendar = this.getTodayCalendar();
        calendar.set(year, month, day);
        this.updateDate(this.selectedTextView, calendar);
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        LocalTime localTime = new LocalTime(hourOfDay, minute);
        selectedTextView.setText(localTime.toString("HH:mm:ss"));
    }

    private void setStartDate() {
        Calendar calendar = this.getTodayCalendar();

        this.updateDate(this.startDate, calendar);
        this.startDate.setInputType(InputType.TYPE_NULL);
    }

    private void setEndtDate() {
        Calendar calendar = this.getTodayCalendar();

        calendar.add(Calendar.DATE, 7);

        this.updateDate(this.endDate, calendar);
        this.endDate.setInputType(InputType.TYPE_NULL);
    }

    private void setStartTime() {
        this.startTime.setText(this.defaultStartTime);
        LocalTime localTime = new LocalTime(new Date());
        startTime.setText(localTime.toString("HH:mm:ss"));

    }

    private void setEndTime() {
        this.endTime.setText(this.defaultEndTime);
    }

    private Calendar getTodayCalendar() {
        Date today = new Date();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);
        return calendar;
    }

    private Calendar getCalendarFromControl(EditText editText) {
        Calendar calendar = Calendar.getInstance();
        Date date = null;

        try {
            date = DateFormat.getDateInstance(DateFormat.MEDIUM).parse(editText.getText().toString());
        } catch (ParseException exception) {
            date = new Date();
        }

        calendar.setTime(date);
        return calendar;
    }

    private void updateDate(EditText view, Calendar calendar) {
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        String formattedDate = DateFormat.getDateInstance(DateFormat.MEDIUM).format(calendar.getTime());
        view.setText(formattedDate);
    }

    private Date getDateFromControl(EditText editText) {
        Date date = null;

        try {
            return DateFormat.getDateInstance(DateFormat.MEDIUM).parse(editText.getText().toString());
        } catch (ParseException exception) {
            Log.w(Prescription.TAG, exception.getMessage(), exception);
        }

        return null;
    }

	@Override
	public void onReceiveMessage(String node, String channel, String message,String MessageType) {
		super.onReceiveMessage(node, channel, message,MessageType);
    }

	@Override
	public void onFileWillReceive(String node, String channel, String fileName,
			String exchangeId) {
		super.onFileWillReceive(node, channel, fileName, exchangeId);
	}

	@Override
	public void onFileProgress(boolean bSend, String node, String channel,
			int progress, String exchangeId) {
		super.onFileProgress(bSend, node, channel, progress, exchangeId);
	}

	@Override
	public void onFileCompleted(int reason, String node, String channel,
			String exchangeId, String fileName) {
		super.onFileCompleted(reason, node, channel, exchangeId, fileName);
	}

	@Override
	public void onNodeEvent(String node, String channel, boolean bJoined) {
		super.onNodeEvent(node, channel, bJoined);
	}

	@Override
	public void onNetworkDisconnected() {
		super.onNetworkDisconnected();
	}

	@Override
	public void onUpdateNodeInfo(String nodeName, String ipAddress) {
		super.onUpdateNodeInfo(nodeName, ipAddress);
	}

	@Override
	public void onConnectivityChanged() {
		super.onConnectivityChanged();
	}

    public void getImage(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, REQ_CODE_PICK_IMAGE);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {

        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

        switch(requestCode) {
            case REQ_CODE_PICK_IMAGE:

                if(resultCode != RESULT_OK) {
                    return;
                }

                Uri selectedImage = imageReturnedIntent.getData();
                String[] filePathColumn = {android.provider.MediaStore.Images.Media.DATA};

                Cursor cursor = getContentResolver().query(
                        selectedImage, filePathColumn, null, null, null);

                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                this.imagePath = cursor.getString(columnIndex);
                cursor.close();

                image.setImageBitmap(BitmapFactory.decodeFile(this.imagePath));
        }
    }
}