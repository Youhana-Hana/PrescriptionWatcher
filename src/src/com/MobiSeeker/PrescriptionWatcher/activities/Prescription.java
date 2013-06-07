package com.MobiSeeker.PrescriptionWatcher.activities;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.InputType;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.MobiSeeker.PrescriptionWatcher.Fragments.DatePickerFragment;
import com.MobiSeeker.PrescriptionWatcher.Fragments.TimePickerFragment;
import com.MobiSeeker.PrescriptionWatcher.R;

import org.joda.time.LocalTime;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import roboguice.activity.RoboFragmentActivity;
import roboguice.inject.InjectResource;
import roboguice.inject.InjectView;

public class Prescription extends RoboFragmentActivity implements
        DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener{

    private final static String dateFormat = "dd/MM/yyyy";

    protected EditText selectedTextView = null;

    protected
    @InjectView(R.id.drugName)
    TextView drugName;

    protected
    @InjectView(R.id.comment)
    TextView comment;

    protected
    @InjectView(R.id.timesPerDay)
    TextView timerPerDay;

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

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.prescription);
        this.setStartDate();
        this.setEndtDate();
        this.setStartTime();
        this.setEndTime();
    }

    public void showDatePicker(View view) {
        this.selectedTextView = (EditText)view;

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
        this.selectedTextView = (EditText)view;

        LocalTime localTime = LocalTime.parse(this.selectedTextView.getText().toString());

        DialogFragment datePickerFragment = new TimePickerFragment();
        Bundle bundle = new Bundle(2);
        bundle.putInt("hour", localTime.getHourOfDay());
        bundle.putInt("minute", localTime.getMinuteOfHour());

        datePickerFragment.setArguments(bundle);
        datePickerFragment.show(getSupportFragmentManager(), "TimePicker");
    }

    public void save(View view) {

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
            date = DateFormat.getDateInstance(DateFormat.DEFAULT).parse(editText.getText().toString());
        }
        catch(ParseException exception) {
            date = new Date();
        }

        calendar.setTime(date);
        return calendar;
    }

    private void updateDate(EditText view, Calendar calendar) {
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.HOUR_OF_DAY);

        String formattedDate = DateFormat.getDateInstance(DateFormat.DEFAULT).format(calendar.getTime());
        view.setText(formattedDate);
    }
}