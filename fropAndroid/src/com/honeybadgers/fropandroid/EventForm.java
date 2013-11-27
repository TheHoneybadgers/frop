package com.honeybadgers.fropandroid;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;

public class EventForm extends Activity {
	EditText title,address;
	TimePicker start, end;
	Button submit;
	CalendarView cal;
	Spinner spin;
    private String array_spinner[];

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_subform);

		title = (EditText) findViewById(R.id.titleText);
		address = (EditText)findViewById(R.id.addressText);

		start = (TimePicker)findViewById(R.id.timeStart);
		end = (TimePicker) findViewById(R.id.timeStop);
		
		cal = (CalendarView) findViewById(R.id.calendarView1);
		spin = (Spinner) findViewById(R.id.spinner1);
		array_spinner=new String[5];
        array_spinner[0]="Party";
        array_spinner[1]="Philanthropy";

        ArrayAdapter adapter = new ArrayAdapter(this,
        android.R.layout.simple_spinner_item, array_spinner);
        spin.setAdapter(adapter);
		
		Button submit = (Button) findViewById(R.id.submitButton);

		submit.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Log.d("EVENT FORM", "Button Pressed");
				postData();
				Log.d("EVENT FORM", "Data Posted");
				Intent myIntent = new Intent(v.getContext(), MainActivity.class);
				v.getContext().startActivity(myIntent);
			}
		});
	}
	
	public void postData(){
		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost("http://dev.m.gatech.edu/d/dgonzalez33/api/fropWeb/events/");
		
		try {
			
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
			
			//TODO: Get the value fields in the following name-value pairs to form input fields
			
			nameValuePairs.add(new BasicNameValuePair("title","Party number 2"));
			nameValuePairs.add(new BasicNameValuePair("date","2013-10-31"));
			nameValuePairs.add(new BasicNameValuePair("org_id","1"));
			nameValuePairs.add(new BasicNameValuePair("foursquare","ab45454"));
			nameValuePairs.add(new BasicNameValuePair("address","College of computing"));
			nameValuePairs.add(new BasicNameValuePair("start_time","2013-10-31T21:00:00"));
			nameValuePairs.add(new BasicNameValuePair("end_time","2013-10-31T21:00:00"));
			nameValuePairs.add(new BasicNameValuePair("summary","This is a summary"));
			nameValuePairs.add(new BasicNameValuePair("type","Rave"));
			nameValuePairs.add(new BasicNameValuePair("special_notes	","none"));
			nameValuePairs.add(new BasicNameValuePair("alcohol","1"));
			
			post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			
			client.execute(post);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
}
