package com.MobiSeeker.PrescriptionWatcher.data;

import com.xtremelabs.robolectric.Robolectric;
import com.xtremelabs.robolectric.RobolectricTestRunner;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

import java.util.Date;

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
        this.calculator.getSchedule(null, startDate, endDate, 0, 0, 1);
    }

    @Test
    public void whenCallingGetScheduleAndMedicineNameIsEmptyShouldThrow() {
        expected.expect(UnsupportedOperationException.class);
        expected.expectMessage("Invalid prescription medicine name.");

        Date startDate = new Date();
        Date endDate = new Date(startDate.getTime()  + (10 * 60 *1000));
        this.calculator.getSchedule("", startDate, endDate, 0, 0, 1);
    }

    @Test
    public void whenCallingGetScheduleAndStartDateLessThanEndDateShouldThrow() {
        expected.expect(UnsupportedOperationException.class);
        expected.expectMessage("Invalid prescription end date. Should be after start date");

        Date startDate = new Date();
        Date endDate = new Date(startDate.getTime()  - (10 * 60 *1000));
        this.calculator.getSchedule("NAME", startDate, endDate, 0, 0, 1);
    }

    @Test
    public void whenCallingGetScheduleAndDosageLessThenZeroShouldThrow() {
        expected.expect(UnsupportedOperationException.class);
        expected.expectMessage("Invalid prescription dosage. Should be greater than zero");

        Date startDate = new Date();
        Date endDate = new Date(startDate.getTime()  + (10 * 60 *1000));
        this.calculator.getSchedule("NAME", startDate, endDate, 0, 0, -1);
    }

    @Test
    public void whenCallingGetScheduleAndDosageIsZeroShouldThrow() {
        expected.expect(UnsupportedOperationException.class);
        expected.expectMessage("Invalid prescription dosage. Should be greater than zero");

        Date startDate = new Date();
        Date endDate = new Date(startDate.getTime()  + (10 * 60 *1000));
        this.calculator.getSchedule("NAME", startDate, endDate, 0, 0, 0);
    }

    @Test
    public void whenCallingGetScheduleAndStartTimeLessThanEndShouldThrow() {
        expected.expect(UnsupportedOperationException.class);
        expected.expectMessage("Invalid prescription end time. Should be after start time");

        Date startDate = new Date();
        Date endDate = new Date(startDate.getTime()  + (10 * 60 *1000));
        this.calculator.getSchedule("NAME", startDate, endDate, 1, 0, 1);
    }

    @Test
    public void whenCallingGetScheduleShouldNotThrow() {
        Date startDate = new Date();
        Date endDate = new Date(startDate.getTime()  + (10 * 60 *1000));
        this.calculator.getSchedule("NAME", startDate, endDate, 1, 3, 1);
    }
}
