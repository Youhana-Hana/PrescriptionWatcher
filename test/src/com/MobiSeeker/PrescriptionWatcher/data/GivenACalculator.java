package com.MobiSeeker.PrescriptionWatcher.data;

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

        Schedule  schedule = this.calculator.getSchedule("NAME", startDate, endDate, startTime, endTime, 2, 3, "Comment");

        calendar.setTime(startDate);
        calendar.set(calendar.get(Calendar.YEAR),
                     calendar.get(Calendar.MONTH),
                     calendar.get(Calendar.DATE),
                     10,
                     0,
                     0);

        Date time1 = calendar.getTime();

        List <Prescription> prescriptions = schedule.getPrescriptions();
        assertEquals(12, prescriptions.size());

        assertEquals("NAME", prescriptions.get(0).getMedicineName());
        assertEquals(2.0, prescriptions.get(0).getDosage());
        assertEquals("Comment", prescriptions.get(0).getComment());
        assertEquals(time1, prescriptions.get(0).getTime());

        assertEquals("NAME", prescriptions.get(5).getMedicineName());
        assertEquals(2.0, prescriptions.get(5).getDosage());
        assertEquals("Comment", prescriptions.get(5).getComment());


        assertEquals("NAME", prescriptions.get(11).getMedicineName());
        assertEquals(2.0, prescriptions.get(11).getDosage());
        assertEquals("Comment", prescriptions.get(11).getComment());

    }
}
