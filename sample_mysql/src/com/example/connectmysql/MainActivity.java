package com.example.connectmysql;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;


import android.os.Bundle;
import android.os.StrictMode;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
	private EditText edi1;
	private EditText edi2;
	private EditText edi3;
	private EditText edi4;
	private EditText edi5;
	private EditText edi6;
	//private EditText edi7;
	private Button but1;
	private TextView txt1;
	private Intent intent;
	public static String result = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
	    StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()  
        .detectDiskReads().detectDiskWrites().detectNetwork()  
        .penaltyLog().build());  

	    StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()  
        .detectLeakedSqlLiteObjects().penaltyLog().penaltyDeath()  
        .build());   
		
		edi1 = (EditText)findViewById(R.id.editText1);
		edi2 = (EditText)findViewById(R.id.editText2);
		edi3 = (EditText)findViewById(R.id.editText3);
		edi4 = (EditText)findViewById(R.id.editText4);
		edi5 = (EditText)findViewById(R.id.editText5);
		edi6 = (EditText)findViewById(R.id.editText6);
		//edi7 = (EditText)findViewById(R.id.editText7);
		but1 = (Button)findViewById(R.id.button1);
		txt1 = (TextView)findViewById(R.id.textView1);
		
	       but1.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					String ip =edi1.getText().toString();
					String port =edi2.getText().toString();
					String data =edi3.getText().toString();
					String user =edi4.getText().toString();
					String password =edi5.getText().toString();
					String sql =edi6.getText().toString();
					
					//是否为空?
					if(ip.equals("")||port.equals("")||data.equals("")||user.equals("")||sql.equals("")||password.equals("")){
						txt1.setText("请将信息填写完整后提交！");
					}
					else{
						String driver="com.mysql.jdbc.Driver";
						String url="jdbc:mysql://"+ip+":"+port+"/"+data;
						WebRs mysql=new WebRs(driver,user,password,url);
				        ResultSet rs=mysql.getres(sql);
				        try {
					        ResultSetMetaData rsmd = rs.getMetaData() ; 
					        int columnCount = rsmd.getColumnCount();
					        for(int i=0;i<columnCount;i++)
					        	result = result + rsmd.getColumnName(i+1)+"\t";
					        result = result +"\n" ;
							while(rs.next()){
								for(int i=1;i<=columnCount;i++)
									result = result + rs.getString(i)+"\t";
								result = result +"\n" ;
							}
							intent=new Intent(MainActivity.this,ReturnValue.class);
							startActivity(intent);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							txt1.setText(e.toString());
							e.printStackTrace();
						}

					}
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
