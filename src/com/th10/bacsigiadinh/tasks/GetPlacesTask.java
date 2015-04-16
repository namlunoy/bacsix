package com.th10.bacsigiadinh.tasks;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.AsyncTask;

import com.google.android.gms.maps.model.LatLng;
import com.th10.bacsigiadinh.helpers.MyHelper;
import com.th10.bacsigiadinh.helpers.MapConfig;
import com.th10.bacsigiadinh.interfaces.MyCallback;

public class GetPlacesTask extends AsyncTask<LatLng, Void, String> {
	private LatLng position;
	private MyCallback callback;

	public GetPlacesTask(MyCallback callback) {
		this.callback = callback;
	}

	@Override
	protected String doInBackground(LatLng... params) {
		position = params[0];
		return MyHelper.GetStringFromURL(MapConfig.getQueryString(position));
	}

	@Override
	protected void onPostExecute(String result) {
		callback.TaskDone(result);
		super.onPostExecute(result);
	}

}
