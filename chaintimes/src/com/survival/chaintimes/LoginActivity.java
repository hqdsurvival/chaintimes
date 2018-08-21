package com.survival.chaintimes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.StringUtils;
import com.cry.library.base.BaseActivity;
import com.cry.library.manager.HttpManager;
import com.cry.library.manager.HttpManager.OnHttpResponseListener;
import com.cry.library.util.JSON;
import com.google.gson.Gson;
import com.survival.application.ChainApplication;
import com.survival.model.LoginModel;
import com.survival.model.User;
import com.survival.utils.Constant;
import com.survival.utils.HttpRequest;
/**
 * 登录
 * @author Survival
 *
 */
public class LoginActivity extends BaseActivity implements OnClickListener {
	
	private final int REGISTER_RESULT = 1;
	private final int LOGIN_CODE = 2;
	
	
	private EditText edit_user;
	private EditText edit_password;
	private TextView txt_quick_login;
	private TextView tv_register;
	private TextView tv_remaber;
	
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        
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
		edit_user = (EditText) findViewById(R.id.edit_user);
		edit_password = (EditText) findViewById(R.id.edit_password);
		txt_quick_login = (TextView) findViewById(R.id.txt_quick_login);
		tv_register = (TextView) findViewById(R.id.tv_register);
		tv_remaber = (TextView) findViewById(R.id.tv_remaber);
	}

	@Override
	public void initData() {
		// TODO Auto-generated method stub
	}

	@Override
	public void initEvent() {
		// TODO Auto-generated method stub
		txt_quick_login.setOnClickListener(this);
		tv_register.setOnClickListener(this);
		tv_remaber.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.txt_quick_login:
			final String username = edit_user.getText().toString();
			final String password = edit_password.getText().toString();
			if(StringUtils.isEmpty(username)){
				showShortToast(R.string.empty_username);
				return;
			}
			if(StringUtils.isEmpty(password)){
				showShortToast(R.string.empty_password);
				return;
			}
			showProgressDialog(R.string.dialog_progress);
			HttpRequest.Login(username, password, LOGIN_CODE, new OnHttpResponseListener() {
				
				@Override
				public void onHttpResponse(int requestCode, String resultJson, Exception e) {
					// TODO Auto-generated method stub
					dismissProgressDialog();
					if(LOGIN_CODE != requestCode){
						showShortToast(R.string.request_error);
						return;
					}
					if(!JSON.isJsonCorrect(resultJson)){
						showShortToast(R.string.response_json_error);
						return;
					}
					Gson gson = new Gson();
					LoginModel loginModel = gson.fromJson(resultJson, LoginModel.class);
					if(loginModel ==null){
						showShortToast(R.string.login_error);
						return;
					}
					if(loginModel.getCode().equals("200")){
						ChainApplication application = (ChainApplication) getApplicationContext();
						User user = new User();
						user.setUser_Name(username);
						user.setPasssWord(password);
						application.saveUserInfo(user);
						
						
						
						HttpManager.getInstance().saveToken(HttpManager.KEY_TOKEN, Constant.Config.token_tag+ " " +loginModel.getToken());
						startActivity(new Intent(getActivity(),MainActivity.class));
						finish();
					}else{
						showShortToast(R.string.login_error);
						return;
					}
				}
			});
			break;
		case R.id.tv_register:
			startActivity(new Intent(getActivity(), RegisterMobileActivity.class));
			break;
		case R.id.tv_remaber:
			startActivity(new Intent(getActivity(),RememberMobileActivity.class));
			break;
		}
	}

}
