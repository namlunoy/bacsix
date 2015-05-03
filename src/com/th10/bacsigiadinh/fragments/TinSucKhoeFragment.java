package com.th10.bacsigiadinh.fragments;


import com.th10.bacsigiadinh.R;
import com.th10.bacsigiadinh.tasks.GetBaoTask;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

public class TinSucKhoeFragment extends Fragment {
	public static String TRANGCHU = "http://vnexpress.net/rss/doi-song.rss";
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		//super.onCreate(savedInstanceState);
		View rootView = inflater.inflate(R.layout.fragment_tinsuckhoe,
				container, false);
		
		//setContentView(R.layout.activity_list_item);
		GetBaoTask asyncTask = new GetBaoTask(getActivity());
		asyncTask.execute(TRANGCHU);
		
		return rootView;
	}

}
