package com.honeybadgers.fropandroid;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.maps.GeoPoint;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

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

		String name = event.get(TAG_TITLE);
		final String eventId = event.get(TAG_EVENT_ID);
		String date = event.get(TAG_DATE);
		String orgId = event.get(TAG_ORG_ID);
		String address = event.get(TAG_ADDRESS);
		String description = event.get(TAG_SUMMARY);

		TextView lblName = (TextView) findViewById(R.id.name_label);
		TextView lblEventId = (TextView) findViewById(R.id.eventId_label);
		TextView lblDate = (TextView) findViewById(R.id.date_label);
		TextView lblOrgID = (TextView) findViewById(R.id.orgId_label);
		TextView lblAddress = (TextView) findViewById(R.id.address_label);
		TextView lblSummary = (TextView) findViewById(R.id.summary_label);
		save = (CheckBox) findViewById(R.id.checkInterested);
		if(MainActivity.user.likedEvents.contains(eventId))
			save.setChecked(true);
		try {
			// Loading map
			initilizeMap();
			
			Marker hamburg = map.addMarker(new MarkerOptions().position(new LatLng(33.77727, -84.38913))
			        .title("Hamburg"));
			    Marker kiel = map.addMarker(new MarkerOptions()
			        .position(new LatLng(33.77727, -84.38913))
			        .title("CIC")
			        .snippet("CIC")
			        .icon(BitmapDescriptorFactory
			            .fromResource(R.drawable.ic_launcher)));

			    // Move the camera instantly to hamburg with a zoom of 15.
			    map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(33.77727, -84.38913), 15));

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
		lblEventId.setText(eventId);
		lblDate.setText(date);
		lblOrgID.setText(orgId);
		lblAddress.setText(address);
		lblSummary.setText(description);
		
		save.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			   @Override
			   public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
				   if (save.isChecked()){
					   MainActivity.user.likedEvents.add(eventId);
					   Serial(MainActivity.user);
					   Log.d("Check Box", "Box was checked Item was added");
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

	// private void initilizeMap() {
	// if (map == null) {
	// Log.d("Detail", "Made is here 2");
	//
	// mapFrag = (SupportMapFragment) getSupportFragmentManager()
	// .findFragmentById(R.id.map);
	// map = mapFrag.getMap();
	// // check if map is created successfully or not
	// if (map == null) {
	// Toast.makeText(getApplicationContext(),
	// "Sorry! unable to create maps", Toast.LENGTH_SHORT)
	// .show();
	// }
	// }
	// }

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
	
	public static void getLocationInfo(String address) {
        StringBuilder stringBuilder = new StringBuilder();
        try {

        address = address.replaceAll(" ","%20");    

        HttpPost httppost = new HttpPost("http://maps.google.com/maps/api/geocode/json?address=" + address + "&sensor=false");
        HttpClient client = new DefaultHttpClient();
        HttpResponse response;
        stringBuilder = new StringBuilder();


            response = client.execute(httppost);
            HttpEntity entity = response.getEntity();
            InputStream stream = entity.getContent();
            int b;
            while ((b = stream.read()) != -1) {
                stringBuilder.append((char) b);
            }
        } catch (ClientProtocolException e) {
        } catch (IOException e) {
        }

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = new JSONObject(stringBuilder.toString());
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        getLatLong(jsonObject);
    }
	
	public static boolean getLatLong(JSONObject jsonObject) {

        try {

            longitute = ((JSONArray)jsonObject.get("results")).getJSONObject(0)
                .getJSONObject("geometry").getJSONObject("location")
                .getDouble("lng");

            latitude = ((JSONArray)jsonObject.get("results")).getJSONObject(0)
                .getJSONObject("geometry").getJSONObject("location")
                .getDouble("lat");

        } catch (JSONException e) {
            return false;

        }

        return true;
    }

	@Override
	protected void onResume() {
		super.onResume();
		initilizeMap();
	}
}
