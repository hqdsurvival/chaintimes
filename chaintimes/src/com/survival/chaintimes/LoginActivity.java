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
import com.survival.model.LoginModel;
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
		edit_user = findViewById(R.id.edit_user);
		edit_password = findViewById(R.id.edit_password);
		txt_quick_login = findViewById(R.id.txt_quick_login);
		tv_register = findViewById(R.id.tv_register);
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
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.txt_quick_login:
			String username = edit_user.getText().toString();
			String password = edit_password.getText().toString();
			if(StringUtils.isEmpty(username)){
				showShortToast(R.string.empty_username);
				return;
			}
			if(StringUtils.isEmpty(password)){
				showShortToast(R.string.empty_password);
				return;
			}
			HttpRequest.Login(username, password, LOGIN_CODE, new OnHttpResponseListener() {
				
				@Override
				public void onHttpResponse(int requestCode, String resultJson, Exception e) {
					// TODO Auto-generated method stub
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
						HttpManager.getInstance().saveToken(Constant.Config.token_tag, loginModel.getToken());
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
			startActivityForResult(new Intent(getActivity(), RegisterActivity.class), REGISTER_RESULT);
			break;
		}
	}

}
