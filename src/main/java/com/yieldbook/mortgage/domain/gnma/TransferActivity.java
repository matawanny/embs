package com.yieldbook.mortgage.domain.gnma;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ancientprogramming.fixedformat4j.annotation.Align;
import com.ancientprogramming.fixedformat4j.annotation.Field;
import com.ancientprogramming.fixedformat4j.annotation.FixedFormatDecimal;
import com.ancientprogramming.fixedformat4j.annotation.FixedFormatPattern;
import com.ancientprogramming.fixedformat4j.annotation.Record;

@Record
public class TransferActivity {
	private String cusip;
	private String poolNumber;
	private String poolIndicator;
	private String poolType;
	private String recrodType;
	private Date poolIssueDate;
    private Integer transferType;
    private Integer sellingIssuerNumber;
    private Integer buyingIssuerNumber;
    private Integer numberOfLoansTransferred;
    private BigDecimal upbOfLoansTransferred;
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
	@Field(offset=20, length=8, align=Align.RIGHT, paddingChar='9')
	@FixedFormatPattern("yyyyMMdd")
	public Date getPoolIssueDate() {
		return poolIssueDate;
	}
	public void setPoolIssueDate(Date poolIssueDate) {
		this.poolIssueDate = poolIssueDate;
	}
	@Field(offset=28, length=1)
	public Integer getTransferType() {
		return transferType;
	}
	public void setTransferType(Integer transferType) {
		this.transferType = transferType;
	}
	@Field(offset=29, length=4, align=Align.RIGHT, paddingChar='0')
	public Integer getSellingIssuerNumber() {
		return sellingIssuerNumber;
	}
	public void setSellingIssuerNumber(Integer sellingIssuerNumber) {
		this.sellingIssuerNumber = sellingIssuerNumber;
	}
	@Field(offset=33, length=4, align=Align.RIGHT, paddingChar='0')
	public Integer getBuyingIssuerNumber() {
		return buyingIssuerNumber;
	}
	public void setBuyingIssuerNumber(Integer buyingIssuerNumber) {
		this.buyingIssuerNumber = buyingIssuerNumber;
	}
	@Field(offset=37, length=6, align=Align.RIGHT, paddingChar='0')	
	public Integer getNumberOfLoansTransferred() {
		return numberOfLoansTransferred;
	}
	public void setNumberOfLoansTransferred(Integer numberOfLoansTransferred) {
		this.numberOfLoansTransferred = numberOfLoansTransferred;
	}
	@Field(offset=43, length=13, align=Align.RIGHT, paddingChar='0')
	@FixedFormatDecimal (decimals=2, useDecimalDelimiter=false)	
	public BigDecimal getUpbOfLoansTransferred() {
		return upbOfLoansTransferred;
	}
	public void setUpbOfLoansTransferred(BigDecimal upbOfLoansTransferred) {
		this.upbOfLoansTransferred = upbOfLoansTransferred;
	}
	@Field(offset=56, length=6)
	public Integer getAsOfDate() {
		return asOfDate;
	}
	public void setAsOfDate(Integer asOfDate) {
		this.asOfDate = asOfDate;
	}
	@Override
	public String toString() {
		return "TransferActivity [poolIssueDate=" + poolIssueDate
				+ ", transferType=" + transferType + ", sellingIssuerNumber="
				+ sellingIssuerNumber + ", buyingIssuerNumber="
				+ buyingIssuerNumber + ", numberOfLoansTransferred="
				+ numberOfLoansTransferred + ", upbOfLoansTransferred="
				+ upbOfLoansTransferred + ", asOfDate=" + asOfDate + "]";
	}

}
