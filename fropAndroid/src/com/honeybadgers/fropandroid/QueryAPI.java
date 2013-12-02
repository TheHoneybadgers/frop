package com.honeybadgers.fropandroid;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.util.Log;

public class QueryAPI {
	String url;
	
	public QueryAPI(){};
	
	public QueryAPI(String link){
		url = link+"users/";
	};

	public String getUsername(String sessId) {

		try {// grab the username by passing the PHPSESSID as a cookie.
			HttpClient httpClient = new DefaultHttpClient();

			HttpGet httpGet = new HttpGet("http://dev.m.gatech.edu/user");
			httpGet.setHeader("Cookie", "PHPSESSID=" + sessId);

			HttpResponse response = httpClient.execute(httpGet);
			return EntityUtils.toString(response.getEntity());

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	public int getPerms(String sessId, String UserName) {
		
			Log.d("making request to", UserName);
			
			try{

			HttpClient client = new DefaultHttpClient();
			HttpGet request = new HttpGet(url+ UserName + "/perms");

			Log.d("making request to", UserName);

			request.setHeader("Cookie", "PHPSESSID=" + sessId);
			HttpResponse response;
			String responseText ;
			
				response = client.execute(request);
				responseText = EntityUtils.toString(response.getEntity());
						
			Log.d("permission", responseText);

			if (responseText.isEmpty()) {
				Log.d("Error", "User does not exist in the system");
				return 4;

			} else {
				try {
					return (Integer.parseInt(responseText));

				} catch (NumberFormatException e) {
					Log.d("Error", "Authentication problem");
					return 4;

				}

			}

		
	}catch(Exception e){
		
		Log.d("Error", "Network problem");
		return 4;}

}
}