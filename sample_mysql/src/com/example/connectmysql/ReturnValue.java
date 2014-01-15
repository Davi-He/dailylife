package com.example.connectmysql;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ReturnValue extends Activity{
	private EditText edi1;
	private Button but1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.returnvalue);
		edi1 = (EditText)findViewById(R.id.editText7);
		but1 = (Button)findViewById(R.id.button2);
		edi1.setText(MainActivity.result);

	    but1.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					MainActivity.result="";
					ReturnValue.this.finish();
				}
	    });
	}
}
