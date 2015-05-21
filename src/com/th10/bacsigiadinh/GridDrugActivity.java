package com.th10.bacsigiadinh;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.th10.bacsigiadinh.adapters.GridDrugAdapter;
import com.th10.bacsigiadinh.helpers.MyHelper;
import com.th10.bacsigiadinh.models.Thuoc;
import com.th10.bacsigiadinh.tasks.AppController;
import com.th10.bacsigiadinh.tasks.TimThuoc;
import com.th10.bacsigiadinh.tasks.TimThuocX;
import com.th10.bacsigiadinh.tasks.TimThuoc.GridClickItemListener;

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

public class GridDrugActivity extends Activity implements
		Response.Listener<JSONArray>, Response.ErrorListener {

	private GridView gridThuoc;
	private static ArrayList<Thuoc> listDrugs;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_grid_drug);

		ActionBar actionBar = getActionBar();
		actionBar.setBackgroundDrawable(getResources().getDrawable(
				R.drawable.background));
		listDrugs = new ArrayList<Thuoc>();
		gridThuoc = (GridView) findViewById(R.id.gridViewThuoc);
		Bundle extra = getIntent().getExtras();
		String type = extra.getString("link");
		String way = extra.getString("way");
		if (way.equals("1")) {
			GetData("type="+type);
		} else {
			String query = extra.getString("query");
			GetData("s="+query);
		}

		gridThuoc.setOnItemClickListener(new GridClickItemListener());

	}

	public void GetData(String type) {
		JsonArrayRequest request = new JsonArrayRequest(
				"http://tuandat1992.byethost9.com/home/search?" + type,
				this, this);
		AppController.getInstance().addToRequestQueue(request);
	}

	@Override
	public void onErrorResponse(VolleyError arg0) {

	}

	@Override
	public void onResponse(JSONArray arg0) {
		try {
			JSONArray array = arg0;

			for (int i = 0; i < array.length(); i++) {
				Thuoc l = new Thuoc();
				JSONObject o = (JSONObject) array.get(i);
				l.setName(o.getString("name"));
				l.setImage(o.getString("image"));
				l.setLink(o.getString("link"));
				listDrugs.add(l);
				System.out.println(l.getName() + " " + l.getLink());
			}
			gridThuoc.setAdapter(new GridDrugAdapter(this, listDrugs));

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public class GridClickItemListener implements OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int position,
				long arg3) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(getBaseContext(), DrugViewer.class);
			intent.putExtra("name", listDrugs.get(position).getName());
			intent.putExtra("image", listDrugs.get(position).getImage());
			intent.putExtra("detail", listDrugs.get(position).getLink());
			startActivity(intent);

		}

	}
}
