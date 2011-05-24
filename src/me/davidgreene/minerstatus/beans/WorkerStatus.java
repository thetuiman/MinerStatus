package me.davidgreene.minerstatus.beans;

import java.io.Serializable;

public class WorkerStatus implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1489566244233639810L;
	private Boolean alive;
	private Integer shares;
	private Integer stales;
	public Boolean getAlive() {
		return alive;
	}
	public void setAlive(Boolean alive) {
		this.alive = alive;
	}
	public Integer getShares() {
		return shares;
	}
	public void setShares(Integer shares) {
		this.shares = shares;
	}
	public Integer getStales() {
		return stales;
	}
	public void setStales(Integer stale) {
		this.stales = stale;
	}
	

}
