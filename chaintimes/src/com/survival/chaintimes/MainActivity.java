package com.survival.chaintimes;

import java.util.List;

import com.cry.library.base.BaseActivity;
import com.cry.library.manager.HttpManager.OnHttpResponseListener;
import com.cry.library.util.JSON;
import com.cry.library.util.Log;
import com.survival.application.ChainApplication;
import com.survival.fragment.AssetChoiceTokenFragment;
import com.survival.fragment.AssetFragment;
import com.survival.fragment.InvestFragment;
import com.survival.fragment.MarketFragment;
import com.survival.fragment.MyFragment;
import com.survival.model.JsonResult;
import com.survival.model.User;
import com.survival.utils.Constant;
import com.survival.utils.HttpRequest;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RadioButton;

/**
 * 主界面
 * @author Survival
 *
 */
public class MainActivity extends BaseActivity implements OnClickListener{

	public static final String KEY_TITLE = "title";
	public static final String KEY_MESSAGE = "message";
	public static final String KEY_EXTRAS = "extras";
	public static boolean isForeground = false;
	
	private RadioButton rb_tab_market;    //投资
	private RadioButton rb_tab_asset;     //资产
	private RadioButton rb_tab_invest;     //理财
	private RadioButton rb_tab_my;     //我的
	
	private static final int WEIGHT = 45;
	private static final int HEIGHT = 45;
	
