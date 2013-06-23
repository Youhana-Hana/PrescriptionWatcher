package com.MobiSeeker.PrescriptionWatcher.connection;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertNotNull;

import static org.mockito.Mockito.*;

public class GivenANetworkListener {
    @Mock
    IChordServiceListener chordServiceListener;

    NetworkListener networkListener = null;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        this.networkListener = new NetworkListener(chordServiceListener);
    }

    @Test
    public void whenConstructingShouldNotThrow() {
        assertNotNull(this.networkListener);
    }

    @Test
    public void whenCallingOnConnectShouldNotifyListener() {
        this.networkListener.onConnected(1);

        verify(this.chordServiceListener).onConnectivityChanged();
    }

    @Test
    public void whenCallingonDisconnectedShouldNotifyListener() {
        this.networkListener.onDisconnected(1);

        verify(this.chordServiceListener).onConnectivityChanged();
    }

    @Test
    public void whenCallingOnConnectShouldNotThrow() {
        this.networkListener = new NetworkListener(null);

        this.networkListener.onConnected(1);
    }

    @Test
    public void whenCallingonDisconnectedShouldNotThrow() {
        this.networkListener = new NetworkListener(null);

        this.networkListener.onDisconnected(1);
    }
}
