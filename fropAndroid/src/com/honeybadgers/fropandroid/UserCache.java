package com.honeybadgers.fropandroid;

import java.io.Serializable;
import java.util.ArrayList;

public class UserCache implements Serializable {
	String username = "";
	ArrayList<String> likedEvents = new ArrayList<String>();
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public ArrayList<String> getLikedEvents() {
		return likedEvents;
	}
	public void setLikedEvents(ArrayList<String> likedEvents) {
		this.likedEvents = likedEvents;
	}	

}
