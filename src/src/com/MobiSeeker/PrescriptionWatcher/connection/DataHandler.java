package com.MobiSeeker.PrescriptionWatcher.connection;

import android.util.Log;

import com.samsung.chord.ChordManager;
import com.samsung.chord.IChordChannel;

public class DataHandler {

    private static final String TAG = "[Chord][ApiService]";

    private static final String TAGClass = "DataHandler : ";

    private static final String CHORD_APITEST_MESSAGE_TYPE = "CHORD_API_MESSAGE_TYPE";

    protected ChordManager chordManager;

    public DataHandler(ChordManager chordManager) {
        this.chordManager = chordManager;
    }

    // Send data message to the node.
    public boolean sendData(String toChannel, byte[] buf, String nodeName) {
        if (this.chordManager == null) {
            Log.v(TAG, "sendData : chordManager IS NULL  !!");
            return false;
        }
        // Request the channel interface for the specific channel name.
        IChordChannel channel = this.chordManager.getJoinedChannel(toChannel);
        if (null == channel) {
            Log.e(TAG, TAGClass + "sendData : invalid channel instance");
            return false;
        }

        if (nodeName == null) {
            Log.v(TAG, "sendData : NODE Name IS NULL !!");
            return false;
        }

        byte[][] payload = new byte[1][];
        payload[0] = buf;

        Log.v(TAG, TAGClass + "sendData : " + new String(buf) + ", des : " + nodeName);

        /*
         * @param toNode The joined node name that the message is sent to. It is
         * mandatory.
         * @param payloadType User defined message type. It is mandatory.
         * @param payload The package of data to send
         * @return Returns true when file transfer is success. Otherwise, false
         * is returned
         */
        if (false == channel.sendData(nodeName, CHORD_APITEST_MESSAGE_TYPE, payload)) {
            Log.e(TAG, TAGClass + "sendData : fail to sendData");
            return false;
        }

        return true;
    }

    // Send data message to the all nodes on the channel.
    public boolean sendDataToAll(String toChannel, byte[] buf) {
        if (this.chordManager == null) {
            Log.v(TAG, "sendDataToAll : chordManager IS NULL  !!");
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

        /*
         * @param payloadType User defined message type. It is mandatory.
         * @param payload The package of data to send
         * @return Returns true when file transfer is success. Otherwise, false
         * is returned.
         */
        if (false == channel.sendDataToAll(CHORD_APITEST_MESSAGE_TYPE, payload)) {
            Log.e(TAG, TAGClass + "sendDataToAll : fail to sendDataToAll");
            return false;
        }

        return true;
    }
}
