package com.MobiSeeker.PrescriptionWatcher.data;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

public class GivenACalculator {
    private Calculator calculator;

    @Rule
    public ExpectedException expected = ExpectedException.none();

    @Before
    public void setup() {
        this.calculator = new Calculator();
    }

    @Test
    public void whenConstructingShouldNotThrow() {
        assertNotNull(this.calculator);
    }

    @Test
    public void whenCallingGetScheduleAndMedicineNameIsNullShouldThrow() {
        expected.expect(UnsupportedOperationException.class);
        expected.expectMessage("Invalid prescription medicine name.");

        Date startDate = new Date();
        Date endDate = new Date(startDate.getTime()  + (10 * 60 *1000));
        Time startTime = Time.valueOf("07:30:00");
        Time endTime = Time.valueOf("22:0:00");

        this.calculator.getSchedule(null, startDate, endDate, startTime, endTime, 2, 1, "Comment");
    }

    @Test
    public void whenCallingGetScheduleAndMedicineNameIsEmptyShouldThrow() {
        expected.expect(UnsupportedOperationException.class);
        expected.expectMessage("Invalid prescription medicine name.");

        Date startDate = new Date();
        Date endDate = new Date(startDate.getTime()  + (10 * 60 *1000));
        Time startTime = Time.valueOf("07:30:00");
        Time endTime = Time.valueOf("22:0:00");

        this.calculator.getSchedule("", startDate, endDate, startTime, endTime, 2, 1, "Comment");
    }

    @Test
    public void whenCallingGetScheduleAndStartDateLessThanEndDateShouldThrow() {
        expected.expect(UnsupportedOperationException.class);
        expected.expectMessage("Invalid prescription end date. Should be after start date");

        Date startDate = new Date();
        Date endDate = new Date(startDate.getTime()  - (10 * 60 *1000));
        Time startTime = Time.valueOf("07:30:00");
        Time endTime = Time.valueOf("22:0:00");

        this.calculator.getSchedule("NAME", startDate, endDate, startTime, endTime, 2, 1 , "Comment");
    }

    @Test
    public void whenCallingGetScheduleAndDosageLessThenZeroShouldThrow() {
        expected.expect(UnsupportedOperationException.class);
        expected.expectMessage("Invalid prescription dosage. Should be greater than zero");

        Date startDate = new Date();
        Date endDate = new Date(startDate.getTime()  + (10 * 60 *1000));
        Time startTime = Time.valueOf("07:30:00");
        Time endTime = Time.valueOf("22:0:00");

        this.calculator.getSchedule("NAME", startDate, endDate, startTime, endTime, -1, 1, "Comment");
    }

    @Test
    public void whenCallingGetScheduleAndDosageIsZeroShouldThrow() {
        expected.expect(UnsupportedOperationException.class);
        expected.expectMessage("Invalid prescription dosage. Should be greater than zero");

        Date startDate = new Date();
        Date endDate = new Date(startDate.getTime()  + (10 * 60 *1000));
        Time startTime = Time.valueOf("07:30:00");
        Time endTime = Time.valueOf("22:0:00");

        this.calculator.getSchedule("NAME", startDate, endDate, startTime, endTime, 0, 1, "Comment");
    }

    @Test
    public void whenCallingGetScheduleAndStartTimeLessThanEndShouldThrow() {
        expected.expect(UnsupportedOperationException.class);
        expected.expectMessage("Invalid prescription end time. Should be after start time");

        Date startDate = new Date();
        Date endDate = new Date(startDate.getTime()  + (10 * 60 *1000));
        Time startTime = Time.valueOf("07:30:00");
        Time endTime = Time.valueOf("07:0:00");

        this.calculator.getSchedule("NAME", startDate, endDate, startTime, endTime, 2, 1, "Comment");
    }

    @Test
    public void whenCallingGetScheduleAndTimesPerDayLessThenZeroShouldThrow() {
        expected.expect(UnsupportedOperationException.class);
        expected.expectMessage("Invalid prescription times per day. Should be greater than zero");

        Date startDate = new Date();
        Date endDate = new Date(startDate.getTime()  + (10 * 60 *1000));
        Time startTime = Time.valueOf("07:30:00");
        Time endTime = Time.valueOf("22:0:00");

        this.calculator.getSchedule("NAME", startDate, endDate, startTime, endTime, 1, -1, "Comment");
    }

