package com.survival.model;

import java.io.Serializable;

/**
 * 用户登录返回的对象
 * @author Survival
 *
 */
public class LoginModel implements Serializable {

	private String Code;
	private String Expire;
	private String Token;
	public String getCode() {
		return Code;
	}
	public void setCode(String code) {
		Code = code;
	}
	public String getExpire() {
		return Expire;
	}
	public void setExpire(String expire) {
		Expire = expire;
	}
	public String getToken() {
		return Token;
	}
	public void setToken(String token) {
		Token = token;
	}
	
	
}
