package com.MobiSeeker.PrescriptionWatcher.data;


import org.junit.Test;

import java.util.Date;

import static junit.framework.Assert.*;

public class GivenAPrescription {

    @Test
    public void whenConstructingShouldNotThrow() {
        Prescription prescription = new Prescription("NAME", new Date(), 2, null);
        assertNotNull(prescription);
    }

    @Test
    public void whenCallingGetMedicineNameShouldReturnExpected() {
        Prescription prescription =  new Prescription("NAME", new Date(), 2, null);

        assertEquals("NAME", prescription.getMedicineName());
     }

    @Test
    public void whenCallingGetTimeShouldReturnExpected() {
        Date date = new Date();

        Prescription prescription =  new Prescription("NAME", date, 2, null);

        assertEquals(date, prescription.getTime());
    }

    @Test
    public void whenCallingGetDosageShouldReturnExpected() {
        Date date = new Date();

        Prescription prescription =  new Prescription("NAME", date, 2, null);

        assertEquals(2.0, prescription.getDosage());
    }

    @Test
    public void whenCallingGetCommentShouldReturnExpected() {
        Date date = new Date();

        Prescription prescription =  new Prescription("NAME", date, 2, null);

        assertNull(prescription.getComment());
    }
}
