package com.th10.bacsigiadinh;

import com.th10.bacsigiadinh.tasks.GetBaoTask;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class ChiTietBaiBaoActivity extends Activity {
	WebView webView;
	String link;
	ProgressDialog mProgressDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chi_tiet_bai_bao);
		Bundle bundle = getIntent().getExtras();
		link = bundle.getString("linkUrl");
		 webView = (WebView) findViewById(R.id.webNvexpress);
		 webView.getSettings().setSupportZoom(true);
		 webView.setInitialScale(1);
		 webView.getSettings().setLoadWithOverviewMode(true);
		 webView.getSettings().setUseWideViewPort(true);
		 webView.setScrollBarStyle(webView.SCROLLBARS_OUTSIDE_OVERLAY);
		 webView.setScrollbarFadingEnabled(false);
		 webView.setWebViewClient(new WebviewClienfinist());
		 mProgressDialog = new ProgressDialog(this);
			mProgressDialog = new ProgressDialog(this,
					ProgressDialog.THEME_DEVICE_DEFAULT_LIGHT);
			mProgressDialog.getWindow().clearFlags(
					WindowManager.LayoutParams.FLAG_DIM_BEHIND);
			mProgressDialog.setTitle("Đang tải xin chờ!");
			mProgressDialog.setMessage("Loading...");
			mProgressDialog.setIndeterminate(false);
			mProgressDialog.show();
		 webView.loadUrl(link);
	}
	
	class WebviewClienfinist extends WebViewClient{

		@Override
		public void onPageFinished(WebView view, String url) {
			if(mProgressDialog!=null){
				mProgressDialog.dismiss();
			}
			super.onPageFinished(view, url);
		}
		
	}
	
}
