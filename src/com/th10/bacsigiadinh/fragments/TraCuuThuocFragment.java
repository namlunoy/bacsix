package com.th10.bacsigiadinh.fragments;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.htmlcleaner.XPatherException;

import android.app.Activity;
import android.app.Fragment;
import android.app.SearchManager;
import android.app.SearchableInfo;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.view.MenuItemCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.TextView;

import com.th10.bacsigiadinh.DrugViewer;
import com.th10.bacsigiadinh.R;
import com.th10.bacsigiadinh.adapters.GridDrugAdapter;
import com.th10.bacsigiadinh.models.Thuoc;

public class TraCuuThuocFragment extends Fragment implements
		OnQueryTextListener, android.widget.SearchView.OnCloseListener {

	private static ArrayList<Thuoc> listDrugs;
	private EditText edtTimThuoc;
	private Button btnTimThuoc;
	private GridView gridThuoc;
	private SearchView searchView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_tracuuthuoc,
				container, false);

		//edtTimThuoc = (EditText) rootView.findViewById(R.id.edtTimThuoc);
		//btnTimThuoc = (Button) rootView.findViewById(R.id.btnTimThuoc);
		gridThuoc = (GridView) rootView.findViewById(R.id.gridViewThuoc);

		/*btnTimThuoc.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Log.d(edtTimThuoc.getText().toString(), "ggg");
				listDrugs = new ArrayList<Thuoc>();
				try {
					TimThuoc("http://www.camnangthuoc.vn/search/drug.drug.article/"
							+ edtTimThuoc.getText().toString());
				} catch (XPatherException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				gridThuoc.setAdapter(new GridDrugAdapter(getActivity(),
						listDrugs));

			}
		});*/
		// gridThuoc.setAdapter(new GridDrugAdapter(getActivity(), listDrugs));
		gridThuoc.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent(getActivity(), DrugViewer.class);
				intent.putExtra("image",listDrugs.get(position).getImage());
				intent.putExtra("detail", listDrugs.get(position).getLink());
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
							listDrugs = new ArrayList<Thuoc>();
							try {
								TimThuoc("http://www.camnangthuoc.vn/search/drug.drug.article/"
										+ query);
							} catch (XPatherException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

							gridThuoc.setAdapter(new GridDrugAdapter(
									getActivity(), listDrugs));
							return false;
						}

						@Override
						public boolean onQueryTextChange(String newText) {
							// TODO Auto-generated method stub
							return false;
						}
					});

		}

	}

	@Override
	public boolean onClose() {
		// TODO Auto-generated method stub
		return false;
	}

	private static void TimThuoc(String html) throws XPatherException {

		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
		StrictMode.setThreadPolicy(policy);
		HtmlCleaner cleaner = new HtmlCleaner();
		CleanerProperties props = cleaner.getProperties();
		props.setAllowHtmlInsideAttributes(false);
		props.setAllowMultiWordAttributes(true);
		props.setRecognizeUnicodeChars(true);
		props.setOmitComments(true);

		TagNode node = null;
		try {
			node = cleaner.clean(new URL(html));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// lấy số trang
		int pageNumber = 0, pages = 1;
		for (Object obj : node.evaluateXPath("//div[@class='pages']/a/@title")) {
			pageNumber = pages;
			pages = Integer.parseInt(obj.toString());
			pageNumber=1;
		}
		
		// lấy dữ liệu từng trang kết quả

		for (int i = 1; i <= pageNumber; i++) {
			if (i > 1) {
				try {
					node = cleaner.clean(new URL(html + "?p=" + i));
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			// Ä‘á»�c tá»«ng Ã´
			int leng = node
					.evaluateXPath("//div[@style='display:table;']/div[1]").length, j = 1;
			while (leng > 0) {
				Thuoc thuoc = new Thuoc();

				for (Object o : node
						.evaluateXPath("//div[@style='display:table;']/div["
								+ j + "]/strong")) {
					// System.out.println(((TagNode) o).getText());
					thuoc.setName(((TagNode) o).getText().toString());
					Object[] obj = node
							.evaluateXPath("//div[@style='display:table;']/div["
									+ j + "]//img/@src");
					// System.out.println("Ten thuoc: " + obj[0].toString());
					thuoc.setImage("http://www.camnangthuoc.vn"
							+ obj[0].toString());
					obj = node
							.evaluateXPath("//div[@style='display:table;']/div["
									+ j + "]//a/@href");
					// System.out.println("Ten thuoc: " + obj[0].toString());
					thuoc.setLink("http://www.camnangthuoc.vn"
							+ obj[0].toString());
				}
				System.out.println(thuoc.getName() + " " + thuoc.getImage()
						+ " " + thuoc.getLink());
				listDrugs.add(thuoc);

				j = j + 1;
				leng = node.evaluateXPath("//div[@style='display:table;']"
						+ "/div[" + j + "]").length;
			}
		}

	}

	@Override
	public boolean onQueryTextSubmit(String query) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onQueryTextChange(String newText) {
		// TODO Auto-generated method stub
		return false;
	}

}
