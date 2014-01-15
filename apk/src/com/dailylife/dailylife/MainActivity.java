package com.dailylife.dailylife;

import android.app.Activity;
import android.os.Bundle;
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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		sendBtn = (Button) this.findViewById(R.id.button_send);
		lvListView = (ListView) this.findViewById(R.id.in);
		eText = (EditText) this.findViewById(R.id.edit_text_out);

		// // try {
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					conn = Dbapi.connect();
				} catch (Exception e) {
					Dbapi.printErrorMessage(e);
					e.printStackTrace();
				}
			}
		});
		Log.i(TAG, "connect mysql ok!");
		// } catch (Exception e) {
		// // TODO Auto-generated catch block
		// // Dbapi.printErrorMessage(e);
		// e.printStackTrace();
		// Toast.makeText(getApplicationContext(), "connect err",
		// Toast.LENGTH_SHORT).show();
		// }

		sendBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.i(TAG, "===============onClick!");
				if (conn == null) {
					new Thread(new Runnable() {

						@Override
						public void run() {
							try {
								conn = Dbapi.connect();
							} catch (Exception e) {
								Dbapi.printErrorMessage(e);
								e.printStackTrace();
							}
						}
					});
				}
				if (conn == null) {
					Toast.makeText(getApplicationContext(), "connect err",
							Toast.LENGTH_SHORT).show();
				}

			}
		});
	}

	private static void connectServer() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					//conn = Dbapi.connect();
				} catch (Exception e) {
					Dbapi.printErrorMessage(e);
					e.printStackTrace();
				}
			}
		});
	}
}
