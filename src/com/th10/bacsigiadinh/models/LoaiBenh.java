package com.th10.bacsigiadinh.models;

public class LoaiBenh {

	private int id;
	private String name;
	private String image;
	
	
	
	public LoaiBenh() {
		super();
	}
	public LoaiBenh(int id, String name, String image) {
		super();
		this.id = id;
		this.name = name;
		this.image = image;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
	
	
}
