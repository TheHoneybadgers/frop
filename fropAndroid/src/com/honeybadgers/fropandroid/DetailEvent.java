package com.honeybadgers.fropandroid;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class DetailEvent extends FragmentActivity {
	CheckBox save;

	// JSON node keys
	private static final String TAG_TITLE = "TITLE";
	private static final String TAG_EVENT_ID = "EVENT_ID";
	private static final String TAG_DATE = "DATE";
	private static final String TAG_ORG_ID = "ORG_ID";
	// private static final String TAG_FOURSQUARE = "FOURSQUARE";
	private static final String TAG_ADDRESS = "ADDRESS";
	private static final String TAG_SUMMARY = "SUMMARY";
	private static final String TAG_START_TIME = "START_TIME";
	private static final String TAG_END_TIME = "END_TIME";
	private GoogleMap map;
	SupportMapFragment mapFrag;
	static double longitute;
	static double latitude;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.event_details);

		// getting intent data
		Intent in = getIntent();
		@SuppressWarnings("unchecked")
		HashMap<String, String> event = (HashMap<String, String>) in
				.getSerializableExtra("hashmap");

		final String name = event.get(TAG_TITLE);
		final String eventId = event.get(TAG_EVENT_ID);
		final String date = event.get(TAG_DATE);
		String orgId = event.get(TAG_ORG_ID);
		final String address = event.get(TAG_ADDRESS);
		final String description = event.get(TAG_SUMMARY);
		final String startTime = event.get(TAG_START_TIME);
		final String endTime = event.get(TAG_END_TIME);

		TextView lblName = (TextView) findViewById(R.id.name_label);
		TextView lblEventId = (TextView) findViewById(R.id.eventId_label);
		TextView lblDate = (TextView) findViewById(R.id.date_label);
		TextView lblOrgID = (TextView) findViewById(R.id.orgId_label);
		TextView lblAddress = (TextView) findViewById(R.id.address_label);
		TextView lblSummary = (TextView) findViewById(R.id.summary_label);
		save = (CheckBox) findViewById(R.id.checkInterested);
		
		LatLong latLong = new LatLong(address);
		latLong.getLocationInfo(); //latLong now should have a lat and long for the address
		
		if(MainActivity.user.likedEvents.contains(eventId))
			save.setChecked(true);
		try {
			// Loading map
			initilizeMap();
			
//			Marker mark = map.addMarker(new MarkerOptions().position(new LatLng(latLong.lat, latLong.lon))
//			        .title("Hamburg"));
			    Marker kiel = map.addMarker(new MarkerOptions()
			        .position(new LatLng(latLong.lat, latLong.lon))
			        .title(name)
			        .snippet(orgId));
			       

			    // Move the camera instantly to hamburg with a zoom of 15.
			    map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latLong.lat, latLong.lon), 15));

			    // Zoom in, animating the camera.
			    map.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);

		} catch (Exception e) {
			final Intent intent = new Intent(
					Intent.ACTION_VIEW,
					Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.gms"));
			intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
			startActivity(intent);
			// e.printStackTrace();
			Log.d("Detail", e.toString());
		}
		Log.d("MAP", address);
		//getLocationInfo(address);
		Log.d("MAP", String.valueOf(latitude) + " " + String.valueOf(longitute));

		lblName.setText(name);
		//lblEventId.setText( eventId);
		lblDate.setText("When: "+date);
		lblOrgID.setText("Hosted by: " + orgId);
		lblAddress.setText("Where: " + address);
		lblSummary.setText(description);
		
		save.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			   @Override
			   public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
				   
				   Date fulldate = null;

			        try {
			            fulldate = new SimpleDateFormat("yyyy-MM-dd-HH:mm").parse(date+"-"+"12:00");
			            Log.d("date", fulldate.toString()+ " Starttime:"+ startTime);
			        } catch (ParseException e) {
			            e.printStackTrace();
			        }
				   
				   if (save.isChecked()){
					   MainActivity.user.likedEvents.add(eventId);
					   Serial(MainActivity.user);
					   Log.d("Check Box", "Box was checked Item was added");
					   Calendar cal = Calendar.getInstance();              
					   Intent intent = new Intent(Intent.ACTION_EDIT);
					   intent.setType("vnd.android.cursor.item/event");
					   intent.putExtra("beginTime", fulldate.getTime());
					   intent.putExtra("allDay", false);
					   //intent.putExtra("rrule", "FREQ=YEARLY");
					   intent.putExtra("endTime", fulldate.getTime()+60*60*1000);
					   intent.putExtra("eventLocation", address);
					   intent.putExtra("description", description);
					   intent.putExtra("title", name);
					   startActivity(intent);
				   }
				   else {
					   if (MainActivity.user.likedEvents.contains(eventId))
						   MainActivity.user.likedEvents.remove(eventId);
					   		Serial(MainActivity.user);
					   		Log.d("Check Box", "Box was unchecked Item was deleted");
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

	protected void Serial(UserCache a) {
		FileOutputStream fos;
		try {
			fos = openFileOutput("user.dat", Context.MODE_PRIVATE);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(a);
			oos.close();
			Toast.makeText(this, "Account settings were saved",
					Toast.LENGTH_SHORT).show();
		} catch (FileNotFoundException e) {
			Toast.makeText(this, "That File Doesn't Exist", Toast.LENGTH_SHORT)
					.show();
			// File file = new File("user.dat");
			e.printStackTrace();
		} catch (IOException e) {
			Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
			e.printStackTrace();
	   		Log.d("Check Box", String.valueOf(e));
		}
	}

	private void initilizeMap() {
		if (map == null) {
			map = ((MapFragment) getFragmentManager()
					.findFragmentById(R.id.map)).getMap();

			// check if map is created successfully or not
			if (map == null) {
				Toast.makeText(getApplicationContext(),
						"Sorry! unable to create maps", Toast.LENGTH_SHORT)
						.show();
			}
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		initilizeMap();
	}
}
