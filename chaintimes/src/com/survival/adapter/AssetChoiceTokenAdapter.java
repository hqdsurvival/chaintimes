package com.survival.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;

import com.cry.library.base.BaseViewAdapter;
import com.survival.model.Coin;
import com.survival.view.AssetChoiceTokenView;

public class AssetChoiceTokenAdapter extends BaseViewAdapter<Coin,AssetChoiceTokenView> {

	public AssetChoiceTokenAdapter(Activity context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view =super.getView(position, convertView, parent); 
		return view;
	}
	@Override
	public AssetChoiceTokenView createView(int position, ViewGroup parent) {
		// TODO Auto-generated method stub
		return new AssetChoiceTokenView(context, resources);
	}

}
