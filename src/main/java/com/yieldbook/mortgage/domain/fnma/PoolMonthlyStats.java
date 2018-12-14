package com.yieldbook.mortgage.domain.fnma;

import java.math.BigDecimal;

import com.ancientprogramming.fixedformat4j.annotation.Align;
import com.ancientprogramming.fixedformat4j.annotation.Field;
import com.ancientprogramming.fixedformat4j.annotation.FixedFormatDecimal;
import com.ancientprogramming.fixedformat4j.annotation.Record;

@Record
public class PoolMonthlyStats {
	private String cusip;
	private String poolPrefix;
	private String poolNumber;
	private String poolType;
	private BigDecimal originalBalance;
	private String currentMonth;
	private Integer currentYear;
    private BigDecimal currentBalance;
    private BigDecimal currentFactor;
    private BigDecimal passThroughRate;
    private Integer issueMM;
    private Integer issueDD;
    private Integer issueYY;
    private Integer maturityMM;
    private Integer maturityDD;
    private Integer maturityYY;
    private Integer originalWAMaturity;
    private String sellerName;
    private String sellerStreet;
    private String sellerCity;
    private String sellerState;
    private String sellerZip;
    private BigDecimal originalWACoupon;
    private String sdSecurityType;
    private String sdInterestRate;
    private String sdPoolPrefix;
    private String sdPoolNumber;
    private BigDecimal currentWACoupon;
    private Integer currentWAMaturity;
    private String filler;	
    
	@Field(offset=1, length=9)    
	public String getCusip() {
		return cusip;
	}
	public void setCusip(String cusip) {
		this.cusip = cusip;
	}
	@Field(offset=10, length=3,align=Align.RIGHT, paddingChar=' ')
	public String getPoolPrefix() {
		return poolPrefix;
	}
	public void setPoolPrefix(String poolPrefix) {
		this.poolPrefix = poolPrefix;
	}
	@Field(offset=13, length=6, align=Align.RIGHT, paddingChar=' ')
	public String getPoolNumber() {
		return poolNumber;
	}
	public void setPoolNumber(String poolNumber) {
		this.poolNumber = poolNumber;
	}
	@Field(offset=19, length=2, align=Align.RIGHT, paddingChar=' ')
	public String getPoolType() {
		return poolType;
	}
	public void setPoolType(String poolType) {
		this.poolType = poolType;
	}
	@Field(offset=21, length=14,align=Align.RIGHT, paddingChar='0')
	@FixedFormatDecimal (decimals=2, useDecimalDelimiter=false)
	public BigDecimal getOriginalBalance() {
		return originalBalance;
	}
	public void setOriginalBalance(BigDecimal originalBalance) {
		this.originalBalance = originalBalance;
	}

	@Field(offset=35, length=3)
	public String getCurrentMonth() {
		return currentMonth;
	}
	public void setCurrentMonth(String currentMonth) {
		this.currentMonth = currentMonth;
	}
	@Field(offset=38, length=2)
	public Integer getCurrentYear() {
		return currentYear;
	}
	public void setCurrentYear(Integer currentYear) {
		this.currentYear = currentYear;
	}
	@Field(offset=40, length=14, align=Align.RIGHT, paddingChar='0')
	@FixedFormatDecimal (decimals=2, useDecimalDelimiter=false)	
	public BigDecimal getCurrentBalance() {
		return currentBalance;
	}
	public void setCurrentBalance(BigDecimal currentBalance) {
		this.currentBalance = currentBalance;
	}
	@Field(offset=54, length=10)
	@FixedFormatDecimal (decimals=8, useDecimalDelimiter=true)
	public BigDecimal getCurrentFactor() {
		return currentFactor;
	}
	public void setCurrentFactor(BigDecimal currentFactor) {
		this.currentFactor = currentFactor;
	}
	@Field(offset=64, length=6)
	@FixedFormatDecimal (decimals=3, useDecimalDelimiter=true)	
	public BigDecimal getPassThroughRate() {
		return passThroughRate;
	}
	public void setPassThroughRate(BigDecimal passThroughRate) {
		this.passThroughRate = passThroughRate;
	}
	@Field(offset=70, length=2)
	public Integer getIssueMM() {
		return issueMM;
	}
	public void setIssueMM(Integer issueMM) {
		this.issueMM = issueMM;
	}
	@Field(offset=72, length=2)
	public Integer getIssueDD() {
		return issueDD;
	}
	public void setIssueDD(Integer issueDD) {
		this.issueDD = issueDD;
	}
	@Field(offset=74, length=2)
	public Integer getIssueYY() {
		return issueYY;
	}
	public void setIssueYY(Integer issueYY) {
		this.issueYY = issueYY;
	}
	
