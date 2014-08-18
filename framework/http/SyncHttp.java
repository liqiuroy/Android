package http;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class SyncHttp extends Thread{
	private String url;
	private String[] params;
	private Handler handler;
	
	public SyncHttp(String url,String[] params,Handler handler){
		this.setUrl(url);
		this.setParams(params);
		this.setHandler(handler);
		this.start();
	}
	
	public void run(){
		MyHttpClient http = new MyHttpClient();
		String rst = null;
		try {
			rst = http.get(this.getUrl(),this.getParams());
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Bundle bundle = new Bundle();
		bundle.putString("rst", rst);
		
		Message msg = new Message();
		msg.setData(bundle);
		
		this.getHandler().sendMessage(msg);
	}

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String[] getParams() {
		return params;
	}

	public void setParams(String[] params) {
		this.params = params;
	}
}
