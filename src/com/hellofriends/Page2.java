package com.hellofriends;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import activity.PActivity;
import android.os.AsyncTask;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class Page2 extends PActivity {
	private ListView listview,leftMenuList;
	private RelativeLayout mainLayout;
	private RelativeLayout leftLayout;
	private boolean leftLayoutIsOpen = false;
	@Override
	protected void init() throws Exception {
		this.leftMenuList.setOnScrollListener(this);
		this.leftMenuList.setOnItemClickListener(this);
		
		resize();//隐藏侧滑栏
		loadLeftMenu();
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

	private void loadLeftMenu() {
		String key = "menuName";
		ArrayList<Map<String,String>> values = new ArrayList<Map<String,String>>();
		String[] strs = {"首页","朋友","个人详情","系统设置","联系我们"};
		for(String s : strs){
			Map<String,String> value = new HashMap<String,String>();
			value.put(key, s);
			values.add(value);
		}
		SimpleAdapter leftMenuData = new SimpleAdapter(this,values,R.layout.page2_leftmenu_item,new String[]{key},new int[]{R.id.menuItemName});
		this.leftMenuList.setAdapter(leftMenuData);
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
	protected void itemClick(AdapterView<?> parent, View view, int position,
			long id) throws Exception {
		if(parent.getId()==R.id.leftMenuList){
			this.alert("TEST",((TextView)(view.findViewById(R.id.menuItemName))).getText().toString());
		}
	}

	@Override
	protected void click(View view) throws Exception {
		int viewId = view.getId();
		if(viewId==R.id.leftbutton){
			leftDeamon(new AsyncTask<Integer, Integer, Void>(){
				@Override
				protected Void doInBackground(Integer... ints) {
					//int ms = ints[1];
					//int dis = ints[1]/ints[0];
					int sec = (int)Math.ceil(Math.abs(ints[1]/ints[0]));
					for(int i=0;i<Math.abs(ints[0]);i++){
						if(ints[0]<0)
							this.publishProgress(-1,sec);
						else
							this.publishProgress(1,sec);
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
		RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)leftLayout.getLayoutParams();
		if((this.leftLayoutIsOpen=!this.leftLayoutIsOpen)==false){
			task.execute(0-params.width,1000);
		}else{
			task.execute(params.width,1000);
		}
	}

	@Override
	protected int getLayout() {
		return R.layout.page2;
	}

	@Override
	protected String getDomain() {
		return null;
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		//android.os.Process.killProcess(android.os.Process.myPid());    //获取PID
		System.exit(0);   //常规java、c#的标准退出法，返回值为0代表正常退出
	}

}
