package com.MobiSeeker.PrescriptionWatcher.data;

import android.content.Context;

import com.xtremelabs.robolectric.Robolectric;
import com.xtremelabs.robolectric.RobolectricTestRunner;

import org.joda.time.DateTime;
import org.joda.time.LocalTime;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

import java.sql.Time;
import java.util.Date;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;

@RunWith(RobolectricTestRunner.class)
public class GivenAnEntry {

    private Context context;

    Date startDate;
    Date endDate;
    Time startTime;
    Time endTime;

    @Before
    public void setup() {
        this.context = Robolectric.getShadowApplication().getApplicationContext();

        this.startTime = Time.valueOf("10:00:00");
        this.endTime = Time.valueOf("12:00:00");

        this.startDate = new Date();
        this.endDate = new DateTime(startDate).plusDays(7).toDate();
    }

    @Rule
    public ExpectedException expected = ExpectedException.none();

    @Test
    public void whenConstructingWithNullMedicineNameShouldThrow() {
        expected.expect(UnsupportedOperationException.class);
        expected.expectMessage("Invalid prescription medicine name.");

        Entry entry = new Entry(this.context, "", this.startDate, this.endDate, this.startTime, this.endTime, 2, 3, null);
    }

    @Test
    public void whenConstructingWithEmptyMedicineNameShouldThrow() {
        expected.expect(UnsupportedOperationException.class);
        expected.expectMessage("Invalid prescription medicine name.");

        Entry entry = new Entry(this.context, null, this.startDate, this.endDate, this.startTime, this.endTime, 2, 3, null);
    }

    @Test
    public void whenConstructingWithStartDateAfterEndDateShouldThrow() {
        expected.expect(UnsupportedOperationException.class);
        expected.expectMessage("Invalid prescription end date. Should be after start date.");

        Date startDate = new Date();
        Date endDate = new Date(startDate.getTime()  - (10 * 60 *1000));


        Entry entry = new Entry(this.context, "NAME", startDate, endDate,
                this.startTime, this.endTime, 2, 3, null);
    }

    @Test
    public void whenConstructingWithStartTimeAfterEndTimeShouldThrow() {
        expected.expect(UnsupportedOperationException.class);
        expected.expectMessage("Invalid prescription end time. Should be after start time.");

        Time startTime = Time.valueOf("10:00:00");
        Time endTime = Time.valueOf("09:00:00");

        Entry entry = new Entry(this.context, "NAME", this.startDate, this.endDate, startTime, endTime, 2, 3, null);
    }

    @Test
    public void whenConstructingWithDosageZeroShouldThrow() {
        expected.expect(UnsupportedOperationException.class);
        expected.expectMessage("Invalid prescription dosage. Should be greater than zero.");

        Entry entry = new Entry(this.context, "NAME", this.startDate, this.endDate, startTime, endTime, 0, 3, null);
    }

    @Test
    public void whenConstructingWithDosageLessThanZeroShouldThrow() {
        expected.expect(UnsupportedOperationException.class);
        expected.expectMessage("Invalid prescription dosage. Should be greater than zero.");


        Entry entry = new Entry(this.context, "NAME", this.startDate, this.endDate, startTime, endTime, -1, 3, null);
    }

    @Test
    public void whenConstructingWithTimesPerDayZeroShouldThrow() {
        expected.expect(UnsupportedOperationException.class);
        expected.expectMessage("Invalid prescription times per day. Should be greater than zero.");

        Entry entry = new Entry(this.context, "NAME", this.startDate, this.endDate, startTime, endTime, 2, 0, null);
    }

    @Test
    public void whenConstructingWithTimesPerDayLessThanZeroShouldThrow() {
        expected.expect(UnsupportedOperationException.class);
        expected.expectMessage("Invalid prescription times per day. Should be greater than zero.");

        Entry entry = new Entry(this.context, "NAME", this.startDate, this.endDate, this.startTime, this.endTime, 2, -1, null);
    }

    @Test
    public void whenConstructingShouldNotThrow() {
        Entry entry = new Entry(this.context,"NAME", this.startDate, this.endDate, this.startTime, this.endTime, 2, 3, null);
        assertNotNull(entry);
    }

    @Test
    public void whenCallingGetMedicineNameShouldReturnExpected() {
        Entry entry = new Entry(this.context,"NAME", this.startDate, this.endDate, this.startTime, this.endTime, 2, 3, null);

        assertEquals("NAME", entry.getMedicineName());
     }

    @Test
    public void whenCallingGetStartDateShouldReturnExpected() {
        Entry entry = new Entry(this.context,"NAME", this.startDate, this.endDate, this.startTime, this.endTime, 2, 3, null);

        assertEquals(this.startDate, entry.getStartDate());
    }

    @Test
    public void whenCallingGetEndDateShouldReturnExpected() {
        Entry entry = new Entry(this.context,"NAME", this.startDate, this.endDate, this.startTime, this.endTime, 2, 3, null);

        assertEquals(this.endDate, entry.getEndDate());
    }

    @Test
    public void whenCallingGetStartTimeShouldReturnExpected() {
        Entry entry = new Entry(this.context,"NAME", this.startDate, this.endDate, this.startTime, this.endTime, 2, 3, null);

        assertEquals(this.startTime, entry.getStartTime());
    }

    @Test
    public void whenCallingGetEndTimeShouldReturnExpected() {
        Entry entry = new Entry(this.context,"NAME", this.startDate, this.endDate, this.startTime, this.endTime, 2, 3, null);

        assertEquals(this.endTime, entry.getEndTime());
    }

    @Test
    public void whenCallingGetDosageShouldReturnExpected() {
        Entry entry = new Entry(this.context,"NAME", this.startDate, this.endDate, startTime, endTime, 2, 3, null);

        assertEquals(2.0, entry.getDosage());
    }

    @Test
    public void whenCallingGetCommentShouldReturnExpected() {
        Entry entry = new Entry(this.context,"NAME", this.startDate, this.endDate, startTime, endTime, 2, 3, null);

        assertNull(entry.getComment());
    }

    @Test
    public void whenCallingGetTimesPerDayShouldReturnExpected() {
        Entry entry = new Entry(this.context,"NAME", this.startDate, this.endDate, startTime, endTime, 2, 3, null);

        assertEquals(3, entry.getTimesPerDay());
    }
}
