package com.th10.bacsigiadinh;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.htmlcleaner.XPatherException;

import com.th10.bacsigiadinh.helpers.MyHelper;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.SyncStateContract.Helpers;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class DrugViewer extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		
		 StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
	     .detectAll()
	     .penaltyLog()
	     .build();
	 StrictMode.setThreadPolicy(policy);
		ActionBar actionBar = getActionBar();
		actionBar.setBackgroundDrawable(getResources().getDrawable(
				R.drawable.background));

		try {
			setContentView(R.layout.drug_viewer);

			Bundle extras = getIntent().getExtras();
			String image = extras.getString("image");
			String link = extras.getString("detail");

			TextView txt = (TextView) findViewById(R.id.showDetailDrug);
			ImageView img = (ImageView) findViewById(R.id.showImageDrug);

			URL url = null;
			Bitmap bmp = null;

			url = new URL(image);
			//
			bmp = BitmapFactory.decodeStream(url.openConnection()
					.getInputStream());

			img.setImageBitmap(bmp);

			String drugDetail = "";
			HtmlCleaner cleaner = new HtmlCleaner();
			TagNode node = null;

			node = cleaner.clean(new URL(link));

			// 12 sắc thái thuốc
			for (int i = 0; i <= 12; i++) {
				for (Object o : node
						.evaluateXPath("//table[@class='durg-detail']//tr[" + i
								+ "]/td")) {
					// System.out.println(((TagNode) o).getText());
					drugDetail = drugDetail + "\n"
							+ ((TagNode) o).getText().toString();
				}
			}
			txt.setText(drugDetail);
		} catch (Exception e) {
			e.printStackTrace();
			MyHelper.Toast(this, "sang roi " + e.getMessage());
		}
	}

}
