package com.survival.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cry.library.base.BaseFragment;
import com.survival.application.ChainApplication;
import com.survival.chaintimes.R;
import com.survival.model.User;

/**
 * 我的
 * @author Survival
 *
 */
public class MyFragment extends BaseFragment implements OnClickListener {
	
	private TextView tv_exit;
	private TextView tv_user_name;
	private ChainApplication application;
	private User user;
	
	@Override
	public View onCreateView(LayoutInflater inflater,ViewGroup group,Bundle savedInstanceState){
		super.onCreateView(inflater, group, savedInstanceState);
		setContentView(R.layout.fragment_my);
		
		initView();
		initData();
		initEvent();
		
		return view;
	}
	@Override
	public void initView() {
		// TODO Auto-generated method stub
		tv_exit = findViewById(R.id.tv_exit);
		tv_user_name = findViewById(R.id.tv_user_name);
	}

	@Override
	public void initData() {
		// TODO Auto-generated method stub
		application = (ChainApplication) getActivity().getApplicationContext();
		if(application ==null){
			return;
		}
		user = application.getUserInfo();
		tv_user_name.setText(user.getUser_Name());
	}

	@Override
	public void initEvent() {
		// TODO Auto-generated method stub
		tv_exit.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.tv_exit:
			if(application ==null){
				return;
			}
			
			application.saveUserInfo(new User());
			showShortToast(getResources().getString(R.string.quit_success));
			getActivity().finish();
			System.exit(0);
			break;

		default:
			break;
		}
	}

}
