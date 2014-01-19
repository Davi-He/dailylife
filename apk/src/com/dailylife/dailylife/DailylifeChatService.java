package com.dailylife.dailylife;

import android.R.integer;
import android.content.Context;
import android.os.Handler;
import android.util.Log;

public class DailylifeChatService {
	private static final String TAG = "DailylifeChatService";
	private static final boolean D = true;

	private final Handler mHandler;
	private int mState = 0;
	public static final int STATE_NONE = 0; // we're doing nothing
	// public static final int STATE_LISTEN = 1; // now listening for incoming
	// connections
	public static final int STATE_CONNECTING = 2; // now initiating an outgoing
													// connection
	public static final int STATE_CONNECTED = 3; // now connected to a remote
													// device

	public DailylifeChatService(Context context, Handler handler) {
		mHandler = handler;
		mState = STATE_NONE;
	}

	private synchronized void setState(int state) {
		if (D)
			Log.d(TAG, "setState() " + mState + " -> " + state);
		mState = state;
	}

	public synchronized int getState() {
		return mState;
	}
	
	public synchronized void connect(){
		
	}
	
	private class sendMessageThread extends Thread{
		
	}
	private class getMessageThread extends Thread{
		
	}
}
