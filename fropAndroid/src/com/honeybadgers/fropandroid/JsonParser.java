package com.honeybadgers.fropandroid;

import java.io.IOException;

import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;

public class JsonParser {

	public JsonParser() {

	}

	
	public JSONArray getJSONFromUrl(String url) {

		try {//grab the JSON data from the URL and return the entities of it.
			HttpClient client = new DefaultHttpClient();
			HttpGet request = new HttpGet(url);
			return new JSONArray(EntityUtils.toString(client.execute(request).getEntity()));
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
	
	public JSONArray getJSONFromUrl(String url, String sessID) {

		try {//grab the JSON data from the URL and return the entities of it.
			HttpClient client = new DefaultHttpClient();
			HttpGet request = new HttpGet(url);
			request.setHeader("Cookie", "PHPSESSID=" + sessID);
			return new JSONArray(EntityUtils.toString(client.execute(request).getEntity()));
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
