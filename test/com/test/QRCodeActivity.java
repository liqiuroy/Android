package com.test;


import utils.QRCodeUtils;
import activity.PActivity;
import android.graphics.Bitmap;
import android.hardware.Camera;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.hellofriends.R;

public class QRCodeActivity extends PActivity {
	private Button btn_create;
	private Button btn_scan;
	private EditText telphone;
	private ImageView img_qrcode;
	@Override
	protected void init() throws Exception {
		this.btn_create.setOnClickListener(this);
		this.btn_scan.setOnClickListener(this);
	}

	@Override
	protected void click(View view) throws Exception {
		int id = view.getId();
		if(id==this.btn_create.getId()){
			createQR();
		}
		else if(id==this.btn_scan.getId()){
			this.to(QRScanActivity.class);
		}
		else if(id==this.leftbutton.getId()){
			this.finish();
		}
	}

	private void createQR() {
		String phonenum = this.telphone.getText().toString();
		Bitmap bitmap = QRCodeUtils.createQRcode(phonenum, 250, 250);
		this.img_qrcode.setImageBitmap(bitmap);
	}

	@Override
	protected int getLayout() {
		return R.layout.qrcode;
	}

	@Override
	protected String getDomain() {
		return null;
	}

}
