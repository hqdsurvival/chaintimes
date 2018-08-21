/*Copyright ©2015 TommyLemon(https://github.com/TommyLemon)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.*/

package com.survival.utils;

import java.util.ArrayList;
import java.util.List;


import com.cry.library.manager.HttpManager;
import com.cry.library.manager.HttpManager.OnHttpResponseListener;
import com.cry.library.model.Parameter;
import com.cry.library.util.StringUtil;

/**HTTP请求工具类
 * @author Survival
 * @use 添加请求方法xxxMethod >> HttpRequest.xxxMethod(...)
 * @must 所有请求的url、请求方法(GET, POST等)、请求参数(key-value方式，必要key一定要加，没提供的key不要加，value要符合指定范围)
 *       都要符合后端给的接口文档
 */
public class HttpRequest {
	//	private static final String TAG = "HttpRequest";

	
	public static final int USER_LIST_RANGE_ALL = 0;
	public static final int USER_LIST_RANGE_RECOMMEND = 1;
	/**添加请求参数，value为空时不添加
	 * @param list
	 * @param key
	 * @param value
	 */
	public static void addExistParameter(List<Parameter> list, String key, Object value) {
		if (list == null) {
			list = new ArrayList<Parameter>();
		}
		if (StringUtil.isNotEmpty(key, true) && StringUtil.isNotEmpty(value, true) ) {
			list.add(new Parameter(key, value));
		}
	}



	//示例代码<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<


	//user<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	public static final String RANGE = "range";

	public static final String USERNAME = "user_name";
	public static final String PASSWORD = "user_password";
	public static final String HEADIMAGE = "head_image";
	public static final String NAME = "name";
	public static final String MOBILEPHONE = "mobilephone";
	public static final String MOTHOD = "mothod";
	public static final String EMAIL = "email";
	public static final String PAGE = "page";
	public static final String USERCODE = "user_code";
	public static final String COINNAME = "coin_name";
	public static final String CONCERNSTATE = "concern_state";
	
	
	
	public static final String SEX = "sex";
	public static final int SEX_MAIL = 1;
	public static final int SEX_FEMAIL = 2;
	public static final int SEX_ALL = 3;
	
	
	/**
	 * 发送短信验证码
	 * @param mobilephone   电话号码
	 * @param mothod    方法
	 * @param requestCode
	 * @param listener
	 */
	public static void SendSms(final String mobilephone,final String mothod,
			final int requestCode, final OnHttpResponseListener listener){
		List<Parameter> paramList = new ArrayList<Parameter>();
		addExistParameter(paramList, MOBILEPHONE, mobilephone);
		addExistParameter(paramList, MOTHOD, mothod);
		

		HttpManager.getInstance().post(paramList, Constant.URL_SENDSMS, requestCode, listener);
	}
	/**
	 * 发送邮箱验证码
	 * @param mobilephone   电话号码
	 * @param mothod    方法
	 * @param requestCode
	 * @param listener
	 */
	public static void SendEmail(final String email,
			final int requestCode, final OnHttpResponseListener listener){
		List<Parameter> paramList = new ArrayList<Parameter>();
		addExistParameter(paramList, EMAIL, email);
		
		HttpManager.getInstance().post(paramList, Constant.URL_SENDEMAIL, requestCode, listener);
	}

	/**
	 *  注册
	 * @param username
	 * @param password
	 * @param head_image
	 */
	public static void Register(final String username,final String password,final String head_image,
			final int requestCode, final OnHttpResponseListener listener){
		List<Parameter> paramList = new ArrayList<Parameter>();
		addExistParameter(paramList, USERNAME, username);
		addExistParameter(paramList, PASSWORD, password);
		addExistParameter(paramList, HEADIMAGE, head_image);

		HttpManager.getInstance().post(paramList, Constant.URL_REGISTER, requestCode, listener);
	}
	
	/**
	 *  登录
	 * @param username
	 * @param password
	 */
	public static void Login(final String username,final String password,
			final int requestCode, final OnHttpResponseListener listener){
		List<Parameter> paramList = new ArrayList<Parameter>();
		addExistParameter(paramList, USERNAME, username);
		addExistParameter(paramList, PASSWORD, password);

		HttpManager.getInstance().post(paramList, Constant.URL_LOGIN, requestCode, listener);
	}
	/**
	 *  判断用户账号是否存在
	 * @param username
	 */
	public static void ExistUserName(final String username,
			final int requestCode, final OnHttpResponseListener listener){
		List<Parameter> paramList = new ArrayList<Parameter>();
		addExistParameter(paramList, USERNAME, username);

		HttpManager.getInstance().post(paramList, Constant.URL_EXISTUSERNAME, requestCode, listener);
	}
	
	/**
	 *  获取用户信息
	 * @param username
	 */
	public static void UserInfo(final String username,
			final int requestCode, final OnHttpResponseListener listener){
		List<Parameter> paramList = new ArrayList<Parameter>();
		addExistParameter(paramList, USERNAME, username);

		HttpManager.getInstance().post(paramList, Constant.URL_GETUSERINFO, requestCode, listener);
	}
	/**
	 *  获取所有货币信息
	 * @param username
	 */
	public static void Coin(final int page,
			final int requestCode, final OnHttpResponseListener listener){
		List<Parameter> paramList = new ArrayList<Parameter>();
		addExistParameter(paramList, PAGE, page);
		
		HttpManager.getInstance().get(paramList, Constant.URL_COIN, requestCode, listener);
	}
	/**
	 *  获取所有货币信息
	 * @param username
	 */
	public static void Wallet(final String user_code,
			final int requestCode, final OnHttpResponseListener listener){
		List<Parameter> paramList = new ArrayList<Parameter>();
		addExistParameter(paramList, USERCODE, user_code);
		
		HttpManager.getInstance().get(paramList, Constant.URL_WALLET, requestCode, listener);
	}
	/**
	 *  更新关注状态
	 * @param usercode  用户code
	 * @param coin_name coin名称  
	 * @param concern_state  关注状态 1 关注 2 不关注
	 */
	public static void walletUpdate(final String user_code,final String coin_name,final int concern_state,
			final int requestCode, final OnHttpResponseListener listener){
		List<Parameter> paramList = new ArrayList<Parameter>();
		addExistParameter(paramList, USERCODE, user_code);
		addExistParameter(paramList, COINNAME, coin_name);
		addExistParameter(paramList, CONCERNSTATE, concern_state);
		
		HttpManager.getInstance().post(paramList, Constant.URL_WALLET_UPDATE, requestCode, listener);
	}
	
	
	//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	

}