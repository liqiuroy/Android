package com.hellofriends;

import activity.PActivity;
import adapter.DataAdapter;
import android.os.AsyncTask;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;

public class Page2 extends PActivity {
	private DataAdapter dataAdapter;
	private ListView listview;
	private RelativeLayout mainLayout;
	private RelativeLayout leftLayout;
	@Override
	protected void init() throws Exception {
		this.listview.setOnScrollListener(this);
		this.listview.setOnItemClickListener(this);
		
		resize();
		loadDataAdapter();
	}
	
	//将侧滑界面移出到屏幕外
	private void resize() {
		DisplayMetrics dm = getResources().getDisplayMetrics();
		RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)mainLayout.getLayoutParams();
		params.width = dm.widthPixels;
		mainLayout.setLayoutParams(params);
		
		params = (RelativeLayout.LayoutParams)leftLayout.getLayoutParams();
		params.leftMargin = 0-params.width;
		leftLayout.setLayoutParams(params);
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

	private void move(Integer dis){
		RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)mainLayout.getLayoutParams();
		params.leftMargin += dis;
		params.rightMargin -= dis;
		mainLayout.setLayoutParams(params);
		
		params = (RelativeLayout.LayoutParams)leftLayout.getLayoutParams();
		params.leftMargin += dis;
		params.rightMargin -= dis;
		leftLayout.setLayoutParams(params);
	}
	
	@Override
	protected void click(View view) throws Exception {
		int viewId = view.getId();
		if(viewId==R.id.leftbutton){
			leftDeamon(new AsyncTask<Integer, Integer, Void>(){
				@Override
				protected Void doInBackground(Integer... ints) {
					int ms = ints[1];
					//int dis = ms/ints[0];
					for(int i=0;i<ints[0];i++){
						this.publishProgress(1);
					}
					return null;
				}

				@Override
				protected void onProgressUpdate(Integer... values) {
					move(values[0]);
				}
			});
		}
	}

	private void leftDeamon(AsyncTask<Integer, Integer, Void> task) {
		task.execute(140,200);
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
