package com.th10.bacsigiadinh.fragments;

import java.util.ArrayList;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.AdapterView.OnItemClickListener;

import com.th10.bacsigiadinh.ChiTietBenh;
import com.th10.bacsigiadinh.DanhSachBenh;
import com.th10.bacsigiadinh.GridDrugActivity;
import com.th10.bacsigiadinh.R;
import com.th10.bacsigiadinh.adapters.ListLoaiBenhAdapter;
import com.th10.bacsigiadinh.adapters.ListLoaiThuocAdapter;
import com.th10.bacsigiadinh.models.LoaiBenh;

public class TraCuuBenhFragment extends Fragment {
	
	private ArrayList<LoaiBenh> listLoaiBenh;
	private ListView lvBenh;
	private SearchView searchView;
	public TraCuuBenhFragment() {
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_tracuubenh, container,
				false);
		listLoaiBenh = new ArrayList<LoaiBenh>();
		lvBenh = (ListView) rootView.findViewById(R.id.listLoaiBenh);
		
		listLoaiBenh.add(new LoaiBenh(261, "Răng hàm mặt", "http://images.alobacsi.vn/Images/Uploaded/Share/2011/09/04/rang.jpg"));
		listLoaiBenh.add(new LoaiBenh(262, "PP Sơ cưu nạn nhân", "http://www.hasanderma.com/upload/images/content/2(1).jpg"));
		listLoaiBenh.add(new LoaiBenh(264, "Bệnh thường gặp ở phụ nữ", "http://images.alobacsi.vn/Images/Uploaded/Share/2014/11/24/Bat-benh-qua-mui-va-mau-cua-dich-am-dao-1.jpg"));
		listLoaiBenh.add(new LoaiBenh(266, "Bệnh ở người già", "http://trungthaosamnhung.com/DATA/luyenvietphap/tin-tuc/052014/nguoi-gia-bi-sot-2.jpg"));
		listLoaiBenh.add(new LoaiBenh(268, "Bênh mắt tai mũi họng", "http://www.giaoducsuckhoe.net/Images/Illustration/External/2010/01/BongDungDauTaiDauHieuCuaViemTaiGiuaCap1.jpg"));
		listLoaiBenh.add(new LoaiBenh(269, "Bệnh phổi hô hấp", "http://healthplus.vn/Images/Uploaded/Share/2014/05/12/50bphong-benhhohap.jpg"));
		listLoaiBenh.add(new LoaiBenh(270, "Tim mạch và hệ tuần hoàn", "http://dantri4.vcmedia.vn/a3HWDOlTcvMNT73KRccc/Image/2013/10/3-93db9.jpg"));
		listLoaiBenh.add(new LoaiBenh(271, "Đường tiêu hóa - vùng bụng", "http://thuocthang.vn/upload/image/Tieu_Hoa/viem-da-day-cap-tinh.jpg"));
		listLoaiBenh.add(new LoaiBenh(274, "Bệnh hệ thần kinh", "http://bacsigiadinh.com/Content/Images/News/nao-he-than-kinh-bacsigiadinh.com.jpg"));
		
		lvBenh.setAdapter(new ListLoaiBenhAdapter(getActivity(), listLoaiBenh));
		lvBenh.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getActivity(),
						GridDrugActivity.class);
				intent.putExtra("link", listLoaiBenh.get(position).getId());
				intent.putExtra("way", "1");
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
									ChiTietBenh.class);
							intent.putExtra("link", query);
							intent.putExtra("way", "2");
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
	
}
