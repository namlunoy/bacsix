package com.th10.bacsigiadinh.fragments;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.th10.bacsigiadinh.R;
import com.th10.bacsigiadinh.adapters.ListBacSiAdapter;
import com.th10.bacsigiadinh.helpers.MyMap;
import com.th10.bacsigiadinh.helpers.MyGPS;
import com.th10.bacsigiadinh.helpers.MyHelper;
import com.th10.bacsigiadinh.models.BacSiTaiNha;
import com.th10.bacsigiadinh.tasks.AppController;

public class BacSiTaiNhaFragment extends Fragment 
		implements Response.Listener<JSONObject> , 
				Response.ErrorListener {
	
	List<BacSiTaiNha> listBacsi = new ArrayList<BacSiTaiNha>();
	ListView listView;
	public BacSiTaiNhaFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_bacsigiadinh, container,
				false);
		listView = (ListView)rootView.findViewById(R.id.listBacsi);
		GetData();
		return rootView;
	}
	
	public void GetData()
	{
		JsonObjectRequest request = new JsonObjectRequest(Method.GET,"http://tuandat1992.byethost9.com/home/index",null,this ,this );
	    AppController.getInstance().addToRequestQueue(request);
	}

	@Override
	public void onResponse(JSONObject object) {
		try {
			JSONArray array = object.getJSONArray("results");
			
			for (int i = 0; i < array.length(); i++) {
				BacSiTaiNha b = new BacSiTaiNha();
				JSONObject o = (JSONObject) array.get(i);
				b.setDia_chi(o.getString("dia_chi"));
				b.setId(o.getString("id"));
				b.setSo_dt(o.getString("so_dt"));
				b.setTen_quan(o.getString("ten_quan"));
				listBacsi.add(b);
			}
			
			ListBacSiAdapter adapter = new ListBacSiAdapter(getActivity(), R.layout.item_bacsi, listBacsi);
			listView.setAdapter(adapter);
			
			
			MyHelper.Toast(getActivity(), "Loadok");
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			MyHelper.Toast(getActivity(), e.getMessage());
		}
		
	}

	@Override
	public void onErrorResponse(VolleyError arg0) {
		// TODO Auto-generated method stub
		
	}
}