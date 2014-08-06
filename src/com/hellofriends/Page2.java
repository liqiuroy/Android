package com.hellofriends;

import activity.PActivity;
import adapter.DataAdapter;
import android.widget.ListView;

public class Page2 extends PActivity {
	private DataAdapter dataAdapter;
	private ListView listview;
	@Override
	protected void init() throws Exception {
		this.listview.setOnScrollListener(this);
		this.listview.setOnItemClickListener(this);
		
		loadDataAdapter();
	}

	private void loadDataAdapter() {
		if(this.listview.getAdapter()==null){
			initDataAdapter();
		}else{
			loadFirstPage();
		}
	}

	private void loadFirstPage() {
		// TODO Auto-generated method stub
		
	}

	private void initDataAdapter() {
		this.dataAdapter = new DataAdapter(this);
	}

	@Override
	protected int getLayout() {
		return R.layout.page2;
	}

	@Override
	protected String getDomain() {
		return null;
	}

}
