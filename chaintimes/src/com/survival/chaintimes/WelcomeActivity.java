package com.survival.chaintimes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ThreadUtils;
import com.cry.library.base.BaseActivity;
import com.cry.library.manager.HttpManager;
import com.survival.application.ChainApplication;
import com.survival.model.User;
import com.survival.utils.HttpRequest;

public class WelcomeActivity extends BaseActivity {
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        
        initView();
        initData();
        initEvent();
        
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
		ThreadUtils.getSinglePool().execute(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					Thread.sleep(2000);
					Intent intent =null;
					ChainApplication application = (ChainApplication) getApplicationContext();
					User user = application.getUserInfo();
					
					if(StringUtils.isEmpty(user.getUser_Name()) && StringUtils.isEmpty(HttpManager.getInstance().getToken(HttpManager.KEY_TOKEN))){
						intent = new Intent(getActivity(),LoginActivity.class);
					}else{
						intent = new Intent(getActivity(), MainActivity.class);
					}
					startActivity(intent);
					finish();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}

	@Override
	public void initEvent() {
		// TODO Auto-generated method stub

	}

}
