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

	public static final String USERNAME = "username";
	public static final String PASSWORD = "password";
	public static final String NAME = "name";
	public static final String MOBILEPHONE = "mobliephone";
	public static final String HEADIMAGE = "headimage";
	
	
	
	public static final String SEX = "sex";
	public static final int SEX_MAIL = 1;
	public static final int SEX_FEMAIL = 2;
	public static final int SEX_ALL = 3;
	
	

	/**
	 *  注册
	 * @param username
	 * @param password
	 * @param name
	 * @param mobliephone
	 */
	public static void Register(final String username,final String password,final String name,final String mobliephone,
			final int requestCode, final OnHttpResponseListener listener){
		List<Parameter> paramList = new ArrayList<Parameter>();
		addExistParameter(paramList, USERNAME, username);
		addExistParameter(paramList, PASSWORD, password);
		addExistParameter(paramList, NAME, name);
		addExistParameter(paramList, MOBILEPHONE, mobliephone);

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
	
	
	//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	

}