	private ChainApplication chainApplication;
	
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        initView();
        initEvent();
        initData();
    }
    @Override
    public void initView() {
    	// TODO Auto-generated method stub
    	rb_tab_market = (RadioButton) findViewById(R.id.main_tab_market);
    	rb_tab_asset = (RadioButton) findViewById(R.id.main_tab_asset);
    	rb_tab_invest = (RadioButton) findViewById(R.id.main_tab_invest);
    	rb_tab_my = (RadioButton) findViewById(R.id.main_tab_my);
    }
    @Override
	public void initData() {
		// TODO Auto-generated method stub
    	chainApplication = (ChainApplication) getApplicationContext();

  		rb_tab_asset.setChecked(true);
  		AssetFragment assetFragment = new AssetFragment();
		setFragmentReplace(R.id.fl_framelayout_content, assetFragment);

		defaultRadioBackground();
		
		showProgressDialog(R.string.dialog_progress);
		HttpRequest.UserInfo(chainApplication.getUserInfo().getUser_Name(), 1, new OnHttpResponseListener() {
			
			@Override
			public void onHttpResponse(int requestCode, String resultJson, Exception e) {
				// TODO Auto-generated method stub
				dismissProgressDialog();
				if(e !=null){
					showShortToast(getResources().getString(R.string.result_fail));
					return;
				}

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
				if(result.getCode() == Constant.Config.RESULT_TOKEN_ERROR){
					chainApplication.saveUserInfo(new User());
					startActivity(new Intent(getActivity(), LoginActivity.class));
					finish();
					return;
				}
				if(result.getCode() != Constant.Config.RESULT_SUCCESS){
					showShortToast(getResources().getString(R.string.result_fail));
					return;
				}
				User user = JSON.parseObject(result.getData(), User.class);
				if(user !=null){
					chainApplication.saveUserInfo(user);
				}
			}
		});
	}
    public void defaultRadioBackground(){
    	//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		//设置RadioButton 顶部的背景图片的大小
		//定义底部标签图片大小 
	    
    	//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>行情>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	    if(rb_tab_market.isChecked()){
	    	Drawable drawhome = getResources().getDrawable(R.drawable.main_tab_market_check); 
	 	    drawhome.setBounds(0, 0, WEIGHT, HEIGHT);//第一0是距左右边距离，第二0是距上下边距离，第三69长度,第四宽度 
	 	    rb_tab_market.setCompoundDrawables(null, drawhome, null, null);  //只放上面
	    }else{
	    	 Drawable drawhome = getResources().getDrawable(R.drawable.main_tab_market); 
	 	     drawhome.setBounds(0, 0, WEIGHT, HEIGHT);//第一0是距左右边距离，第二0是距上下边距离，第三69长度,第四宽度 
	 	     rb_tab_market.setCompoundDrawables(null, drawhome, null, null);  //只放上面
	    }
    	//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>行情>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    	//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>资产产品>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	    if(rb_tab_asset.isChecked()){
	    	Drawable drawcheck = getResources().getDrawable(R.drawable.main_tab_asset_check_v2); 
		    drawcheck.setBounds(0, 0, WEIGHT, HEIGHT);//第一0是距左右边距离，第二0是距上下边距离，第三69长度,第四宽度 
		    rb_tab_asset.setCompoundDrawables(null, drawcheck, null, null);  //只放上面
	    }else{
	    	Drawable drawcheck = getResources().getDrawable(R.drawable.main_tab_asset_v2); 
		    drawcheck.setBounds(0, 0, WEIGHT, HEIGHT);//第一0是距左右边距离，第二0是距上下边距离，第三69长度,第四宽度 
		    rb_tab_asset.setCompoundDrawables(null, drawcheck, null, null);  //只放上面
	    }
    	//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>资产产品>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    	//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>理财管理>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	    if(rb_tab_invest.isChecked()){
	    	Drawable drawcheck = getResources().getDrawable(R.drawable.main_tab_invest_check); 
		    drawcheck.setBounds(0, 0, WEIGHT, HEIGHT);//第一0是距左右边距离，第二0是距上下边距离，第三69长度,第四宽度 
		    rb_tab_invest.setCompoundDrawables(null, drawcheck, null, null);  //只放上面
	    }else{
	    	Drawable drawcheck = getResources().getDrawable(R.drawable.main_tab_invest); 
		    drawcheck.setBounds(0, 0, WEIGHT, HEIGHT);//第一0是距左右边距离，第二0是距上下边距离，第三69长度,第四宽度 
		    rb_tab_invest.setCompoundDrawables(null, drawcheck, null, null);  //只放上面
	    }
    	//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>理财管理>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    	//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>我的>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	    if(rb_tab_my.isChecked()){
	    	Drawable drawcheck = getResources().getDrawable(R.drawable.main_tab_my_check); 
		    drawcheck.setBounds(0, 0, WEIGHT, HEIGHT);//第一0是距左右边距离，第二0是距上下边距离，第三69长度,第四宽度 
		    rb_tab_my.setCompoundDrawables(null, drawcheck, null, null);  //只放上面
	    }else{
	    	Drawable drawcheck = getResources().getDrawable(R.drawable.main_tab_my); 
		    drawcheck.setBounds(0, 0, WEIGHT, HEIGHT);//第一0是距左右边距离，第二0是距上下边距离，第三69长度,第四宽度 
		    rb_tab_my.setCompoundDrawables(null, drawcheck, null, null);  //只放上面
	    }
    	//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>我的>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	    //设置RadioButton 顶部的背景图片的大小
	    //<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
	    
    }

	@Override
	public void initEvent() {
		// TODO Auto-generated method stub
		rb_tab_market.setOnClickListener(this);
		rb_tab_asset.setOnClickListener(this);
		rb_tab_invest.setOnClickListener(this);
		rb_tab_my.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.main_tab_market:
			MarketFragment marketFragment = new MarketFragment();
			setFragmentReplace(R.id.fl_framelayout_content, marketFragment);
			break;
		case R.id.main_tab_asset:
			AssetFragment assetFragment = new AssetFragment();
			setFragmentReplace(R.id.fl_framelayout_content, assetFragment);
			break;
		case R.id.main_tab_invest:
			InvestFragment investFragment = new InvestFragment();
			setFragmentReplace(R.id.fl_framelayout_content, investFragment);
			break;
		case R.id.main_tab_my:
			MyFragment myFragment = new MyFragment();
			setFragmentReplace(R.id.fl_framelayout_content, myFragment);
			break;
		}
		defaultRadioBackground();
	}
	@Override
	public void onActivityResult(int requestCode,int resultCode,Intent data){
		super.onActivityResult(requestCode, resultCode, data);
		
		Log.d(TAG,Integer.toString(resultCode));
		FragmentManager fm = getSupportFragmentManager();  
        int index = requestCode >> 16;  
        if (index != 0) {  
            index--;  
            if (fm.getFragments() == null || index < 0  
                    || index >= fm.getFragments().size()) {  
                Log.w(TAG, "Activity result fragment index out of range: 0x"  
                        + Integer.toHexString(requestCode));  
                return;  
            }  
            Fragment frag = fm.getFragments().get(index);  
            if (frag == null) {  
                Log.w(TAG, "Activity result no fragment exists for index: 0x"  
                        + Integer.toHexString(requestCode));  
            } else {  
                handleResult(frag, requestCode, resultCode, data);  
            }  
            return;  
        }  
  
		
	}
	/** 
     * 递归调用，对所有子Fragement生效 
     *  
     * @param frag 
     * @param requestCode 
     * @param resultCode 
     * @param data 
     */  
    private void handleResult(Fragment frag, int requestCode, int resultCode,  
            Intent data) {  
        frag.onActivityResult(requestCode & 0xffff, resultCode, data);  
        List<Fragment> frags = frag.getChildFragmentManager().getFragments();  
        if (frags != null) {  
            for (Fragment f : frags) {  
                if (f != null)  
                    handleResult(f, requestCode, resultCode, data);  
            }  
        }  
    }  

	@Override
	public Activity getActivity() {
		// TODO Auto-generated method stub
		return this;
	}
	//记录用户首次点击返回键的时间
    private long firstTime=0;
	protected String TAG = "MainActivity";
	@Override
	public boolean onKeyDown(int keyCode,KeyEvent event){
		if(keyCode==KeyEvent.KEYCODE_BACK && event.getAction()==KeyEvent.ACTION_DOWN){
            if (System.currentTimeMillis()-firstTime>2000){
                showShortToast(R.string.quit_app);
                firstTime=System.currentTimeMillis();
            }else{
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
	}
	
}
