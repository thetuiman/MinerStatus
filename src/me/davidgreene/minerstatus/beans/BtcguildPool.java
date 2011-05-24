package me.davidgreene.minerstatus.beans;

import java.io.Serializable;

public class BtcguildPool implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4258828827151924731L;
	
	private Double hash_rate;
	private Integer active_workers;
	private Integer active_shares;
	private String round_time;
	
	public Double getHash_rate() {
		return hash_rate;
	}
	public void setHash_rate(Double hash_rate) {
		this.hash_rate = hash_rate;
	}
	public Integer getActive_workers() {
		return active_workers;
	}
	public void setActive_workers(Integer active_workers) {
		this.active_workers = active_workers;
	}
	public Integer getActive_shares() {
		return active_shares;
	}
	public void setActive_shares(Integer active_shares) {
		this.active_shares = active_shares;
	}
	public String getRound_time() {
		return round_time;
	}
	public void setRound_time(String round_time) {
		this.round_time = round_time;
	}

}
