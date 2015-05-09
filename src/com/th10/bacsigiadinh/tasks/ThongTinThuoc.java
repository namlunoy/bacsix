package com.th10.bacsigiadinh.tasks;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;

import com.th10.bacsigiadinh.DrugViewer;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.view.WindowManager;
import android.widget.TextView;

public class ThongTinThuoc extends AsyncTask<String, Void, String> {
	private ProgressDialog mProgressDialog;
	private String drugDetail;
	DrugViewer act;


	
	
	public ThongTinThuoc(DrugViewer act) {
		super();
		this.act=act;
	}

	@Override
	protected void onPostExecute(String result) {
		mProgressDialog.dismiss();
		
		act.txt.setText(result.trim());
		super.onPostExecute(result);
	}

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		mProgressDialog = new ProgressDialog(act);
		mProgressDialog = new ProgressDialog(act,
				ProgressDialog.THEME_DEVICE_DEFAULT_LIGHT);
		mProgressDialog.getWindow().clearFlags(
				WindowManager.LayoutParams.FLAG_DIM_BEHIND);
		mProgressDialog.setTitle("Đang tải xin chờ!");
		mProgressDialog.setMessage("Loading...");
		mProgressDialog.setIndeterminate(false);
		super.onPreExecute();
	}

	@Override
	protected String doInBackground(String... arg0) {
		try {
			drugDetail="";
			HtmlCleaner cleaner = new HtmlCleaner();
			CleanerProperties props = cleaner.getProperties();
			props.setAllowHtmlInsideAttributes(false);
			props.setAllowMultiWordAttributes(true);
			props.setRecognizeUnicodeChars(true);
			props.setOmitComments(true);

			TagNode node = null;
			try {
				node = cleaner.clean(new URL(arg0[0]));
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// 12 sắc thái thuốc
			for (int i = 0; i < 12; i++) {
				
				for (Object o : node
						.evaluateXPath("//table[@class='durg-detail']//tr[" + i
								+ "]/td")) {
					// System.out.println(((TagNode) o).getText());
					drugDetail = drugDetail + "\n"
							+ ((TagNode) o).getText().toString();
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return drugDetail;
	}

}
