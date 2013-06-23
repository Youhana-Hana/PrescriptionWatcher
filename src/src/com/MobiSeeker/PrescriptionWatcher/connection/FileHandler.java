package com.MobiSeeker.PrescriptionWatcher.connection;

import android.util.Log;

import com.samsung.chord.ChordManager;
import com.samsung.chord.IChordChannel;

public class FileHandler {

    private static final String TAG = "[Chord][ApiService]";

    private static final String TAGClass = "FileHandler : ";

    private static final String MESSAGE_TYPE_FILE_NOTIFICATION = "FILE_NOTIFICATION_V2";

    private static final long SHARE_FILE_TIMEOUT_MILISECONDS = 1000 * 60 * 5;

    protected ChordManager chordManager;

    public FileHandler(ChordManager chordManager) {
        this.chordManager = chordManager;
    }

    // Send file to the node on the channel.
    public String sendFile(String toChannel, String strFilePath, String toNode) {
        Log.d(TAG, TAGClass + "sendFile() ");

        // Request the channel interface for the specific channel name.
        IChordChannel channel = this.chordManager.getJoinedChannel(toChannel);
        if (null == channel) {
            Log.e(TAG, TAGClass + "sendFile() : invalid channel instance");
            return null;
        }
        /*
         * @param toNode The node name that the file is sent to. It is
         * mandatory.
         * @param fileType User defined file type. It is mandatory.
         * @param filePath The absolute path of the file to be transferred. It
         * is mandatory.
         * @param timeoutMsec The time to allow the receiver to accept the
         * receiving data requests.
         */
        return channel.sendFile(toNode, MESSAGE_TYPE_FILE_NOTIFICATION, strFilePath,
                SHARE_FILE_TIMEOUT_MILISECONDS);
    }

    // Accept to receive file.
    public boolean acceptFile(String fromChannel, String exchangeId) {
        Log.d(TAG, TAGClass + "acceptFile()");
        // Request the channel interface for the specific channel name.
        IChordChannel channel = this.chordManager.getJoinedChannel(fromChannel);
        if (null == channel) {
            Log.e(TAG, TAGClass + "acceptFile() : invalid channel instance");
            return false;
        }

        /*
         * @param exchangeId Exchanged ID
         * @param chunkTimeoutMsec The timeout to request the chunk data.
         * @param chunkRetries The count that allow to retry to request chunk
         * data.
         * @param chunkSize Chunk size
         */
        return channel.acceptFile(exchangeId, 30*1000, 2, 300 * 1024);
    }

    // Cancel file transfer while it is in progress.
    public boolean cancelFile(String channelName, String exchangeId) {
        Log.d(TAG, TAGClass + "cancelFile()");
        // Request the channel interface for the specific channel name.
        IChordChannel channel = this.chordManager.getJoinedChannel(channelName);
        if (null == channel) {
            Log.e(TAG, TAGClass + "cancelFile() : invalid channel instance");
            return false;
        }

        // @param exchangeId Exchanged ID
        return channel.cancelFile(exchangeId);
    }

    // Reject to receive file.
    public boolean rejectFile(String fromChannel, String coreTransactionId) {
        Log.d(TAG, TAGClass + "rejectFile()");
        // Request the channel interface for the specific channel name.
        IChordChannel channel = this.chordManager.getJoinedChannel(fromChannel);
        if (null == channel) {
            Log.e(TAG, TAGClass + "cancelFile() : invalid channel instance");
            return false;
        }

        // @param exchangeId Exchanged ID
        return channel.rejectFile(coreTransactionId);
    }
}
