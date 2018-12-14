package com.yieldbook.mortgage.domain.gnma;

import com.ancientprogramming.fixedformat4j.annotation.Field;
import com.ancientprogramming.fixedformat4j.annotation.Record;

@Record
public class RemovalByIssuer {
	private String cusip;
	private String poolNumber;
	private String poolIndicator;
	private String poolType;
	private String recrodType;
	private Integer issuer;
	private StatsElement24 mortgagorPayoff;
	private StatsElement24 repurchaseDelinquent;
	private StatsElement24 foreclosureWithClaimPayment;
	private StatsElement24 repurchaseLossMitigation;
	private StatsElement24 substitution;
	private StatsElement24 otherRemoval;
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
	@Field(offset=20, length=4)
	public Integer getIssuer() {
		return issuer;
	}
	public void setIssuer(Integer issuer) {
		this.issuer = issuer;
	}
	@Field(offset=24, length=24)
	public StatsElement24 getMortgagorPayoff() {
		return mortgagorPayoff;
	}
	public void setMortgagorPayoff(StatsElement24 mortgagorPayoff) {
		this.mortgagorPayoff = mortgagorPayoff;
	}
	@Field(offset=48, length=24)
	public StatsElement24 getRepurchaseDelinquent() {
		return repurchaseDelinquent;
	}
	public void setRepurchaseDelinquent(StatsElement24 repurchaseDelinquent) {
		this.repurchaseDelinquent = repurchaseDelinquent;
	}
	@Field(offset=72, length=24)
	public StatsElement24 getForeclosureWithClaimPayment() {
		return foreclosureWithClaimPayment;
	}
	public void setForeclosureWithClaimPayment(
			StatsElement24 foreclosureWithClaimPayment) {
		this.foreclosureWithClaimPayment = foreclosureWithClaimPayment;
	}
	@Field(offset=96, length=24)
	public StatsElement24 getRepurchaseLossMitigation() {
		return repurchaseLossMitigation;
	}
	public void setRepurchaseLossMitigation(StatsElement24 repurchaseLossMitigation) {
		this.repurchaseLossMitigation = repurchaseLossMitigation;
	}
	@Field(offset=120, length=24)
	public StatsElement24 getSubstitution() {
		return substitution;
	}
	public void setSubstitution(StatsElement24 substitution) {
		this.substitution = substitution;
	}
	@Field(offset=144, length=24)
	public StatsElement24 getOtherRemoval() {
		return otherRemoval;
	}
	public void setOtherRemoval(StatsElement24 otherRemoval) {
		this.otherRemoval = otherRemoval;
	}
	@Field(offset=168, length=6)
	public Integer getAsOfDate() {
		return asOfDate;
	}
	public void setAsOfDate(Integer asOfDate) {
		this.asOfDate = asOfDate;
	}
	@Override
	public String toString() {
		return "RemovalsByIssuer [issuer=" + issuer + ", mortgagorPayoff="
				+ mortgagorPayoff + ", repurchaseDelinquent="
				+ repurchaseDelinquent + ", foreclosureWithClaimPayment="
				+ foreclosureWithClaimPayment + ", repurchaseLossMitigation="
				+ repurchaseLossMitigation + ", substitution=" + substitution
				+ ", otherRemoval=" + otherRemoval + ", asOfDate=" + asOfDate
				+ "]";
	}
}
