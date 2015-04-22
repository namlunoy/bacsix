package com.th10.bacsigiadinh.helpers;

import com.google.android.gms.maps.model.LatLng;

public class MyMap {
	public static String API_KEY = "AIzaSyDjpYbcGOvN7GxL7OnovQQz1dBxhFUrBzE";
	public static int RADIUS = 1000;
	public static String TYPES = "hospital|pharmacy";
	public static String keyword = "nha+thuoc";
	public static boolean SENSOR = false;

	public static String getQueryString(LatLng position) {
		String url = "https://maps.googleapis.com/maps/api/place/search/json?"
				+ "types="+ TYPES 
				+ "&location=" + position.latitude + ","+position.longitude
				+ "&radius="+RADIUS
				+ "&sensor="+SENSOR
				+ "&key="+API_KEY;
		MyHelper.Log("MapConfig.getQueryString", url);
		//https://maps.googleapis.com/maps/api/place/search/json?location=-33.88471,151.218237&radius=100&key=AIzaSyDjpYbcGOvN7GxL7OnovQQz1dBxhFUrBzE&sensor=true
	
		//https://maps.googleapis.com/maps/api/place/textsearch/json?query=nha+thuoc&key=AIzaSyDjpYbcGOvN7GxL7OnovQQz1dBxhFUrBzE
	//	return "https://maps.googleapis.com/maps/api/place/textsearch/json?location="+position.latitude+","+position.longitude+"&query="+keyword+"&radius="+RADIUS+"&key=AIzaSyDjpYbcGOvN7GxL7OnovQQz1dBxhFUrBzE&sensor=true";
		return "https://maps.googleapis.com/maps/api/place/textsearch/json?query=nha+thuoc&key=AIzaSyDjpYbcGOvN7GxL7OnovQQz1dBxhFUrBzE"+"&location="+position.latitude+","+position.longitude+"&radius="+RADIUS;
	}
}
