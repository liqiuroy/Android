package com.hellofriends;

import activity.PActivity;

public class Page1 extends PActivity {

	@Override
	protected void init() throws Exception {
		// TODO Auto-generated method stub
		this.to(Page2.class);
	}

	@Override
	protected int getLayout() {
		// TODO Auto-generated method stub
		return R.layout.page1;
	}

	@Override
	protected String getDomain() {
		// TODO Auto-generated method stub
		return null;
	}

}
