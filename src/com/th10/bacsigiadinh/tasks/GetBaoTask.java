package com.th10.bacsigiadinh.tasks;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.jsoup.Jsoup;

import com.th10.bacsigiadinh.ChiTietBaiBaoActivity;
import com.th10.bacsigiadinh.R;
import com.th10.bacsigiadinh.adapters.ListBaiBaoAdapter;
import com.th10.bacsigiadinh.helpers.MyHelper;
import com.th10.bacsigiadinh.helpers.Netutil;
import com.th10.bacsigiadinh.models.BaiBao;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class GetBaoTask extends AsyncTask<String, Void, List<BaiBao>> {
	BaiBao rssItem = null;
	Activity activityCha;
	List<BaiBao> arrayList = new ArrayList<BaiBao>();
	ProgressDialog mProgressDialog;
	
	public GetBaoTask(Activity activityCha) {
		super();
		this.activityCha = activityCha;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		mProgressDialog = new ProgressDialog(activityCha);
		mProgressDialog = new ProgressDialog(activityCha,
				ProgressDialog.THEME_DEVICE_DEFAULT_LIGHT);
		mProgressDialog.getWindow().clearFlags(
				WindowManager.LayoutParams.FLAG_DIM_BEHIND);
		mProgressDialog.setTitle("Đang tải xin chờ! ");
		mProgressDialog.setMessage("Loading...");
		mProgressDialog.setIndeterminate(false);
		mProgressDialog.show();
	}

	@Override
	protected List<BaiBao> doInBackground(String... Urls) {
		List<BaiBao> itemList = null;
		try {
			itemList = new ArrayList<BaiBao>();

			InputStream in = Netutil._openConnection(Urls[0]);
			Document doc = null;
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db;
			db = dbf.newDocumentBuilder();
			doc = db.parse(in);
			doc.getDocumentElement().normalize();
			NodeList definitionElements = doc.getElementsByTagName("item");
			for (int i = 0; i < definitionElements.getLength(); i++) {
				Node itemNode = definitionElements.item(i);
				if (itemNode.getNodeType() == Node.ELEMENT_NODE) {
					rssItem = new BaiBao();
					Element item = (Element) itemNode;
					rssItem.setTitle(item.getElementsByTagName("title").item(0)
							.getTextContent().toString().trim());
					//rssItem.setLink(item.getElementsByTagName("link").item(0)
					//		.getTextContent().toString().trim());
					final String s = item.getElementsByTagName("description")
							.item(0).getTextContent().toString().trim();
					rssItem.setDescription(s);
					String a = s.substring(7, s.length());
					org.jsoup.nodes.Document document = Jsoup.parse(a);
					org.jsoup.nodes.Element element = document.select("img")
							.first();
					rssItem.setImgUrl(element.attr("src").toString());

					rssItem.setLink(item.getElementsByTagName("link").item(0)
							.getTextContent().toString().trim());
					rssItem.setDate(item.getElementsByTagName("pubDate")
							.item(0).getTextContent().toString().trim());
					itemList.add(rssItem);
					
				}
			}
			return itemList;
		} catch (Exception e) {
			Log.d("TEST", "doInBackground");
			e.printStackTrace();
		}

		return itemList;
	}

	@Override
	protected void onPostExecute(List<BaiBao> result) {
		ListView listView;
		mProgressDialog.dismiss();
		if (result == null) {
			//Toast.makeText(activityCha, "BÃ¡Â»â€¹ lÃ¡Â»â€”i :'(", 0).show();
		} else {
			arrayList = result;
			ListBaiBaoAdapter listAdapter = new ListBaiBaoAdapter(activityCha,
					R.layout.item_baibao, result);
			listView = (ListView) activityCha.findViewById(R.id.listViewRss);
			listView.setAdapter(listAdapter);
			listView.setOnItemClickListener(new RssListClickListener());
			listAdapter.notifyDataSetChanged();
		}

		super.onPostExecute(result);

	}

	public class RssListClickListener implements OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			if (MyHelper.checkInternetConnection(activityCha)) {
				Intent intent = new Intent(activityCha, ChiTietBaiBaoActivity.class);
				intent.putExtra("linkUrl", arrayList.get(position).getLink()
						.toString());
				intent.putExtra("urlImage", arrayList.get(position).getImgUrl()
						.toString());
				intent.putExtra("Title", arrayList.get(position).getTitle()
						.toString());
				activityCha.startActivity(intent);
			} else {
				//Toast.makeText(activityCha,"KiÃ¡Â»Æ’m tra lÃ¡ÂºÂ¡i kÃ¡ÂºÂ¿t nÃ¡Â»â€˜i internet", Toast.LENGTH_LONG)
				//		.show();
			}
			
		}
	}
}
