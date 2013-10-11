package com.honeybadgers.fropandroid;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import com.honeybadgers.fropandroid.DetailEvent;

import com.honeybadgers.fropandroid.JsonParser;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class EventFragment extends ListFragment {
	
	// url to make request
		private static String url = "http://frop.apiary.io/events/this_week";
		
		// JSON Node names
		private static final String TAG_EVENTS = "events";
		private static final String TAG_TITLE = "TITLE";
		private static final String TAG_EVENT_ID = "EVENT_ID";
		private static final String TAG_DATE = "DATE";
		private static final String TAG_ORG_ID = "ORG_ID";
		private static final String TAG_FOURSQUARE = "FOURSQUARE";
		private static final String TAG_ADDRESS = "ADDRESS";
		private static final String TAG_START_TIME = "START_TIME";
		private static final String TAG_END_TIME = "END_TIME";
		private static final String TAG_APPROVED_DATE = "APPROVED_DATE";
		private static final String TAG_SUMMARY = "SUMMARY";
		private static final String TAG_TYPE = "TYPE";
		private static final String TAG_SPECIAL_NOTES = "SPECIAL_NOTES";
		private static final String TAG_ALCOHOL = "ALCOHOL";
		private static final String TAG_DATE_CHANGED = "DATE_CHANGED";

		JSONArray events = null;
		ArrayList<HashMap<String, String>> eventList;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		 eventList = new ArrayList<HashMap<String, String>>();
		
		//Pass the URL to the JSON parser and get a JSON object in response
		JsonParser jParser = new JsonParser();
		JSONObject json = jParser.getJSONFromUrl(url);

		try {
			events = json.getJSONArray(TAG_EVENTS);//grab all the events
			for(int i = 0; i < events.length(); i++){
				JSONObject c = events.getJSONObject(i);
				String eventId = c.getString(TAG_EVENT_ID);
				String date = c.getString(TAG_DATE);
				String orgId = c.getString(TAG_ORG_ID);
				String fourSquare = c.getString(TAG_FOURSQUARE);
				String address = c.getString(TAG_ADDRESS);
				String descrip = c.getString(TAG_SUMMARY);
				String title = c.getString(TAG_TITLE);
				HashMap<String, String> map = new HashMap<String, String>();
				map.put(TAG_EVENT_ID, eventId);//add the tag and variable to the HASH
				map.put(TAG_DATE, date);
				map.put(TAG_ORG_ID, orgId);
				map.put(TAG_FOURSQUARE, fourSquare);
				map.put(TAG_ADDRESS, address);
				map.put(TAG_SUMMARY, descrip);
				map.put(TAG_TITLE, title);
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
				new String[] { TAG_TITLE, TAG_DATE, TAG_SUMMARY }, new int[] {
						R.id.eventName, R.id.eventDate, R.id.eventDescrip });//Parsed JSON into an actual list
		setListAdapter(adapter);
		
		
		
		return view;
	}
	
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		ListView lv = getListView();

		lv.setOnItemClickListener(new OnItemClickListener() {//Goto the details of the event

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// getting values from selected ListItem
				String eventName = ((TextView) view.findViewById(R.id.eventName)).getText().toString();
				String date = ((TextView) view.findViewById(R.id.eventDate)).getText().toString();
				String description = ((TextView) view.findViewById(R.id.eventDescrip)).getText().toString();
				
				Intent in = new Intent(getActivity().getApplicationContext(), DetailEvent.class);
				in.putExtra(TAG_TITLE, eventName);
				in.putExtra(TAG_DATE, date);
				in.putExtra(TAG_SUMMARY, description);
				startActivity(in);

			}
		});
		
		super.onResume();
	}

}
