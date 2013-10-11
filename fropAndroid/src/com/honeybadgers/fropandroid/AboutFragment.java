package com.honeybadgers.fropandroid;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.ContextThemeWrapper;

public class AboutFragment extends DialogFragment{
	
	 @Override
	    public Dialog onCreateDialog(Bundle savedInstanceState) {
	        // Use the Builder class for convenient dialog construction
		
	        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(
	        		getActivity(), R.style.AppBaseTheme));
	        builder.setMessage(R.string.about_us)
	               .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
	                   public void onClick(DialogInterface dialog, int id) {
	                   }
	               });
	              
	        // Create the AlertDialog object and return it
	        return builder.create();
	    }


}
