package com.th10.bacsigiadinh.items;

import android.app.Fragment;

public class DrawerMenuItem {
	private String title;
	private int iconId;
	private Fragment fragment;
	
	public Fragment getFragment() {
		return fragment;
	}
	public DrawerMenuItem(){}
	public DrawerMenuItem(String title, int iconId,Fragment fragment) {
		this.title = title;
		this.iconId = iconId;
		this.fragment = fragment;
	}

	public int getIconId() {
		return iconId;
	}

	public void setIconId(int iconId) {
		this.iconId = iconId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
