package com.honeybadgers.fropandroid;

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

	// Create a session manager object, which will check if a user is logged in
	// or not
	static SessionManagement session;
	String userName; // The GT username recieved from the API
	String sessionId; // The session ID received after logging in

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Next two lines are required to successfully perform network
		// operations
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();

		StrictMode.setThreadPolicy(policy);

		Intent intent = getIntent();
		data = intent.getData();
		session = new SessionManagement(getApplicationContext());

		 

		// if no data set, redirect the user to login page
		try {
			
			// Logging to check if sharedprefs work
						Log.d("stored username", session.getName());
						Log.d("stored session Id", session.getSessId());
			

		} catch (NullPointerException e) {
			
			try{
				
				sessionId = data.getQueryParameter("sessionId");
				}catch(NullPointerException e2){
					
					Intent i = new Intent(MainActivity.this, Login.class);
					i.setAction("LOGIN");

					// Closing all the Activities
					i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

					// Add new Flag to start new Activity
					i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					i.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY); // Keep no record that
																	// login was
																	// started, so that
																	// when you press
																	// back after
																	// logging in, you
																	// don't go back to
																	// the Login page
					MainActivity.this.finish();
					startActivity(i);
				}
			
			try {
				// Get the username and session id and put them in shared
				// preferences
				
				JsonParser j1 = new JsonParser();

				userName = j1.getUsername(sessionId);
				session.createLoginSession(userName, sessionId);
				Log.d("before setting pref", "haha");

				// Logging to check if sharedprefs work
				Log.d("stored username", session.getName());
				Log.d("stored session Id", session.getSessId());
			} catch (NullPointerException e3) {

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

			if (position == 1) {

				Fragment fragment = new EventFragment();
				return fragment;

			} else {
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
			// Show 3 total pages.
			return 3;
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

}
