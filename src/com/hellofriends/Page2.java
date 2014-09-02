package com.hellofriends;

import http.SyncHttp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import object.JsonObject;

import org.apache.http.client.ClientProtocolException;

import com.test.QRCodeActivity;

import utils.JSONUtil;
import activity.PActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import data.I;

public class Page2 extends PActivity {
	private ListView leftMenuList;
	private ListView mainList;
	private RelativeLayout mainLayout;
	private RelativeLayout leftLayout;
	private PopupWindow writeWindow;
	private boolean leftLayoutIsOpen = false;
	private Handler syncHandler;
	@Override
	protected void init() throws Exception {
		this.leftMenuList.setOnScrollListener(this);
		this.leftMenuList.setOnItemClickListener(this);
		this.mainList.setOnItemClickListener(this);
		
		resize();//隐藏侧滑栏
		loadMainList();
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

	private void loadMainList() {
		String key = "name";
		ArrayList<Map<String,String>> values = new ArrayList<Map<String,String>>();
		for(int i=0;i<10;i++){
			Map<String,String> value = new HashMap<String,String>();
			value.put(key, "李民");
			values.add(value);
		}
		SimpleAdapter mainData = new SimpleAdapter(this,values,R.layout.page2_mainlist_item,new String[]{},new int[]{});
		this.mainList.setAdapter(mainData);
	}
	
	private void loadLeftMenu() {
		String key = "menuName";
		ArrayList<Map<String,String>> values = new ArrayList<Map<String,String>>();
		String[] strs = {"首页","朋友","个人详情","系统设置","联系我们","二维码","业务办理"};
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
			String textName = ((TextView)(view.findViewById(R.id.menuItemName))).getText().toString();
			this.alert("TEST",textName);
			menuClick(textName);
		}
		else if(parent.getId()==R.id.mainList){
			this.to(Page3.class);
		}
	}

	private void menuClick(String textName) {
		if("二维码".equals(textName)){
			this.to(QRCodeActivity.class);
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
		}else if(viewId==R.id.rightbutton){
			showWritenWindow(view);
		}else if(viewId==R.id.submitBtn){
			subContent();
		}
	}

	private void subContent() throws ClientProtocolException, IOException {
		final EditText et = (EditText)this.writeWindow.getContentView().findViewById(R.id.editText);
		final String content = et.getText().toString();
		this.syncHandler = new Handler(){
			@Override
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
				Bundle bundle = msg.getData();
				try {
					JsonObject jo = JSONUtil.toJsonObject(bundle.getString("rst"));
				} catch (Exception e) {
					e.printStackTrace();
				}
				et.setText("");
				writeWindow.dismiss();
			}
		};
		SyncHttp syncHttp = new SyncHttp(I.SAVE_TIMELINE,new String[]{content},this.syncHandler);
		//syncHttp.start();
		//this.http.get(I.SAVE_TIMELINE,new String[]{content});
		//et.setText("");
		//this.writeWindow.dismiss();
	}

	private void showWritenWindow(View view) {
		if(this.writeWindow==null){
			createWritenWindow();
		}
		
		this.writeWindow.setFocusable(true);
		this.writeWindow.showAsDropDown(view);
		System.out.println("is showing");
	}

	private void createWritenWindow() {
		View view = this.getLayoutInflater().inflate(R.layout.page2_writenwindow, null);
		view.findViewById(R.id.submitBtn).setOnClickListener(this);
		this.writeWindow = new PopupWindow(view,this.mainLayout.getWidth(),R.dimen.page2_writenwindow_height);
		//this.writeWindow.setAnimationStyle(R.style.PopupAnimation);
		this.writeWindow.setClippingEnabled(true);
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
		this.exit();
	}

}
