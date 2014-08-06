package com.hellofriends;

import activity.InitActivity;

public class MainActivity extends InitActivity {

	@Override
	protected void init() {
		// TODO Auto-generated method stub
		super.init();
		
		this.to(Page1.class);
	}

}
