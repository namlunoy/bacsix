package com.th10.bacsigiadinh.helpers;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import org.apache.http.HttpStatus;

import android.util.Log;

public class Netutil {

	public static InputStream _openConnection(String uriString) {

		InputStream in = null;
		try {
			Thread.sleep(1000);
			URL uri = new URL(uriString);
			URLConnection conn = uri.openConnection();

			// Ä‘áº©y request tá»« device
			HttpURLConnection httpReq = (HttpURLConnection) conn;
			httpReq.setRequestProperty(
					"User-Agent",
					"Mozilla/5.0 (Macintosh; Intel Mac OS X 10.9; rv:31.0) Gecko/20100101 Firefox/31.0");
			httpReq.setRequestMethod("GET");
			httpReq.setAllowUserInteraction(false);
			httpReq.setFollowRedirects(true);
			if (httpReq.getResponseCode() == HttpStatus.SC_OK) {
				in = httpReq.getInputStream();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// tráº£ vá»� binary
		return in;
	}

	public static String getXmlFromUrl(String url) {
		InputStream in = Netutil._openConnection(url);
		String data;
		InputStreamReader inreader = new InputStreamReader(in);
		BufferedReader bufreader = new BufferedReader(inreader);
		StringBuilder builder = new StringBuilder();
		if (in != null) {
			try {
				while ((data = bufreader.readLine()) != null) {
					builder.append(data);
					builder.append("\n");
					Log.d("Readed", new String(data));
				}
				in.close();
				
			} catch (IOException ex) {
				Log.e("ERROR", ex.getMessage());
			}
		}
		return builder.toString();
	}
}
