package com.jpm.interview.kohli.vivek.entity;

import java.math.BigDecimal;
import java.util.Currency;

public class TradeInstruction {

	private String entity;
	
	private Direction direction;
	
	private BigDecimal fxRate;
	
	private Currency currency;
	 
	private String instDate;
	
	private String settDate;
	
	private String effectiveSettDate;
	
	private int units;
	
	private BigDecimal pricePerUnit;
	
	public String getEntity() {
		return entity;
	}

	public void setEntity(String entity) {
		this.entity = entity;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public BigDecimal getFxRate() {
		return fxRate;
	}

	public void setFxRate(BigDecimal fxRate) {
		this.fxRate = fxRate;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public String getInstDate() {
		return instDate;
	}

	public void setInstDate(String instDate) {
		this.instDate = instDate;
	}

	public String getSettDate() {
		return settDate;
	}

	public void setSettDate(String settDate) {
		this.settDate = settDate;
	}

	public String getEffectiveSettDate() {
		return effectiveSettDate;
	}

	public void setEffectiveSettDate(String effectiveSettDate) {
		this.effectiveSettDate = effectiveSettDate;
	}

	public int getUnits() {
		return units;
	}

	public void setUnits(int units) {
		this.units = units;
	}

	public BigDecimal getPricePerUnit() {
		return pricePerUnit;
	}

	public void setPricePerUnit(BigDecimal pricePerUnit) {
		this.pricePerUnit = pricePerUnit;
	}

	
	@Override
	public String toString() {
		return "TradeInstruction [entity=" + entity + ", direction="
				+ direction + ", fxRate=" + fxRate + ", currency=" + currency
				+ ", instDate=" + instDate + ", settDate=" + settDate
				+ ", effectiveSettDate=" + effectiveSettDate + ", units="
				+ units + ", pricePerUnit=" + pricePerUnit + "]\n";
	}
}
