package com.th10.bacsigiadinh.tasks;

import com.th10.bacsigiadinh.helpers.MyHelper;
import com.th10.bacsigiadinh.models.NhaThuoc;

import android.os.AsyncTask;

public class GetDetailAsync extends AsyncTask<String, Void, String>{

	private NhaThuoc nhathuoc;
	public GetDetailAsync(NhaThuoc n) {
		nhathuoc = n;
	}
	
	@Override
	protected String doInBackground(String... URLs) {
		return MyHelper.GetStringFromURL(URLs[0]);
	}
	
	@Override
	protected void onPostExecute(String result) {
		//Nó trả về đoạn json, mình cần lấy các thông tin chi tiết của nó ra
		
	}

}
