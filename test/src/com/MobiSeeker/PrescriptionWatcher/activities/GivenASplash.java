package com.MobiSeeker.PrescriptionWatcher.activities;

import android.content.Context;
import android.content.Intent;

import com.xtremelabs.robolectric.Robolectric;
import com.xtremelabs.robolectric.RobolectricTestRunner;
import com.xtremelabs.robolectric.shadows.ShadowApplication;
import com.xtremelabs.robolectric.shadows.ShadowIntent;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

@RunWith(RobolectricTestRunner.class)
public class GivenASplash {

    private ShadowApplication shadowApplication;
    private Context context;

    @Before
    public void setUp() throws Exception {
        this.shadowApplication = Robolectric.getShadowApplication();
        this.context = shadowApplication.getApplicationContext();
    }

    @Test
    public void whenCallingOnCreateShouldNotThrow() {
        Splash activity = new Splash();
        activity.onCreate(null);
        assertNotNull(activity);
    }

    @Test
    public void whenGoHomeShouldNotThrow() {
        Splash activity = new Splash();
        activity.onCreate(null);
        activity.goToHome();

        Intent intent = Robolectric.getShadowApplication().getNextStartedActivity();
        assertNotNull(intent);
        ShadowIntent shadowIntent = Robolectric.shadowOf(intent);
        assertEquals(PrescriptionWatcher.class, shadowIntent.getIntentClass());
    }

    @Test
    public void whenCallingLoadingShouldNotThrow() {
        Splash activity = new Splash();
        activity.onCreate(null);
        activity.loading();
    }
}
