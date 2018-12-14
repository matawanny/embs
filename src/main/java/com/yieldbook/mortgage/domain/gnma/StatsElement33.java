package com.yieldbook.mortgage.domain.gnma;

import java.math.BigDecimal;

import com.ancientprogramming.fixedformat4j.annotation.Align;
import com.ancientprogramming.fixedformat4j.annotation.Field;
import com.ancientprogramming.fixedformat4j.annotation.FixedFormatDecimal;
import com.ancientprogramming.fixedformat4j.annotation.Record;

@Record
public class StatsElement33 {
	private String entityValue; 
	private BigDecimal upb;
	private BigDecimal percentOfUpb;
	private Integer numberOfLoans;
	private BigDecimal percentOfLoans;

	@Field(offset=1, length=4, align=Align.RIGHT, paddingChar='0')	
	public String getEntityValue() {
		return entityValue;
	}
	public void setEntityValue(String entityValue) {
		this.entityValue = entityValue;
	}
	@Field(offset=5, length=13, align=Align.RIGHT, paddingChar='0')		
	@FixedFormatDecimal (decimals=2, useDecimalDelimiter=false)		
	public BigDecimal getUpb() {
		return upb;
	}
	public void setUpb(BigDecimal upb) {
		this.upb = upb;
	}
	@Field(offset=18, length=5, align=Align.RIGHT, paddingChar='0')		
	@FixedFormatDecimal (decimals=2, useDecimalDelimiter=false)		
	public BigDecimal getPercentOfUpb() {
		return percentOfUpb;
	}
	public void setPercentOfUpb(BigDecimal percentOfUpb) {
		this.percentOfUpb = percentOfUpb;
	}
	@Field(offset=23, length=6, align=Align.RIGHT, paddingChar='0')	
	public Integer getNumberOfLoans() {
		return numberOfLoans;
	}
	public void setNumberOfLoans(Integer numberOfLoans) {
		this.numberOfLoans = numberOfLoans;
	}
	@Field(offset=29, length=5, align=Align.RIGHT, paddingChar='0')		
	@FixedFormatDecimal (decimals=2, useDecimalDelimiter=false)			
	public BigDecimal getPercentOfLoans() {
		return percentOfLoans;
	}
	public void setPercentOfLoans(BigDecimal percentOfLoans) {
		this.percentOfLoans = percentOfLoans;
	}
	@Override
	public String toString() {
		return "StatsElement33 [element=" + entityValue + ", upb=" + upb
				+ ", percentOfUpb=" + percentOfUpb + ", numberOfLoans="
				+ numberOfLoans + ", percentOfLoans=" + percentOfLoans + "]";
	}

}
