package com.honeybadgers.fropandroid;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OptionalDataException;
import java.io.StreamCorruptedException;
import java.util.Locale;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends FragmentActivity {

	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a
	 * {@link android.support.v4.app.FragmentPagerAdapter} derivative, which
	 * will keep every loaded fragment in memory. If this becomes too memory
	 * intensive, it may be best to switch to a
	 * {@link android.support.v4.app.FragmentStatePagerAdapter}.
	 */
	SectionsPagerAdapter mSectionsPagerAdapter;

	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	ViewPager mViewPager;
	static Uri data;
	QueryAPI api;  // Provides fuctions to query the API whether a user is logged in, and to get permissions
	static SessionManagement session; // Create a session manager object, which will check if a user is logged in or not
	String userName; // The GT username recieved from the API
	String sessionId; // The session ID received after logging in
	static UserCache user = new UserCache();
	static String base_url = "http://dev.m.gatech.edu/d/tpeet3/api/fropWeb/";
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Deserial();
		
		// Next two lines are required to successfully perform network
		// operations
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);

		Intent intent = getIntent();
		data = intent.getData();
		session = new SessionManagement(getApplicationContext(), base_url);
		api = new QueryAPI(base_url);

		// if no data set, redirect the user to login page
		try {

			// Logging to check if sharedprefs work
			Log.d("stored username", session.getName());
			Log.d("stored session Id", session.getSessId());
			//Log.d("User perms", Integer.toString(api.getPerms(session.getSessId(), session.getName())));

		} catch (NullPointerException e) {

			try {

				sessionId = data.getQueryParameter("sessionId");
			} catch (NullPointerException e2) {

				Intent i = new Intent(MainActivity.this, Login.class);
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
				MainActivity.this.finish();
				startActivity(i);
			}

			try {
				// Get the username and session id and put them in shared
				// preferences

				
				userName =api.getUsername(sessionId);
				int perms  = api.getPerms(sessionId, userName);
				session.createLoginSession(userName,  sessionId, perms);
				Log.d("before setting pref", "haha");

				// Logging to check if sharedprefs work
				Log.d("stored username", userName);
				Log.d("stored session Id", session.getSessId());
				Log.d("User perms", Integer.toString(session.getPerms()));
			} catch (NullPointerException e3) {
				Log.d("exception", "Null");

			}

		}

		Log.d("continue", "back to main");

		// Create the adapter that will return a fragment for each of the three
		// primary sections of the app.
		mSectionsPagerAdapter = new SectionsPagerAdapter(
				getSupportFragmentManager());

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {

		case R.id.action_about:
			AboutFragment dialog = new AboutFragment();
			dialog.show(getSupportFragmentManager(), null);
			break;
		case R.id.action_settings:
			break;
		case R.id.action_logout:
			session.clear();
			Intent i = new Intent(MainActivity.this, Login.class);
			i.setAction("LOGOUT");

			// Closing all the Activities
			i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

			// Add new Flag to start new Activity
			i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			/*
			 * Keep no record that login was started, so that when you press
			 * back after logging in, you don't go back to the Login page
			 */
			i.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
			MainActivity.this.finish();
			startActivity(i);
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the sections/tabs/pages.
	 */
	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			// getItem is called to instantiate the fragment for the given page.
			// Return a DummySectionFragment (defined as a static inner class
			// below) with the page number as its lone argument.
			
			if (position == 2) {

				Fragment fragment = new SubForm();
				return fragment;

			}
			else if (position == 1) {

				Fragment fragment = new EventFragment();
				return fragment;

			} 
			else if (position == 0) {

				Fragment fragment = new EventFragmentLiked();
				return fragment;

			}
			else {
				Fragment fragment = new DummySectionFragment();
				Bundle args = new Bundle();
				args.putInt(DummySectionFragment.ARG_SECTION_NUMBER,
						position + 1);
				fragment.setArguments(args);
				return fragment;
			}
		}

		@Override
		public int getCount() {
						
			// show 4 pages if permission above normal user, show 2 otherwise.
			if(session.getPerms() < 3 ){
				return 4;
				}else{
				return 2;
				}
		}

		@Override
		public CharSequence getPageTitle(int position) {
			Locale l = Locale.getDefault();
			switch (position) {
			case 0:
				return getString(R.string.title_section1).toUpperCase(l);
			case 1:
				return getString(R.string.title_section2).toUpperCase(l);
			case 2:
				return getString(R.string.title_section3).toUpperCase(l);
			}
			return null;
		}
	}
	
	public void Deserial() {
		FileInputStream fin;

		try {
			fin = openFileInput("user.dat");
			ObjectInputStream ois = new ObjectInputStream(fin);
			user = (UserCache) ois.readObject();
			ois.close();
			Toast.makeText(getApplicationContext(), "User was recovered", Toast.LENGTH_SHORT)
					.show();
		} catch (StreamCorruptedException e) {
			Toast.makeText(getApplicationContext(), "Failed consistency tests", Toast.LENGTH_SHORT)
					.show();
			e.printStackTrace();
		} catch (OptionalDataException e) {
			Toast.makeText(getApplicationContext(), "Optional Data Error", Toast.LENGTH_SHORT)
					.show();
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			Toast.makeText(getApplicationContext(), "File Not Found", Toast.LENGTH_SHORT).show();
			e.printStackTrace();
		} catch (IOException e) {
			Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
