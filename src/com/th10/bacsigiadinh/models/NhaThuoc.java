package com.th10.bacsigiadinh.models;

import java.util.ArrayList;
import java.util.List;

import com.google.android.gms.maps.model.LatLng;

public class NhaThuoc {
	private String id;
	private String name;
	private LatLng location;
	private List<String> types;
	private String photoUrl;
	private String address;
	private String photoId;

	public NhaThuoc() {
		types = new ArrayList<String>();
	}

	// ------------------ getset

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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPhotoUrl() {
		return photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

	public String getPhotoId() {
		return photoId;
	}

	public void setPhotoId(String photoId) {
		this.photoId = photoId;
	}
}
