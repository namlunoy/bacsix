package com.th10.bacsigiadinh.models;

import java.util.ArrayList;

public class RootObject {

	
	public RootObject() {
		results = new ArrayList<NhaThuoc>();
	}
	
	private String status;
	private ArrayList<NhaThuoc> results;
	
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public ArrayList<NhaThuoc> getResults() {
		return results;
	}
	public void setResults(ArrayList<NhaThuoc> results) {
		this.results = results;
	}
	
	
}
