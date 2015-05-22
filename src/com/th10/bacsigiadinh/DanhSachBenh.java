package com.th10.bacsigiadinh;

import java.util.ArrayList;

import com.th10.bacsigiadinh.models.Benh;
import com.th10.bacsigiadinh.tasks.TimBenh;

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
		
		listBenhs = new ArrayList<Benh>();
		listBenh = (ListView) findViewById(R.id.listBenh);
		Bundle extra = getIntent().getExtras();
		
		String way = extra.getString("way");
		String qqq="";
		if (way.equals("1")){
			qqq = "http://camnangthuoc.vn/category/view/disease_cat/article/"+extra.getInt("link");
		}else {
			qqq ="http://camnangthuoc.vn/search/disease.listview-disease.article/"+extra.getString("link");
		}
		
		new TimBenh(listBenhs, this).execute(qqq);
		
	}
}
