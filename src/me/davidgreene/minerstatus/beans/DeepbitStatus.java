package me.davidgreene.minerstatus.beans;

import java.io.Serializable;
import java.util.Map;

public class DeepbitStatus implements Status, Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6449549746661969052L;
	private Double confirmed_reward;
	private Double hashrate;
	private Boolean ipa;
	private Map<String,WorkerStatus> workers;
	private String apiKey;
	
	public Double getConfirmed_reward() {
		return confirmed_reward;
	}

	public void setConfirmed_reward(Double confirmed_reward) {
		this.confirmed_reward = confirmed_reward;
	}

	public Double getHashrate() {
		return hashrate;
	}

	public void setHashrate(Double hashrate) {
		this.hashrate = hashrate;
	}

	public Boolean getIpa() {
		return ipa;
	}

	public void setIpa(Boolean ipa) {
		this.ipa = ipa;
	}	
	
	@Override
	public String getUsername() {
		return "Worker(s)";
	}

	@Override
	public String getDisplayCol1() {
		return (hashrate == null) ? "error" : hashrate.toString();
	}

	public String getDisplayCol2() {
		return (confirmed_reward == null) ? "error" : confirmed_reward.toString();
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
		return "Hashrate";
	}

	@Override
	public String getDisplayCol2Label() {
		return "Confirmed Reward";
	}

	public void setWorkers(Map<String,WorkerStatus> workers) {
		this.workers = workers;
	}

	public Map<String,WorkerStatus> getWorkers() {
		return workers;
	}

}
