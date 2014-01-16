package com.test.touchcount;

import java.text.SimpleDateFormat;
import java.util.concurrent.CountDownLatch;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class TouchCount extends Activity {

	private Button btnButton = null;
	private int count = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.activity_main);

		View decorView = getWindow().getDecorView();
		// Hide both the navigation bar and the status bar.
		// SYSTEM_UI_FLAG_FULLSCREEN is only available on Android 4.1 and
		// higher, but as
		// a general rule, you should design your app to hide the status bar
		// whenever you
		// hide the navigation bar.
		int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
				| View.SYSTEM_UI_FLAG_FULLSCREEN;
		decorView.setSystemUiVisibility(uiOptions);

		decorView
				.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
					@Override
					public void onSystemUiVisibilityChange(int visibility) {
						// Note that system bars will only be "visible" if none
						// of the
						// LOW_PROFILE, HIDE_NAVIGATION, or FULLSCREEN flags are
						// set.
						if ((visibility & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0) {
							// TODO: The system bars are visible. Make any
							// desired
							// adjustments to your UI, such as showing the
							// action bar or
							// other navigational controls.
							Log.i("adfdfa","aaaaaaa");
						} else {
							// TODO: The system bars are NOT visible. Make any
							// desired
							// adjustments to your UI, such as hiding the action
							// bar or
							// other navigational controls.
							Log.i("adfdfa","aaaaaa");
						}
					}
				});

		btnButton = (Button) this.findViewById(R.id.btn);
		btnButton.setOnClickListener(new View.OnClickListener() {

			SimpleDateFormat sDateFormat = new SimpleDateFormat(
					"yyyy-MM-dd   hh:mm:ss");
			String date = sDateFormat.format(new java.util.Date());

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				count++;
				btnButton.setText(count + "\n\n\n" + date.toString());
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
