package com.MobiSeeker.PrescriptionWatcher.connection;

import com.xtremelabs.robolectric.RobolectricTestRunner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doReturn;

@RunWith(RobolectricTestRunner.class)
public class GivenAChordManagerService {

    @Mock
    com.samsung.chord.ChordManager chordManager;

    ChordManagerService chordManagerService = null;

    List<Integer> availableInterfaces = null;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        this.chordManagerService = new ChordManagerService(chordManager);

        doReturn(this.availableInterfaces).when(chordManager).getAvailableInterfaceTypes();
    }

    @Test
    public void whenConstructingShouldNotThrow() {
        assertNotNull(this.chordManagerService);
    }

    @Test
    public void whenCallingGetAvailableInterfaceTypesShouldReturnExpected() {
        assertEquals(availableInterfaces, this.chordManagerService.getAvailableInterfaceTypes());
    }

    @Test
    public void whenCallingGetChordFilePathShouldReturnExpected() {
        assertTrue(this.chordManagerService.getChordFilePath().endsWith("Chord"));
    }

}
