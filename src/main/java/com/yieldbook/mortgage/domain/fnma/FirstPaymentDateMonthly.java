package com.yieldbook.mortgage.domain.fnma;

import static com.yieldbook.mortgage.utility.YieldBookUtilities.convertStringToBigDecimal;

import java.math.BigDecimal;

import com.ancientprogramming.fixedformat4j.annotation.Align;
import com.ancientprogramming.fixedformat4j.annotation.Field;
import com.ancientprogramming.fixedformat4j.annotation.FixedFormatDecimal;
import com.ancientprogramming.fixedformat4j.annotation.Record;

@Record
public class FirstPaymentDateMonthly {
	private String poolNumber;
	private String poolPrefix;
	private Integer recordType;
	private Integer firstPaymentDate;
	private String originalNoteRate;
	private BigDecimal percentSecurityBalance;
	private BigDecimal loanSurvivalRatio;
	private String filler;
	
	@Field(offset=1, length=6,align=Align.RIGHT, paddingChar='0')  
	public String getPoolNumber() {
		return poolNumber;
	}
	public void setPoolNumber(String poolNumber) {
		this.poolNumber = poolNumber;
	}
	@Field(offset=7, length=3, align=Align.RIGHT, paddingChar=' ')  
	public String getPoolPrefix() {
		return poolPrefix;
	}
	public void setPoolPrefix(String poolPrefix) {
		this.poolPrefix = poolPrefix;
	}
	@Field(offset=10, length=1)  
	public Integer getRecordType() {
		return recordType;
	}
	public void setRecordType(Integer recordType) {
		this.recordType = recordType;
	}
	@Field(offset=11, length=8)
	public Integer getFirstPaymentDate() {
		return firstPaymentDate;
	}
	public void setFirstPaymentDate(Integer firstPaymentDate) {
		this.firstPaymentDate = firstPaymentDate;
	}

	@Field(offset=19, length=7, align=Align.LEFT, paddingChar='{')
	//@FixedFormatDecimal (decimals=4, useDecimalDelimiter=false)
	public String getOriginalNoteRate() {
		return originalNoteRate;
	}
	
	public BigDecimal getOriginalNoteRate1() {
		return convertStringToBigDecimal(originalNoteRate,4);
	}
	
	public void setOriginalNoteRate(String originalNoteRate) {
		this.originalNoteRate = originalNoteRate;
	}
	
	@Field(offset=26, length=7, align=Align.LEFT, paddingChar='{')
	@FixedFormatDecimal (decimals=4, useDecimalDelimiter=false)
	public BigDecimal getPercentSecurityBalance() {
		return percentSecurityBalance;
	}
	public void setPercentSecurityBalance(BigDecimal percentSecurityBalance) {
		this.percentSecurityBalance = percentSecurityBalance;
	}
	
	@Field(offset=33, length=7, align=Align.LEFT, paddingChar='{')
	@FixedFormatDecimal (decimals=4, useDecimalDelimiter=false)
	public BigDecimal getLoanSurvivalRatio() {
		return loanSurvivalRatio;
	}
	public void setLoanSurvivalRatio(BigDecimal loanSurvivalRatio) {
		this.loanSurvivalRatio = loanSurvivalRatio;
	}

	@Field(offset=40, length=361)
	public String getFiller() {
		return filler;
	}
	public void setFiller(String filler) {
		this.filler = filler;
	}
	
	@Override
	public String toString() {
		return "FirstPaymentDateMonthly [poolNumber=" + poolNumber
				+ ", poolPrefix=" + poolPrefix + ", recordType=" + recordType
				+ ", firstPaymentDate=" + firstPaymentDate
				+ ", originalNoteRate=" + originalNoteRate
				+ ", originalNoteRate1=" + getOriginalNoteRate1()
				+ ", percentSecurityBalance=" + percentSecurityBalance
				+ ", loanSurvivalRatio=" + loanSurvivalRatio + ", filler="
				+ filler + "]";
	}
	
}
