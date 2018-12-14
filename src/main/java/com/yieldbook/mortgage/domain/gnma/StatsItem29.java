package com.yieldbook.mortgage.domain.gnma;

import java.math.BigDecimal;

import com.ancientprogramming.fixedformat4j.annotation.Align;
import com.ancientprogramming.fixedformat4j.annotation.Field;
import com.ancientprogramming.fixedformat4j.annotation.FixedFormatDecimal;
import com.ancientprogramming.fixedformat4j.annotation.Record;

@Record
public class StatsItem29 {
	private BigDecimal upb; 
	private BigDecimal percentOfPoolUpb;
	private Integer numberOfLoans;
	private BigDecimal percentOfPoolLoansOfKind;
	
	@Field(offset=1, length=13, align=Align.RIGHT, paddingChar='0')
	@FixedFormatDecimal (decimals=2, useDecimalDelimiter=false)	
	public BigDecimal getUpb() {
		return upb;
	}
	public void setUpb(BigDecimal upb) {
		this.upb = upb;
	}
	@Field(offset=14, length=5, align=Align.RIGHT, paddingChar='0')
	@FixedFormatDecimal (decimals=2, useDecimalDelimiter=false)	
	public BigDecimal getPercentOfPoolUpb() {
		return percentOfPoolUpb;
	}
	public void setPercentOfPoolUpb(BigDecimal percentOfPoolUpb) {
		this.percentOfPoolUpb = percentOfPoolUpb;
	}
	@Field(offset=19, length=6, align=Align.RIGHT, paddingChar='0')	
	public Integer getNumberOfLoans() {
		return numberOfLoans;
	}
	public void setNumberOfLoans(Integer numberOfLoans) {
		this.numberOfLoans = numberOfLoans;
	}
	@Field(offset=25, length=5, align=Align.RIGHT, paddingChar='0')
	@FixedFormatDecimal (decimals=2, useDecimalDelimiter=false)	
	public BigDecimal getPercentOfPoolLoansOfKind() {
		return percentOfPoolLoansOfKind;
	}
	public void setPercentOfPoolLoansOfKind(BigDecimal percentOfPoolLoansOfKind) {
		this.percentOfPoolLoansOfKind = percentOfPoolLoansOfKind;
	}
	@Override
	public String toString() {
		return "StatsItem29 [upb=" + upb
				+ ", percentOfPoolUpb=" + percentOfPoolUpb + ", numberOfLoans="
				+ numberOfLoans + ", percentOfPoolLoansOfKind="
				+ percentOfPoolLoansOfKind + "]";
	}
	

}
