package com.example.yamba;

import winterwell.jtwitter.Twitter;
import winterwell.jtwitter.TwitterException;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class StatusActivity extends Activity implements OnClickListener {

	EditText statusEdit;
	Button updateBtn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_status);
		
		statusEdit = (EditText) findViewById(R.id.txtUpdateStatus);
		updateBtn = (Button) findViewById(R.id.btnUpdateStatus);
		
		updateBtn.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.status, menu);
		return true;
	}

	@Override
	public void onClick(View buttton) {
		String statusText = statusEdit.getText().toString();
		String result = "";
		try {
			Twitter twitter = new Twitter("student", "password");
            twitter.setAPIRootUrl("http://yamba.marakana.com/api");
			
			switch (buttton.getId()) {
				case R.id.btnUpdateStatus:
					twitter.setStatus(statusText);
					result = "Message posted successfully";
					break;
			}
		} catch (TwitterException e) {
			Log.e("Yamba", "Can't connect to server", e);
		}	
		
		Toast.makeText(this, result, Toast.LENGTH_LONG).show();
		Log.d("Yamba", "onClicked with: " +statusText);
		
	}

}
