package com.survival.fragment;

import java.util.ArrayList;
import java.util.List;

import com.cry.library.base.BaseHttpListFragment;
import com.cry.library.interfaces.AdapterCallBack;
import com.cry.library.interfaces.CacheCallBack;
import com.cry.library.manager.HttpManager.OnHttpResponseListener;
import com.cry.library.util.JSON;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.survival.adapter.AssetChoiceTokenAdapter;
import com.survival.application.ChainApplication;
import com.survival.chaintimes.R;
import com.survival.model.Coin;
import com.survival.model.JsonResult;
import com.survival.model.User;
import com.survival.model.Wallet;
import com.survival.utils.Constant;
import com.survival.utils.HttpRequest;
import com.survival.view.AssetChoiceTokenView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.AdapterView.OnItemClickListener;

/**用户列表界面fragment
 * @author Survival
 * @use new UserListFragment(),详细使用见.DemoFragmentActivity(initData方法内)
 * @must 查看 .HttpManager 中的@must和@warn
 *       查看 .SettingUtil 中的@must和@warn
 */
public class AssetChoiceTokenFragment extends BaseHttpListFragment<Coin, AssetChoiceTokenAdapter> implements CacheCallBack<Coin>, OnClickListener {
//	private static final String TAG = "UserListFragment";
	
	//与Activity通信<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	public static final String ARGUMENT_RANGE = "ARGUMENT_RANGE";
	public static final int RANGE_ALL = HttpRequest.USER_LIST_RANGE_ALL;
	public ArrayList<Wallet> wallets;
	public ChainApplication chainApplication;
	public User user;
	
	private String SEARCH;
	private int range = RANGE_ALL;
	public static AssetChoiceTokenFragment createInstance(int range,String search,ArrayList<Wallet> arrayList) {
		
		AssetChoiceTokenFragment fragment = new AssetChoiceTokenFragment();
		
		Bundle bundle = new Bundle();
		bundle.putInt(ARGUMENT_RANGE, range);
		bundle.putString("search", search);
		bundle.putSerializable("wallet", arrayList);
		fragment.setArguments(bundle);
		return fragment;
	}

	//与Activity通信>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	protected static final String TAG = "SiteChoice";

	@SuppressWarnings("unchecked")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);

		argument = getArguments();
		if (argument != null) {
			range = argument.getInt(ARGUMENT_RANGE, range);
			SEARCH = argument.getString("search");
			wallets = (ArrayList<Wallet>) argument.getSerializable("wallet");
		}

		//功能归类分区方法，必须调用<<<<<<<<<<
		initView();
		initData();
		initEvent();
		//功能归类分区方法，必须调用>>>>>>>>>>

		lvBaseList.onRefresh();
		return view;
	}


	//UI显示区(操作UI，但不存在数据获取或处理代码，也不存在事件监听代码)<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	@Override
	public void initView() {//必须调用
		super.initView();
		
	}

	@Override
	public void setList(final List<Coin> list) {
		setList(new AdapterCallBack<AssetChoiceTokenAdapter>() {

			@Override
			public AssetChoiceTokenAdapter createAdapter() {
				return new AssetChoiceTokenAdapter(context);
			}

			@Override
			public void refreshAdapter() {
				adapter.refresh(list);
			}
		});
	}

	@Override
	public void initData() {//必须调用
		super.initData();
		chainApplication = (ChainApplication) getActivity().getApplicationContext();
		user = chainApplication.getUserInfo();
	}

	@Override
	public void getListAsync(final int page) {
		//加载数据>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		GetCoinAll(page);
	}

	@Override
	public List<Coin> parseArray(String json) {
		return JSON.parseArray(json, Coin.class);
	}

	@Override
	public Class<Coin> getCacheClass() {
		return Coin.class;
	}
	@Override
	public String getCacheGroup() {
		return "range=" + range;
	}
	@Override
	public String getCacheId(Coin data) {
		return data == null ? null : "" + data.getCoin_code();
	}
	@Override
	public int getCacheCount() {
		return 10;
	}

	@Override
	public void initEvent() {//必须调用
		super.initEvent();

		lvBaseList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
				final Coin coin = (Coin) lvBaseList.getItemAtPosition(position);
				if(coin !=null){
					if(coin.isCoin_choice()){
						coin.setCoin_choice(false);
					}else{
						coin.setCoin_choice(true);
					}
					showProgressDialog(R.string.dialog_progress);
					HttpRequest.walletUpdate(user.getUser_code(), coin.getCoin_name(), coin.isCoin_choice() ? 1 : 2, 1, new OnHttpResponseListener() {
						
						@Override
						public void onHttpResponse(int requestCode, String resultJson, Exception e) {
							// TODO Auto-generated method stub
							dismissProgressDialog();
							
							if(requestCode !=1){
								showShortToast(getResources().getString(R.string.result_error));
								return;
							}
							if(!JSON.isJsonCorrect(resultJson)){
								showShortToast(getResources().getString(R.string.result_error));
								return;
							}
							JsonResult result = JSON.parseObject(resultJson, JsonResult.class);
							if(result ==null){
								showShortToast(getResources().getString(R.string.result_fail));
								return;
							}
							if(result.getCode() != Constant.Config.RESULT_SUCCESS){
								showShortToast(getResources().getString(R.string.result_fail));
								return;
							}
							
							if(view !=null){
								ImageView img_choice = (ImageView) view.findViewById(R.id.img_choice);
								if(img_choice !=null){
									if(coin.isCoin_choice()){
										img_choice.setImageResource(R.drawable.check_yes);
									}else{
										img_choice.setImageResource(R.drawable.check_no);
									}
								}
							}
						}
					});
					
				}
				
			}
		});
	}
	
	
	/**
	 * 获取所有的token
	 */
	public void GetCoinAll(final int page){
		
		HttpRequest.Coin(page,1, new OnHttpResponseListener() {
			
			@Override
			public void onHttpResponse(int requestCode, String resultJson, Exception e) {
				// TODO Auto-generated method stub
				
				ArrayList<Coin> array = new ArrayList<Coin>();
				if(!JSON.isJsonCorrect(resultJson)){
					showShortToast(getResources().getString(R.string.result_error));
					onLoadFailed(1, e);
					return;
				}
				JsonResult result = JSON.parseObject(resultJson, JsonResult.class);
				if(result ==null){
					showShortToast(getResources().getString(R.string.result_fail));
					onLoadFailed(1, e);
					return;
				}
				if(result.getCode() != Constant.Config.RESULT_SUCCESS){
					showShortToast(getResources().getString(R.string.result_fail));
					onLoadFailed(1, e);
					return;
				}
				Gson gson = new Gson();
				array = gson.fromJson(result.getData(),new TypeToken<ArrayList<Coin>>(){}.getType());
				//=====================判断是否有关注的币种===============================
				if(wallets !=null){
					for (Wallet wallet : wallets) {
						for (Coin coin : array) {
							if(wallet.getCoin_name().equals(coin.getCoin_name()) && wallet.getConcern_state().equals("1")){
								coin.setCoin_choice(true);
							}
						}
					}
				}
				//=====================判断是否有关注的币种===============================
				if (array == null ||e != null) {
					//fragment_isexistdata_callback.IsExistData(false);
					onLoadFailed(page, e);
				} else {
					//fragment_isexistdata_callback.IsExistData(array.size() >0 ? true : false);
					onLoadSucceed(page, array);
				}
			}
		});
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
//		case R.id.tv_button_search:
//			//lvBaseList.onRefresh();
//			break;
//		default:
//			break;
		}
	}
}