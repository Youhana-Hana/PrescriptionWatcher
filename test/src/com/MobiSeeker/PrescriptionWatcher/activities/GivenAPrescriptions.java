package com.MobiSeeker.PrescriptionWatcher.activities;

import android.content.Context;
import android.widget.ListAdapter;

import com.MobiSeeker.PrescriptionWatcher.data.Entry;
import com.MobiSeeker.PrescriptionWatcher.data.PrescriptionRepository;
import com.MobiSeeker.PrescriptionWatcher.test.utils.*;
import com.xtremelabs.robolectric.Robolectric;
import com.xtremelabs.robolectric.RobolectricTestRunner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.*;

import static junit.framework.Assert.assertNotNull;

@RunWith(RobolectricTestRunner.class)
public class GivenAPrescriptions {

    private Context context;

    private Prescriptions activity;

    private
    @Mock
    PrescriptionRepository prescriptionRepository;

    private ArrayList<Entry> entries;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        this.context = Robolectric.getShadowApplication().getApplicationContext();
        this.entries = new ArrayList<Entry>();

        doReturn(this.entries).when(this.prescriptionRepository).getEntries(this.activity);

        this.activity = new Prescriptions();
        this.activity.onCreate(null);
        this.activity.prescriptionRepository = this.prescriptionRepository;
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

    @Test
    public void whenCallingOnStartShouldCallGetEntriesList() throws Exception{
        this.activity.onStart();

        verify(this.prescriptionRepository).getEntries(this.activity);
    }

    @Test
    public void whenCallingOnStartShouldSetActivityAdapter() {
        this.activity.onStart();
        ListAdapter adapter = this.activity.getListAdapter();

        assertNotNull(adapter);
        assertEquals(0, adapter.getCount());
    }
}
