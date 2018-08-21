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

import android.R.integer;


/**常量工具类
 * @author Survival
 * @warn TODO 修改里面所有常量
 */
public class Constant {
	
	public static class Config{
		//token tag
		public static final String token_tag = "Bearer";
		//用户信息存储
		public static final String Shared_UserInfo = "userinfo";
		public static final String User_Code_KEY = "user_code";
		public static final String User_Name_Key = "user_name";
		public static final String Head_Image_Key = "head_image"; 
		public static final String Password_Key = "password";
		public static final String User_Email_Key = "user_email";
		public static final String MobilePhone_Key ="mobilephone"; 
		public static final String AddTime_Key = "addtime";
		
		//短信类型
		public static enum SendSmsEnum{
			//登录
			login ,
			//注册
			register ,
			//重置密码
			resetpass
		}
		//成功
		public static final int RESULT_SUCCESS = 1;
		//失败
		public static final int RESULT_FAIL = 2;
		//其他异常
		public static final int RESULT_OTHER = 3;
		//token异常
		public static final int RESULT_TOKEN_ERROR = 401;
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
	public static final String URL= ISDEBUG ?  "http://35.236.135.153:15559/chaintimes/" : "http://172.16.32.26:15559/chaintimes/";
	
	
	//=========================================接口地址==================================================
	//注册
	public static final String URL_REGISTER = URL +"register";
	//登录
	public static final String URL_LOGIN = URL +"login";
	//更新用户信息
	public static final String URL_MODIFY= URL +"user";
	//获取用户信息
	public static final String URL_GETUSERINFO= URL +"userinfo";
	//用户是否存在
	public static final String URL_EXISTUSERNAME= URL +"username";
	//发送短信验证码
	public static final String URL_SENDSMS= URL +"sendsms";
	//发送邮箱验证码
	public static final String URL_SENDEMAIL= URL +"sendemail";
	//获取所有货币信息
	public static final String URL_COIN = URL + "coin";
	//获取用户资产
	public static final String URL_WALLET = URL + "wallet";
	//更新关注状态
	public static final String URL_WALLET_UPDATE = URL + "walletUpdate";
		
	//=========================================接口地址==================================================
	
	
	
	
	
}

