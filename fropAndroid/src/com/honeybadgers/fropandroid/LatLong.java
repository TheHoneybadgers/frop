package com.honeybadgers.fropandroid;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LatLong {
	static double lat;
	static double lon;
	static String address;

	public LatLong(String address) {
		this.address = address;
	}

	public static void getLocationInfo() {
		StringBuilder stringBuilder = new StringBuilder();
		try {

			address = address.replaceAll(" ", "%20");
			address = address.replaceAll("\\|", "%20");

			HttpGet httppost = new HttpGet(
					"http://maps.google.com/maps/api/geocode/json?address=" + "%22"
							+ address + "%22" + "&sensor=false");
			//HttpGet httppost = new HttpGet("http://maps.google.com/maps/api/geocode/json?address=%22fox-theater,%20atlanta%20%22&sensor=false");
			HttpClient client = new DefaultHttpClient();
			HttpResponse response;
			stringBuilder = new StringBuilder();

			response = client.execute(httppost);
			HttpEntity entity = response.getEntity();
			InputStream stream = entity.getContent();
			int b;
			while ((b = stream.read()) != -1) {
				stringBuilder.append((char) b);
			}
		} catch (ClientProtocolException e) {
		} catch (IOException e) {
		}

		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject = new JSONObject(stringBuilder.toString());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		getLatLong(jsonObject);
	}
	
	public static boolean getLatLong(JSONObject jsonObject) {

        try {

            lon = ((JSONArray)jsonObject.get("results")).getJSONObject(0)
                .getJSONObject("geometry").getJSONObject("location")
                .getDouble("lng");

            lat = ((JSONArray)jsonObject.get("results")).getJSONObject(0)
                .getJSONObject("geometry").getJSONObject("location")
                .getDouble("lat");

        } catch (JSONException e) {
            return false;

        }

        return true;
    }
}
