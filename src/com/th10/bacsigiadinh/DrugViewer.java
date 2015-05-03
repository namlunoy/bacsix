package com.th10.bacsigiadinh;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.htmlcleaner.XPatherException;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class DrugViewer extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		ActionBar actionBar = getActionBar();
		actionBar.setBackgroundDrawable(getResources().getDrawable(
				R.drawable.background));
		
		setContentView(R.layout.drug_viewer);

		Bundle extras = getIntent().getExtras();
		String image = extras.getString("image");
		String link = extras.getString("detail");

		TextView txt = (TextView) findViewById(R.id.showDetailDrug);
		ImageView img = (ImageView) findViewById(R.id.showImageDrug);

		URL url = null;
		Bitmap bmp = null;

		try {
			url = new URL(image);
			bmp = BitmapFactory.decodeStream(url.openConnection()
					.getInputStream());
		} catch (MalformedURLException e) {

		} catch (IOException e) {

		}

		img.setImageBitmap(bmp);

		String drugDetail = "";
		HtmlCleaner cleaner = new HtmlCleaner();
		TagNode node = null;
		try {
			node = cleaner.clean(new URL(link));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 12 sắc thái thuốc
		for (int i = 0; i <= 12; i++) {

			try {
				for (Object o : node
						.evaluateXPath("//table[@class='durg-detail']//tr[" + i
								+ "]/td")) {
					// System.out.println(((TagNode) o).getText());
					drugDetail = drugDetail + "\n"
							+ ((TagNode) o).getText().toString();
				}
			} catch (XPatherException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		txt.setText(drugDetail);
	}

}
