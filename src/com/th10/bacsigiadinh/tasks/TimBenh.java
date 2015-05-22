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
import android.widget.ListView;

import com.th10.bacsigiadinh.ChiTietBenh;
import com.th10.bacsigiadinh.DrugViewer;
import com.th10.bacsigiadinh.GridDrugActivity;
import com.th10.bacsigiadinh.R;
import com.th10.bacsigiadinh.adapters.GridDrugAdapter;
import com.th10.bacsigiadinh.adapters.ListBenhAdapter;
import com.th10.bacsigiadinh.helpers.MyHelper;
import com.th10.bacsigiadinh.models.Benh;
import com.th10.bacsigiadinh.models.Thuoc;

public class TimBenh extends AsyncTask<String, Void, Void> {
	private ProgressDialog mProgressDialog;
	private ArrayList<Benh> listBenh;
	private Activity act;
	private ListView lvBenh;
	
	

	public TimBenh(ArrayList<Benh> listBenh, Activity act) {
		super();
		this.listBenh = listBenh;
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
		
		lvBenh = (ListView) act.findViewById(R.id.listBenh);
		lvBenh.setAdapter(new ListBenhAdapter(act,listBenh));
		lvBenh.setOnItemClickListener(new ListClickItemListener());
		super.onPostExecute(result);
	}

	@Override
	protected Void doInBackground(String... params) {
		try {
			listBenh = new ArrayList<Benh>();
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

			int leng = node.evaluateXPath("//div[@style='display:table;']/div[1]").length, j = 1;
			System.out.println(leng);
			while (leng > 0) {
				Benh benh = new Benh();
				for (Object o : node
						.evaluateXPath("//div[@style='display:table;']/div[" + j+ "]//a")) {
					benh.setName(((TagNode) o).getText().toString());
					System.out.println(((TagNode) o).getText());
				}
				Object[] obj = node
						.evaluateXPath("//div[@style='display:table;']/div["
								+ j + "]//a/@href");
				benh.setLink(obj[0].toString());
				System.out.println("Link: " + obj[0].toString());
				listBenh.add(benh);
				j = j + 1;
				leng = node.evaluateXPath("//div[@style='display:table;']"
						+ "/div[" + j + "]").length;
			}
			
		
			MyHelper.Log("xxx", "xong xongxong");
		} catch (Exception e) {
			MyHelper.Log("xxx", "loi loi loi loi: " + e.getMessage());
		}
		return null;
	}
	
	public class ListClickItemListener implements OnItemClickListener{

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int position,
				long arg3) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(act, ChiTietBenh.class);
			intent.putExtra("link", "http://camnangthuoc.vn"+listBenh.get(position).getLink());
			act.startActivity(intent);
			
		}
		
	}
}
