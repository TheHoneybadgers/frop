package com.honeybadgers.fropandroid;

import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

public class DetailEvent  extends Activity {
	
	// JSON node keys
	private static final String TAG_TITLE = "TITLE";
	private static final String TAG_EVENT_ID = "EVENT_ID";
	private static final String TAG_DATE = "DATE";
	private static final String TAG_ORG_ID = "ORG_ID";
	//private static final String TAG_FOURSQUARE = "FOURSQUARE";
	private static final String TAG_ADDRESS = "ADDRESS";
	private static final String TAG_SUMMARY = "SUMMARY";
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_details);
        
        // getting intent data
        Intent in = getIntent();
        @SuppressWarnings("unchecked")
		HashMap<String, String> event = (HashMap<String, String> )in.getSerializableExtra("hashmap");
        
        String name = event.get(TAG_TITLE);
        String eventId = event.get(TAG_EVENT_ID);
        String date = event.get(TAG_DATE);
        String orgId = event.get(TAG_ORG_ID);
        String address = event.get(TAG_ADDRESS);
        String description = event.get(TAG_SUMMARY);
        
        // Get JSON values from previous intent
//        String name = in.getStringExtra(TAG_TITLE);
//        String cost = in.getStringExtra(TAG_DATE);
//        String description = in.getStringExtra(TAG_SUMMARY);

       
        // Displaying all values on the screen
        TextView lblName = (TextView) findViewById(R.id.name_label);
        TextView lblEventId = (TextView) findViewById(R.id.eventId_label);
        TextView lblDate = (TextView) findViewById(R.id.date_label);
        TextView lblOrgID = (TextView) findViewById(R.id.orgId_label);
        TextView lblAddress = (TextView) findViewById(R.id.address_label);
        TextView lblSummary = (TextView) findViewById(R.id.summary_label);
        
        lblName.setText(name);
        lblEventId.setText(eventId);
        lblDate.setText(date);
        lblOrgID.setText(orgId);
        lblAddress.setText(address);
        lblSummary.setText(description);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
