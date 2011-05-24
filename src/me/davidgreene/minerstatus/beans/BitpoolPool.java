package me.davidgreene.minerstatus.beans;

import java.io.Serializable;

public class BitpoolPool implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5074046199767555947L;
	private String currentRound;
	private String currentSpeed;
	
	public String getCurrentRound() {
		return currentRound;
	}
	public void setCurrentRound(String currentRound) {
		this.currentRound = currentRound;
	}
	public String getCurrentSpeed() {
		return currentSpeed;
	}
	public void setCurrentSpeed(String currentSpeed) {
		this.currentSpeed = currentSpeed;
	}
}
