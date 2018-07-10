package com.survival.chaintimes;

import android.app.Activity;
import android.os.Bundle;

import com.cry.library.base.BaseActivity;

/**
 * 注册
 * @author Survival
 *
 */
public class RegisterActivity extends BaseActivity {
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        
        initView();
        initEvent();
        initData();
    }
	@Override
	public Activity getActivity() {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub

	}

	@Override
	public void initData() {
		// TODO Auto-generated method stub

	}

	@Override
	public void initEvent() {
		// TODO Auto-generated method stub

	}


}
