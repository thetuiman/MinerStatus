package me.davidgreene.minerstatus.beans;

import java.io.Serializable;

public class BitpoolStatus implements Status, Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4757193380729099732L;
	private BitpoolUser User;
	private BitpoolPool Pool;
	private String apiKey;
	
	@Override
	public String getUsername() {
		return getUser().getUsername();
	}
	@Override
	public String getDisplayCol1() {
		return getUser().getCurrSpeed();
	}
	@Override
	public String getDisplayCol2() {
		return getUser().getHistorical();
	}
	
	public BitpoolUser getUser() {
		return User;
	}
	public void setUser(BitpoolUser user) {
		this.User = user;
	}
	public BitpoolPool getPool() {
		return Pool;
	}
	public void setPool(BitpoolPool pool) {
		this.Pool = pool;
	}
	public String getApiKey() {
		return apiKey;
	}
	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}
	@Override
	public String getUsernameLabel() {
		return "";
	}
	@Override
	public String getDisplayCol1Label() {
		return "Current Speed";
	}
	@Override
	public String getDisplayCol2Label() {
		return "Historical Payout";
	}


}
