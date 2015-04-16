package com.th10.bacsigiadinh.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.th10.bacsigiadinh.R;

public class TraCuuThuocFragment extends Fragment {
	private EditText edtTimThuoc;

	public TraCuuThuocFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_tracuuthuoc, container,
				false);
		//sdfsdfsdfsdfsdfsdfsdfsdf
		edtTimThuoc = (EditText) rootView.findViewById(R.id.edtTimThuoc);
		
		
		return rootView;
	}
	
	public void TimThuoc(String htlm){
		
		
	}
}
