package com.th10.bacsigiadinh.tasks;

import com.th10.bacsigiadinh.helpers.MyHelper;
import com.th10.bacsigiadinh.models.NhaThuoc;

import android.os.AsyncTask;

public class GetPhotoAsync extends AsyncTask<String, Void, String> {

	NhaThuoc nhathuoc;
	public GetPhotoAsync(NhaThuoc n) {
		nhathuoc = n;
	}
	
	@Override
	protected String doInBackground(String... URLs) {
		return MyHelper.GetStringFromURL(URLs[0]);
	}
	
	@Override
	protected void onPostExecute(String result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
	}

}
