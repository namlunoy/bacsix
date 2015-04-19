package com.th10.bacsigiadinh.adapters;

import java.util.List;
import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;
import com.th10.bacsigiadinh.R;
import com.th10.bacsigiadinh.models.BaiBao;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListBaiBaoAdapter extends ArrayAdapter<BaiBao> {
	// Activi sử dụng
	private Activity activity;
	// item view ở layout
	private int itemListViewId;
	// List Song được đưa vào
	private List<BaiBao> listRssItem;

	public ListBaiBaoAdapter(Activity context, int resource, List<BaiBao> objects) {
		super(context, resource, objects);
		activity = context;
		itemListViewId = resource;
		listRssItem = objects;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// Lấy view ở ngoài và gán lại với item kia
		convertView = activity.getLayoutInflater()
				.inflate(itemListViewId, null);

		// Sau đó xử lý với từng control trong view, bây giờ đó chính là
		// convertview
		TextView txtTitle = (TextView) convertView
				.findViewById(R.id.txtnameTitle);
		TextView txtTimeUpdate = (TextView) convertView
				.findViewById(R.id.txttimeUpdate);
		ImageView image = (ImageView) convertView
				.findViewById(R.id.image_UrlAnh);

		BaiBao s = listRssItem.get(position);
//		txtTitle.setTypeface(MyTypeface.Comic(getContext()));
//		txtTimeUpdate.setTypeface(MyTypeface.Comic(getContext()));
		txtTitle.setText(s.getTitle());
		txtTimeUpdate.setText(s.getDate());
		UrlImageViewHelper.setUrlDrawable(image, s.getImgUrl());
		return convertView;
	}
}
