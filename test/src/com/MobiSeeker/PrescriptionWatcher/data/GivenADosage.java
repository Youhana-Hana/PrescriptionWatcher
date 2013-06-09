package com.MobiSeeker.PrescriptionWatcher.data;

import org.junit.Test;

import java.util.Date;

import static junit.framework.Assert.*;

public class GivenADosage {

    @Test
    public void whenConstructingShouldNotThrow() {
        Dosage dosage = new Dosage("NAME", new Date(), 2, null);
        assertNotNull(dosage);
    }

    @Test
    public void whenCallingGetMedicineNameShouldReturnExpected() {
        Dosage dosage =  new Dosage("NAME", new Date(), 2, null);

        assertEquals("NAME", dosage.getMedicineName());
     }

    @Test
    public void whenCallingGetTimeShouldReturnExpected() {
        Date date = new Date();

        Dosage dosage =  new Dosage("NAME", date, 2, null);

        assertEquals(date, dosage.getTime());
    }

    @Test
    public void whenCallingGetDosageShouldReturnExpected() {
        Date date = new Date();

        Dosage dosage =  new Dosage("NAME", date, 2, null);

        assertEquals(2.0, dosage.getDosage());
    }

    @Test
    public void whenCallingGetCommentShouldReturnExpected() {
        Date date = new Date();

        Dosage dosage =  new Dosage("NAME", date, 2, null);

        assertNull(dosage.getComment());
    }
}
