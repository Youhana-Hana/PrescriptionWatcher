package com.MobiSeeker.PrescriptionWatcher.activities;

import com.MobiSeeker.PrescriptionWatcher.test.utils.*;
import com.xtremelabs.robolectric.RobolectricTestRunner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.Assert.assertNotNull;

import com.MobiSeeker.PrescriptionWatcher.test.utils.*;

@RunWith(RobolectricTestRunner.class)
public class GivenAPrescriptions {

    private Prescriptions activity;

    @Before
    public void setUp() throws Exception {
        this.activity = new Prescriptions();
        this.activity.onCreate(null);
    }

    @Test
    public void whenCallingOnCreateShouldNotThrow() {
        assertNotNull(this.activity);
    }

    @Test
    public void whenCallingCreatePrescriptionShouldStartPrescriptionActivity() {
        this.activity.createPrescription(null);
        Assert.assertActivityStarted(Prescription.class);
    }
}
