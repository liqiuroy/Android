package com.test;

import java.io.IOException;

import activity.PActivity;
import adapter.ListenerAdapter;
import android.hardware.Camera;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.hellofriends.R;

public class QRScanActivity extends PActivity {
	private SurfaceView sfv;
	@Override
	protected void init() throws Exception {
		this.sfv.getHolder().addCallback(new ListenerAdapter(){
			@Override
			public void surfaceChanged(SurfaceHolder holder, int arg1, int arg2,
					int arg3) {
				super.surfaceChanged(holder, arg1, arg2, arg3);
				try {
					mCamera.setPreviewDisplay(holder);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			@Override
			public void surfaceCreated(SurfaceHolder holder) {
				super.surfaceCreated(holder);
				mCamera = Camera.open();
				mCamera.setDisplayOrientation(90);
				mCamera.autoFocus(new ListenerAdapter(){
					@Override
					public void onAutoFocus(boolean state, Camera camera) {
						if(state){
							
						}
					}
				});
				
			}

			@Override
			public void surfaceDestroyed(SurfaceHolder arg0) {
				super.surfaceDestroyed(arg0);
			}
		});
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
