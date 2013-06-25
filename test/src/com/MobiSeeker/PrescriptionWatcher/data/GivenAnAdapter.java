package com.MobiSeeker.PrescriptionWatcher.data;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.MobiSeeker.PrescriptionWatcher.R;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import org.joda.time.LocalTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;

import java.sql.Time;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

@RunWith(RobolectricTestRunner.class)
public class GivenAnAdapter {

    private Context context;

    private Adapter adapter;

    private ArrayList<Entry> entries;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        this.context = Robolectric.getShadowApplication().getApplicationContext();

        this.entries = new ArrayList<Entry>();

        Date startDate = DateFormat.getDateInstance(DateFormat.MEDIUM).parse("Dec 10, 2012");
        Date endDate = DateFormat.getDateInstance(DateFormat.MEDIUM).parse("Dec 20, 2012");

        Entry entry = new Entry(this.context, "NAME",
                startDate,
                endDate,
                Time.valueOf("10:12:00"),
                Time.valueOf("17:30:00"),
                3.0,
                2,
                "Comment"
        );

        entries.add(entry);
        this.adapter = new Adapter(this.context, 0, entries);
    }

    @Test
    public void whenConstructingShouldNotThrow() {
        assertNotNull(this.adapter);
    }

    @Test
    public void whenCallingGetViewShouldeturnExpected() {
        View view = this.adapter.getView(0, null, null);

        TextView title = (TextView) view.findViewById(R.id.title);
        TextView timerPerDay = (TextView) view.findViewById(R.id.timesPerDay);
        TextView start = (TextView) view.findViewById(R.id.start);
        TextView end = (TextView) view.findViewById(R.id.end);

        assertEquals("NAME", title.getText().toString());
        assertEquals("2 Times per day", timerPerDay.getText().toString());
        assertEquals("Dec 10, 2012 10:12:00", start.getText().toString());
        assertEquals("Dec 20, 2012 17:30:00", end.getText().toString());
    }

}
