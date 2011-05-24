package me.davidgreene.minerstatus.beans;

import java.io.Serializable;

public class BtcMine implements Serializable, Status {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4929377426083392967L;
	private String total_bounty;
	private String confirmed_bounty;
	private Integer solved_blocks;
	private Integer round_shares;
	private String estimated_bounty;
	private Integer solved_shares;
	private String unconfirmed_bounty;
	private String hashrate;
	private String total_payout;
	private String apiKey;
	
	@Override
	public String getUsername() {
		return "Worker(s)";
	}

	@Override
	public String getDisplayCol1() {
		return hashrate;
	}

	@Override
	public String getDisplayCol2() {
		return total_payout;
	}

	@Override
	public void setApiKey(String apiKey) {
		this.apiKey=apiKey;
	}

	@Override
	public String getApiKey() {
		return apiKey;
	}

	public String getTotal_bounty() {
		return total_bounty;
	}

	public void setTotal_bounty(String total_bounty) {
		this.total_bounty = total_bounty;
	}

	public String getConfirmed_bounty() {
		return confirmed_bounty;
	}

	public void setConfirmed_bounty(String confirmed_bounty) {
		this.confirmed_bounty = confirmed_bounty;
	}

	public Integer getSolved_blocks() {
		return solved_blocks;
	}

	public void setSolved_blocks(Integer solved_blocks) {
		this.solved_blocks = solved_blocks;
	}

	public Integer getRound_shares() {
		return round_shares;
	}

	public void setRound_shares(Integer round_shares) {
		this.round_shares = round_shares;
	}

	public String getEstimated_bounty() {
		return estimated_bounty;
	}

	public void setEstimated_bounty(String estimated_bounty) {
		this.estimated_bounty = estimated_bounty;
	}

	public Integer getSolved_shares() {
		return solved_shares;
	}

	public void setSolved_shares(Integer solved_shares) {
		this.solved_shares = solved_shares;
	}

	public String getUnconfirmed_bounty() {
		return unconfirmed_bounty;
	}

	public void setUnconfirmed_bounty(String unconfirmed_bounty) {
		this.unconfirmed_bounty = unconfirmed_bounty;
	}

	public String getHashrate() {
		return hashrate;
	}

	public void setHashrate(String hashrate) {
		this.hashrate = hashrate;
	}

	public String getTotal_payout() {
		return total_payout;
	}

	public void setTotal_payout(String total_payout) {
		this.total_payout = total_payout;
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
		return "Total Payout";
	}

}
