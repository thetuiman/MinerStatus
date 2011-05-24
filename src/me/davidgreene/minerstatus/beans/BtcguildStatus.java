package me.davidgreene.minerstatus.beans;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;

public class BtcguildStatus implements Status, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2399234390831522839L;

	private BtcguildUser user;
	private BtcguildPool pool;
	private Map<String, BtcguildWorker> workers;
	private String apiKey;
	
	@Override
	public String getUsername() {
		return "Worker(s)";
	}

	@Override
	public String getDisplayCol1() {
		return user.getConfirmed_rewards().toString();
	}

	@Override
	public String getDisplayCol2() {
		Double hashRate = 0D;
		Iterator it = workers.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pairs = (Map.Entry)it.next();
			pairs.getKey();
			BtcguildWorker worker = (BtcguildWorker) pairs.getValue();
			hashRate += worker.getHash_rate();
		}
		return hashRate.toString();
	}

	@Override
	public String getUsernameLabel() {
		return "";
	}

	@Override
	public String getDisplayCol1Label() {
		return "Confirmed Rewards";
	}

	@Override
	public String getDisplayCol2Label() {
		return "Hash Rate";
	}

	public BtcguildUser getUser() {
		return user;
	}

	public void setUser(BtcguildUser user) {
		this.user = user;
	}

	public BtcguildPool getPool() {
		return pool;
	}

	public void setPool(BtcguildPool pool) {
		this.pool = pool;
	}

	public Map<String, BtcguildWorker> getWorkers() {
		return workers;
	}

	public void setWorkers(Map<String, BtcguildWorker> workers) {
		this.workers = workers;
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

}
