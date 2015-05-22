package com.th10.bacsigiadinh;

import java.util.ArrayList;

import com.th10.bacsigiadinh.models.Benh;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

public class DanhSachBenh extends Activity {

	private ListView listBenh;
	private ArrayList<Benh> listBenhs;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ActionBar actionBar = getActionBar();
		actionBar.setBackgroundDrawable(getResources().getDrawable(
				R.drawable.background));
		setContentView(R.layout.activity_danh_sach_benh);
		
		listBenhs = new ArrayList<>();
		listBenh = (ListView) findViewById(R.id.listBenh);
		Bundle extra = getIntent().getExtras();
		String link = extra.getString("link");
		String way = extra.getString("way");
		if (way.equals("1")){
			link = "http://camnangthuoc.vn/category/view/disease_cat/article/"+link;
		}else {
			link ="http://camnangthuoc.vn/search/disease.listview-disease.article/"+link;
		}
		
		
		
	}
}
