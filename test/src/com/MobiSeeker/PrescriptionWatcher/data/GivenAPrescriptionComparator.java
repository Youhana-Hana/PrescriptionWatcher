package com.MobiSeeker.PrescriptionWatcher.data;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

public class GivenAPrescriptionComparator {

    private PrescriptionComparator comparator;

    @Before
    public void setup() {
        this.comparator = new PrescriptionComparator();
    }

    @Test
    public void whenConstructingShouldNotThrow() {
        assertNotNull(this.comparator);
    }

    @Test
    public void whenCompareTwoEqualsPrescriptionsShouldReturnZero() {
        Date time = new Date();
        Prescription first = new Prescription("NAME1", time, 0, null);
        Prescription second = new Prescription("NAME2", time, 0, null);
        PrescriptionComparator comparator = new PrescriptionComparator();
        int result = comparator.compare(first, second);

        assertEquals(0, result);
    }


    @Test
    public void whenCompareAndFirstLessThanSecondShouldReturnMinusOne() {
        Date time1 = new Date();
        Date time2 = new Date(time1.getTime() + (10 *60 *1000));

        Prescription first = new Prescription("NAME1", time1, 0, null);

        Prescription second = new Prescription("NAME2", time2, 0, null);
        PrescriptionComparator comparator = new PrescriptionComparator();
        int result = comparator.compare(first, second);

        assertEquals(-1, result);
    }

    @Test
    public void whenCompareAndFirstGreaterThanSecondShouldReturnOne() {
        Date time1 = new Date();
        Date time2 = new Date(time1.getTime() -(10 *60 *1000));

        Prescription first = new Prescription("NAME1", time1, 0, null);

        Prescription second = new Prescription("NAME2", time2, 0, null);
        PrescriptionComparator comparator = new PrescriptionComparator();
        int result = comparator.compare(first, second);

        assertEquals(1, result);
    }

    @Test
    public void whenCompareAndFirstIsNULLAndSecondHasValueShouldReturnMinusOne() {
        Date time1 = new Date();
        Date time2 = new Date(time1.getTime() -(10 *60 *1000));

        Prescription first = new Prescription("NAME1", null, 0, null);

        Prescription second = new Prescription("NAME2", time2, 0, null);
        PrescriptionComparator comparator = new PrescriptionComparator();
        int result = comparator.compare(first, second);

        assertEquals(-1, result);
    }

    @Test
    public void whenCompareAndFirstIsNotNULLAndSecondIsNULLShouldReturnOne() {
        Date time1 = new Date();

        Prescription first = new Prescription("NAME1", time1, 0, null);

        Prescription second = new Prescription("NAME2", null, 0, null);
        PrescriptionComparator comparator = new PrescriptionComparator();
        int result = comparator.compare(first, second);

        assertEquals(1, result);
    }

    @Test
    public void whenCompareAndFirstAndSecondAreNULLShouldReturnZero() {
        Prescription first = new Prescription("NAME1", null, 0, null);
        Prescription second = new Prescription("NAME2", null, 0, null);
        PrescriptionComparator comparator = new PrescriptionComparator();

        int result = comparator.compare(first, second);

        assertEquals(0, result);
    }

}
