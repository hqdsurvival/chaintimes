package com.survival.model;

import java.io.Serializable;

public class JsonResult implements Serializable{

	private int Code;
	private String Message;
	private String Data;
	
	public int getCode() {
		return Code;
	}
	public void setCode(int code) {
		Code = code;
	}
	public String getMessage() {
		return Message;
	}
	public void setMessage(String message) {
		Message = message;
	}
	public String getData() {
		return Data;
	}
	public void setData(String data) {
		Data = data;
	}
	
}
