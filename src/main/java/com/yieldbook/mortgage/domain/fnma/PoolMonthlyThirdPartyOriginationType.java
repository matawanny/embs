package com.yieldbook.mortgage.domain.fnma;

import java.math.BigDecimal;

import com.ancientprogramming.fixedformat4j.annotation.Align;
import com.ancientprogramming.fixedformat4j.annotation.Field;
import com.ancientprogramming.fixedformat4j.annotation.FixedFormatDecimal;
import com.ancientprogramming.fixedformat4j.annotation.Record;

@Record
public class PoolMonthlyThirdPartyOriginationType {
	private Integer recordType;
	private String poolNumber;
	private BigDecimal thirdPartyOriginationUpbPercent;
	private Integer brokerLoanCount;
	private BigDecimal brokerUpb;
	private BigDecimal brokerUpbPercent;
	private Integer correspondentLoanCount;
	private BigDecimal correspondentUpb;
	private BigDecimal correspondentUpbPercent;
	private Integer retailLoanCount;
	private BigDecimal retailUpb;
	private BigDecimal retailUpbPercent;
	private String filler;
	
	@Field(offset=1, length=1)  
	public Integer getRecordType() {
		return recordType;
	}
	public void setRecordType(Integer recordType) {
		this.recordType = recordType;
	}
	@Field(offset=2, length=6,align=Align.RIGHT, paddingChar=' ')  
	public String getPoolNumber() {
		return poolNumber;
	}
	public void setPoolNumber(String poolNumber) {
		this.poolNumber = poolNumber;
	}
	@Field(offset=8, length=5,align=Align.RIGHT, paddingChar='0')  
	@FixedFormatDecimal (decimals=2, useDecimalDelimiter=false)	
	public BigDecimal getThirdPartyOriginationUpbPercent() {
		return thirdPartyOriginationUpbPercent;
	}
	public void setThirdPartyOriginationUpbPercent(
			BigDecimal thirdPartyOriginationUpbPercent) {
		this.thirdPartyOriginationUpbPercent = thirdPartyOriginationUpbPercent;
	}
	@Field(offset=13, length=8,align=Align.RIGHT, paddingChar='0')  
	public Integer getBrokerLoanCount() {
		return brokerLoanCount;
	}
	public void setBrokerLoanCount(Integer brokerLoanCount) {
		this.brokerLoanCount = brokerLoanCount;
	}
	@Field(offset=21, length=15,align=Align.RIGHT, paddingChar='0')  
	@FixedFormatDecimal (decimals=2, useDecimalDelimiter=false)		
	public BigDecimal getBrokerUpb() {
		return brokerUpb;
	}
	public void setBrokerUpb(BigDecimal brokerUpb) {
		this.brokerUpb = brokerUpb;
	}
	@Field(offset=36, length=5,align=Align.RIGHT, paddingChar='0')  
	@FixedFormatDecimal (decimals=2, useDecimalDelimiter=false)		
	public BigDecimal getBrokerUpbPercent() {
		return brokerUpbPercent;
	}
	public void setBrokerUpbPercent(BigDecimal brokerUpbPercent) {
		this.brokerUpbPercent = brokerUpbPercent;
	}
	@Field(offset=41, length=8,align=Align.RIGHT, paddingChar='0')  
	public Integer getCorrespondentLoanCount() {
		return correspondentLoanCount;
	}
	public void setCorrespondentLoanCount(Integer correspondentLoanCount) {
		this.correspondentLoanCount = correspondentLoanCount;
	}
	@Field(offset=49, length=15,align=Align.RIGHT, paddingChar='0')  
	@FixedFormatDecimal (decimals=2, useDecimalDelimiter=false)	
	public BigDecimal getCorrespondentUpb() {
		return correspondentUpb;
	}
	public void setCorrespondentUpb(BigDecimal correspondentUpb) {
		this.correspondentUpb = correspondentUpb;
	}
	@Field(offset=64, length=5,align=Align.RIGHT, paddingChar='0')  
	@FixedFormatDecimal (decimals=2, useDecimalDelimiter=false)	
	public BigDecimal getCorrespondentUpbPercent() {
		return correspondentUpbPercent;
	}
	public void setCorrespondentUpbPercent(BigDecimal correspondentUpbPercent) {
		this.correspondentUpbPercent = correspondentUpbPercent;
	}
	@Field(offset=69, length=8,align=Align.RIGHT, paddingChar='0')  
	public Integer getRetailLoanCount() {
		return retailLoanCount;
	}
	public void setRetailLoanCount(Integer retailLoanCount) {
		this.retailLoanCount = retailLoanCount;
	}
	@Field(offset=77, length=15,align=Align.RIGHT, paddingChar='0')  
	@FixedFormatDecimal (decimals=2, useDecimalDelimiter=false)	
	public BigDecimal getRetailUpb() {
		return retailUpb;
	}
	public void setRetailUpb(BigDecimal retailUpb) {
		this.retailUpb = retailUpb;
	}
	@Field(offset=92, length=5,align=Align.RIGHT, paddingChar='0')  
	@FixedFormatDecimal (decimals=2, useDecimalDelimiter=false)	
	public BigDecimal getRetailUpbPercent() {
		return retailUpbPercent;
	}
 
	public void setRetailUpbPercent(BigDecimal retailUpbPercent) {
		this.retailUpbPercent = retailUpbPercent;
	}
	@Field(offset=97, length=204,align=Align.RIGHT, paddingChar='0') 
	public String getFiller() {
		return filler;
	}
	public void setFiller(String filler) {
		this.filler = filler;
	}
	@Override
	public String toString() {
		return "PoolMonthlyThirdPartyOriginationType [thirdPartyOriginationUpbPercent="
				+ thirdPartyOriginationUpbPercent
				+ ", brokerLoanCount="
				+ brokerLoanCount
				+ ", brokerUpb="
				+ brokerUpb
				+ ", brokerUpbPercent="
				+ brokerUpbPercent
				+ ", correspondentLoanCount="
				+ correspondentLoanCount
				+ ", correspondentUpb="
				+ correspondentUpb
				+ ", correspondentUpbPercent="
				+ correspondentUpbPercent
				+ ", retailLoanCount="
				+ retailLoanCount
				+ ", retailUpb="
				+ retailUpb + ", retailUpbPercent=" + retailUpbPercent + "]";
	}

}
