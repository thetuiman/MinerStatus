package me.davidgreene.minerstatus.beans;

import java.io.Serializable;

public class MtGox implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3039167272598591252L;
	private Ticker ticker;

	public Ticker getTicker() {
		return ticker;
	}

	public void setTicker(Ticker ticker) {
		this.ticker = ticker;
	}
	
}
