package com.survival.application;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.cry.library.base.BaseApplication;
import com.survival.model.User;
import com.survival.utils.Constant;

public class ChainApplication extends BaseApplication {

	public static ChainApplication context;
	private SharedPreferences sharedPreferences;
	
	public static ChainApplication getInstance() {
		return context;
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		context = this;
		
	}
	/**
	 * 存储用户信息
	 * @param UserName
	 * @param PassWord
	 */
	public void saveUserInfo(User user){
		sharedPreferences = getSharedPreferences(Constant.Config.Shared_UserInfo, MODE_PRIVATE);
		Editor editor = sharedPreferences.edit();
		editor.remove(Constant.Config.User_Name_Key).putString(Constant.Config.User_Name_Key, user.getUser_Name());
		editor.remove(Constant.Config.Password_Key).putString(Constant.Config.Password_Key, user.getPasssWord());
		editor.remove(Constant.Config.User_Code_KEY).putString(Constant.Config.User_Code_KEY, user.getUser_code());
		editor.remove(Constant.Config.Head_Image_Key).putString(Constant.Config.Head_Image_Key, user.getHead_image());
		editor.remove(Constant.Config.MobilePhone_Key).putString(Constant.Config.MobilePhone_Key, user.getMobilephone());
		editor.remove(Constant.Config.AddTime_Key).putString(Constant.Config.AddTime_Key, user.getAddTime());
		editor.commit();
	}
	/**
	 * 获取用户信息
	 * @return
	 */
	public User getUserInfo(){
		User user = new User();
		sharedPreferences = getSharedPreferences(Constant.Config.Shared_UserInfo, MODE_PRIVATE);
		user.setUser_Name(sharedPreferences.getString(Constant.Config.User_Name_Key, ""));
		user.setPasssWord(sharedPreferences.getString(Constant.Config.Password_Key, ""));
		user.setUser_code(sharedPreferences.getString(Constant.Config.User_Code_KEY, ""));
		user.setMobilephone(sharedPreferences.getString(Constant.Config.MobilePhone_Key, ""));
		user.setUser_email(sharedPreferences.getString(Constant.Config.User_Email_Key, ""));
		
		return user;
	}
}
