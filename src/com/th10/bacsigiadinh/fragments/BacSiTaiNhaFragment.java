package com.th10.bacsigiadinh.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.th10.bacsigiadinh.R;

public class BacSiTaiNhaFragment extends Fragment {
	public BacSiTaiNhaFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_bacsigiadinh, container,
				false);

		return rootView;
	}
}