package com.MobiSeeker.PrescriptionWatcher.data;

import org.junit.Test;
import org.junit.Before;

import java.util.Date;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

public class GivenASchedule {

    private Schedule schedule;

    @Before
    public void setup() {
        this.schedule = new Schedule();
    }

    @Test
    public void whenConstructingShouldNotThrow() {
        assertNotNull(this.schedule);
    }

    @Test
    public void whenGetPrescriptionShouldNotReturnNull() {
        assertNotNull(this.schedule.getPrescriptions());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void whenGetPrescriptionShouldBeReadOnly() {
        this.schedule.getPrescriptions().add(new Prescription(null, null, 0, null));
    }

    @Test
    public void whenCallingAddCollectionCountShouldBeIncreasedAndItemPushedToTheCollection() {
        Prescription prescription = new Prescription("NAME", null, 0, null);
        this.schedule.add(prescription);

        assertEquals(1, this.schedule.getPrescriptions().size());
        assertEquals(prescription, this.schedule.getPrescriptions().get(0));
    }

    @Test
    public void whenCallingGetgetPrescriptionsShouldRetrievePrescriptionsSortedByTime() {
        Date time1 = new Date();
        Date time2 = new Date(time1.getTime()  - (10 *60 *1000));
        Date time3 = new Date(time2.getTime()  - (10 *60 *1000));

        Prescription first = new Prescription("FIRST", time1, 0, null);
        Prescription second = new Prescription("SECOND", time2, 0, null);
        Prescription third = new Prescription("THIRD", time3, 0, null);
        this.schedule.add(first);
        this.schedule.add(second);
        this.schedule.add(third);

        List<Prescription> prescriptions = this.schedule.getPrescriptions();
        assertEquals(third, prescriptions.get(0));
        assertEquals(second, prescriptions.get(1));
        assertEquals(first, prescriptions.get(2));
    }
}
