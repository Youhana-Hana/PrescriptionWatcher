package com.MobiSeeker.PrescriptionWatcher.data;

import org.joda.time.DateTime;
import org.joda.time.LocalTime;
import org.junit.Test;

import java.util.Date;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;

public class GivenAnEntry {

    @Test
    public void whenConstructingShouldNotThrow() {
        Entry entry = new Entry("NAME", new Date(), new Date(), LocalTime.parse("10:00"), LocalTime.parse("12:00"), 2, 3, null);
        assertNotNull(entry);
    }

    @Test
    public void whenCallingGetMedicineNameShouldReturnExpected() {
        Entry entry = new Entry("NAME", new Date(), new Date(), LocalTime.parse("10:00"), LocalTime.parse("12:00"), 2, 3, null);

        assertEquals("NAME", entry.getMedicineName());
     }

    @Test
    public void whenCallingGetStartDateShouldReturnExpected() {
        Date date = new Date();
        Entry entry = new Entry("NAME", new Date(), new Date(), LocalTime.parse("10:00"), LocalTime.parse("12:00"), 2, 3, null);

        assertEquals(date, entry.getStartDate());
    }

    @Test
    public void whenCallingGetEndDateShouldReturnExpected() {
        DateTime date = DateTime.parse("10-10-12");
        date.plusDays(7);

        Entry entry = new Entry("NAME", new Date(), date.toDate(), LocalTime.parse("10:00"), LocalTime.parse("12:00"), 2, 3, null);

        assertEquals(date.toDate(), entry.getEndDate());
    }

    @Test
    public void whenCallingGetStartTimeShouldReturnExpected() {
        DateTime date = DateTime.parse("10-10-12");
        date.plusDays(7);

        LocalTime time = LocalTime.parse("10:00");
        Entry entry = new Entry("NAME", new Date(), date.toDate(), time, LocalTime.parse("12:00"), 2, 3, null);

        assertEquals(time, entry.getStartTime());
    }

    @Test
    public void whenCallingGetEndTimeShouldReturnExpected() {
        DateTime date = DateTime.parse("10-10-12");
        date.plusDays(7);

        LocalTime time = LocalTime.parse("10:00");
        LocalTime endTime = LocalTime.parse("17:00");

        Entry entry = new Entry("NAME", new Date(), date.toDate(), time, endTime, 2, 3, null);

        assertEquals(endTime, entry.getEndTime());
    }

    @Test
    public void whenCallingGetDosageShouldReturnExpected() {
        Date date = new Date();
        Entry entry = new Entry("NAME", new Date(), new Date(), LocalTime.parse("10:00"), LocalTime.parse("12:00"), 2, 3, null);

        assertEquals(2.0, entry.getDosage());
    }

    @Test
    public void whenCallingGetCommentShouldReturnExpected() {
        Date date = new Date();
        Entry entry = new Entry("NAME", new Date(), new Date(), LocalTime.parse("10:00"), LocalTime.parse("12:00"), 2, 3, null);

        assertNull(entry.getComment());
    }

    @Test
    public void whenCallingGetTimesPerDayShouldReturnExpected() {
        Date date = new Date();
        Entry entry = new Entry("NAME", new Date(), new Date(), LocalTime.parse("10:00"), LocalTime.parse("12:00"), 2, 3, null);

        assertEquals(3, entry.getTimesPerDay());
    }
}
