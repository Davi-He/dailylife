package com.dailylife.dailylife;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.sql.*;

public class MainActivity extends Activity {
	// private Bundle
	private static String TAG = "dailylife";
	private Button sendBtn = null;
	private ListView lvListView = null;
	private EditText eText = null;
	private Connection conn = null;
	public Handler mHandle = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			Log.i(TAG, "handleMessage :" + msg.what);
			switch (msg.what) {
			case 0:
				Toast.makeText(getApplicationContext(), "connect err",
						Toast.LENGTH_SHORT).show();
				break;
			case 1:
				Toast.makeText(getApplicationContext(), "connected",
						Toast.LENGTH_SHORT).show();
				break;

			default:
				break;
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		sendBtn = (Button) this.findViewById(R.id.button_send);
		lvListView = (ListView) this.findViewById(R.id.in);
		eText = (EditText) this.findViewById(R.id.edit_text_out);

		// connectServer();

		sendBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.i(TAG, "===============onClick!");
				// mHandle.sendEmptyMessage(1);
				if (eText.getText() != null) {
					// Dbapi.sendMessage(conn, eText.getText().toString());
					new Thread(new Runnable() {
						
						@Override
						public void run() {
							// TODO Auto-generated method stub
							Dbapi.getMessage();
						}
					}).start();
					// try {
					// Dbapi.getres(conn);
					// } catch (SQLException e) {
					// // TODO Auto-generated catch block
					// e.printStackTrace();
					// }
				}
			}
		});
	}

	private void connectServer() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					conn = Dbapi.connect();
					mHandle.sendEmptyMessage(1);
				} catch (Exception e) {
					mHandle.sendEmptyMessage(0);
					// Dbapi.printErrorMessage(e);
					e.printStackTrace();
				}
			}
		}).start();
	}
}
