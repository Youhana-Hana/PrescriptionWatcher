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
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;

import static org.mockito.Mockito.*;

@RunWith(RobolectricTestRunner.class)
public class GivenAFileHandler {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Mock
    ChordManager chordManager;

    @Mock
    IChordChannel chordChannel;

    FileHandler fileHandler;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        this.fileHandler = new FileHandler(this.chordManager);
    }

    @Test
    public void whenConstructingShouldNotThrow() throws IllegalArgumentException{
        Assert.assertNotNull(this.fileHandler);
    }

    @Test
    public void whenConstructingAndChordManagerIsNullShouldThrow() throws IllegalArgumentException{
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Invalid ChordManager. null value");

        this.fileHandler = new FileHandler(null);
    }

    @Test
    public void whenCallingSendFileAndSucceededToJoinChannelShouldRetuenNull() {
        doReturn("TOKEN").when(this.chordChannel).sendFile("NODE", FileHandler.MESSAGE_TYPE_FILE_NOTIFICATION, "PATH", FileHandler.SHARE_FILE_TIMEOUT_MILISECONDS);
        doReturn(this.chordChannel).when(this.chordManager).getJoinedChannel("CHANNEL");

        String actual = this.fileHandler.sendFile("CHANNEL", "PATH", "NODE");

        Assert.assertEquals("TOKEN", actual);

        verify(this.chordManager).getJoinedChannel("CHANNEL");
        verify(this.chordChannel).sendFile("NODE", FileHandler.MESSAGE_TYPE_FILE_NOTIFICATION, "PATH", FileHandler.SHARE_FILE_TIMEOUT_MILISECONDS);
    }

    @Test
    public void whenCallingSendFileAndFailedToJoinChannelShouldRetuenNull() {
        doReturn(null).when(this.chordManager).getJoinedChannel("CHANNEL");

        String actual = this.fileHandler.sendFile("CHANNEL", "PATH", "NODE");

        Assert.assertNull("TOKEN", actual);

        verify(this.chordManager).getJoinedChannel("CHANNEL");
    }

    @Test
    public void whenCallingAcceptFileAndFailedToJoinChannelShouldRetuenFalse() {
        doReturn(null).when(this.chordManager).getJoinedChannel("CHANNEL");

        boolean actual = this.fileHandler.acceptFile("CHANNEL", "EXCHANGEID");

        Assert.assertFalse(actual);

        verify(this.chordManager).getJoinedChannel("CHANNEL");
    }

    @Test
    public void whenCallingAcceptFileShouldRetuenExpected() {
        doReturn(this.chordChannel).when(this.chordManager).getJoinedChannel("CHANNEL");
        doReturn(true).when(this.chordChannel).acceptFile("EXCHANGEID", FileHandler.chunkTimeoutMsec, FileHandler.chunkRetries, FileHandler.chunkSize);

        boolean actual = this.fileHandler.acceptFile("CHANNEL", "EXCHANGEID");

        Assert.assertTrue(actual);

        verify(this.chordManager).getJoinedChannel("CHANNEL");
        verify(this.chordChannel).acceptFile("EXCHANGEID", FileHandler.chunkTimeoutMsec, FileHandler.chunkRetries, FileHandler.chunkSize);
    }

    @Test
    public void whenCallingCancelFileAndFailedToJoinChannelShouldRetuenFalse() {
        doReturn(null).when(this.chordManager).getJoinedChannel("CHANNEL");

        boolean actual = this.fileHandler.cancelFile("CHANNEL", "EXCHANGEID");

        Assert.assertFalse(actual);

        verify(this.chordManager).getJoinedChannel("CHANNEL");
    }

    @Test
    public void whenCallingCancelFileShouldRetuenExpected() {
        doReturn(this.chordChannel).when(this.chordManager).getJoinedChannel("CHANNEL");
        doReturn(true).when(this.chordChannel).cancelFile("EXCHANGEID");

        boolean actual = this.fileHandler.cancelFile("CHANNEL", "EXCHANGEID");

        Assert.assertTrue(actual);

        verify(this.chordManager).getJoinedChannel("CHANNEL");
        verify(this.chordChannel).cancelFile("EXCHANGEID");
    }

    @Test
    public void whenCallingRejectFileAndFailedToJoinChannelShouldRetuenFalse() {
        doReturn(null).when(this.chordManager).getJoinedChannel("CHANNEL");

        boolean actual = this.fileHandler.rejectFile("CHANNEL", "CORETRANSACTIONID");

        Assert.assertFalse(actual);

        verify(this.chordManager).getJoinedChannel("CHANNEL");
    }

    @Test
    public void whenCallingRejectFileShouldRetuenExpected() {
        doReturn(this.chordChannel).when(this.chordManager).getJoinedChannel("CHANNEL");
        doReturn(true).when(this.chordChannel).rejectFile("CORETRANSACTIONID");

        boolean actual = this.fileHandler.rejectFile("CHANNEL", "CORETRANSACTIONID");

        Assert.assertTrue(actual);

        verify(this.chordManager).getJoinedChannel("CHANNEL");
        verify(this.chordChannel).rejectFile("CORETRANSACTIONID");
    }
}
