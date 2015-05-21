package com.th10.bacsigiadinh;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.htmlcleaner.XPatherException;

import com.androidquery.AQuery;
import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;
import com.th10.bacsigiadinh.helpers.MyHelper;
import com.th10.bacsigiadinh.tasks.ThongTinThuoc;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.SyncStateContract.Helpers;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

public class DrugViewer extends Activity {

	private ProgressDialog mProgressDialog;
	public TextView txt;
	private String drugDetail;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
		StrictMode.setThreadPolicy(policy);
		ActionBar actionBar = getActionBar();
		actionBar.setBackgroundDrawable(getResources().getDrawable(
				R.drawable.background));

		setContentView(R.layout.drug_viewer);

		Bundle extras = getIntent().getExtras();
		String image = extras.getString("image");
		System.out.println(image);
		String link = extras.getString("detail");
		String name = extras.getString("name");

		txt = (TextView) findViewById(R.id.showDetail);
		ImageView img = (ImageView) findViewById(R.id.showImageDrug);

		UrlImageViewHelper.setUrlDrawable(img, image);
		new ThongTinThuoc(this).execute(link);

	}

}