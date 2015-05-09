package com.th10.bacsigiadinh.adapters;

import java.util.ArrayList;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxStatus;
import com.androidquery.callback.BitmapAjaxCallback;
import com.th10.bacsigiadinh.R;
import com.th10.bacsigiadinh.models.Thuoc;

import android.content.Context;
import android.graphics.Bitmap;

import android.graphics.Point;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class GridDrugAdapter extends BaseAdapter {

	private Context context;
	private final ArrayList<Thuoc> listDrug;
	private int width;
	private int height;

	public GridDrugAdapter(Context context, ArrayList<Thuoc> listDrug) {

		this.context = context;
		this.listDrug = listDrug;

		WindowManager wm = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();

		Point size = new Point();
		display.getSize(size);
		width = size.x;
		height = size.y;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return listDrug.size();
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

	public class Holder {
		TextView textView;
		ImageView imageView;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {

		Holder holder = new Holder();
		View rowView;
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		rowView = inflater.inflate(R.layout.grid_drug, null);

		rowView.setLayoutParams(new GridView.LayoutParams((width / 2) - 5,
				height / 5));
		rowView.setPadding(8, 8, 8, 8);

		holder.textView = (TextView) rowView.findViewById(R.id.textDrug);
		holder.imageView = (ImageView) rowView.findViewById(R.id.imageDrug);

		// holder.imageView.setX(height/5-50);
		// holder.imageView.setY(width/2-50);
		AQuery aq = new AQuery(convertView);

		holder.textView.setText(listDrug.get(position).getName());

		aq.id(holder.imageView)
				.progress(this)
				.image(listDrug.get(position).getImage(), true, true, 0, 0,
						new BitmapAjaxCallback() {

							@Override
							protected void callback(String url, ImageView iv,
									Bitmap bm, AjaxStatus status) {
								iv.setImageBitmap(bm);
							}

						});

		/*
		 * URL url = null; Bitmap bmp = null;
		 * 
		 * try { url = new URL(listDrug.get(position).getImage()); bmp =
		 * BitmapFactory.decodeStream(url.openConnection() .getInputStream()); }
		 * catch (MalformedURLException e) {
		 * 
		 * } catch (IOException e) {
		 * 
		 * } holder.imageView.setImageBitmap(bmp);
		 */

		/*
		 * rowView.setOnClickListener(new View.OnClickListener() {
		 * 
		 * @Override public void onClick(View v) { // TODO Auto-generated method
		 * stub
		 * 
		 * Toast.makeText(context, "You Clicked " +
		 * listDrug.get(position).getLink(), Toast.LENGTH_LONG).show();
		 * 
		 * } });
		 */
		return rowView;
	}

}
