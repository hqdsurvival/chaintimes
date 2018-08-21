package com.survival.model;

import java.io.Serializable;

/**
 * 货币
 * @author Survival
 *
 */
public class Coin implements Serializable{

	private String coin_code;   //币种系统编码
	private String coin_name;   //币名称
	private String coin_detail; //币描述
	private String coin_img;    //图片
	private boolean coin_choice;   //是否关注
	public String getCoin_code() {
		return coin_code;
	}
	public void setCoin_code(String coin_code) {
		this.coin_code = coin_code;
	}
	public String getCoin_name() {
		return coin_name;
	}
	public void setCoin_name(String coin_name) {
		this.coin_name = coin_name;
	}
	public String getCoin_detail() {
		return coin_detail;
	}
	public void setCoin_detail(String coin_detail) {
		this.coin_detail = coin_detail;
	}
	public String getCoin_img() {
		return coin_img;
	}
	public void setCoin_img(String coin_img) {
		this.coin_img = coin_img;
	}
	public boolean isCoin_choice() {
		return coin_choice;
	}
	public void setCoin_choice(boolean coin_choice) {
		this.coin_choice = coin_choice;
	}
	
	
}
