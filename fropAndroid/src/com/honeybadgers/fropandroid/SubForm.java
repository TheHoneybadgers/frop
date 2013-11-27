package com.honeybadgers.fropandroid;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class SubForm extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		
		View rootView = inflater.inflate(R.layout.subform_fragment, container, false);
		
		Button submit = (Button) rootView.findViewById(R.id.eventButton);		
		
		submit.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Log.d("FORM", "Button Pressed");
				Intent myIntent = new Intent(v.getContext(), EventForm.class);
				v.getContext().startActivity(myIntent);
			}
		});
		return rootView;
	}

	@Override
	public void onResume() {
		


		super.onResume();
	}

}
