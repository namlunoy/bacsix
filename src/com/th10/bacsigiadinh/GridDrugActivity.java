package com.th10.bacsigiadinh;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;

import com.th10.bacsigiadinh.helpers.MyHelper;
import com.th10.bacsigiadinh.models.Thuoc;
import com.th10.bacsigiadinh.tasks.TimThuoc;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

public class GridDrugActivity extends Activity {

	private GridView gridThuoc;
	private static ArrayList<Thuoc> listDrugs;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_grid_drug);

		ActionBar actionBar = getActionBar();
		actionBar.setBackgroundDrawable(getResources().getDrawable(
				R.drawable.background));
		gridThuoc = (GridView) findViewById(R.id.gridViewThuoc);
		Bundle extra = getIntent().getExtras();
		String link = extra.getString("link");
		
		new TimThuoc(listDrugs,this).execute(link);
		
		
	}
}
