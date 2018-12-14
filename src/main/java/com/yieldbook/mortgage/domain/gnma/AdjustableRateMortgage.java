package com.yieldbook.mortgage.domain.gnma;

import java.math.BigDecimal;
import java.util.Date;

import com.ancientprogramming.fixedformat4j.annotation.Align;
import com.ancientprogramming.fixedformat4j.annotation.Field;
import com.ancientprogramming.fixedformat4j.annotation.FixedFormatDecimal;
import com.ancientprogramming.fixedformat4j.annotation.Record;

@Record
public class AdjustableRateMortgage {
	private String cusip;
	private String poolNumber;
	private String poolIndicator;
	private String poolType;
	private String recrodType;
	private Integer lookBackPeriod;
	private String indexType;
	private Date nextAdjustmentDate;
	private Date priorAdjustmentDate;
	private Integer monthsToAdjust;
	private BigDecimal weightedAverageMortgageMargin;
	private BigDecimal maximumMortgageMargin;
	private BigDecimal minimumMortgageMargin;
	private Integer initialInterestRateCap;
	private Integer subsequentInterestRateCap;
	private Integer lifetimeInterestRateCap;
	private BigDecimal lifetimeInterestRateCeiling;
	private BigDecimal nextInterestRateCeiling;
	private BigDecimal lifetimeInterestRateFloor;
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
	@Field(offset=20, length=2, align=Align.RIGHT, paddingChar='0')
	public Integer getLookBackPeriod() {
		return lookBackPeriod;
	}
	public void setLookBackPeriod(Integer lookBackPeriod) {
		this.lookBackPeriod = lookBackPeriod;
	}
	@Field(offset=22, length=5)
	public String getIndexType() {
		return indexType;
	}
	public void setIndexType(String indexType) {
		this.indexType = indexType;
	}
	@Field(offset=27, length=8, align=Align.RIGHT, paddingChar='9')
	public Date getNextAdjustmentDate() {
		return nextAdjustmentDate;
	}
	public void setNextAdjustmentDate(Date nextAdjustmentDate) {
		this.nextAdjustmentDate = nextAdjustmentDate;
	}
	@Field(offset=35, length=8, align=Align.RIGHT, paddingChar='9')
	public Date getPriorAdjustmentDate() {
		return priorAdjustmentDate;
	}
	public void setPriorAdjustmentDate(Date priorAdjustmentDate) {
		this.priorAdjustmentDate = priorAdjustmentDate;
	}
	@Field(offset=43, length=3, align=Align.RIGHT, paddingChar='0')
	public Integer getMonthsToAdjust() {
		return monthsToAdjust;
	}
	public void setMonthsToAdjust(Integer monthsToAdjust) {
		this.monthsToAdjust = monthsToAdjust;
	}
	@Field(offset=46, length=5, align=Align.RIGHT, paddingChar='0')
	@FixedFormatDecimal (decimals=3, useDecimalDelimiter=false)
	public BigDecimal getWeightedAverageMortgageMargin() {
		return weightedAverageMortgageMargin;
	}
	public void setWeightedAverageMortgageMargin(
			BigDecimal weightedAverageMortgageMargin) {
		this.weightedAverageMortgageMargin = weightedAverageMortgageMargin;
	}
	@Field(offset=51, length=5, align=Align.RIGHT, paddingChar='0')
	@FixedFormatDecimal (decimals=3, useDecimalDelimiter=false)
	public BigDecimal getMaximumMortgageMargin() {
		return maximumMortgageMargin;
	}
	public void setMaximumMortgageMargin(BigDecimal maximumMortgageMargin) {
		this.maximumMortgageMargin = maximumMortgageMargin;
	}
	@Field(offset=56, length=5, align=Align.RIGHT, paddingChar='0')
	@FixedFormatDecimal (decimals=3, useDecimalDelimiter=false)
	public BigDecimal getMinimumMortgageMargin() {
		return minimumMortgageMargin;
	}
	public void setMinimumMortgageMargin(BigDecimal minimumMortgageMargin) {
		this.minimumMortgageMargin = minimumMortgageMargin;
	}
	@Field(offset=61, length=1)
	public Integer getInitialInterestRateCap() {
		return initialInterestRateCap;
	}
	public void setInitialInterestRateCap(Integer initialInterestRateCap) {
		this.initialInterestRateCap = initialInterestRateCap;
	}
	@Field(offset=62, length=1)
	public Integer getSubsequentInterestRateCap() {
		return subsequentInterestRateCap;
	}
	public void setSubsequentInterestRateCap(Integer subsequentInterestRateCap) {
		this.subsequentInterestRateCap = subsequentInterestRateCap;
	}
	@Field(offset=63, length=1)
	public Integer getLifetimeInterestRateCap() {
		return lifetimeInterestRateCap;
	}
	public void setLifetimeInterestRateCap(Integer lifetimeInterestRateCap) {
		this.lifetimeInterestRateCap = lifetimeInterestRateCap;
	}
	@Field(offset=64, length=5, align=Align.RIGHT, paddingChar='0')
	@FixedFormatDecimal (decimals=3, useDecimalDelimiter=false)
	public BigDecimal getLifetimeInterestRateCeiling() {
		return lifetimeInterestRateCeiling;
	}
	public void setLifetimeInterestRateCeiling(
			BigDecimal lifetimeInterestRateCeiling) {
		this.lifetimeInterestRateCeiling = lifetimeInterestRateCeiling;
	}
	@Field(offset=69, length=5, align=Align.RIGHT, paddingChar='0')
	@FixedFormatDecimal (decimals=3, useDecimalDelimiter=false)
	public BigDecimal getNextInterestRateCeiling() {
		return nextInterestRateCeiling;
	}
	public void setNextInterestRateCeiling(BigDecimal nextInterestRateCeiling) {
		this.nextInterestRateCeiling = nextInterestRateCeiling;
	}
	@Field(offset=74, length=5, align=Align.RIGHT, paddingChar='0')
	@FixedFormatDecimal (decimals=3, useDecimalDelimiter=false)
	public BigDecimal getLifetimeInterestRateFloor() {
		return lifetimeInterestRateFloor;
	}
	public void setLifetimeInterestRateFloor(BigDecimal lifetimeInterestRateFloor) {
		this.lifetimeInterestRateFloor = lifetimeInterestRateFloor;
	}
	@Field(offset=79, length=6)
	public Integer getAsOfDate() {
		return asOfDate;
	}
	public void setAsOfDate(Integer asOfDate) {
		this.asOfDate = asOfDate;
	}
	@Override
	public String toString() {
		return "AdjustableRateMortgage [poolNumber=" + poolNumber
				+ ", lookBackPeriod=" + lookBackPeriod + ", indexType="
				+ indexType + ", nextAdjustmentDate=" + nextAdjustmentDate
				+ ", priorAdjustmentDate=" + priorAdjustmentDate
				+ ", monthsToAdjust=" + monthsToAdjust
				+ ", weightedAverageMortgageMargin="
				+ weightedAverageMortgageMargin + ", maximumMortgageMargin="
				+ maximumMortgageMargin + ", minimumMortgageMargin="
				+ minimumMortgageMargin + ", initialInterestRateCap="
				+ initialInterestRateCap + ", subsequentInterestRateCap="
				+ subsequentInterestRateCap + ", lifetimeInterestRateCap="
				+ lifetimeInterestRateCap + ", lifetimeInterestRateCeiling="
				+ lifetimeInterestRateCeiling + ", nextInterestRateCeiling="
				+ nextInterestRateCeiling + ", lifetimeInterestRateFloor="
				+ lifetimeInterestRateFloor + "]";
	}
	

}
