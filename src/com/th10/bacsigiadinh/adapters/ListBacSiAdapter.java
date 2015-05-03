package com.th10.bacsigiadinh.adapters;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.th10.bacsigiadinh.R;
import com.th10.bacsigiadinh.models.BacSiTaiNha;
import com.th10.bacsigiadinh.models.BaiBao;

public class ListBacSiAdapter extends ArrayAdapter<BacSiTaiNha> {

	Activity context;
	int viewId;
	List<BacSiTaiNha> list;

	public ListBacSiAdapter(Activity context, int resource,
			List<BacSiTaiNha> objects) {
		super(context, resource, objects);
		this.context = context;
		viewId = resource;
		list = objects;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		convertView = context.getLayoutInflater().inflate(viewId, null);
		BacSiTaiNha b = list.get(position);
		ImageView image = (ImageView) convertView.findViewById(R.id.bs_image);
		TextView txtDiaChi = (TextView)convertView.findViewById(R.id.bs_diachi);
		TextView txtName = (TextView)convertView.findViewById(R.id.bs_name);
		TextView txtSDT = (TextView)convertView.findViewById(R.id.bs_sdt);
		
		txtDiaChi.setText(b.getDia_chi());
		txtName.setText(b.getTen_quan());
		txtSDT.setText(b.getSo_dt());
		
		return convertView;
	}
}
