package com.dailylife.dailylife;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
	// private Bundle
	private static String TAG = "dailylife";
	private Button sendBtn = null;
	private ListView lvListView = null;
	private EditText eText = null;
	private ArrayAdapter<String> arrayAdapter;
	private List<String> listStrings;
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
		listStrings = new ArrayList<String>();
		arrayAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_expandable_list_item_1, getData());
		lvListView.setAdapter(arrayAdapter);
		// setContentView(lvListView);

		sendBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.i(TAG, "===============onClick!");
				// mHandle.sendEmptyMessage(1);
				if (!"".equals(eText.getText().toString().trim())) {
					Log.i(TAG, "===============send message! :"
							+ eText.getText().toString());
					// Dbapi.sendMessage(conn, eText.getText().toString());
					listStrings.add(eText.getText().toString());
					arrayAdapter.notifyDataSetChanged();

					new Thread(new Runnable() {
						@Override
						public void run() {
							// TODO Auto-generated method stub
							// Dbapi.getMessage();
							Dbapi.sendMessage(eText.getText().toString(),
									MainActivity.this);
							// Dbapi.getMessage();
							// ListView.
						}
					}).start();
					eText.setText("");
				}
			}
		});
	}

	private List<String> getData() {
		listStrings.add("1");
		return listStrings;
	}
}
