package com.MobiSeeker.PrescriptionWatcher.layout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.MobiSeeker.PrescriptionWatcher.R;
import com.xtremelabs.robolectric.Robolectric;
import com.xtremelabs.robolectric.RobolectricTestRunner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.Assert.assertNotNull;

@RunWith(RobolectricTestRunner.class)
public class GivenAnEntry {

    private Context context;

    @Before
    public void setUp() throws Exception {
        this.context = Robolectric.getShadowApplication().getApplicationContext();
    }

    @Test
    public void whenInflateLayoutShouldContainsExpectedElements() {
        LayoutInflater layoutInflater = (LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.entry, null, false);
        TextView title = (TextView)view.findViewById(R.id.title);
        TextView timesPerDay = (TextView)view.findViewById(R.id.timesPerDay);
        TextView start = (TextView)view.findViewById(R.id.start);
        TextView end = (TextView)view.findViewById(R.id.end);

        assertNotNull(title);
        assertNotNull(timesPerDay);
        assertNotNull(start);
        assertNotNull(end);
    }

}
