package com.MobiSeeker.PrescriptionWatcher.activities;

import android.content.Context;

import com.xtremelabs.robolectric.Robolectric;
import com.xtremelabs.robolectric.RobolectricTestRunner;
import com.xtremelabs.robolectric.shadows.ShadowApplication;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.Assert.assertNotNull;

@RunWith(RobolectricTestRunner.class)
public class GivenAnActivity {

    private ShadowApplication shadowApplication;
    private Context context;

    @Before
    public void setUp() throws Exception {
        this.shadowApplication = Robolectric.getShadowApplication();
        this.context = shadowApplication.getApplicationContext();
    }

    @Test
    public void whenCallingOnCreateShouldNotThrow() {
        PrescriptionWatcher activity = new PrescriptionWatcher();
        activity.onCreate(null);
        assertNotNull(activity);
    }
}
