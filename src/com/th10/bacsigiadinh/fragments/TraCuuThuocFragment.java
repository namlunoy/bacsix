package com.th10.bacsigiadinh.fragments;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;

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

import com.th10.bacsigiadinh.DrugViewer;
import com.th10.bacsigiadinh.GridDrugActivity;
import com.th10.bacsigiadinh.R;
import com.th10.bacsigiadinh.adapters.GridDrugAdapter;
import com.th10.bacsigiadinh.adapters.ListLoaiThuocAdapter;
import com.th10.bacsigiadinh.helpers.MyHelper;
import com.th10.bacsigiadinh.models.LoaiThuoc;
import com.th10.bacsigiadinh.models.Thuoc;

public class TraCuuThuocFragment extends Fragment implements
		OnQueryTextListener, android.widget.SearchView.OnCloseListener {

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
		listLoaiThuocs
				.add(new LoaiThuoc("Thuốc hạ sốt",
						"http://www.camnangthuoc.vn/category/view/drugs/article/1223/","http://www.ichnhi.vn/uploads/images/2015/02/13/14237958949011.jpg"));
		listLoaiThuocs
				.add(new LoaiThuoc("Thuốc trị đau lưng",
						"http://www.camnangthuoc.vn/category/view/drugs/article/1123/","http://suckhoedanong.vn/uploads/SUCKHOE/benhkhac/dau-lung.jpg"));
		listLoaiThuocs
				.add(new LoaiThuoc("Thuốc cảm, sổ mũi",
						"http://www.camnangthuoc.vn/category/view/drugs/article/1220/","http://benh.vn/upload/image/tre-so-mui-benhvn.jpg"));
		listLoaiThuocs
				.add(new LoaiThuoc("Thuốc chống viêm",
						"http://www.camnangthuoc.vn/category/view/drugs/article/1221/","http://bvdkhagiang.org.vn/news/images/benh-nang-6.jpg"));
		
		listLoaiThuocs
				.add(new LoaiThuoc("Thuốc giảm đau",
						"http://www.camnangthuoc.vn/category/view/drugs/article/1121/","http://t4gquangtri.vn/images/stories/THUMUCANH/THONGTINDUOC/thuoc%20dau%20dau.jpg"));
		listLoaiThuocs
				.add(new LoaiThuoc("Thuốc sổ mũi, nghẹt mũi",
						"http://www.camnangthuoc.vn/category/view/drugs/article/1222/","http://mecuti.vn/wp-content/uploads/2014/10/C%C3%A1ch-%C4%91i%E1%BB%81u-tr%E1%BB%8B-ch%E1%BA%A3y-n%C6%B0%E1%BB%9Bc-m%C5%A9i-%E1%BB%9F-tr%E1%BA%BB-em-hi%E1%BB%87u-qu%E1%BA%A3-gi%C3%BAp-tr%E1%BA%BB-mau-kh%E1%BB%8Fe-m%C3%A0-c%C3%A1c-m%E1%BA%B9-c%C3%B3-th%E1%BB%83-tham-kh%E1%BA%A3o.jpg"));
		listLoaiThuocs
				.add(new LoaiThuoc("Thuốc trị loãng xương",
						"http://www.camnangthuoc.vn/category/view/drugs/article/1244/","http://images.tuoitre.vn/tianyon/ImageView.aspx?ThumbnailID=703374"));
		listLoaiThuocs
				.add(new LoaiThuoc("Thuốc trị thấp khớp",
						"http://www.camnangthuoc.vn/category/view/drugs/article/1240/","http://lohha.com.vn/wp-content/uploads/2013/06/khop3.jpg"));
		listLoaiThuocs
				.add(new LoaiThuoc("Thuốc viêm dạ dày",
						"http://www.camnangthuoc.vn/category/view/drugs/article/1267/","http://www.tinhbotnghe.vn/Upload/images/benh-dau-da-day.jpg"));

		listThuoc.setAdapter(new ListLoaiThuocAdapter(getActivity(),
				listLoaiThuocs));
		
		listThuoc.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getActivity(),
						GridDrugActivity.class);
				intent.putExtra("link",listLoaiThuocs.get(position).getLink());
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

	@Override
	public boolean onClose() {
		// TODO Auto-generated method stub
		return false;
	}

	private class TimThuoc extends AsyncTask<String, Void, Void> {
		private ProgressDialog mProgressDialog;

		@Override
		protected void onPreExecute() {
			mProgressDialog = new ProgressDialog(getActivity());
			mProgressDialog = new ProgressDialog(getActivity(),
					ProgressDialog.THEME_DEVICE_DEFAULT_LIGHT);
			mProgressDialog.getWindow().clearFlags(
					WindowManager.LayoutParams.FLAG_DIM_BEHIND);
			mProgressDialog.setTitle("Đang tải xin chờ!");
			mProgressDialog.setMessage("Loading...");
			mProgressDialog.setIndeterminate(false);
			mProgressDialog.show();
			super.onPreExecute();
		}

		@Override
		protected void onPostExecute(Void result) {
			mProgressDialog.dismiss();
			// gridThuoc.setAdapter(new GridDrugAdapter(getActivity(),
			// listDrugs));
			super.onPostExecute(result);
		}

		@Override
		protected Void doInBackground(String... params) {
			try {
				listDrugs = new ArrayList<Thuoc>();
				HtmlCleaner cleaner = new HtmlCleaner();
				CleanerProperties props = cleaner.getProperties();
				props.setAllowHtmlInsideAttributes(false);
				props.setAllowMultiWordAttributes(true);
				props.setRecognizeUnicodeChars(true);
				props.setOmitComments(true);

				TagNode node = null;
				try {
					node = cleaner.clean(new URL(params[0]));
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				// lấy số trang
				int pageNumber = 1;// pages = 1;
				// for (Object obj : node
				// .evaluateXPath("//div[@class='pages']/a/@title")) {
				// pageNumber = pages;
				// pages = Integer.parseInt(obj.toString());
				// pageNumber = 1;
				// }

				// lấy dữ liệu từng trang kết quả

				for (int i = 1; i <= pageNumber; i++) {
					if (i > 1) {
						try {
							node = cleaner
									.clean(new URL(params[0] + "?p=" + i));
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
							// System.out.println("Ten thuoc: " +
							// obj[0].toString());
							thuoc.setImage("http://www.camnangthuoc.vn"
									+ obj[0].toString());
							obj = node
									.evaluateXPath("//div[@style='display:table;']/div["
											+ j + "]//a/@href");
							// System.out.println("Ten thuoc: " +
							// obj[0].toString());
							thuoc.setLink("http://www.camnangthuoc.vn"
									+ obj[0].toString());
						}
						System.out.println(thuoc.getName() + " "
								+ thuoc.getImage() + " " + thuoc.getLink());
						listDrugs.add(thuoc);

						j = j + 1;
						leng = node
								.evaluateXPath("//div[@style='display:table;']"
										+ "/div[" + j + "]").length;
					}
				}
				MyHelper.Log("xxx", "xong xongxong");
			} catch (Exception e) {
				MyHelper.Log("xxx", "loi loi loi loi: " + e.getMessage());
			}
			return null;
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
