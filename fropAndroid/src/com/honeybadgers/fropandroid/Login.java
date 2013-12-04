package com.honeybadgers.fropandroid;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

public class Login extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		Log.d("Enter", "in login activity");

		Intent intent1 = this.getIntent();
		// To get the action of the intent use
		String action = intent1.getAction();
		
		
		if (action.equals("LOGIN")) {
			Log.d("Enter", "Intent creator" + intent1.toString());
			
			Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://dev.m.gatech.edu/login?url=usercomments://loggedin&sessionTransfer=window"));
			myIntent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);    					  //So that when you press back from the main activity, it dosent go back to the browser
			startActivity(myIntent);
		} else if(action.equals("LOGOUT")){
			
Log.d("Enter", "Logging out");
			
			Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://login.gatech.edu/cas/logout"));
			myIntent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);    					  //So that when you press back from the main activity, it dosent go back to the browser
			startActivity(myIntent);
			
			
			
		}else {
			Log.d("Enter", "Intent creator" + intent1.toString());
			Log.d("Enter", "Starting main activity");
			Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
			
			// Closing all the Activities
			myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

			// Add new Flag to start new Activity
			myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			myIntent.setAction("Login complete");
			myIntent.setData(intent1.getData());
			Login.this.finish();
			startActivity(myIntent);

		}

	}
}