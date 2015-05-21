package com.th10.bacsigiadinh.tasks;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.AdapterView.OnItemClickListener;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.Request.Method;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.th10.bacsigiadinh.DrugViewer;
import com.th10.bacsigiadinh.GridDrugActivity;
import com.th10.bacsigiadinh.R;
import com.th10.bacsigiadinh.adapters.GridDrugAdapter;
import com.th10.bacsigiadinh.adapters.ListLoaiThuocAdapter;
import com.th10.bacsigiadinh.helpers.MyHelper;
import com.th10.bacsigiadinh.models.LoaiThuoc;
import com.th10.bacsigiadinh.models.Thuoc;

public class TimThuocX extends AsyncTask<String, Void, Void> implements Response.Listener<JSONArray> , 
Response.ErrorListener{
	private ProgressDialog mProgressDialog;
	private ArrayList<Thuoc> listDrug;
	private Activity act;
	private GridView gridThuoc;
	
	

	public TimThuocX(ArrayList<Thuoc> listDrug, Activity act) {
		super();
		this.listDrug = listDrug;
		this.act = act;
	}

	@Override
	protected void onPreExecute() {
		mProgressDialog = new ProgressDialog(act);
		mProgressDialog = new ProgressDialog(act,
				ProgressDialog.THEME_DEVICE_DEFAULT_LIGHT);
		mProgressDialog.getWindow().clearFlags(
				WindowManager.LayoutParams.FLAG_DIM_BEHIND);
		mProgressDialog.setTitle("Đang tải xin chờ!");
		mProgressDialog.setMessage("Loading...");
		mProgressDialog.setIndeterminate(false);
		mProgressDialog.show();
		super.onPreExecute();
	}

	@Override
	protected void onPostExecute(Void result) {
		mProgressDialog.dismiss();
		
		gridThuoc = (GridView) act.findViewById(R.id.gridViewThuoc);
		gridThuoc.setAdapter(new GridDrugAdapter(act,listDrug));
		gridThuoc.setOnItemClickListener(new GridClickItemListener());
		super.onPostExecute(result);
	}

	@Override
	protected Void doInBackground(String... params) {
		Thuoc thuoc = new Thuoc();
		GetData(params[0]);
		//listDrug.add(thuoc);
		return null;
	}
	
	public class GridClickItemListener implements OnItemClickListener{

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int position,
				long arg3) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(act, DrugViewer.class);
			intent.putExtra("name", listDrug.get(position).getName());
			intent.putExtra("image", listDrug.get(position).getImage());
			intent.putExtra("detail", listDrug.get(position).getLink());
			act.startActivity(intent);
			
		}
		
	}
	
	public void GetData(String type)
	{
		JsonArrayRequest request = new JsonArrayRequest("http://tuandat1992.byethost9.com/home/search?type="+type,this, this);
	    AppController.getInstance().addToRequestQueue(request);
	}

	@Override
	public void onErrorResponse(VolleyError arg0) {
		
	}

	@Override
	public void onResponse(JSONArray arg0) {
		try {
			JSONArray array = arg0;

			for (int i = 0; i < array.length(); i++) {
				Thuoc l = new Thuoc();
				JSONObject o = (JSONObject) array.get(i);
				l.setName(o.getString("name"));
				l.setImage(o.getString("image"));
				l.setLink("link");
				listDrug.add(l);
				System.out.println(l.getName() + " "
						+ l.getLink());
			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}
