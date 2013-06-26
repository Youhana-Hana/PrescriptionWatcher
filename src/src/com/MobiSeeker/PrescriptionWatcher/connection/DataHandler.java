package com.MobiSeeker.PrescriptionWatcher.connection;

import android.util.Log;

import com.samsung.chord.ChordManager;
import com.samsung.chord.IChordChannel;

public class DataHandler {

    private static final String TAG = "[Chord][ApiService]";

    private static final String TAGClass = "DataHandler : ";

    protected static final String CHORD_APITEST_MESSAGE_TYPE = "CHORD_API_MESSAGE_TYPE";

    protected ChordManager chordManager;

    public DataHandler(ChordManager chordManager) {

        if (chordManager == null) {
            Log.e(TAG, TAGClass + "DataHandler() Invalid ChordManager. null value");
            throw new IllegalArgumentException("Invalid ChordManager. null value");
        }

        this.chordManager = chordManager;
    }

    // Send data message to the node.
    public boolean sendData(String toChannel, byte[] buf, String nodeName) {

        if (nodeName == null) {
            Log.v(TAG, "sendData : NODE Name IS NULL !!");
            return false;
        }

        if (nodeName.isEmpty()) {
            Log.v(TAG, "sendData : NODE Name IS empty !!");
            return false;
        }


        if (buf == null) {
            Log.v(TAG, "sendData : buffer IS null !!");
            return false;
        }

        IChordChannel channel = this.chordManager.getJoinedChannel(toChannel);
        if (null == channel) {
            Log.e(TAG, TAGClass + "sendData : invalid channel instance");
            return false;
        }

        byte[][] payload = new byte[1][];
        payload[0] = buf;

        Log.v(TAG, TAGClass + "sendData : " + new String(buf) + ", des : " + nodeName);

        return channel.sendData(nodeName, CHORD_APITEST_MESSAGE_TYPE, payload);

       }

    // Send data message to the all nodes on the channel.
    public boolean sendDataToAll(String toChannel, byte[] buf) {

        if (buf == null) {
            Log.v(TAG, "sendDataToAll : buffer IS null !!");
            return false;
        }

        // Request the channel interface for the specific channel name.
        IChordChannel channel = this.chordManager.getJoinedChannel(toChannel);
        if (null == channel) {
            Log.e(TAG, TAGClass + "sendDataToAll : invalid channel instance");
            return false;
        }

        byte[][] payload = new byte[1][];
        payload[0] = buf;

        Log.v(TAG, TAGClass + "sendDataToAll : " + new String(buf));

        return channel.sendDataToAll(CHORD_APITEST_MESSAGE_TYPE, payload);
    }
}