    @Test
    public void whenCallingGetScheduleAndTimesPerIsZeroShouldThrow() {
        expected.expect(UnsupportedOperationException.class);
        expected.expectMessage("Invalid prescription times per day. Should be greater than zero");

        Date startDate = new Date();
        Date endDate = new Date(startDate.getTime()  + (10 * 60 *1000));
        Time startTime = Time.valueOf("07:30:00");
        Time endTime = Time.valueOf("22:0:00");

        this.calculator.getSchedule("NAME", startDate, endDate, startTime, endTime, 1, 0, "Comment");
    }

    @Test
    public void whenCallingGetScheduleShouldNotThrow() {
        Date startDate = new Date();
        Date endDate = new Date(startDate.getTime()  + (10 * 60 *1000));
        Time startTime = Time.valueOf("07:30:00");
        Time endTime = Time.valueOf("22:0:00");

        Schedule schedule = this.calculator.getSchedule("NAME", startDate, endDate, startTime, endTime, 2, 1, "Comment");
        assertNotNull(schedule);
    }

    @Test
    public void whenCallingGetScheduleAndStartDateSameAsEndDateShouldReturnExpected() {
        Date startDate = new Date();
        Date endDate = startDate;
        Time startTime = Time.valueOf("07:00:00");
        Time endTime = Time.valueOf("22:0:00");

        Schedule  schedule = this.calculator.getSchedule("NAME", startDate, endDate, startTime, endTime, 2, 5, "Comment");

        assertEquals(5, schedule.getPrescriptions().size());
    }

    @Test
    public void whenCallingGetScheduleAndEndDateGreaterThanStartDateShouldReturnExpected() {
        Date startDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        calendar.add(Calendar.DATE, 7);

        Date endDate = calendar.getTime();

        Time startTime = Time.valueOf("07:00:00");
        Time endTime = Time.valueOf("22:0:00");

        Schedule  schedule = this.calculator.getSchedule("NAME", startDate, endDate, startTime, endTime, 2, 5, "Comment");

        assertEquals(40, schedule.getPrescriptions().size());
    }

    @Test
    public void whenCallingGetScheduleShouldReturnExpected() {
        Date startDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        calendar.add(Calendar.DATE, 3);

        Date endDate = calendar.getTime();

        Time startTime = Time.valueOf("10:00:00");
        Time endTime = Time.valueOf("22:0:00");

        Schedule  schedule = this.calculator.getSchedule("NAME", startDate, endDate,
                                                        startTime, endTime,
                                                        2, 3, "Comment");

        DateTime dateTime1 = new DateTime(startDate);
        dateTime1 = dateTime1.withTime(10, 0, 0, 0);

        List <Dosage> prescriptions = schedule.getPrescriptions();
        assertEquals(12, prescriptions.size());

        assertEquals("NAME", prescriptions.get(0).getMedicineName());
        assertEquals(2.0, prescriptions.get(0).getDosage());
        assertEquals("Comment", prescriptions.get(0).getComment());

        assertEquals(dateTime1.toDate(), prescriptions.get(0).getTime());
        assertEquals(dateTime1.plusHours(4).toDate(), prescriptions.get(1).getTime());
        assertEquals(dateTime1.plusHours(8).toDate(), prescriptions.get(2).getTime());

        assertEquals(dateTime1.plusDays(1).toDate(), prescriptions.get(3).getTime());
        assertEquals(dateTime1.plusDays(1).plusHours(4).toDate(), prescriptions.get(4).getTime());
        assertEquals(dateTime1.plusDays(1).plusHours(8).toDate(), prescriptions.get(5).getTime());

        assertEquals(dateTime1.plusDays(2).toDate(), prescriptions.get(6).getTime());
        assertEquals(dateTime1.plusDays(2).plusHours(4).toDate(), prescriptions.get(7).getTime());
        assertEquals(dateTime1.plusDays(2).plusHours(8).toDate(), prescriptions.get(8).getTime());

        assertEquals(dateTime1.plusDays(3).toDate(), prescriptions.get(9).getTime());
        assertEquals(dateTime1.plusDays(3).plusHours(4).toDate(), prescriptions.get(10).getTime());
        assertEquals(dateTime1.plusDays(3).plusHours(8).toDate(), prescriptions.get(11).getTime());

        assertEquals("NAME", prescriptions.get(5).getMedicineName());
        assertEquals(2.0, prescriptions.get(5).getDosage());
        assertEquals("Comment", prescriptions.get(5).getComment());

        assertEquals("NAME", prescriptions.get(11).getMedicineName());
        assertEquals(2.0, prescriptions.get(11).getDosage());
        assertEquals("Comment", prescriptions.get(11).getComment());
    }
}
