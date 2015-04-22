package com.th10.bacsigiadinh.models;

import java.util.ArrayList;
import java.util.List;

import com.google.android.gms.maps.model.LatLng;

public class NhaThuoc {
	private String name;
	private LatLng location;
	private List<String> types;
	
	public NhaThuoc() {
		types = new ArrayList<String>();
	}
	
	
	public List<String> getTypes() {
		return types;
	}
	public void setTypes(List<String> types) {
		this.types = types;
	}
	public String getName() {
		return name;
	}
	public void setName(String ten) {
		this.name = ten;
	}
	public LatLng getLocation() {
		return location;
	}
	public void setLocation(LatLng location) {
		this.location = location;
	}
	
}
