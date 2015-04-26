package com.th10.bacsigiadinh.helpers;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;

public class MyGPS extends Service implements LocationListener {
	// The minimum distance to change Updates in meters
	private static final long KHOANG_CACH = 10; // 10 meters

	// The minimum time between updates in milliseconds
	private static final long UPDATE_TIME = 1000 * 60 * 1; // 1 minute
	private Context context;
	private LocationManager locationManager;
	public static Location location = null;

	public MyGPS(Context context) {
		this.context = context;
	}

	public LatLng getMyLocation() {
		if (location == null) {
			locationManager = (LocationManager) context
					.getSystemService(LOCATION_SERVICE);

//			if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
//				MyHelper.Log("MyGPS", "Sử dụng GPS");
//				locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, UPDATE_TIME, KHOANG_CACH,this);
//				location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
//
//			} else 
			if (locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
				MyHelper.Log("MyGPS", "Sử dung internet!");
				locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, UPDATE_TIME,KHOANG_CACH, this);
				location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
			} else {
				MyHelper.Log("MyGPS", "Không có cái nào bật cả!");
			}

		}

		return new LatLng(location.getLatitude(), location.getLongitude());
	}

	@Override
	public void onLocationChanged(Location location) {

	}

	@Override
	public void onProviderDisabled(String provider) {

	}

	@Override
	public void onProviderEnabled(String provider) {

	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {

	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

}
