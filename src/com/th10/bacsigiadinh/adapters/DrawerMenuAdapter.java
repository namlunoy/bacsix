package com.th10.bacsigiadinh.adapters;

import java.util.ArrayList;

import com.th10.bacsigiadinh.R;
import com.th10.bacsigiadinh.items.DrawerMenuItem;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class DrawerMenuAdapter extends BaseAdapter {
	private Context context;
	private ArrayList<DrawerMenuItem> items;

	public DrawerMenuAdapter(Context context, ArrayList<DrawerMenuItem> items) {
		super();
		this.context = context;
		this.items = items;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {

			LayoutInflater mInflater = (LayoutInflater) context
					.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

			convertView = mInflater
					.inflate(R.layout.item_gridview_drawer, null);

		}

		ImageView icon = (ImageView) convertView
				.findViewById(R.id.cc_icon_item_menu);
		TextView title = (TextView) convertView
				.findViewById(R.id.cc_name_item_menu);

		icon.setImageResource(items.get(position).getIconId());
		title.setText(items.get(position).getTitle());

		return convertView;
	}

	@Override
	public int getCount() {
		return items.size();
	}

	@Override
	public Object getItem(int index) {
		return items.get(index);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

}
