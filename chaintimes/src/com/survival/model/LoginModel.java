package com.survival.model;

import java.io.Serializable;

/**
 * 用户登录返回的对象
 * @author Survival
 *
 */
public class LoginModel implements Serializable {

	private String code;
	private String expire;
	private String token;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getExpire() {
		return expire;
	}
	public void setExpire(String expire) {
		this.expire = expire;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	
}
