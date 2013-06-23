package com.MobiSeeker.PrescriptionWatcher.connection;

import com.samsung.chord.ChordManager;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class GivenAChannelInformation {

    @Mock
    com.samsung.chord.ChordManager chordManager;

    ChannelInformation channelInformation = null;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        this.channelInformation = new ChannelInformation(chordManager);
    }

    @Test
    public void whenConstructingShouldNotThrow() {
        assertNotNull(this.channelInformation);
    }

    @Test
    public void whenCallinggetAvailableInterfaceTypesShouldReturnExpected() {
        assertEquals(ChordManager.PUBLIC_CHANNEL, this.channelInformation.getPublicChannel());
    }

    @Test
    public void whenCallingGetgetPrivateChannelShouldReturnExpected() {
        this.channelInformation.setPrivateChannel("CHANNEL");

        assertEquals("CHANNEL", channelInformation.getPrivateChannel());
    }
}
