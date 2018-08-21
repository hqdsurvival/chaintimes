package com.survival.fragment;

import java.util.concurrent.ExecutionException;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cry.library.base.BaseFragment;
import com.survival.chaintimes.R;
/**
 * 理财
 * @author Survival
 *
 */
public class InvestFragment extends BaseFragment {
	@Override
	public View onCreateView(LayoutInflater inflater,ViewGroup group,Bundle savedInstanceState){
		super.onCreateView(inflater, group, savedInstanceState);
		setContentView(R.layout.fragment_invest);
		
		initView();
		initData();
		initEvent();
		
		return view;
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
