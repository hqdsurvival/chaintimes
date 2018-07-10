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

import java.io.File;


/**常量工具类
 * @author Survival
 * @warn TODO 修改里面所有常量
 */
public class Constant {
	
	public static class Config{
		//token tag
		public static final String token_tag = "Bearer";
	}
	
	//handler请求成功
	public static final int HANDLER_WHAT_SUCCESS = 1;
	//回调结果1，表示成功
	public static final int RESULTCODESUCCESS = 1;
	//回调结果0，表示失败
	public static final int RESULTCODEERROR = 0;

	//是否是测试版本
	public static final boolean ISDEBUG = false;
	//数据服务器地址【网关】    http://172.16.30.122:15555/    119.23.134.105  s1.natapp.cc   http://cairuyi.com.cn:15554
	public static final String URL= ISDEBUG ?  "http://172.16.32.8:8080/chaintimes/" : "http://172.16.32.8:8080/chaintimes/";
	
	
	//=========================================接口地址==================================================
	//注册
	public static final String URL_REGISTER = URL +"linking/register";
	//登录
	public static final String URL_LOGIN = URL +"linking/login";
	//更新用户信息
	public static final String URL_MODIFY= URL +"linking/user";
	//获取用户信息
	public static final String URL_GETUSERINFO= URL +"user/bitun";
	//用户是否存在
	public static final String URL_EXISTUSERNAME= URL +"linking/username";
		
	//=========================================接口地址==================================================
	
	
	
	
	
}

