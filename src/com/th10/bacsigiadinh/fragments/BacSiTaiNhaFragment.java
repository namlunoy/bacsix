package com.th10.bacsigiadinh.fragments;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.th10.bacsigiadinh.R;
import com.th10.bacsigiadinh.helpers.MyMap;
import com.th10.bacsigiadinh.helpers.MyGPS;
import com.th10.bacsigiadinh.helpers.MyHelper;
import com.th10.bacsigiadinh.tasks.AppController;

public class BacSiTaiNhaFragment extends Fragment {
	public BacSiTaiNhaFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_bacsigiadinh, container,
				false);
test();
		return rootView;
	}
	
	public void test()
	{
		//https://maps.googleapis.com/maps/api/place/search/json?location=-33.88471,151.218237&radius=100&sensor=true&key=AIzaSyDjpYbcGOvN7GxL7OnovQQz1dBxhFUrBzE
		String url = MyMap.getQueryString((new MyGPS(getActivity()).getMyLocation()));
		JsonObjectRequest jsonObjReq = new JsonObjectRequest(Method.GET,
				url, null, new Response.Listener<JSONObject>() {
	 
	                @Override
	                public void onResponse(JSONObject response) {
	                    Log.d("", response.toString());
	 
	                    try {
	                        // Parsing json object response
	                        // response will be a json object
	                        String name = response.getString("status");
	                      
	 
	                       String jsonResponse = "";
	                        jsonResponse += "Name: " + name + "\n\n";
	                    
	 
	                    //    txtResponse.setText(jsonResponse);
	                        	MyHelper.Toast(getActivity(), jsonResponse);
	                        	
	                    } catch (JSONException e) {
	                        e.printStackTrace();
	                        Toast.makeText(getActivity(),
	                                "Error: " + e.getMessage(),
	                                Toast.LENGTH_LONG).show();
	                    }
	                }
	            }, new Response.ErrorListener() {
	 
	          

					@Override
					public void onErrorResponse(VolleyError error) {
						   VolleyLog.d("", "Error: " + error.getMessage());
		                    Toast.makeText(getActivity(),
		                            error.getMessage(), Toast.LENGTH_SHORT).show();
					}
	            });
	 
	    // Adding request to request queue
	    AppController.getInstance().addToRequestQueue(jsonObjReq);
	}
}