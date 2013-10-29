package com.honeybadgers.fropandroid;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.app.Activity;
import android.content.Intent;

public class Login extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		Intent intent = getIntent();
	    // To get the action of the intent use
	    String action = intent.getAction();

	    if (!action.equals(Intent.ACTION_VIEW)) {
	    	Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://vanguard0.nimbus.cip.gatech.edu/login?url=usercomments://loggedin&sessionTransfer=window"));
	    	startActivity(myIntent);
	    }
	    else
	    {
	    	Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
	    	myIntent.setData(intent.getData());
			startActivity(myIntent);
	    }
		
		

	}
}