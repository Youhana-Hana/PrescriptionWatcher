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

import java.util.Date;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;

@RunWith(RobolectricTestRunner.class)
public class GivenAnEntry {

    private Context context;

    @Before
    public void setup() {
        this.context = Robolectric.getShadowApplication().getApplicationContext();
    }

    @Rule
    public ExpectedException expected = ExpectedException.none();

    @Test
    public void whenConstructingWithNullMedicineNameShouldThrow() {
        expected.expect(UnsupportedOperationException.class);
        expected.expectMessage("Invalid prescription medicine name.");

        Entry entry = new Entry(this.context, "", new Date(), new Date(), LocalTime.parse("10:00"), LocalTime.parse("12:00"), 2, 3, null);
    }

    @Test
    public void whenConstructingWithEmptyMedicineNameShouldThrow() {
        expected.expect(UnsupportedOperationException.class);
        expected.expectMessage("Invalid prescription medicine name.");

        Entry entry = new Entry(this.context, null, new Date(), new Date(), LocalTime.parse("10:00"), LocalTime.parse("12:00"), 2, 3, null);
    }

    @Test
    public void whenConstructingWithStartDateAfterEndDateShouldThrow() {
        expected.expect(UnsupportedOperationException.class);
        expected.expectMessage("Invalid prescription end date. Should be after start date.");

        Date startDate = new Date();
        Date endDate = new Date(startDate.getTime()  - (10 * 60 *1000));

        Entry entry = new Entry(this.context, "NAME", startDate, endDate, LocalTime.parse("10:00"), LocalTime.parse("12:00"), 2, 3, null);
    }

    @Test
    public void whenConstructingWithStartTimeAfterEndTimeShouldThrow() {
        expected.expect(UnsupportedOperationException.class);
        expected.expectMessage("Invalid prescription end time. Should be after start time.");

        Entry entry = new Entry(this.context, "NAME", new Date(), new Date(), LocalTime.parse("10:00"), LocalTime.parse("09:00"), 2, 3, null);
    }

    @Test
    public void whenConstructingWithDosageZeroShouldThrow() {
        expected.expect(UnsupportedOperationException.class);
        expected.expectMessage("Invalid prescription dosage. Should be greater than zero.");

        Entry entry = new Entry(this.context, "NAME", new Date(), new Date(), LocalTime.parse("10:00"), LocalTime.parse("12:00"), 0, 3, null);
    }

    @Test
    public void whenConstructingWithDosageLessThanZeroShouldThrow() {
        expected.expect(UnsupportedOperationException.class);
        expected.expectMessage("Invalid prescription dosage. Should be greater than zero.");

        Entry entry = new Entry(this.context, "NAME", new Date(), new Date(), LocalTime.parse("10:00"), LocalTime.parse("12:00"), -1, 3, null);
    }

    @Test
    public void whenConstructingWithTimesPerDayZeroShouldThrow() {
        expected.expect(UnsupportedOperationException.class);
        expected.expectMessage("Invalid prescription times per day. Should be greater than zero.");

        Entry entry = new Entry(this.context, "NAME", new Date(), new Date(), LocalTime.parse("10:00"), LocalTime.parse("12:00"), 2, 0, null);
    }

    @Test
    public void whenConstructingWithTimesPerDayLessThanZeroShouldThrow() {
        expected.expect(UnsupportedOperationException.class);
        expected.expectMessage("Invalid prescription times per day. Should be greater than zero.");

        Entry entry = new Entry(this.context, "NAME", new Date(), new Date(), LocalTime.parse("10:00"), LocalTime.parse("12:00"), 2, -1, null);
    }

    @Test
    public void whenConstructingShouldNotThrow() {
        Entry entry = new Entry(this.context,"NAME", new Date(), new Date(), LocalTime.parse("10:00"), LocalTime.parse("12:00"), 2, 3, null);
        assertNotNull(entry);
    }

    @Test
    public void whenCallingGetMedicineNameShouldReturnExpected() {
        Entry entry = new Entry(this.context,"NAME", new Date(), new Date(), LocalTime.parse("10:00"), LocalTime.parse("12:00"), 2, 3, null);

        assertEquals("NAME", entry.getMedicineName());
     }

    @Test
    public void whenCallingGetStartDateShouldReturnExpected() {
        Date date = new Date();
        Entry entry = new Entry(this.context,"NAME", new Date(), new Date(), LocalTime.parse("10:00"), LocalTime.parse("12:00"), 2, 3, null);

        assertEquals(date, entry.getStartDate());
    }

    @Test
    public void whenCallingGetEndDateShouldReturnExpected() {
        Date startDate = new Date();
        DateTime date = new DateTime(startDate);
        date.plusDays(7);

        Entry entry = new Entry(this.context,"NAME", startDate, date.toDate(), LocalTime.parse("10:00"), LocalTime.parse("12:00"), 2, 3, null);

        assertEquals(date.toDate(), entry.getEndDate());
    }

    @Test
    public void whenCallingGetStartTimeShouldReturnExpected() {
        Date startDate = new Date();
        DateTime date = new DateTime(startDate);
        date.plusDays(7);

        LocalTime time = LocalTime.parse("10:00");
        Entry entry = new Entry(this.context,"NAME", startDate, date.toDate(), time, LocalTime.parse("12:00"), 2, 3, null);

        assertEquals(time, entry.getStartTime());
    }

    @Test
    public void whenCallingGetEndTimeShouldReturnExpected() {
        Date startDate = new Date();
        DateTime date = new DateTime(startDate);
        date.plusDays(7);

        LocalTime time = LocalTime.parse("10:00");
        LocalTime endTime = LocalTime.parse("17:00");

        Entry entry = new Entry(this.context,"NAME", startDate, date.toDate(), time, endTime, 2, 3, null);

        assertEquals(endTime, entry.getEndTime());
    }

    @Test
    public void whenCallingGetDosageShouldReturnExpected() {
        Date date = new Date();
        Entry entry = new Entry(this.context,"NAME", new Date(), new Date(), LocalTime.parse("10:00"), LocalTime.parse("12:00"), 2, 3, null);

        assertEquals(2.0, entry.getDosage());
    }

    @Test
    public void whenCallingGetCommentShouldReturnExpected() {
        Date date = new Date();
        Entry entry = new Entry(this.context,"NAME", new Date(), new Date(), LocalTime.parse("10:00"), LocalTime.parse("12:00"), 2, 3, null);

        assertNull(entry.getComment());
    }

    @Test
    public void whenCallingGetTimesPerDayShouldReturnExpected() {
        Date date = new Date();
        Entry entry = new Entry(this.context,"NAME", new Date(), new Date(), LocalTime.parse("10:00"), LocalTime.parse("12:00"), 2, 3, null);

        assertEquals(3, entry.getTimesPerDay());
    }
}
