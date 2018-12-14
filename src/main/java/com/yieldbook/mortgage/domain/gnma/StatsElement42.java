package com.yieldbook.mortgage.domain.gnma;

import java.math.BigDecimal;

import com.ancientprogramming.fixedformat4j.annotation.Align;
import com.ancientprogramming.fixedformat4j.annotation.Field;
import com.ancientprogramming.fixedformat4j.annotation.FixedFormatDecimal;
import com.ancientprogramming.fixedformat4j.annotation.Record;

@Record
public class StatsElement42 {
	private BigDecimal poolUpb; 
	private BigDecimal upbAsAPercentOfTotalPool;
	private Integer numberOfLoans;
	private BigDecimal numberOfLoansAsAPercentOfTotalLoans;
	private BigDecimal originalLoanBalance;
	
	@Field(offset=1, length=13, align=Align.RIGHT, paddingChar='0')		
	@FixedFormatDecimal (decimals=2, useDecimalDelimiter=false)		
	public BigDecimal getPoolUpb() {
		return poolUpb;
	}
	public void setPoolUpb(BigDecimal poolUpb) {
		this.poolUpb = poolUpb;
	}
	@Field(offset=14, length=5, align=Align.RIGHT, paddingChar='0')		
	@FixedFormatDecimal (decimals=2, useDecimalDelimiter=false)		
	public BigDecimal getUpbAsAPercentOfTotalPool() {
		return upbAsAPercentOfTotalPool;
	}
	public void setUpbAsAPercentOfTotalPool(BigDecimal upbAsAPercentOfTotalPool) {
		this.upbAsAPercentOfTotalPool = upbAsAPercentOfTotalPool;
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
	public BigDecimal getNumberOfLoansAsAPercentOfTotalLoans() {
		return numberOfLoansAsAPercentOfTotalLoans;
	}
	public void setNumberOfLoansAsAPercentOfTotalLoans(
			BigDecimal numberOfLoansAsAPercentOfTotalLoans) {
		this.numberOfLoansAsAPercentOfTotalLoans = numberOfLoansAsAPercentOfTotalLoans;
	}
	@Field(offset=30, length=13, align=Align.RIGHT, paddingChar='0')		
	@FixedFormatDecimal (decimals=2, useDecimalDelimiter=false)		
	public BigDecimal getOriginalLoanBalance() {
		return originalLoanBalance;
	}
	public void setOriginalLoanBalance(BigDecimal originalLoanBalance) {
		this.originalLoanBalance = originalLoanBalance;
	}
	@Override
	public String toString() {
		return "StatsElement42 [poolUpb=" + poolUpb
				+ ", upbAsAPercentOfTotalPool=" + upbAsAPercentOfTotalPool
				+ ", numberOfLoans=" + numberOfLoans
				+ ", numberOfLoansAsAPercentOfTotalLoans="
				+ numberOfLoansAsAPercentOfTotalLoans
				+ ", originalLoanBalance=" + originalLoanBalance + "]";
	}

}
