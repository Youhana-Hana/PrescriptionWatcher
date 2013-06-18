package com.MobiSeeker.PrescriptionWatcher.activities;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.MobiSeeker.PrescriptionWatcher.Fragments.DatePickerFragment;
import com.MobiSeeker.PrescriptionWatcher.Fragments.TimePickerFragment;
import com.MobiSeeker.PrescriptionWatcher.R;
import com.MobiSeeker.PrescriptionWatcher.data.Entry;
import com.MobiSeeker.PrescriptionWatcher.data.PrescriptionRepository;

import org.joda.time.LocalTime;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import roboguice.activity.RoboFragmentActivity;
import roboguice.inject.InjectResource;
import roboguice.inject.InjectView;

public class Prescription extends RoboFragmentActivity implements
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

    public static void start(Context context) {
        Intent intent = new Intent(context, Prescription.class);
        context.startActivity(intent);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.prescription);
        init();

        this.prescriptionRepository = new PrescriptionRepository();
    }

    private void init() {
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
                            LocalTime.parse(this.startTime.getText().toString()),
                            LocalTime.parse(this.endTime.getText().toString()),
                            this.getDosage(),
                            this.getTimesPerDay(),
                            this.comment.getText().toString());

            this.prescriptionRepository.save(this, entry);
            Toast.makeText(this, this.addingPrescriptionSucceeded, Toast.LENGTH_SHORT).show();
        } catch (UnsupportedOperationException exception) {
            Toast.makeText(this, addingPrescriptionFailed + " " + exception.getMessage(), Toast.LENGTH_LONG).show();
            Log.e(Prescription.TAG, "Failed to save prescription", exception);
        } catch (Exception exception) {
            Log.e(Prescription.TAG, "Failed to save prescription", exception);
        }
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

    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        Calendar calendar = this.getTodayCalendar();
        calendar.set(year, month, day);
        this.updateDate(this.selectedTextView, calendar);
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        LocalTime localTime = new LocalTime(hourOfDay, minute);
        selectedTextView.setText(localTime.toString("HH:mm"));
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

}