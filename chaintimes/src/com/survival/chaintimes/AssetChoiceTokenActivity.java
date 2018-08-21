package com.survival.chaintimes;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.cry.library.base.BaseActivity;
import com.cry.library.manager.HttpManager.OnHttpResponseListener;
import com.cry.library.util.JSON;
import com.survival.fragment.AssetChoiceTokenFragment;
import com.survival.model.JsonResult;
import com.survival.model.Wallet;
import com.survival.utils.Constant;
import com.survival.utils.HttpRequest;

/**
 * 资产--选择token
 * @author Survival
 *
 */
public class AssetChoiceTokenActivity extends BaseActivity implements OnClickListener {
	
	private TextView asset_add_token;
	private ImageView img_left;
	private ImageView img_search;
	
	private ArrayList<Wallet> wallets;
	
	private static final int result_code = 1;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asset_choice_token);
        
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
		asset_add_token = (TextView) findViewById(R.id.tv_title);
		img_left = (ImageView) findViewById(R.id.img_left);
		img_search = (ImageView) findViewById(R.id.img_seach);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void initData() {
		// TODO Auto-generated method stub
		asset_add_token.setText(R.string.asset_add_token);
		wallets =  (ArrayList<Wallet>) getIntent().getSerializableExtra("wallet");
		
		AssetChoiceTokenFragment fragment = AssetChoiceTokenFragment.createInstance(AssetChoiceTokenFragment.RANGE_ALL, "",wallets);
		fragmentManager
		.beginTransaction()
		.add(R.id.fl_token, fragment)
		.show(fragment)
		.commit();
	}

	@Override
	public void initEvent() {
		// TODO Auto-generated method stub
		img_left.setOnClickListener(this);
		img_search.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.img_left:
			setResult(Constant.RESULTCODESUCCESS);
			finish();
			break;
		case R.id.img_seach:
			
			break;

		default:
			break;
		}
	}


}
