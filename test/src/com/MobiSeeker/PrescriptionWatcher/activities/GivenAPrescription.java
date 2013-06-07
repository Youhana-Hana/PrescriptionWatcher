package com.MobiSeeker.PrescriptionWatcher.activities;

import android.content.Context;
import android.widget.TextView;

import com.xtremelabs.robolectric.Robolectric;
import com.xtremelabs.robolectric.RobolectricTestRunner;
import com.xtremelabs.robolectric.shadows.ShadowApplication;
import com.xtremelabs.robolectric.shadows.ShadowDialog;
import com.xtremelabs.robolectric.shadows.ShadowEditText;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

@RunWith(RobolectricTestRunner.class)
public class GivenAPrescription {

    private ShadowApplication shadowApplication;
    private Context context;

    Prescription activity;

    @Before
    public void setUp() throws Exception {
        this.shadowApplication = Robolectric.getShadowApplication();
        this.context = shadowApplication.getApplicationContext();

        this.activity = new Prescription();
        this.activity.onCreate(null);
    }

    @Test
    public void whenCallingOnCreateShouldNotThrow() {

        assertNotNull(this.activity);
    }

    @Test
    public void whenCallingOnCreateShouldRenderLayout() {
        assertNotNull(this.activity.drugName);
        assertNotNull(this.activity.comment);
        assertNotNull(this.activity.startDate);
        assertNotNull(this.activity.endDate);
        assertNotNull(this.activity.startTime);
        assertNotNull(this.activity.endTime);
    }

    @Test
    public void startDateShouldShowsToday() {
        Date today = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);

        String formattedDate = DateFormat.getDateInstance(DateFormat.DEFAULT).format(calendar.getTime());

        assertEquals(formattedDate, this.activity.startDate.getText().toString());
    }

    @Test
    public void endDateShouldShowsNextWeek() {
        Date today = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);
        calendar.add(Calendar.DATE, 7);

        String formattedDate = DateFormat.getDateInstance(DateFormat.DEFAULT).format(calendar.getTime());
        assertEquals(formattedDate, this.activity.endDate.getText().toString());
    }

    @Test
    public void startTimeShouldShowsSevenAM() {
        assertEquals("07:00", this.activity.startTime.getText().toString());
    }

    @Test
    public void endTimeShouldShowsSevenAM() {
        assertEquals("22:00", this.activity.endTime.getText().toString());
    }

    @Test
    public void whenCallingShowDatePickerShouldShowsCorrectDate() {
        this.activity.showDatePicker(this.activity.startDate);

        ShadowDialog dialog =  Robolectric.getShadowApplication().getLatestDialog();
        assertNotNull(dialog);

        assertTrue(dialog.isShowing());
    }

    @Test
    public void whenCallingShowTimePickerShouldShowsCorrectDate() {
        this.activity.showDatePicker(this.activity.startTime);

        ShadowDialog dialog =  Robolectric.getShadowApplication().getLatestDialog();
        assertNotNull(dialog);

        assertTrue(dialog.isShowing());
    }

    @Test
    public void whenCallingonDateSetShouldUpdateSelectedDate() {
        this.activity.showDatePicker(this.activity.startDate);

        this.activity.onDateSet(null, 1979, Calendar.MARCH, 31);

        assertEquals("Mar 31, 1979", this.activity.startDate.getText().toString());
    }

    @Test
    public void whenCallingonDateSetAndStartDateIsEmptyShouldShowNow() {
        this.activity.startDate.setText("");
        this.activity.showDatePicker(this.activity.startDate);

        this.activity.onDateSet(null, 1979, Calendar.MARCH, 31);

        assertEquals("Mar 31, 1979", this.activity.startDate.getText().toString());
    }

    @Test
    public void whenCallingOnTimeSetShouldUpdateSelectedTimeForStartTime() {
        this.activity.showTimePicker(this.activity.startTime);

        this.activity.onTimeSet(null, 10, 50);

        assertEquals("10:50", this.activity.startTime.getText().toString());
    }

    @Test
    public void whenCallingOnTimeSetShouldUpdateSelectedTimeForEndTime() {
        this.activity.showTimePicker(this.activity.endTime);

        this.activity.onTimeSet(null, 22, 50);

        assertEquals("22:50", this.activity.endTime.getText().toString());
    }

    @Test
    public void whenCallingSaveShouldNotThrow() {
        this.activity.save(null);
    }

    @Test
    public void whenCallingCancelShouldNotThrow() {
        this.activity.cancel(null);
    }
}
