package com.th10.bacsigiadinh.fragments;

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

import android.app.Fragment;
import android.app.ProgressDialog;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;

import com.android.volley.Response;
import com.android.volley.Request.Method;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.th10.bacsigiadinh.DrugViewer;
import com.th10.bacsigiadinh.GridDrugActivity;
import com.th10.bacsigiadinh.R;
import com.th10.bacsigiadinh.adapters.GridDrugAdapter;
import com.th10.bacsigiadinh.adapters.ListLoaiThuocAdapter;
import com.th10.bacsigiadinh.helpers.MyHelper;
import com.th10.bacsigiadinh.models.LoaiThuoc;
import com.th10.bacsigiadinh.models.Thuoc;
import com.th10.bacsigiadinh.tasks.AppController;

public class TraCuuThuocFragment extends Fragment implements
		android.widget.SearchView.OnCloseListener {

	private static ArrayList<Thuoc> listDrugs;
	private ArrayList<LoaiThuoc> listLoaiThuocs;
	private ListView listThuoc;
	private GridView gridThuoc;
	private SearchView searchView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_tracuuthuoc,
				container, false);
		listLoaiThuocs = new ArrayList<LoaiThuoc>();
		listThuoc = (ListView) rootView.findViewById(R.id.listLoaiThuoc);
		System.out.println("get data");
		GetData();
		listThuoc.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getActivity(),
						GridDrugActivity.class);
				intent.putExtra("link", listLoaiThuocs.get(position).getLink());
				intent.putExtra("way", "1");
				startActivity(intent);

			}
		});

		return rootView;

	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		setHasOptionsMenu(true);
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		// TODO Auto-generated method stub
		super.onCreateOptionsMenu(menu, inflater);

		inflater.inflate(R.menu.menu_search_view, menu);
		MenuItem searchItem = menu.findItem(R.id.action_search);
		searchView = (SearchView) searchItem.getActionView();

		if (searchView != null) {
			searchView
					.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

						@Override
						public boolean onQueryTextSubmit(String query) {

							searchView.clearFocus();

							Intent intent = new Intent(getActivity(),
									GridDrugActivity.class);
							intent.putExtra("link",
									"http://www.camnangthuoc.vn/search/drug.drug.article/"
											+ query);
							intent.putExtra("way", "2");
							startActivity(intent);

							return false;
						}

						@Override
						public boolean onQueryTextChange(String newText) {
							return false;
						}

					});

		}

	}

	public void GetData() {
		JsonArrayRequest request = new JsonArrayRequest(
				"http://tuandat1992.byethost9.com/home/get_thuoc",
				new Listener<JSONArray>() {

					@Override
					public void onResponse(JSONArray arg0) {
						try {
							JSONArray array = arg0;

							for (int i = 0; i < array.length(); i++) {
								LoaiThuoc l = new LoaiThuoc();
								JSONObject o = (JSONObject) array.get(i);
								l.setName(o.getString("name"));
								l.setImage(o.getString("image"));
								l.setLink(o.getString("ID"));
								listLoaiThuocs.add(l);
								System.out.println(l.getName() + " "
										+ l.getLink());
							}

							listThuoc.setAdapter(new ListLoaiThuocAdapter(
									getActivity(), listLoaiThuocs));

						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							MyHelper.Toast(getActivity(), e.getMessage());
						}

					}
				}, null);

		AppController.getInstance().addToRequestQueue(request);

	}

	@Override
	public boolean onClose() {
		// TODO Auto-generated method stub
		return false;
	}

}
