package com.MobiSeeker.PrescriptionWatcher.connection;

import com.samsung.chord.ChordManager;
import com.samsung.chord.IChordChannel;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;

import static org.mockito.Mockito.*;

@RunWith(RobolectricTestRunner.class)
public class GivenADataHandler {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Mock
    ChordManager chordManager;

    @Mock
    IChordChannel chordChannel;

    DataHandler dataHandler;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        this.dataHandler = new DataHandler(this.chordManager);
    }

    @Test
    public void whenConstructingShouldNotThrow() throws IllegalArgumentException{
        Assert.assertNotNull(this.dataHandler);
    }

    @Test
    public void whenConstructingAndChordManagerIsNullShouldThrow() throws IllegalArgumentException{
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Invalid ChordManager. null value");

        this.dataHandler = new DataHandler(null);
    }

    @Test
    public void whenCallingSendDataAndNodeNameIsNullShouldRetuenFalse() {

        boolean actual = this.dataHandler.sendData("CHANNEL", null, null);

        Assert.assertFalse(actual);
    }

    @Test
    public void whenCallingSendDataAndNodeNameIsEmptyShouldRetuenFalse() {

        boolean actual = this.dataHandler.sendData("CHANNEL", null, "");

        Assert.assertFalse(actual);
    }


    @Test
    public void whenCallingSendDataAndBufferIsNullShouldRetuenFalse() {

        boolean actual = this.dataHandler.sendData("CHANNEL", null, "NODE");

        Assert.assertFalse(actual);
    }

    @Test
    public void whenCallingSendDataAndFailedToJoinChannelShouldRetuenFalse() {
        doReturn(null).when(this.chordManager).getJoinedChannel("CHANNEL");

        boolean actual = this.dataHandler.sendData("CHANNEL", new byte[1], "NODE");

        Assert.assertFalse(actual);

        verify(this.chordManager).getJoinedChannel("CHANNEL");
    }

    @Test
    public void whenCallingSendDataShouldRetuenExpected() {
        byte[] buf = new byte[1];

        byte[][] payload = new byte[1][];
        payload[0] = buf;

        doReturn(this.chordChannel).when(this.chordManager).getJoinedChannel("CHANNEL");
        doReturn(true).when(this.chordChannel).sendData("NODE", DataHandler.CHORD_APITEST_MESSAGE_TYPE, payload);

        boolean actual = this.dataHandler.sendData("CHANNEL", buf, "NODE");

        Assert.assertTrue(actual);

        verify(this.chordManager).getJoinedChannel("CHANNEL");
        verify(this.chordChannel).sendData("NODE", DataHandler.CHORD_APITEST_MESSAGE_TYPE, payload);
    }

    @Test
    public void whenCallingSendDataToAllAndBufferIsNullShouldRetuenFalse() {

        boolean actual = this.dataHandler.sendDataToAll("CHANNEL", null);

        Assert.assertFalse(actual);
    }

    @Test
    public void whenCallingSendDataToAllAndFailedToJoinChannelShouldRetuenFalse() {
        doReturn(null).when(this.chordManager).getJoinedChannel("CHANNEL");

        boolean actual = this.dataHandler.sendDataToAll("CHANNEL", new byte[1]);

        Assert.assertFalse(actual);

        verify(this.chordManager).getJoinedChannel("CHANNEL");
    }

    @Test
    public void whenCallingSendDataToAllShouldRetuenExpected() {
        byte[] buf = new byte[1];

        byte[][] payload = new byte[1][];
        payload[0] = buf;

        doReturn(this.chordChannel).when(this.chordManager).getJoinedChannel("CHANNEL");
        doReturn(true).when(this.chordChannel).sendDataToAll(DataHandler.CHORD_APITEST_MESSAGE_TYPE, payload);

        boolean actual = this.dataHandler.sendDataToAll("CHANNEL", buf);

        Assert.assertTrue(actual);

        verify(this.chordManager).getJoinedChannel("CHANNEL");
        verify(this.chordChannel).sendDataToAll(DataHandler.CHORD_APITEST_MESSAGE_TYPE, payload);
    }
}
