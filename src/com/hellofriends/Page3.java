package com.hellofriends;

import activity.PActivity;
import android.view.View;
import android.widget.RelativeLayout;

public class Page3 extends PActivity{
	private RelativeLayout xylayout;
	@Override
	protected void init() throws Exception {
		this.xylayout.setOnClickListener(this);
	}

	@Override
	protected void click(View view) throws Exception {
		if(view.getId()==R.id.xylayout){
			displayPage();
		}
	}

	private void displayPage() {
		ScaleAnimationHelper scaleHelper = new ScaleAnimationHelper(this,2);    
		scaleHelper.ScaleInAnimation(xylayout); 
		//scaleHelper.ScaleOutAnimation(xylayout);   
	}

	@Override
	protected int getLayout() {
		return R.layout.page3;
	}

	@Override
	protected String getDomain() {
		return null;
	}

}
