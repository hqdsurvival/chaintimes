package com.survival.application;

import com.cry.library.base.BaseApplication;

public class ChainApplication extends BaseApplication {

	public static ChainApplication context;
	
	public static ChainApplication getInstance() {
		return context;
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		context = this;
	}
}
