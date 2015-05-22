package com.th10.bacsigiadinh.adapters;

import java.util.ArrayList;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxStatus;
import com.androidquery.callback.BitmapAjaxCallback;
import com.th10.bacsigiadinh.R;
import com.th10.bacsigiadinh.models.Benh;
import com.th10.bacsigiadinh.models.LoaiBenh;
import com.th10.bacsigiadinh.models.LoaiThuoc;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListBenhAdapter extends BaseAdapter {

	private ArrayList<Benh> listBenh;
	private Activity context;

	public ListBenhAdapter(Activity context,
			ArrayList<Benh> listBenh) {
		this.context = context;
		this.listBenh = listBenh;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return listBenh.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View rowView = context.getLayoutInflater().inflate(R.layout.item_loai_thuoc, null);
		
		Benh benh = listBenh.get(position);
		
		//ImageView img = (ImageView) rowView.findViewById(R.id.imgLoaiThuoc);
		TextView txt = (TextView) rowView.findViewById(R.id.txtLoaiThuoc);
		txt.setText(benh.getName());
		
		return rowView;
	}

}
