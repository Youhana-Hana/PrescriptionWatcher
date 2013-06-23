package com.MobiSeeker.PrescriptionWatcher.connection;

import com.samsung.chord.ChordManager;

public class ChannelInformation {

    protected ChordManager chordManager;

    private String channelName;

    public ChannelInformation(ChordManager chordManager) {
        this.chordManager = chordManager;
    }

    public String getPublicChannel() {
        return this.chordManager.PUBLIC_CHANNEL;
    }

    public String getPrivateChannel() {
        return this.channelName;
    }

    public void setPrivateChannel(String channelName) {
        this.channelName = channelName;
    }
}
