package com.MobiSeeker.PrescriptionWatcher.data;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertNotNull;

public class GivenAPrescriptionRepository {

    private PrescriptionRepository repository;

    @Before
    public void setup() {
     this.repository = new PrescriptionRepository();
    }

    @Test
    public void whenConstructingShouldNotThrow() {
        assertNotNull(this.repository);
    }

    @Test
    public void whenCallingSaveShouldNotThrow() {
        this.repository.save(null);
    }
}
