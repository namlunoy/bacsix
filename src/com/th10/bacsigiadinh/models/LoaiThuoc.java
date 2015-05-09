package com.th10.bacsigiadinh.models;

public class LoaiThuoc {

	private String name;
	private String link;
	private String image;
	
	
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	
	public LoaiThuoc(String name,String link, String image){
		this.name=name;
		this.link=link;
		this.image=image;
	}
	
}
