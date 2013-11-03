package com.honeybadgers.fropandroid;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

public class SessionManagement {

	// create a jsonparser object
	JsonParser j1 = new JsonParser();

	// Shared Preferences
	SharedPreferences pref;

	// Editor for Shared preferences
	Editor editor;

	// Context
	Context _context;

	// Shared pref mode
	int PRIVATE_MODE = 0;

	// Sharedpref file name
	private static final String PREF_NAME = "SocialGreekPref";

	// All Shared Preferences Keys
	private static final String IS_LOGIN = "IsLoggedIn";

	// User name (make variable public to access from outside)
	public static final String KEY_NAME = "name";

	// Email address (make variable public to access from outside)
	public static final String SESS_ID = "sessionId";

	// Constructor
	public SessionManagement(Context context) {
		this._context = context;
		pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
		editor = pref.edit();

	}

	/**
	 * Create login session
	 * */
	public void createLoginSession(String name, String sessionId) {
		// Storing login value as TRUE
		editor.putBoolean(IS_LOGIN, true);

		// Storing name in pref
		editor.putString(KEY_NAME, name);

		// Storing sessionId in pref
		editor.putString(SESS_ID, sessionId);

		// commit changes
		editor.commit();
	}

	//Retrieve the stored username
	String getName(){
		String name = pref.getString(KEY_NAME, null);
		return name;
	}
	
	//Retrieve the stored session Id
	String getSessId(){
		String sessId = pref.getString(SESS_ID, null);
		return sessId;
	}
	
	
	/**
	 * Check login method wil check user login status If false it will redirect
	 * user to login page Else won't do anything
	 * */
	public void checkLogin(String sessId) {
		// Check login status
		if (!this.isLoggedIn(sessId)) {
			// user is not logged in redirect him to Login Activity
			Intent i = new Intent(_context, Login.class);
			i.setAction("LOGIN");
			// Closing all the Activities
			i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

			// Add new Flag to start new Activity
			i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

			// Staring Login Activity
			_context.startActivity(i);
		}

	}

	// Forcibly open the login page
	public void gotoLogin() {
		Log.d("intent data", "In gotologin");
		// user is not logged in redirect him to Login Activity

		Intent i = new Intent(_context, Login.class);
		i.setAction("LOGIN");
		// Closing all the Activities
		i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

		// Add new Flag to start new Activity
		i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

		// Staring Login Activity
		_context.startActivity(i);

	}

	/*
	 * Quick check for login *
	 */
	// Get Login State
	public boolean isLoggedIn(String sessId) {
		String result = j1.getUsername(sessId);

		if (result == null || result.isEmpty()) {
			return false;
		}
		return true;

	}
}
