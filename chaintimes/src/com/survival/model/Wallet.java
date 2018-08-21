package com.survival.model;

import java.io.Serializable;

/**
 * 虚拟货币资产
 * @author Survival
 *
 */
public class Wallet implements Serializable{
	private String wallet_id;
	private String wallet_code;
	private String user_code;
	private String coin_name;
	private String wallet_receive_code;
	private String coin_balance;
	private String concern_state;
	private String coin_img;
	private String addTime;
	public String getWallet_id() {
		return wallet_id;
	}
	public void setWallet_id(String wallet_id) {
		this.wallet_id = wallet_id;
	}
	public String getWallet_code() {
		return wallet_code;
	}
	public void setWallet_code(String wallet_code) {
		this.wallet_code = wallet_code;
	}
	public String getUser_code() {
		return user_code;
	}
	public void setUser_code(String user_code) {
		this.user_code = user_code;
	}
	public String getCoin_name() {
		return coin_name;
	}
	public void setCoin_name(String coin_name) {
		this.coin_name = coin_name;
	}
	public String getWallet_receive_code() {
		return wallet_receive_code;
	}
	public void setWallet_receive_code(String wallet_receive_code) {
		this.wallet_receive_code = wallet_receive_code;
	}
	public String getCoin_balance() {
		return coin_balance;
	}
	public void setCoin_balance(String coin_balance) {
		this.coin_balance = coin_balance;
	}
	public String getConcern_state() {
		return concern_state;
	}
	public void setConcern_state(String concern_state) {
		this.concern_state = concern_state;
	}
	public String getCoin_img() {
		return coin_img;
	}
	public void setCoin_img(String coin_img) {
		this.coin_img = coin_img;
	}
	public String getAddTime() {
		return addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
	
	
}
