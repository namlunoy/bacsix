package com.th10.bacsigiadinh.adapters;

import java.util.ArrayList;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxStatus;
import com.androidquery.callback.BitmapAjaxCallback;
import com.th10.bacsigiadinh.R;
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

public class ListLoaiThuocAdapter extends BaseAdapter {

	private ArrayList<LoaiThuoc> listLoaiThuoc;
	private Activity context;

	public ListLoaiThuocAdapter(Activity context,
			ArrayList<LoaiThuoc> listLoaiThuoc) {
		this.context = context;
		this.listLoaiThuoc = listLoaiThuoc;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return listLoaiThuoc.size();
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
		
		LoaiThuoc loaithuoc = listLoaiThuoc.get(position);
		
		ImageView img = (ImageView) rowView.findViewById(R.id.imgLoaiThuoc);
		TextView txt = (TextView) rowView.findViewById(R.id.txtLoaiThuoc);
		txt.setText(loaithuoc.getName());
		
		AQuery aq = new AQuery(convertView);


		aq.id(img)
				.progress(this)
				.image(loaithuoc.getImage(), true, true, 0, 0,
						new BitmapAjaxCallback() {

							@Override
							protected void callback(String url, ImageView iv,
									Bitmap bm, AjaxStatus status) {
								iv.setImageBitmap(bm);
							}

						});
			
		return rowView;
	}

}
