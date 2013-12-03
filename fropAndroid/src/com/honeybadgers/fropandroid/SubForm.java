package com.honeybadgers.fropandroid;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

public class SubForm extends Fragment {
	
	EditText title,address;
	TimePicker start, end;
	Button submit;
	DatePicker cal;
	Spinner spin;
	CheckBox alc;
    Date date = new Date();
    
    private static final String TAG_ORG_ID = "ORG_ID";
    String  titleVal, dateVal, addressVal,start_timeVal ,end_timeVal ,typeVal,orgID;
    int alcohol_present = 0;
    // url's to make request
  		private String base_url = MainActivity.base_url;
 		private  String user_url = base_url + "users/";
 		private String post_url = base_url + "events/";
 		JSONArray user_details = null;

 		
 		public void postData(){
 			HttpClient client = new DefaultHttpClient();
 			HttpPost post = new HttpPost(post_url);
 			
 			try {
 				
 				List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
 				
 				//TODO: Get the value fields in the following name-value pairs to form input fields
 				
 				nameValuePairs.add(new BasicNameValuePair("title",titleVal));
 				nameValuePairs.add(new BasicNameValuePair("date",dateVal));
 				nameValuePairs.add(new BasicNameValuePair("org_id",orgID));
 				nameValuePairs.add(new BasicNameValuePair("foursquare","ab45454"));
 				nameValuePairs.add(new BasicNameValuePair("address",addressVal));
 				nameValuePairs.add(new BasicNameValuePair("start_time",start_timeVal));
 				nameValuePairs.add(new BasicNameValuePair("end_time",end_timeVal));
 				nameValuePairs.add(new BasicNameValuePair("summary","This is a summary"));
 				nameValuePairs.add(new BasicNameValuePair("type",typeVal));
 				nameValuePairs.add(new BasicNameValuePair("special_notes	","none"));
 				nameValuePairs.add(new BasicNameValuePair("alcohol",Integer.toString(alcohol_present)));
 				
 				post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
 				
 				client.execute(post);
 				
 			} catch (Exception e) {
 				// TODO: handle exception
 			}
 			
 		}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		
		View rootView = inflater.inflate(R.layout.activity_subform, container, false);
								
		return rootView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		//Pass the URL to the JSON parser and get a JSON Array in response
		JsonParser jParser = new JsonParser();
		
		user_details = jParser.getJSONFromUrl(user_url + MainActivity.session.getName(), MainActivity.session.getSessId());
		
		JSONObject o;
		
		
		try {
			o = user_details.getJSONObject(0);
			Log.d("length of Json array",Integer.toString(user_details.length()));
			orgID = o.getString(TAG_ORG_ID);					//Extract the user's org id from the returned JSON
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(NullPointerException n){
			Log.d("exception", "NullPointer");
			Toast.makeText(getActivity(), "Not logged In, Please login to create an event", Toast.LENGTH_SHORT).show();
			Intent i = new Intent(getActivity(), Login.class);
			i.setAction("LOGIN");

			// Closing all the Activities
			i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

			// Add new Flag to start new Activity
			i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			/*
			 * Keep no record that login was started, so that when you press
			 * back after logging in, you don't go back to the Login page
			 */
			i.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
			getActivity().finish();
			startActivity(i);
		}
		
		
		title = (EditText) getView().findViewById(R.id.titleText);
		Log.d("here", "here");
		address = (EditText)getView().findViewById(R.id.addressText);

		start = (TimePicker)getView().findViewById(R.id.timeStart);
		end = (TimePicker)getView(). findViewById(R.id.timeStop);
		
		cal = (DatePicker) getView().findViewById(R.id.datePicker1);
		spin = (Spinner)getView(). findViewById(R.id.spinner1);
		
		alc = (CheckBox)getView().findViewById(R.id.AlcoholBox);
		
		
		 // get the current date
	    final Calendar c = Calendar.getInstance();
	   
	    c.add(Calendar.DAY_OF_MONTH, 1);
	    int mYear = c.get(Calendar.YEAR);
	    int mMonth = c.get(Calendar.MONTH);
	    int mDay = c.get(Calendar.DAY_OF_MONTH);
	    cal.init(mYear, mMonth, mDay, null);							//Set the datepicker to the tomorrow's date
	    c.add(Calendar.DAY_OF_MONTH, -1);
	    cal.setMinDate(c.getTimeInMillis());								//Set the minimum choosable date as today
	     c.add(Calendar.YEAR, 1);														
	    cal.setMaxDate(c.getTimeInMillis());						  //Set the maximum choosable date as 1 year after today
	    
	    
		
       		
		Button submit = (Button) getView().findViewById(R.id.submitButton);

		submit.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				
				titleVal = title.getText().toString();
				dateVal = cal.getYear()+"-"+cal.getMonth()+"-"+cal.getDayOfMonth();
				start_timeVal = dateVal+"T"+start.getCurrentHour() + ":"+ start.getCurrentMinute()+":00";
				end_timeVal = dateVal+"T"+end.getCurrentHour() + ":"+ end.getCurrentMinute()+":00";
				addressVal = address.getText().toString();
				typeVal =  spin.getSelectedItem().toString();
				if(alc.isChecked()){
					alcohol_present = 1;
				}
				
				Log.d("EVENT FORM", "Button Pressed");
				postData();
				Log.d("EVENT FORM", "Data Posted");
				Intent myIntent = new Intent(v.getContext(), MainActivity.class);
				v.getContext().startActivity(myIntent);
			}
		});
		
	};
	
	@Override
	public void onResume() {
		


		super.onResume();
	}

}