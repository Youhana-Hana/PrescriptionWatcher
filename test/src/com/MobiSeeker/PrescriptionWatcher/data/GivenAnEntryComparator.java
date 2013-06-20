package com.MobiSeeker.PrescriptionWatcher.data;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import java.sql.Time;
import java.util.Date;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

public class GivenAnEntryComparator {

    private EntryComparator comparator;

    private Date startDate;
    private Date endDate;
    private Time startTime;
    private Time endTime;

    private Entry first;
    private Entry second;

    @Before
    public void setup() {
        this.comparator = new EntryComparator();
        this.startTime = Time.valueOf("10:00:00");
        this.endTime = Time.valueOf("12:00:00");

        this.startDate = new Date();
        this.endDate = new DateTime(startDate).plusDays(7).toDate();

        this.first  = new Entry(null, "NAME", this.startDate, this.endDate, startTime, endTime, 2, 3, null);

        Date startDate2 = new Date(this.endDate.getTime());

        this.second = new Entry(null, "NAME", startDate2, this.endDate, startTime, endTime, 2, 3, null);
    }

    @Test
    public void whenConstructingShouldNotThrow() {
        assertNotNull(this.comparator);
    }

    @Test
    public void whenCompareTwoEqualsPrescriptionsShouldReturnZero() {
        this.second = new Entry(null, "NAME", this.startDate, this.endDate, startTime, endTime, 2, 3, null);

        int result = this.comparator.compare(first, second);

        assertEquals(0, result);
    }


    @Test
    public void whenCompareAndFirstLessThanSecondShouldReturnMinusOne() {
        int result = this.comparator.compare(first, second);

        assertEquals(-1, result);
    }

    @Test
    public void whenCompareAndFirstGreaterThanSecondShouldReturnOne() {
        int result = comparator.compare(second, first);

        assertEquals(1, result);
    }

    @Test
    public void whenCompareAndFirstIsNULLAndSecondHasValueShouldReturnMinusOne() {
        int result = comparator.compare(null, second);

        assertEquals(-1, result);
    }

    @Test
    public void whenCompareAndFirstIsNotNULLAndSecondIsNULLShouldReturnOne() {
        int result = comparator.compare(first, null);

        assertEquals(1, result);
    }

    @Test
    public void whenCompareAndFirstAndSecondAreNULLShouldReturnZero() {
        int result = comparator.compare(null, null);

        assertEquals(0, result);
    }

}