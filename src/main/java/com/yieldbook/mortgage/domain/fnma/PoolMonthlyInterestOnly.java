package com.yieldbook.mortgage.domain.fnma;

import java.math.BigDecimal;



public class PoolMonthlyInterestOnly {
	private String poolNumber;
	private Integer waMonthsToAmortization;
	private Integer numberOfLoansPerPool;
	private String poolPrefix;
	private String cusip;
	
	private Integer remainingMonthsToAmortization;
	private Integer numberOfLoansPerPoolPerRemainingMonthsToAmortization;
	private BigDecimal upbPercent;
	private String comment;
	
	public String getPoolNumber() {
		return poolNumber;
	}
	public void setPoolNumber(String poolNumber) {
		this.poolNumber = poolNumber;
	}

	public Integer getWaMonthsToAmortization() {
		return waMonthsToAmortization;
	}
	public void setWaMonthsToAmortization(Integer waMonthsToAmortization) {
		this.waMonthsToAmortization = waMonthsToAmortization;
	}
	public Integer getNumberOfLoansPerPool() {
		return numberOfLoansPerPool;
	}
	public void setNumberOfLoansPerPool(Integer numberOfLoansPerPool) {
		this.numberOfLoansPerPool = numberOfLoansPerPool;
	}
	public String getPoolPrefix() {
		return poolPrefix;
	}
	public void setPoolPrefix(String poolPrefix) {
		this.poolPrefix = poolPrefix;
	}
	public String getCusip() {
		return cusip;
	}
	public void setCusip(String cusip) {
		this.cusip = cusip;
	}

	public Integer getRemainingMonthsToAmortization() {
		return remainingMonthsToAmortization;
	}
	public void setRemainingMonthsToAmortization(
			Integer remainingMonthsToAmortization) {
		this.remainingMonthsToAmortization = remainingMonthsToAmortization;
	}
	public Integer getNumberOfLoansPerPoolPerRemainingMonthsToAmortization() {
		return numberOfLoansPerPoolPerRemainingMonthsToAmortization;
	}
	public void setNumberOfLoansPerPoolPerRemainingMonthsToAmortization(
			Integer numberOfLoansPerPoolPerRemainingMonthsToAmortization) {
		this.numberOfLoansPerPoolPerRemainingMonthsToAmortization = numberOfLoansPerPoolPerRemainingMonthsToAmortization;
	}
	public BigDecimal getUpbPercent() {
		return upbPercent;
	}
	public void setUpbPercent(BigDecimal upbPercent) {
		this.upbPercent = upbPercent;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	@Override
	public String toString() {
		return "PoolMonthlyInterestOnly [poolNumber=" + poolNumber
				+ ", waMonthsToAmortization=" + waMonthsToAmortization
				+ ", numberOfLoansPerPool=" + numberOfLoansPerPool
				+ ", poolPrefix=" + poolPrefix + ", cusip=" + cusip
				+ ", remainingMonthsToAmortization="
				+ remainingMonthsToAmortization
				+ ", numberOfLoansPerPoolPerRemainingMonthsToAmortization="
				+ numberOfLoansPerPoolPerRemainingMonthsToAmortization
				+ ", upbPercent=" + upbPercent + ", comment=" + comment + "]";
	}
}
