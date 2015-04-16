package com.th10.bacsigiadinh.tasks;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import com.th10.bacsigiadinh.helpers.MyHelper;
import com.th10.bacsigiadinh.interfaces.MyCallback;

import android.os.AsyncTask;
import android.util.Log;

public class DownloadStringTask extends AsyncTask<String, Integer, String> {
	private MyCallback callback;

	public DownloadStringTask(MyCallback call) {
		this.callback = call;
	}

	@Override
	protected String doInBackground(String... URLs) {
		return MyHelper.GetStringFromURL(URLs[0]);
	}

	@Override
	protected void onPostExecute(String result) {
		callback.TaskDone(result);
		super.onPostExecute(result);
	}

}
