package com.MobiSeeker.PrescriptionWatcher.connection;

import android.util.Log;

import com.samsung.chord.ChordManager;
import com.samsung.chord.IChordChannel;
import com.samsung.chord.IChordChannelListener;

import java.util.List;

public class NodeManager {

    public static String CHORD_API_CHANNEL = "com.MobiSeeker.PrescriptionWatcher";

    private static final String TAG = "[Chord][ApiService]";

    private static final String TAGClass = "NodeManager : ";

    protected ChordManager chordManager = null;

    protected IChordChannelListener chordChannelListener = null;

    protected ChannelInformation channelInformation = null;

    public NodeManager(ChordManager chordManager, IChordChannelListener chordChannelListener, ChannelInformation channelInformation) {
        this.chordManager = chordManager;

        this.chordChannelListener = chordChannelListener;

        this.channelInformation = channelInformation;
    }

    // Requests for nodes on the channel.
    public List<String> getJoinedNodeList(String channelName) {
        Log.d(TAG, TAGClass + "getJoinedNodeList()");
        // Request the channel interface for the specific channel name.
        IChordChannel channel = this.chordManager.getJoinedChannel(channelName);
        if (null == channel) {
            Log.e(TAG, TAGClass + "getJoinedNodeList() : invalid channel instance-" + channelName);
            return null;
        }

        return channel.getJoinedNodeList();
    }

    /*
     * Set a keep-alive timeout. Node has keep-alive timeout. The timeoutMsec
     * determines the maximum keep-alive time to wait to leave when there is no
     * data from the nodes. Default time is 15000 millisecond.
     */
    public void setNodeKeepAliveTimeout(long timeoutMsec) {
        Log.d(TAG, TAGClass + "setNodeKeepAliveTimeout()");
        // @param timeoutMsec Timeout with millisecond.
        this.chordManager.setNodeKeepAliveTimeout(timeoutMsec);
    }

    // Get an IPv4 address that the node has.
    public String getNodeIpAddress(String channelName, String nodeName) {
        Log.d(TAG, TAGClass + "getNodeIpAddress() channelName : " + channelName + ", nodeName : "
                + nodeName);
        // Request the channel interface for the specific channel name.
        IChordChannel channel = this.chordManager.getJoinedChannel(channelName);
        if(null == channel){
            Log.e(TAG, TAGClass + "getNodeIpAddress : invalid channel instance");
            return "";
        }

        /*
         * @param nodeName The node name to find IPv4 address.
         * @return Returns an IPv4 Address.When there is not the node name in
         * the channel, null is returned.
         */
        return channel.getNodeIpAddress(nodeName);
    }

    // Request for joined channel interfaces.
    public List<IChordChannel> getJoinedChannelList() {
        Log.d(TAG, TAGClass + "getJoinedChannelList()");
        // @return Returns a list of handle for joined channel. It returns an
        // empty list, there is no joined channel.
        return this.chordManager.getJoinedChannelList();
    }

    // Join a desired channel with a given listener.
    public IChordChannel joinChannel(String channelName) {
        Log.d(TAG, TAGClass + "joinChannel()" + channelName);
        if (channelName == null || channelName.equals("")) {
            Log.e(TAG, TAGClass + "joinChannel > " + channelName
                    + " is invalid! Default private channel join");
            this.channelInformation.setPrivateChannel(CHORD_API_CHANNEL);
        } else {
            this.channelInformation.setPrivateChannel(channelName);
        }

        /*
         * @param channelName Channel name. It is a mandatory input.
         * @param listener A listener that gets notified when there is events in
         * joined channel mandatory. It is a mandatory input.
         * @return Returns a handle of the channel if it is joined successfully,
         * null otherwise.
         */
        IChordChannel channelInst = this.chordManager.joinChannel(channelName, this.chordChannelListener);

        if (null == channelInst) {
            Log.d(TAG, "fail to joinChannel! ");
            return null;
        }

        return channelInst;
    }

    // Leave a given channel.
    public void leaveChannel() {
        Log.d(TAG, TAGClass + "leaveChannel()");
        // @param channelName Channel name
        this.chordManager.leaveChannel(this.channelInformation.getPrivateChannel());
        this.channelInformation.setPrivateChannel("");
    }
}
