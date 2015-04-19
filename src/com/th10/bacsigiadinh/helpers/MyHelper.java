package com.th10.bacsigiadinh.helpers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;
/**
 * Lá»›p nĂ y dĂ¹ng Ä‘á»ƒ thá»±c hiá»‡n cĂ¡c phÆ°Æ¡ng thá»©c mĂ  Ä‘Æ°á»£c sá»­ dá»¥ng nhiá»�u láº§n
 *
 */
public class MyHelper {
	public static void Log(String title, String mess) {
		Log.d("mylog", title + ": " + mess);
	}

	public static void Toast(Context context, String mess) {
		Toast.makeText(context, mess, 10).show();
	}
	
	public static boolean checkInternetConnection(Context context) {
		NetworkInfo info = ((ConnectivityManager) context
				.getSystemService(context.CONNECTIVITY_SERVICE))
				.getActiveNetworkInfo();
		return info == null ? false : info.isConnected();
	}

	public static String InputStreamToString(InputStream is) {
		try {
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(is));
			StringBuilder content = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null)
				content.append(line);
			return content.toString();
		} catch (IOException e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}

	public static String GetStringFromURL(String url) {
		try {
			HttpClient client = new DefaultHttpClient();
			HttpPost GETRequest = new HttpPost(url);
			HttpResponse response = client.execute(GETRequest);
			return InputStreamToString(response.getEntity().getContent());
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}

}
