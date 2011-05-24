package me.davidgreene.minerstatus.beans;

import java.io.Serializable;

public class BtcguildUser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7805430620398782585L;
	
	private Double confirmed_rewards;
	private Double unconfirmed_rewards;
	private Double estimated_rewards;
	private Double payouts;
	
	public Double getConfirmed_rewards() {
		return confirmed_rewards;
	}
	public void setConfirmed_rewards(Double confirmed_rewards) {
		this.confirmed_rewards = confirmed_rewards;
	}
	public Double getUnconfirmed_rewards() {
		return unconfirmed_rewards;
	}
	public void setUnconfirmed_rewards(Double unconfirmed_rewards) {
		this.unconfirmed_rewards = unconfirmed_rewards;
	}
	public Double getEstimated_rewards() {
		return estimated_rewards;
	}
	public void setEstimated_rewards(Double estimated_rewards) {
		this.estimated_rewards = estimated_rewards;
	}
	public Double getPayouts() {
		return payouts;
	}
	public void setPayouts(Double payouts) {
		this.payouts = payouts;
	}
	
}
