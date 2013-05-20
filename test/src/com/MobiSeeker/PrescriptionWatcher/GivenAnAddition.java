package com.MobiSeeker.PrescriptionWatcher;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GivenAnAddition {

    @Test
    public void whenCallingAdd() {
        Addition addition = new Addition();
        int expected = addition.add(1,2);
        assertEquals(3, expected);
    }
}
