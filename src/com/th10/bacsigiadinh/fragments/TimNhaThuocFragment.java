package com.th10.bacsigiadinh.fragments;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.Request.Method;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.th10.bacsigiadinh.R;
import com.th10.bacsigiadinh.helpers.MyHelper;
import com.th10.bacsigiadinh.helpers.MyGPS;
import com.th10.bacsigiadinh.helpers.MyMap;
import com.th10.bacsigiadinh.interfaces.MyCallback;
import com.th10.bacsigiadinh.interfaces.MyNhaThuocCallback;
import com.th10.bacsigiadinh.models.NhaThuoc;
import com.th10.bacsigiadinh.models.RootObject;
import com.th10.bacsigiadinh.tasks.AppController;
import com.th10.bacsigiadinh.tasks.DownloadStringTask;
import com.th10.bacsigiadinh.tasks.GetDetailAsync;
import com.th10.bacsigiadinh.tasks.GetPhotoAsync;

import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.location.Location;
import android.os.Bundle;
import android.provider.SyncStateContract.Helpers;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class TimNhaThuocFragment extends Fragment implements
		OnMapReadyCallback, ConnectionCallbacks, OnConnectionFailedListener,
		 Response.Listener<JSONObject> , Response.ErrorListener , MyCallback {

	static View rootView = null;
	MapFragment mapFragment;
	GoogleMap map;
	MyGPS myGPS;
	GoogleApiClient mGoogleApiClient;
	TextView cc_status;
	List<Marker> markers = null;
	Map<Marker, NhaThuoc> mar_nha = new HashMap<Marker, NhaThuoc>();
	Dialog detailDialog;
	
	public TimNhaThuocFragment() {
		MyHelper.Log("xxx", "Ham tao!");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		myGPS = new MyGPS(getActivity());
				
		MyHelper.Log("xxx", "onCreateView");

		// Máp chỉ được phép load 1 lần duy nhất
		if (rootView == null)
		{
			rootView = inflater.inflate(R.layout.fragment_timnhathuoc,container, false);
			if(mapFragment == null)
				mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
			mapFragment.getMapAsync(this);
		
		}else{
			onMapReady(map);
		}

		

		mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
				.addApi(Places.GEO_DATA_API).addApi(Places.PLACE_DETECTION_API)
				.addConnectionCallbacks(this)
				.addOnConnectionFailedListener(this).build();

		return rootView;
	}

	void CauHinhMap() {
		map.setMyLocationEnabled(true);
		map.getUiSettings().setZoomControlsEnabled(true);
		// map.getUiSettings().set
		
		/*
		//Su kien click vào 1 địa điểm
		//Hiện ra bảng thông tin chi tiết của nó
		map.setOnMarkerClickListener(new OnMarkerClickListener() {
			//Làm sau để nhận diện marker nào với điểm nào
			@Override
			public boolean onMarkerClick(Marker m) {
			    // Create custom dialog object
                final Dialog dialog = new Dialog(getActivity());
                detailDialog = dialog;
                
                // Include dialog.xml file
                dialog.setContentView(R.layout.dialog_nhathuoc);
                
                // Set dialog title
                dialog.setTitle("Thông tin");
                
                // set values for custom dialog components - text, image and button
                TextView name = (TextView) dialog.findViewById(R.id.nt_name);
                TextView address = (TextView) dialog.findViewById(R.id.nt_address);
                TextView phone = (TextView) dialog.findViewById(R.id.nt_call);
                ImageView image = (ImageView) dialog.findViewById(R.id.nt_photo);
                Button bt_call = (Button) dialog.findViewById(R.id.nt_call);
                Button bt_show = (Button) dialog.findViewById(R.id.nt_show);
                Button bt_cancel = (Button) dialog.findViewById(R.id.nt_cancel);
                
                NhaThuoc n = mar_nha.get(m);
                name.setText(n.getName());
                address.setText(n.getAddress());
               
                
                dialog.show();
				return true;
			}
		});
		*/
	}

	@Override
	public void onMapReady(GoogleMap map) {
		this.map = map;
		CauHinhMap();
		LatLng myLocation = myGPS.getMyLocation();
		if (myLocation != null) {
			CameraPosition cameraPosition = new CameraPosition.Builder().target(myLocation).zoom(15).build();
			map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
		} else {
			MyHelper.Log("onMapReady", "myLocation is NULL");
		}

		
		if(markers == null)	{
			//------  gửi request  ----
			String url = MyMap.getQueryString(myLocation);
			JsonObjectRequest request = new JsonObjectRequest(Method.GET,url,null,this ,this );
			MyHelper.Toast(getActivity(), myLocation.toString());
			AppController.getInstance().addToRequestQueue(request);
		}
	}



	@Override
	public void onResponse(JSONObject object) {
		RootObject root = new RootObject();
		
		try {
			root.setStatus(object.getString("status"));
			JSONArray results = object.getJSONArray("results");
			
			for (int i = 0; i < results.length(); i++) {
				JSONObject node = (JSONObject) results.get(i);
				NhaThuoc x = new NhaThuoc();
				
				x.setId(node.getString("id"));
				x.setName(node.getString("name"));
				x.setAddress(node.getString("formatted_address"));
				
				double lng = node.getJSONObject("geometry").getJSONObject("location").getDouble("lng");
				double lat = node.getJSONObject("geometry").getJSONObject("location").getDouble("lat");
				x.setLocation(new LatLng(lat, lng));
				
				JSONArray array = node.getJSONArray("types");
				List<String> types = new ArrayList<String>();
				for (int j = 0; j < array.length(); j++)
					types.add(array.getString(j));
				x.setTypes(types);
				
				
				//x.setPhotoId(((JSONObject)node.getJSONArray("photos").get(0)).getString("photo_reference"));
			
				
				root.getResults().add(x);
				
			}
		
		} catch (JSONException e) {
			e.printStackTrace();
			MyHelper.Toast(getActivity(), "onResponse" + e.getMessage());
		}
		MyHelper.Toast(getActivity(), "Có "+root.getResults().size());
		//Chuyển hết về marker
		markers = new ArrayList<Marker>();
		for (int i = 0; i < root.getResults().size(); i++) {
			MarkerOptions o = new MarkerOptions();
			NhaThuoc nt = root.getResults().get(i);
			o.position(nt.getLocation());
			o.title(nt.getName());
		
			Marker currentMarker= map.addMarker(o);
			markers.add(currentMarker);
			mar_nha.put(currentMarker, nt);
			currentMarker.showInfoWindow();
			
			//String url = "https://maps.googleapis.com/maps/api/place/details/json?placeid="+nt.getId()+"&key="+MyMap.API_KEY;
			//new GetDetailAsync(nt).execute(url);
			
			//url = "https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&photoreference="+nt.getPhotoId()+"&key="+MyMap.API_KEY;
			//new GetPhotoAsync(nt).execute(url);
		}
	
		
		
	
	}

	@Override
	public void onErrorResponse(VolleyError e) {
		MyHelper.Toast(getActivity(),"onErrorResponse: "+ e.getMessage());
		
	}

	
	@Override
	public void onStart() {
		super.onStart();
		mGoogleApiClient.connect();
	}

	@Override
	public void onStop() {
		mGoogleApiClient.disconnect();
		super.onStop();
	}

	@Override
	public void onConnected(Bundle arg0) {}

	@Override
	public void onConnectionSuspended(int arg0) {}

	@Override
	public void onConnectionFailed(ConnectionResult arg0) {
	}

	//Lấy thông tin chi tiết
	@Override
	public void TaskDone(String result) {
		detailDialog.show();
	}



}
