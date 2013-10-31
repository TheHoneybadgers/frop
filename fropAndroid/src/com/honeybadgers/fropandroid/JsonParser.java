package com.honeybadgers.fropandroid;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;

public class JsonParser {

	public JsonParser() {

	}

public String getUsername(String sessId){
		
		try {//grab the username by passing the PHPSESSID as a cookie.
			HttpClient httpClient = new DefaultHttpClient();
			
			HttpGet httpGet = new HttpGet("http://dev.m.gatech.edu/user");
			httpGet.setHeader("Cookie","PHPSESSID="+ sessId);

			HttpResponse response = httpClient.execute(httpGet);
			return EntityUtils.toString( response.getEntity());


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
	
	public JSONArray getJSONFromUrl(String url) {

		try {//grab the JSON data from the URL and return the entities of it.
			return new JSONArray(EntityUtils.toString(new DefaultHttpClient().execute(new HttpGet(url)).getEntity()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}
}
