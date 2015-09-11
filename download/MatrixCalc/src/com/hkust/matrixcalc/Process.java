package com.hkust.matrixcalc;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;

public class Process extends ActionBarActivity{
	private TextView process;
	private String text;
	 protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.process);
	        Bundle bundle=this.getIntent().getExtras();
	        text=bundle.getString("process");
	        process=(TextView)findViewById(R.id.text_process);
	        process.setText(text);
	        
	 }
}
