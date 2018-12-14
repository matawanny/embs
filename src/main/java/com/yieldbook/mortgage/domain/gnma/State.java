package com.yieldbook.mortgage.domain.gnma;

import java.math.BigDecimal;

import com.ancientprogramming.fixedformat4j.annotation.Align;
import com.ancientprogramming.fixedformat4j.annotation.Field;
import com.ancientprogramming.fixedformat4j.annotation.FixedFormatDecimal;
import com.ancientprogramming.fixedformat4j.annotation.Record;

@Record
public class State {
	private String cusip;
	private String poolNumber;
	private String poolIndicator;
	private String poolType;
	private String recrodType;

	private String stateAbbreviation;
	private BigDecimal currentSecurityBalanceForState;
	private BigDecimal balanceAsAPercent;
	private Integer numberOfLoansForState;
	private BigDecimal numberOfLoansAsAPercent;
	private Integer totalLoans;
	private BigDecimal originalBalanceForState;

	private Integer asOfDate;

	@Field(offset=1, length=9)
	public String getCusip() {
		return cusip;
	}
	public void setCusip(String cusip) {
		this.cusip = cusip;
	}
	@Field(offset=10, length=6)
	public String getPoolNumber() {
		return poolNumber;
	}
	public void setPoolNumber(String poolNumber) {
		this.poolNumber = poolNumber;
	}
	@Field(offset=16, length=1)
	public String getPoolIndicator() {
		return poolIndicator;
	}
	public void setPoolIndicator(String poolIndicator) {
		this.poolIndicator = poolIndicator;
	}
	@Field(offset=17, length=2)
	public String getPoolType() {
		return poolType;
	}
	public void setPoolType(String poolType) {
		this.poolType = poolType;
	}
	@Field(offset=19, length=1)
	public String getRecrodType() {
		return recrodType;
	}
	public void setRecrodType(String recrodType) {
		this.recrodType = recrodType;
	}
	@Field(offset=20, length=2)
	public String getStateAbbreviation() {
		return stateAbbreviation;
	}
	public void setStateAbbreviation(String stateAbbreviation) {
		this.stateAbbreviation = stateAbbreviation;
	}
	@Field(offset=22, length=13, align=Align.RIGHT, paddingChar='0')
	@FixedFormatDecimal (decimals=2, useDecimalDelimiter=false)	
	public BigDecimal getCurrentSecurityBalanceForState() {
		return currentSecurityBalanceForState;
	}
	public void setCurrentSecurityBalanceForState(
			BigDecimal currentSecurityBalanceForState) {
		this.currentSecurityBalanceForState = currentSecurityBalanceForState;
	}
	@Field(offset=35, length=5, align=Align.RIGHT, paddingChar='0')
	@FixedFormatDecimal (decimals=2, useDecimalDelimiter=false)
	public BigDecimal getBalanceAsAPercent() {
		return balanceAsAPercent;
	}
	public void setBalanceAsAPercent(BigDecimal balanceAsAPercent) {
		this.balanceAsAPercent = balanceAsAPercent;
	}
	@Field(offset=40, length=6, align=Align.RIGHT, paddingChar='0')
	public Integer getNumberOfLoansForState() {
		return numberOfLoansForState;
	}
	public void setNumberOfLoansForState(Integer numberOfLoansForState) {
		this.numberOfLoansForState = numberOfLoansForState;
	}
	@Field(offset=46, length=5, align=Align.RIGHT, paddingChar='0')
	@FixedFormatDecimal (decimals=2, useDecimalDelimiter=false)
	public BigDecimal getNumberOfLoansAsAPercent() {
		return numberOfLoansAsAPercent;
	}
	public void setNumberOfLoansAsAPercent(BigDecimal numberOfLoansAsAPercent) {
		this.numberOfLoansAsAPercent = numberOfLoansAsAPercent;
	}
	@Field(offset=51, length=6, align=Align.RIGHT, paddingChar='0')
	public Integer getTotalLoans() {
		return totalLoans;
	}
	public void setTotalLoans(Integer totalLoans) {
		this.totalLoans = totalLoans;
	}
	@Field(offset=57, length=13, align=Align.RIGHT, paddingChar='0')
	@FixedFormatDecimal (decimals=2, useDecimalDelimiter=false)	
	public BigDecimal getOriginalBalanceForState() {
		return originalBalanceForState;
	}
	public void setOriginalBalanceForState(BigDecimal originalBalanceForState) {
		this.originalBalanceForState = originalBalanceForState;
	}
	@Field(offset=70, length=6)
	public Integer getAsOfDate() {
		return asOfDate;
	}
	public void setAsOfDate(Integer asOfDate) {
		this.asOfDate = asOfDate;
	}
	@Override
	public String toString() {
		return "State [poolNumber=" + poolNumber + ", stateAbbreviation="
				+ stateAbbreviation + ", currentSecurityBalanceForState="
				+ currentSecurityBalanceForState + ", balanceAsAPercent="
				+ balanceAsAPercent + ", numberOfLoansForState="
				+ numberOfLoansForState + ", numberOfLoansAsAPercent="
				+ numberOfLoansAsAPercent + ", totalLoans=" + totalLoans
				+ ", originalBalanceForState=" + originalBalanceForState + "]";
	}
	
}
