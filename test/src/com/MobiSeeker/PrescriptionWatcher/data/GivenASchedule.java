package com.MobiSeeker.PrescriptionWatcher.data;

import org.junit.Test;
import org.junit.Before;

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
}
