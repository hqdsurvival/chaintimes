package com.survival.chaintimes;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.StringUtils;
import com.cry.library.base.BaseActivity;
import com.cry.library.manager.HttpManager.OnHttpResponseListener;
import com.cry.library.util.JSON;
import com.cry.library.util.StringUtil;
import com.survival.model.JsonResult;
import com.survival.utils.Constant;
import com.survival.utils.HttpRequest;

/**
 * 注册--设置密码
 * @author Survival
 *
 */
@SuppressLint("HandlerLeak") 
public class PassWordActivity extends BaseActivity implements OnClickListener {
	
	private EditText et_password;
	private EditText et_db_password;
	private TextView tv_submit;
	
	private static final String  EXTRA_PHONE = "mobilephone";
	private static final String EXTRA_EMAIL = "email";
	private String mobilephone = "";
	private String emails = "";
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_password);
        
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
		et_password = (EditText) findViewById(R.id.et_password);
		et_db_password = (EditText) findViewById(R.id.et_db_password);
		tv_submit = (TextView) findViewById(R.id.tv_submit);
	}

	@Override
	public void initData() {
		// TODO Auto-generated method stub
		String phone = getIntent().getStringExtra(EXTRA_PHONE);
		String email  = getIntent().getStringExtra(EXTRA_EMAIL);
		if(StringUtils.isEmpty(phone) && StringUtils.isEmpty(email)){
			finish();
		}
		mobilephone = phone;
		emails = email;

	}

	@Override
	public void initEvent() {
		// TODO Auto-generated method stub
		tv_submit.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.tv_submit:
			if(StringUtils.isEmpty(mobilephone) && StringUtils.isEmpty(emails)){
				showShortToast(getResources().getString(R.string.register_mobile_email_empty));
				finish();
			}
			String password = et_password.getText().toString();
			String dbpassword = et_db_password.getText().toString();
			
			if(StringUtils.isEmpty(password) || StringUtils.isEmpty(dbpassword)){
				showShortToast(getResources().getString(R.string.register_password_empty));
				return;
			}
			if(!password.equals(dbpassword)){
				showShortToast(getResources().getString(R.string.register_password_equals));
				return;
			}
			showProgressDialog(getResources().getString(R.string.dialog_progress));
			HttpRequest.Register(StringUtils.isEmpty(mobilephone) ?  emails : mobilephone, dbpassword, "", 1, new OnHttpResponseListener() {
				
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
					showShortToast(getResources().getString(R.string.register_sueecss));
					Intent intent = new Intent(getActivity(),LoginActivity.class);
					startActivity(intent);
					finish();
				}
			});
			break;
		default:
			break;
		}
	}


}
