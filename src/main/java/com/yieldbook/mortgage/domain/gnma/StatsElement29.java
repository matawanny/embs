package com.yieldbook.mortgage.domain.gnma;

import java.math.BigDecimal;

import com.ancientprogramming.fixedformat4j.annotation.Align;
import com.ancientprogramming.fixedformat4j.annotation.Field;
import com.ancientprogramming.fixedformat4j.annotation.FixedFormatDecimal;
import com.ancientprogramming.fixedformat4j.annotation.Record;

@Record
public class StatsElement29 {
	private Integer element; 
	private Integer numberOfLoans;
	private BigDecimal upbOfLoans;
	private BigDecimal percentOfPoolUpb;

	@Field(offset=1, length=5, align=Align.RIGHT, paddingChar='0')	
	public Integer getElement() {
		return element;
	}
	public void setElement(Integer element) {
		this.element = element;
	}
	@Field(offset=6, length=6, align=Align.RIGHT, paddingChar='0')	
	public Integer getNumberOfLoans() {
		return numberOfLoans;
	}
	public void setNumberOfLoans(Integer numberOfLoans) {
		this.numberOfLoans = numberOfLoans;
	}
	@Field(offset=12, length=13, align=Align.RIGHT, paddingChar='0')
	@FixedFormatDecimal (decimals=2, useDecimalDelimiter=false)	
	public BigDecimal getUptOfLoans() {
		return upbOfLoans;
	}
	public void setUptOfLoans(BigDecimal upbOfLoans) {
		this.upbOfLoans = upbOfLoans;
	}
	@Field(offset=25, length=5, align=Align.RIGHT, paddingChar='0')
	@FixedFormatDecimal (decimals=2, useDecimalDelimiter=false)	
	public BigDecimal getPercentOfPoolUpb() {
		return percentOfPoolUpb;
	}
	public void setPercentOfPoolUpb(BigDecimal percentOfPoolUpb) {
		this.percentOfPoolUpb = percentOfPoolUpb;
	}
	@Override
	public String toString() {
		return "StatsElement29 [element=" + element + ", numberOfLoans="
				+ numberOfLoans + ", uptOfLoans=" + upbOfLoans
				+ ", percentOfPoolUpb=" + percentOfPoolUpb + "]";
	}
	

}
