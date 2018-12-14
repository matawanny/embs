package com.yieldbook.mortgage.domain.fnma;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.ancientprogramming.fixedformat4j.annotation.Align;
import com.ancientprogramming.fixedformat4j.annotation.Field;
import com.ancientprogramming.fixedformat4j.annotation.FixedFormatDecimal;
import com.ancientprogramming.fixedformat4j.annotation.Record;

@Record
public class PoolMonthlyServicer {
	private Integer recordType;
	private String poolNumber;
	private String servicerName;
	private BigDecimal servicerUPB;
	private BigDecimal servicerPercent;
	private Integer loanCount;
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
	@Field(offset=8, length=40,align=Align.LEFT, paddingChar=' ')  
	public String getServicerName() {
		return servicerName;
	}
	public void setServicerName(String servicerName) {
		this.servicerName = servicerName;
	}
	@Field(offset=48, length=15,align=Align.RIGHT, paddingChar='0')  
	@FixedFormatDecimal (decimals=2, useDecimalDelimiter=false)		
	public BigDecimal getServicerUPB() {
		return servicerUPB;
	}
	public void setServicerUPB(BigDecimal servicerUPB) {
		this.servicerUPB = servicerUPB;
	}
	@Field(offset=63, length=5,align=Align.RIGHT, paddingChar='0')  
	@FixedFormatDecimal (decimals=2, useDecimalDelimiter=false)		
	public BigDecimal getServicerPercent() {
		return servicerPercent;
	}
	public void setServicerPercent(BigDecimal servicerPercent) {
		this.servicerPercent = servicerPercent;
	}
	@Field(offset=68, length=8,align=Align.RIGHT, paddingChar='0')  
	public Integer getLoanCount() {
		return loanCount;
	}
	public void setLoanCount(Integer loanCount) {
		this.loanCount = loanCount;
	}
	public String getFiller() {
		return filler;
	}
	public void setFiller(String filler) {
		this.filler = filler;
	}
	@Override
	public String toString() {
		return "PoolMonthlyServicer [servicerName=" + servicerName
				+ ", servicerUPB=" + servicerUPB + ", servicerPercent="
				+ servicerPercent + ", loanCount=" + loanCount + "]";
	}
	
}
