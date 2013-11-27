package com.honeybadgers.fropandroid;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class EventFragment extends ListFragment {
	
	// url to make request
		private static String url = "http://dev.m.gatech.edu/d/tpeet3/api/fropWeb/events";
		
		// JSON Node names
		private static final String TAG_EVENT_ID = "EVENT_ID";
		private static final String TAG_DATE = "DATE";
		private static final String TAG_ORG_ID = "ORG_ID";
		private static final String TAG_TITLE = "TITLE";
		private static final String TAG_FOURSQUARE = "FOURSQUARE";
		private static final String TAG_ADDRESS = "ADDRESS";
		private static final String TAG_START_TIME = "START_TIME";
		private static final String TAG_END_TIME = "END_TIME";
		private static final String TAG_APPROVED_DATE = "APPROVED_DATE";
		private static final String TAG_SUMMARY = "SUMMARY";
		private static final String TAG_TYPE = "TYPE";
		private static final String TAG_SPECIAL_NOTES = "SPECIAL_NOTES";
		private static final String TAG_ALCOHOL = "ALCOHOL";
		private static final String TAG_CREATED_DATE = "CREATED_DATE";
		private static final String TAG_DATE_CHANGED = "DATE_CHANGED";

		JSONArray events = null;
		ArrayList<HashMap<String, String>> eventList;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		 eventList = new ArrayList<HashMap<String, String>>();
		
		//Pass the URL to the JSON parser and get a JSON Array in response
		JsonParser jParser = new JsonParser();
		
		
		events = jParser.getJSONFromUrl(url);

		try {
			
			Log.d("length of Json array",Integer.toString(events.length()));
			for(int i = 0; i < events.length(); i++){
				JSONObject c = events.getJSONObject(i);
				String eventId = c.getString(TAG_EVENT_ID);
				String date = c.getString(TAG_DATE);
				String orgId = c.getString(TAG_ORG_ID);
				String fourSquare = c.getString(TAG_FOURSQUARE);
				String address = c.getString(TAG_ADDRESS);
				String descrip = c.getString(TAG_SUMMARY);
				String title = c.getString(TAG_TITLE);
				String type = c.getString(TAG_TYPE);
				HashMap<String, String> map = new HashMap<String, String>();
				map.put(TAG_EVENT_ID, eventId);//add the tag and variable to the HASH
				map.put(TAG_DATE, date);
				map.put(TAG_ORG_ID, orgId);
				map.put(TAG_FOURSQUARE, fourSquare);
				map.put(TAG_ADDRESS, address);
				map.put(TAG_SUMMARY, descrip);
				map.put(TAG_TITLE, title);
				map.put(TAG_TYPE, type);
				eventList.add(map);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_event, container, false);
		
		ListAdapter adapter = new SimpleAdapter(getActivity(), eventList,
				R.layout.list_item,
				new String[] { TAG_TYPE, TAG_TITLE, TAG_DATE, TAG_SUMMARY }, new int[] {
						R.id.eventType, R.id.eventName, R.id.eventDate, R.id.eventDescrip
						});//Parsed JSON into an actual list
		setListAdapter(adapter);
		
		return view;
	}
	
	@Override
	public void onResume() {
		
		ListView lv = getListView();
		lv.setOnItemClickListener(new OnItemClickListener() {					//Goto the details of the event

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Log.d("Position of clicked item", Integer.toString(position));
				
				Intent in = new Intent(getActivity().getApplicationContext(), DetailEvent.class);
				
				//pass the hashmap entry for an event to the detailevent class
				in.putExtra("hashmap", eventList.get(position));
				Log.d("EVENT LIST PASSED: ", String.valueOf(eventList.get(position)));

				startActivity(in);

			}
		});
		
		super.onResume();
	}

}
