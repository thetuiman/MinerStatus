package me.davidgreene.minerstatus.beans;

import java.io.Serializable;

public class Ticker implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -289439365080216663L;
	private Double high;
	private Double low;
	private Integer vol;
	private Double buy;
	private Double sell;
	private Double last;
	public Double getHigh() {
		return high;
	}
	public void setHigh(Double high) {
		this.high = high;
	}
	public Double getLow() {
		return low;
	}
	public void setLow(Double low) {
		this.low = low;
	}
	public Integer getVol() {
		return vol;
	}
	public void setVol(Integer vol) {
		this.vol = vol;
	}
	public Double getBuy() {
		return buy;
	}
	public void setBuy(Double buy) {
		this.buy = buy;
	}
	public Double getSell() {
		return sell;
	}
	public void setSell(Double sell) {
		this.sell = sell;
	}
	public Double getLast() {
		return last;
	}
	public void setLast(Double last) {
		this.last = last;
	}
}
