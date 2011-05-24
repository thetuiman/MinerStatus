package me.davidgreene.minerstatus.beans;

import java.io.Serializable;

public class BtcguildWorker implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6917436321008955505L;
	
	private Double hash_rate;
	private Integer round_shares;
	private Integer round_stales;
	private Integer reset_shares;
	private Integer reset_stales;
	private Integer total_shares;
	private Integer total_stales;
	private String last_share;
	private String worker_name;
	private Integer blocks_found;
	
	public Double getHash_rate() {
		return hash_rate;
	}
	public void setHash_rate(Double hash_rate) {
		this.hash_rate = hash_rate;
	}
	public Integer getRound_shares() {
		return round_shares;
	}
	public void setRound_shares(Integer round_shares) {
		this.round_shares = round_shares;
	}
	public Integer getRound_stales() {
		return round_stales;
	}
	public void setRound_stales(Integer round_stales) {
		this.round_stales = round_stales;
	}
	public Integer getReset_shares() {
		return reset_shares;
	}
	public void setReset_shares(Integer reset_shares) {
		this.reset_shares = reset_shares;
	}
	public Integer getReset_stales() {
		return reset_stales;
	}
	public void setReset_stales(Integer reset_stales) {
		this.reset_stales = reset_stales;
	}
	public Integer getTotal_shares() {
		return total_shares;
	}
	public void setTotal_shares(Integer total_shares) {
		this.total_shares = total_shares;
	}
	public Integer getTotal_stales() {
		return total_stales;
	}
	public void setTotal_stales(Integer total_stales) {
		this.total_stales = total_stales;
	}
	public String getLast_share() {
		return last_share;
	}
	public void setLast_share(String last_share) {
		this.last_share = last_share;
	}
	public String getWorker_name() {
		return worker_name;
	}
	public void setWorker_name(String worker_name) {
		this.worker_name = worker_name;
	}
	public Integer getBlocks_found() {
		return blocks_found;
	}
	public void setBlocks_found(Integer blocks_found) {
		this.blocks_found = blocks_found;
	}
	

}
