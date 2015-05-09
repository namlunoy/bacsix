package com.th10.bacsigiadinh.tasks;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.AdapterView.OnItemClickListener;

import com.th10.bacsigiadinh.DrugViewer;
import com.th10.bacsigiadinh.GridDrugActivity;
import com.th10.bacsigiadinh.R;
import com.th10.bacsigiadinh.adapters.GridDrugAdapter;
import com.th10.bacsigiadinh.helpers.MyHelper;
import com.th10.bacsigiadinh.models.Thuoc;

public class TimThuoc extends AsyncTask<String, Void, Void> {
	private ProgressDialog mProgressDialog;
	private ArrayList<Thuoc> listDrug;
	private Activity act;
	private GridView gridThuoc;
	
	

	public TimThuoc(ArrayList<Thuoc> listDrug, Activity act) {
		super();
		this.listDrug = listDrug;
		this.act = act;
	}

	@Override
	protected void onPreExecute() {
		mProgressDialog = new ProgressDialog(act);
		mProgressDialog = new ProgressDialog(act,
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
		
		gridThuoc = (GridView) act.findViewById(R.id.gridViewThuoc);
		gridThuoc.setAdapter(new GridDrugAdapter(act,listDrug));
		gridThuoc.setOnItemClickListener(new GridClickItemListener());
		super.onPostExecute(result);
	}

	@Override
	protected Void doInBackground(String... params) {
		try {
			listDrug = new ArrayList<Thuoc>();
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
						node = cleaner.clean(new URL(params[0] + "?p=" + i));
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

					if (node.evaluateXPath("//div[@style='display:table;']/div[" + j
							+ "]/strong").length ==0){
						for (Object o : node
								.evaluateXPath("//div[@style='display:table;']/div["
										+ j + "]/a")) {
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
					}
					else {
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
					}
					
					listDrug.add(thuoc);

					j = j + 1;
					leng = node.evaluateXPath("//div[@style='display:table;']"
							+ "/div[" + j + "]").length;
				}
			}
			MyHelper.Log("xxx", "xong xongxong");
		} catch (Exception e) {
			MyHelper.Log("xxx", "loi loi loi loi: " + e.getMessage());
		}
		return null;
	}
	
	public class GridClickItemListener implements OnItemClickListener{

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int position,
				long arg3) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(act, DrugViewer.class);
			intent.putExtra("name", listDrug.get(position).getName());
			intent.putExtra("image", listDrug.get(position).getImage());
			intent.putExtra("detail", listDrug.get(position).getLink());
			act.startActivity(intent);
			
		}
		
	}
}
