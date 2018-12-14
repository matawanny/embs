package com.yieldbook.mortgage.domain.gnma;

import java.math.BigDecimal;

import com.ancientprogramming.fixedformat4j.annotation.Align;
import com.ancientprogramming.fixedformat4j.annotation.Field;
import com.ancientprogramming.fixedformat4j.annotation.FixedFormatDecimal;
import com.ancientprogramming.fixedformat4j.annotation.Record;

@Record
public class StatsElement24{
	private Integer numberOfLoans;
	private BigDecimal upbOfLoans;
	private BigDecimal percentOfPoolUpb;

	@Field(offset=1, length=6, align=Align.RIGHT, paddingChar='0')	
	public Integer getNumberOfLoans() {
		return numberOfLoans;
	}
	public void setNumberOfLoans(Integer numberOfLoans) {
		this.numberOfLoans = numberOfLoans;
	}
	@Field(offset=7, length=13, align=Align.RIGHT, paddingChar='0')
	@FixedFormatDecimal (decimals=2, useDecimalDelimiter=false)	
	public BigDecimal getUpbOfLoans() {
		return upbOfLoans;
	}
	public void setUpbOfLoans(BigDecimal upbOfLoans) {
		this.upbOfLoans = upbOfLoans;
	}
	@Field(offset=20, length=5, align=Align.RIGHT, paddingChar='0')
	@FixedFormatDecimal (decimals=2, useDecimalDelimiter=false)	
	public BigDecimal getPercentOfPoolUpb() {
		return percentOfPoolUpb;
	}
	public void setPercentOfPoolUpb(BigDecimal percentOfPoolUpb) {
		this.percentOfPoolUpb = percentOfPoolUpb;
	}
	@Override
	public String toString() {
		return "[numberOfLoans=" + numberOfLoans + ", uptOfLoans="
				+ upbOfLoans + ", percentOfPoolUpb=" + percentOfPoolUpb + "]";
	}
	
	private boolean isNull(){
		if(numberOfLoans==0
				&&BigDecimal.ZERO.setScale(2).equals(upbOfLoans)
				&&BigDecimal.ZERO.setScale(2).equals(percentOfPoolUpb))
			return true;
		else
			return false;
	}
	

}
