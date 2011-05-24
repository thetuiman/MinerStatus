package me.davidgreene.minerstatus.beans;

import java.io.Serializable;

public class BitpoolUser implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3800039392595679950L;
	private String username;
	private String status;
	private String currSpeed;
	private String joinDt;
	private String lastSeen;
	private String active;
	private String estimated;
	private String unconfirmed;
	private String historical;
	private String unpaid;
	private Integer[] solvedBlocks;
	private String efficiency;
	private Long requested;
	private Long submitted;
	
	public String getDisplayCol1(){
		return getCurrSpeed();
	}
	
	public String getDisplayCol2(){
		return getHistorical();
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCurrSpeed() {
		return currSpeed;
	}
	public void setCurrSpeed(String currSpeed) {
		this.currSpeed = currSpeed;
	}
	public String getJoinDt() {
		return joinDt;
	}
	public void setJoinDt(String joinDt) {
		this.joinDt = joinDt;
	}
	public String getLastSeen() {
		return lastSeen;
	}
	public void setLastSeen(String lastSeen) {
		this.lastSeen = lastSeen;
	}
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
	public String getEstimated() {
		return estimated;
	}
	public void setEstimated(String estimated) {
		this.estimated = estimated;
	}
	public String getUnconfirmed() {
		return unconfirmed;
	}
	public void setUnconfirmed(String unconfirmed) {
		this.unconfirmed = unconfirmed;
	}
	public String getHistorical() {
		return historical;
	}
	public void setHistorical(String historical) {
		this.historical = historical;
	}
	public String getUnpaid() {
		return unpaid;
	}
	public void setUnpaid(String unpaid) {
		this.unpaid = unpaid;
	}
	public Integer[] getSolvedBlocks() {
		return solvedBlocks;
	}
	public void setSolvedBlocks(Integer[] solvedBlocks) {
		this.solvedBlocks = solvedBlocks;
	}
	public String getEfficiency() {
		return efficiency;
	}
	public void setEfficiency(String efficiency) {
		this.efficiency = efficiency;
	}
	public Long getRequested() {
		return requested;
	}
	public void setRequested(Long requested) {
		this.requested = requested;
	}
	public Long getSubmitted() {
		return submitted;
	}
	public void setSubmitted(Long submitted) {
		this.submitted = submitted;
	}
}
