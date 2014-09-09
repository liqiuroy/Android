package com.test;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import activity.PActivity;
import adapter.ListenerAdapter;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.hellofriends.R;

public class QRScanActivity extends PActivity {
	private SurfaceView sfv;
	private Timer scanTimer;
	@Override
	protected void init() throws Exception {
		this.sfv.getHolder().addCallback(new ListenerAdapter(){
			@Override
			public void surfaceChanged(SurfaceHolder holder, int arg1, int arg2,
					int arg3) {
				super.surfaceChanged(holder, arg1, arg2, arg3);
				System.out.println("surfaceChanged");
				Camera.Parameters parameters = mCamera.getParameters();    
		        //parameters.setPreviewSize(width, height);//设置尺寸    
		        parameters.setPictureFormat(PixelFormat.JPEG);  
		        mCamera.setParameters(parameters);    
		        mCamera.startPreview();//开始预览  
			}

			@Override
			public void surfaceCreated(SurfaceHolder holder) {
				super.surfaceCreated(holder);
				System.out.println("surfaceCreated");
				mCamera = Camera.open();
				mCamera.setDisplayOrientation(90);
				try {
					mCamera.setPreviewDisplay(holder);
				} catch (IOException e) {
					e.printStackTrace();
					mCamera.release();
					mCamera = null;
				}
				/*mCamera.autoFocus(new ListenerAdapter(){
					@Override
					public void onAutoFocus(boolean state, Camera camera) {
						if(state){
							mCamera.setOneShotPreviewCallback(previewCallback);
						}
					}
				});*/
			}

			@Override
			public void surfaceDestroyed(SurfaceHolder holder) {
				super.surfaceDestroyed(holder);
				System.out.println("surfaceDestroyed");
				mCamera.setPreviewCallback(null);
				mCamera.stopPreview();
				mCamera = null;
			}
		});
		
		this.scanTimer = new Timer();
		this.scanTimer.schedule(new TimerTask(){
			@Override
			public void run() {
				if(mCamera!=null){
					mCamera.autoFocus(new ListenerAdapter(){
						@Override
						public void onAutoFocus(boolean state, Camera camera) {
							if(state){
								mCamera.setOneShotPreviewCallback(new Camera.PreviewCallback() {
									
									@Override
									public void onPreviewFrame(byte[] arg0, Camera camera) {
										System.out.println("获取二维码图片");
									}
								});
							}
						}
					});
				}
			}
			
		}, 0, 80);
	}

	@Override
	protected int getLayout() {
		return R.layout.qr_scan;
	}

	@Override
	protected String getDomain() {
		return null;
	}

}