	@Field(offset=76, length=2)
	public Integer getMaturityMM() {
		return maturityMM;
	}
	public void setMaturityMM(Integer maturityMM) {
		this.maturityMM = maturityMM;
	}
	@Field(offset=78, length=2)	
	public Integer getMaturityDD() {
		return maturityDD;
	}
	public void setMaturityDD(Integer maturityDD) {
		this.maturityDD = maturityDD;
	}
	@Field(offset=80, length=2)
	public Integer getMaturityYY() {
		return maturityYY;
	}
	public void setMaturityYY(Integer maturityYY) {
		this.maturityYY = maturityYY;
	}
	@Field(offset=82, length=3)
	public Integer getOriginalWAMaturity() {
		return originalWAMaturity;
	}
	public void setOriginalWAMaturity(Integer originalWAMaturity) {
		this.originalWAMaturity = originalWAMaturity;
	}
	@Field(offset=85, length=40, align=Align.LEFT, paddingChar=' ')
	public String getSellerName() {
		return sellerName;
	}
	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}
	@Field(offset = 125, length = 30, align=Align.LEFT, paddingChar=' ')	
	public String getSellerStreet() {
		return sellerStreet;
	}
	public void setSellerStreet(String sellerStreet) {
		this.sellerStreet = sellerStreet;
	}
	@Field(offset = 155, length = 15, align=Align.LEFT, paddingChar=' ')
	public String getSellerCity() {
		return sellerCity;
	}
	public void setSellerCity(String sellerCity) {
		this.sellerCity = sellerCity;
	}
	@Field(offset = 170, length = 2, align=Align.LEFT, paddingChar=' ')		
	public String getSellerState() {
		return sellerState;
	}
	public void setSellerState(String sellerState) {
		this.sellerState = sellerState;
	}
	@Field(offset = 172, length = 5, align=Align.LEFT, paddingChar=' ')		
	public String getSellerZip() {
		return sellerZip;
	}
	public void setSellerZip(String sellerZip) {
		this.sellerZip = sellerZip;
	}
	@Field(offset=177, length=6)
	@FixedFormatDecimal (decimals=4, useDecimalDelimiter=false)	
	public BigDecimal getOriginalWACoupon() {
		return originalWACoupon;
	}
	public void setOriginalWACoupon(BigDecimal originalWACoupon) {
		this.originalWACoupon = originalWACoupon;
	}
	@Field(offset=183, length=5, align=Align.RIGHT, paddingChar=' ')		
	public String getSdSecurityType() {
		return sdSecurityType;
	}
	public void setSdSecurityType(String sdSecurityType) {
		this.sdSecurityType = sdSecurityType;
	}
	@Field(offset=188, length=6, align=Align.RIGHT, paddingChar='0')	
	public String getSdInterestRate() {
		return sdInterestRate;
	}
	public void setSdInterestRate(String sdInterestRate) {
		this.sdInterestRate = sdInterestRate;
	}
	@Field(offset=194, length=3, align=Align.RIGHT, paddingChar=' ')		
	public String getSdPoolPrefix() {
		return sdPoolPrefix;
	}
	public void setSdPoolPrefix(String sdPoolPrefix) {
		this.sdPoolPrefix = sdPoolPrefix;
	}
	@Field(offset=197, length=6, align=Align.RIGHT, paddingChar=' ')	
	public String getSdPoolNumber() {
		return sdPoolNumber;
	}
	public void setSdPoolNumber(String sdPoolNumber) {
		this.sdPoolNumber = sdPoolNumber;
	}
	@Field(offset=203, length=6,align=Align.RIGHT, paddingChar='0')
	@FixedFormatDecimal (decimals=4, useDecimalDelimiter=false)	
	public BigDecimal getCurrentWACoupon() {
		return currentWACoupon;
	}
	public void setCurrentWACoupon(BigDecimal currentWACoupon) {
		this.currentWACoupon = currentWACoupon;
	}
	@Field(offset=209, length=3,align=Align.RIGHT, paddingChar='0')
	public Integer getCurrentWAMaturity() {
		return currentWAMaturity;
	}
	public void setCurrentWAMaturity(Integer currentWAMaturity) {
		this.currentWAMaturity = currentWAMaturity;
	}
	@Field(offset=212, length=9)
	public String getFiller() {
		return filler;
	}
	public void setFiller(String filler) {
		this.filler = filler;
	}
	@Override
	public String toString() {
		return "PoolMonthlyStats [cusip=" + cusip + ", poolPrefix="
				+ poolPrefix + ", poolNumber=" + poolNumber + ", poolType="
				+ poolType + ", originalBalance=" + originalBalance
				+ ", currentMonth=" + currentMonth + ", currentYear="
				+ currentYear + ", currentBalance=" + currentBalance
				+ ", currentFactor=" + currentFactor + ", passThroughRate="
				+ passThroughRate + ", issueMM=" + issueMM + ", issueDD="
				+ issueDD + ", issueYY=" + issueYY + ", maturityMM="
				+ maturityMM + ", maturityDD=" + maturityDD + ", maturityYY="
				+ maturityYY + ", originalWAMaturity=" + originalWAMaturity
				+ ", sellerName=" + sellerName + ", sellerStreet="
				+ sellerStreet + ", sellerCity=" + sellerCity
				+ ", sellerState=" + sellerState + ", sellerZip=" + sellerZip
				+ ", originalWACoupon=" + originalWACoupon
				+ ", sdSecurityType=" + sdSecurityType + ", sdInterestRate="
				+ sdInterestRate + ", sdPoolPrefix=" + sdPoolPrefix
				+ ", sdPoolNumber=" + sdPoolNumber + ", currentWACoupon="
				+ currentWACoupon + ", currentWAMaturity=" + currentWAMaturity
				+ "]";
	}

}
