package com.th10.bacsigiadinh;

import java.util.ArrayList;

import com.th10.bacsigiadinh.adapters.DrawerMenuAdapter;
import com.th10.bacsigiadinh.fragments.BacSiTaiNhaFragment;
import com.th10.bacsigiadinh.fragments.CongCuFragment;
import com.th10.bacsigiadinh.fragments.TimNhaThuocFragment;
import com.th10.bacsigiadinh.fragments.TinTucFragment;
import com.th10.bacsigiadinh.fragments.TraCuuBenhFragment;
import com.th10.bacsigiadinh.fragments.TraCuuThuocFragment;
import com.th10.bacsigiadinh.helpers.MyHelper;
import com.th10.bacsigiadinh.interfaces.MyCallback;
import com.th10.bacsigiadinh.items.DrawerMenuItem;
import com.th10.bacsigiadinh.tasks.DownloadStringTask;
import com.th10.bacsigiadinh.tasks.GetPlacesTask;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;

public class MainActivity extends Activity implements MyCallback {
	private DrawerLayout mDrawerLayout;
	private GridView gridView;
	private LinearLayout linearLayout;
	private ActionBarDrawerToggle mDrawerToggle;

	// nav drawer title
	private CharSequence mDrawerTitle;

	private CharSequence mTitle;

	private ArrayList<DrawerMenuItem> menuItems;
	private DrawerMenuAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
//Công thay dổi
		
	//	/sdfsdfs
		mTitle = mDrawerTitle = getTitle();

		//
		menuItems = new ArrayList<DrawerMenuItem>();
		//sds ô
		menuItems.add(new DrawerMenuItem("Tìm Nhà Thuốc",
				R.drawable.ic_nhathuoc, new TimNhaThuocFragment()));
		menuItems.add(new DrawerMenuItem("Tra Cứu Thuốc", R.drawable.ic_thuoc,
				new TraCuuThuocFragment()));
		menuItems.add(new DrawerMenuItem("Tra Cứu Bệnh", R.drawable.ic_benh,
				new TraCuuBenhFragment()));
		menuItems.add(new DrawerMenuItem("Bác Sĩ Tại Nhà", R.drawable.ic_bacsi,
				new BacSiTaiNhaFragment()));
		menuItems.add(new DrawerMenuItem("Cài Đặt", R.drawable.ic_congcu,
				new CongCuFragment()));
		menuItems.add(new DrawerMenuItem("Tin Sức Khỏe",
				R.drawable.ic_tintuc, new TinTucFragment()));

	
		//------------------- findview  -------------------
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		linearLayout = (LinearLayout) findViewById(R.id.cc_linearLayout);
		gridView = (GridView) findViewById(R.id.cc_menu_gridview);

		// ------------------------
		gridView.setOnItemClickListener(new SlideMenuClickListener());

		// setting the nav drawer list adapter
		adapter = new DrawerMenuAdapter(getApplicationContext(), menuItems);
		gridView.setAdapter(adapter);

		// enabling action bar app icon and behaving it as toggle button
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);

		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.drawable.ic_drawer, // nav menu toggle icon
				R.string.app_name, // nav drawer open - description for
									// accessibility
				R.string.app_name // nav drawer close - description for
									// accessibility
		) {
			public void onDrawerClosed(View view) {
				getActionBar().setTitle(mTitle);
				// calling onPrepareOptionsMenu() to show action bar icons
				invalidateOptionsMenu();
			}

			public void onDrawerOpened(View drawerView) {
				getActionBar().setTitle(mDrawerTitle);
				// calling onPrepareOptionsMenu() to hide action bar icons
				invalidateOptionsMenu();
			}
		};
		mDrawerLayout.setDrawerListener(mDrawerToggle);

		if (savedInstanceState == null) {
			// on first time display view for first nav item
			displayView(2);
		}

		ActionBar actionBar = getActionBar();
		actionBar.setBackgroundDrawable(getResources().getDrawable(
				R.drawable.background));
	}

	// Slide menu item click listener
	private class SlideMenuClickListener implements
			ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// display view for selected nav drawer item
			displayView(position);
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// toggle nav drawer on selecting action bar app icon/title
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		// Handle action bar actions click
		switch (item.getItemId()) {
		case R.id.action_settings:
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	// Diplaying fragment view for selected nav drawer list item

	private void displayView(int position) {

		Fragment fragment = null;
	
		fragment = menuItems.get(position).getFragment();

		if (fragment != null) {
			FragmentManager fragmentManager = getFragmentManager();
			fragmentManager.beginTransaction()
					.replace(R.id.frame_container, fragment).commit();

			// update selected item and title, then close the drawer
			gridView.setItemChecked(position, true);
			gridView.setSelection(position);
			setTitle(menuItems.get(position).getTitle());
			mDrawerLayout.closeDrawer(linearLayout);
		} else {
			// error in creating fragment
			Log.e("MainActivity", "Error in creating fragment");
		}
	}

	@Override
	public void setTitle(CharSequence title) {
		mTitle = title;
		getActionBar().setTitle(mTitle);
	}

	/**
	 * When using the ActionBarDrawerToggle, you must call it during
	 * onPostCreate() and onConfigurationChanged()...
	 */

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		// Pass any configuration change to the drawer toggls
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

	@Override
	public void TaskDone(String result) {
		MyHelper.Toast(this, result);
	}

}
