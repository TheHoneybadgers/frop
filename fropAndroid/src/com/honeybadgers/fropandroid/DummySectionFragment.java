package com.honeybadgers.fropandroid;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DummySectionFragment extends Fragment {
		
	//The fragment argument representing the section number for this fragment.
	public static final String ARG_SECTION_NUMBER = "section_number";

	public DummySectionFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		
		View rootView = inflater.inflate(R.layout.fragment_main_dummy, container, false);
		TextView dummyTextView = (TextView) rootView.findViewById(R.id.section_label);
		dummyTextView.setText(Integer.toString(getArguments().getInt(ARG_SECTION_NUMBER)));
		
		String userName =MainActivity.session.getName();
	    String sessionId = MainActivity.session.getSessId();
	    int permissions = MainActivity.session.getPerms();
	    String role;
	    switch(permissions){
		case 1:
			role = "IFC chair";
			break;
		case 2:
			role = "fraternity social chair";
			break;
		case 3:
			role = "normal user";
			break;
		default:
			role = "normal user";
			break;
			
		
		
		}
	    
	   // String username =api.getUsername(sessionId);
	    dummyTextView.setText("Username:"+userName + " Session ID: " + sessionId+" Role: "+role);
		return rootView;
	}

}
