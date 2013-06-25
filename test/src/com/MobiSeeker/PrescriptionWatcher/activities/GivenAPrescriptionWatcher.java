package com.MobiSeeker.PrescriptionWatcher.activities;

import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.MobiSeeker.PrescriptionWatcher.test.utils.*;
import static junit.framework.Assert.assertNotNull;

@RunWith(RobolectricTestRunner.class)
public class GivenAPrescriptionWatcher {

    private PrescriptionWatcher activity;

    @Before
    public void setUp() throws Exception {
        this.activity = new PrescriptionWatcher();
        this.activity.onCreate(null);
    }

    @Test
    public void whenCallingOnCreateShouldNotThrow() {
        assertNotNull(this.activity);
    }

    @Test
    public void whenCallingViewPrescriptionsShouldNotThrow() {
        this.activity.viewPrescriptions(null);
        Assert.assertActivityStarted(Prescriptions.class);
    }

    @Test
    public void whenCallingViewContactsShouldNotThrow() {
        this.activity.viewContacts(null);
    }

    @Test
    public void whenCallingViewSettingsShouldNotThrow() {
        this.activity.viewSettings(null);
    }
}
