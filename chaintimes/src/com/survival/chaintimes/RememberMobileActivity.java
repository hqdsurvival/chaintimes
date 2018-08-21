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
 * 忘记密码--手机验证码
 * @author Survival
 *
 */
@SuppressLint("HandlerLeak") 
public class RememberMobileActivity extends BaseActivity implements OnClickListener {
	
	private EditText et_code;
	private EditText tv_phone;
	private TextView tv_next;
	private TextView tv_send;
	
	private final int hanlder_over = 1;
	private final int hanlder_continue = 2;
	//短信验证码
	private String sms_code = "";
	private int timelen = 120;
	
	public  Handler myHandler = new Handler() {
		public void handleMessage(Message msg){
			int time = (int) msg.obj;
			switch (msg.what) {
			case hanlder_over:
				tv_send.setText(getResources().getString(R.string.register_sendsms));
				if(!tv_send.isEnabled()){
					tv_send.setEnabled(true);
				}
				timelen  =120;
				break;
			case hanlder_continue:
				if(tv_send.isEnabled()){
					tv_send.setEnabled(false);
				}
				time = time-1;
				tv_send.setText(Integer.toString(time));
				Message message = new Message();
				message.what = time >0? hanlder_continue : hanlder_over;
				message.obj = time;
				myHandler.sendMessageDelayed(message, 1000);
				break;

			default:
				break;
			}
		}
	};
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remember_mobile);
        
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
		tv_phone = (EditText) findViewById(R.id.tv_phone);
		tv_next = (TextView) findViewById(R.id.tv_next);
		tv_send = (TextView) findViewById(R.id.tv_send);
		et_code = (EditText) findViewById(R.id.et_smscode);
	}

	@Override
	public void initData() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initEvent() {
		// TODO Auto-generated method stub
		tv_next.setOnClickListener(this);
		tv_send.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		String phone = tv_phone.getText().toString();
		
		switch (v.getId()) {
		case R.id.tv_send:
			if(StringUtils.isEmpty(phone)){
				showShortToast(getResources().getString(R.string.register_phone_isempty));
				return;
			}
			if(!StringUtil.isPhone(phone)){
				showShortToast(getResources().getString(R.string.register_phone_issuccess));
				return;
			}
			showProgressDialog(getResources().getString(R.string.dialog_progress));
			HttpRequest.SendSms(phone, Constant.Config.SendSmsEnum.resetpass.toString(), 1,new OnHttpResponseListener() {
				
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
					sms_code = result.getMessage();
					showShortToast(getResources().getString(R.string.result_sendsms_success));
					Message message = new Message();
					message.what = hanlder_continue;
					message.obj = timelen;
					myHandler.sendMessage(message);
				}
			});
			break;
		case R.id.tv_next:
			String code = et_code.getText().toString();
			if(StringUtils.isEmpty(phone)){
				showShortToast(getResources().getString(R.string.register_phone_isempty));
				return;
			}
			if(!StringUtil.isPhone(phone)){
				showShortToast(getResources().getString(R.string.register_phone_issuccess));
				return;
			}
			if(StringUtils.isEmpty(code)){
				showShortToast(getResources().getString(R.string.register_verification_code_empty));
				return;
			}
			if(!code.equals(sms_code)){
				showShortToast(getResources().getString(R.string.register_verification_code_fail));
				return;
			}
			showShortToast(getResources().getString(R.string.register_verification_code_success));
			
			Intent intent = new Intent(getActivity(),RememberPassWordActivity.class);
			Bundle bundle = new Bundle();
			bundle.putString("mobilephone", phone);
			intent.putExtras(bundle);
			startActivity(intent);
			finish();
			break;
		
		default:
			break;
		}
	}


}
