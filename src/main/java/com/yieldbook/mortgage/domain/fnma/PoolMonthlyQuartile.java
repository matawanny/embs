package com.yieldbook.mortgage.domain.fnma;

import java.math.BigDecimal;

import com.ancientprogramming.fixedformat4j.annotation.Align;
import com.ancientprogramming.fixedformat4j.annotation.Field;
import com.ancientprogramming.fixedformat4j.annotation.FixedFormatDecimal;
import com.ancientprogramming.fixedformat4j.annotation.Record;
@Record
public class PoolMonthlyQuartile {
	private Integer recordType;
	private String poolNumber;
	private Integer CreditScoreWa;
	private Integer CreditScoreMinimum;
	private Integer CreditScoreQuartile1;
	private Integer CreditScoreQuartile2;
	private Integer CreditScoreQuartile3;
	private Integer CreditScoreQuartile4;
	private BigDecimal CreditScoreQuartilePercentMissing;
	private Integer LtvWa;
	private Integer LtvMinimum;
	private Integer LtvQuartile1;
	private Integer LtvQuartile2;
	private Integer LtvQuartile3;
	private Integer LtvQuartile4;
	private BigDecimal LtvPercentMissing;
	private Long LtvLoanCountMissing;
	private BigDecimal OneUnitUpb;
	private BigDecimal OneUnitPercent;
	private Long OneUnitLoanCount;
	private BigDecimal Two_FourUnitUpb;
	private BigDecimal Tow_FourUnitPercent;
	private Long Two_FourUnitLoanCount; 
	private BigDecimal PurchaseUpb;
	private BigDecimal PurchasePercent;
	private Long PurchaseLoanCount;
	private BigDecimal RefinanceUpb;
	private BigDecimal RefinancePercent;
	private Long RefinanceLoanCount;	
	private BigDecimal PrincipalUpb;
	private BigDecimal PrincipalPercent;
	private Long PrincipalLoanCount;		
	private BigDecimal SecondUpb;
	private BigDecimal SecondPercent;
	private Long SecondLoanCount;		
	private BigDecimal InvestmentUpb;
	private BigDecimal InvestmentPercent;
	private Long InvestmentLoanCount;		
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
	@Field(offset=8, length=4,align=Align.RIGHT, paddingChar='0')  
	public Integer getCreditScoreWa() {
		return CreditScoreWa;
	}
	public void setCreditScoreWa(Integer creditScoreWa) {
		CreditScoreWa = creditScoreWa;
	}
	@Field(offset=12, length=4,align=Align.RIGHT, paddingChar='0')  
	public Integer getCreditScoreMinimum() {
		return CreditScoreMinimum;
	}
	public void setCreditScoreMinimum(Integer creditScoreMinimum) {
		CreditScoreMinimum = creditScoreMinimum;
	}
	@Field(offset=16, length=4,align=Align.RIGHT, paddingChar='0')  
	public Integer getCreditScoreQuartile1() {
		return CreditScoreQuartile1;
	}
	public void setCreditScoreQuartile1(Integer creditScoreQuartile1) {
		CreditScoreQuartile1 = creditScoreQuartile1;
	}
	@Field(offset=20, length=4,align=Align.RIGHT, paddingChar='0')  
	public Integer getCreditScoreQuartile2() {
		return CreditScoreQuartile2;
	}
	public void setCreditScoreQuartile2(Integer creditScoreQuartile2) {
		CreditScoreQuartile2 = creditScoreQuartile2;
	}
	@Field(offset=24, length=4,align=Align.RIGHT, paddingChar='0')  
	public Integer getCreditScoreQuartile3() {
		return CreditScoreQuartile3;
	}
	public void setCreditScoreQuartile3(Integer creditScoreQuartile3) {
		CreditScoreQuartile3 = creditScoreQuartile3;
	}
	@Field(offset=28, length=4,align=Align.RIGHT, paddingChar='0')  
	public Integer getCreditScoreQuartile4() {
		return CreditScoreQuartile4;
	}
	public void setCreditScoreQuartile4(Integer creditScoreQuartile4) {
		CreditScoreQuartile4 = creditScoreQuartile4;
	}
	@Field(offset=32, length=5,align=Align.RIGHT, paddingChar='0')  
	@FixedFormatDecimal (decimals=2, useDecimalDelimiter=false)
	public BigDecimal getCreditScoreQuartilePercentMissing() {
		return CreditScoreQuartilePercentMissing;
	}
	public void setCreditScoreQuartilePercentMissing(
			BigDecimal creditScoreQuartilePercentMissing) {
		CreditScoreQuartilePercentMissing = creditScoreQuartilePercentMissing;
	}
	@Field(offset=37, length=3,align=Align.RIGHT, paddingChar='0') 
	public Integer getLtvWa() {
		return LtvWa;
	}
	public void setLtvWa(Integer ltvWa) {
		LtvWa = ltvWa;
	}
	@Field(offset=40, length=3,align=Align.RIGHT, paddingChar='0') 	
	public Integer getLtvMinimum() {
		return LtvMinimum;
	}
	public void setLtvMinimum(Integer ltvMinimum) {
		LtvMinimum = ltvMinimum;
	}
	@Field(offset=43, length=3,align=Align.RIGHT, paddingChar='0') 	
	public Integer getLtvQuartile1() {
		return LtvQuartile1;
	}
	public void setLtvQuartile1(Integer ltvQuartile1) {
		LtvQuartile1 = ltvQuartile1;
	}
	@Field(offset=46, length=3,align=Align.RIGHT, paddingChar='0') 	
	public Integer getLtvQuartile2() {
		return LtvQuartile2;
	}
	public void setLtvQuartile2(Integer ltvQuartile2) {
		LtvQuartile2 = ltvQuartile2;
	}
	@Field(offset=49, length=3,align=Align.RIGHT, paddingChar='0') 
	public Integer getLtvQuartile3() {
		return LtvQuartile3;
	}
	public void setLtvQuartile3(Integer ltvQuartile3) {
		LtvQuartile3 = ltvQuartile3;
	}
	@Field(offset=52, length=3,align=Align.RIGHT, paddingChar='0')
	public Integer getLtvQuartile4() {
		return LtvQuartile4;
	}
	public void setLtvQuartile4(Integer ltvQuartile4) {
		LtvQuartile4 = ltvQuartile4;
	}
	@Field(offset=55, length=5,align=Align.RIGHT, paddingChar='0')
	@FixedFormatDecimal (decimals=2, useDecimalDelimiter=false)	
	public BigDecimal getLtvPercentMissing() {
		return LtvPercentMissing;
	}
	public void setLtvPercentMissing(BigDecimal ltvPercentMissing) {
		LtvPercentMissing = ltvPercentMissing;
	}
	@Field(offset=60, length=8,align=Align.RIGHT, paddingChar='0')	
	public Long getLtvLoanCountMissing() {
		return LtvLoanCountMissing;
	}
	
	public void setLtvLoanCountMissing(Long ltvLoanCountMissing) {
		LtvLoanCountMissing = ltvLoanCountMissing;
	}
	@Field(offset=68, length=15,align=Align.RIGHT, paddingChar='0')
	@FixedFormatDecimal (decimals=2, useDecimalDelimiter=false)
	public BigDecimal getOneUnitUpb() {
		return OneUnitUpb;
	}
	public void setOneUnitUpb(BigDecimal oneUnitUpb) {
		OneUnitUpb = oneUnitUpb;
	}
	@Field(offset=83, length=5,align=Align.RIGHT, paddingChar='0')
	@FixedFormatDecimal (decimals=2, useDecimalDelimiter=false)	
	public BigDecimal getOneUnitPercent() {
		return OneUnitPercent;
	}
	public void setOneUnitPercent(BigDecimal oneUnitPercent) {
		OneUnitPercent = oneUnitPercent;
	}
	@Field(offset=88, length=8,align=Align.RIGHT, paddingChar='0')	
	public Long getOneUnitLoanCount() {
		return OneUnitLoanCount;
	}
	public void setOneUnitLoanCount(Long oneUnitLoanCount) {
		OneUnitLoanCount = oneUnitLoanCount;
	}
	@Field(offset=96, length=15,align=Align.RIGHT, paddingChar='0')
	@FixedFormatDecimal (decimals=2, useDecimalDelimiter=false)	
	public BigDecimal getTwo_FourUnitUpb() {
		return Two_FourUnitUpb;
	}
	public void setTwo_FourUnitUpb(BigDecimal two_FourUnitUpb) {
		Two_FourUnitUpb = two_FourUnitUpb;
	}
	@Field(offset=111, length=5,align=Align.RIGHT, paddingChar='0')
	@FixedFormatDecimal (decimals=2, useDecimalDelimiter=false)	
	public BigDecimal getTow_FourUnitPercent() {
		return Tow_FourUnitPercent;
	}
	public void setTow_FourUnitPercent(BigDecimal tow_FourUnitPercent) {
		Tow_FourUnitPercent = tow_FourUnitPercent;
	}
	@Field(offset=116, length=8, align=Align.RIGHT, paddingChar='0')	
	public Long getTwo_FourUnitLoanCount() {
		return Two_FourUnitLoanCount;
	}
	public void setTwo_FourUnitLoanCount(Long two_FourUnitLoanCount) {
		Two_FourUnitLoanCount = two_FourUnitLoanCount;
	}
	@Field(offset=124, length=15,align=Align.RIGHT, paddingChar='0')
	@FixedFormatDecimal (decimals=2, useDecimalDelimiter=false)		
	public BigDecimal getPurchaseUpb() {
		return PurchaseUpb;
	}

	public void setPurchaseUpb(BigDecimal purchaseUpb) {
		PurchaseUpb = purchaseUpb;
	}
	@Field(offset=139, length=5,align=Align.RIGHT, paddingChar='0')
	@FixedFormatDecimal (decimals=2, useDecimalDelimiter=false)			
	public BigDecimal getPurchasePercent() {
		return PurchasePercent;
	}
	public void setPurchasePercent(BigDecimal purchasePercent) {
		PurchasePercent = purchasePercent;
	}
	@Field(offset=144, length=8,align=Align.RIGHT, paddingChar='0')
	public Long getPurchaseLoanCount() {
		return PurchaseLoanCount;
	}
	public void setPurchaseLoanCount(Long purchaseLoanCount) {
		PurchaseLoanCount = purchaseLoanCount;
	}
	@Field(offset=152, length=15,align=Align.RIGHT, paddingChar='0')
	@FixedFormatDecimal (decimals=2, useDecimalDelimiter=false)		
	public BigDecimal getRefinanceUpb() {
		return RefinanceUpb;
	}
	public void setRefinanceUpb(BigDecimal refinanceUpb) {
		RefinanceUpb = refinanceUpb;
	}
	@Field(offset=167, length=5,align=Align.RIGHT, paddingChar='0')
	@FixedFormatDecimal (decimals=2, useDecimalDelimiter=false)			
	public BigDecimal getRefinancePercent() {
		return RefinancePercent;
	}
	public void setRefinancePercent(BigDecimal refinancePercent) {
		RefinancePercent = refinancePercent;
	}
	@Field(offset=172, length=8,align=Align.RIGHT, paddingChar='0')	
	public Long getRefinanceLoanCount() {
		return RefinanceLoanCount;
	}
	public void setRefinanceLoanCount(Long refinanceLoanCount) {
		RefinanceLoanCount = refinanceLoanCount;
	}
	@Field(offset=180, length=15,align=Align.RIGHT, paddingChar='0')
	@FixedFormatDecimal (decimals=2, useDecimalDelimiter=false)		
	public BigDecimal getPrincipalUpb() {
		return PrincipalUpb;
	}
	public void setPrincipalUpb(BigDecimal principalUpb) {
		PrincipalUpb = principalUpb;
	}
	@Field(offset=195, length=5,align=Align.RIGHT, paddingChar='0')
	@FixedFormatDecimal (decimals=2, useDecimalDelimiter=false)		
	public BigDecimal getPrincipalPercent() {
		return PrincipalPercent;
	}
	public void setPrincipalPercent(BigDecimal principalPercent) {
		PrincipalPercent = principalPercent;
	}
	@Field(offset=200, length=8,align=Align.RIGHT, paddingChar='0')
	public Long getPrincipalLoanCount() {
		return PrincipalLoanCount;
	}
	public void setPrincipalLoanCount(Long principalLoanCount) {
		PrincipalLoanCount = principalLoanCount;
	}
	@Field(offset=208, length=15,align=Align.RIGHT, paddingChar='0')
	@FixedFormatDecimal (decimals=2, useDecimalDelimiter=false)		
	public BigDecimal getSecondUpb() {
		return SecondUpb;
	}
	public void setSecondUpb(BigDecimal secondUpb) {
		SecondUpb = secondUpb;
	}
	@Field(offset=223, length=5,align=Align.RIGHT, paddingChar='0')
	@FixedFormatDecimal (decimals=2, useDecimalDelimiter=false)		
	public BigDecimal getSecondPercent() {
		return SecondPercent;
	}
	public void setSecondPercent(BigDecimal secondPercent) {
		SecondPercent = secondPercent;
	}
	@Field(offset=228, length=8,align=Align.RIGHT, paddingChar='0')	
	public Long getSecondLoanCount() {
		return SecondLoanCount;
	}
	public void setSecondLoanCount(Long secondLoanCount) {
		SecondLoanCount = secondLoanCount;
	}
	@Field(offset=236, length=15,align=Align.RIGHT, paddingChar='0')
	@FixedFormatDecimal (decimals=2, useDecimalDelimiter=false)		
	public BigDecimal getInvestmentUpb() {
		return InvestmentUpb;
	}
	public void setInvestmentUpb(BigDecimal investmentUpb) {
		InvestmentUpb = investmentUpb;
	}
	@Field(offset=251, length=5,align=Align.RIGHT, paddingChar='0')
	@FixedFormatDecimal (decimals=2, useDecimalDelimiter=false)	
	public BigDecimal getInvestmentPercent() {
		return InvestmentPercent;
	}
	public void setInvestmentPercent(BigDecimal investmentPercent) {
		InvestmentPercent = investmentPercent;
	}
	@Field(offset=256, length=8,align=Align.RIGHT, paddingChar='0')
	public Long getInvestmentLoanCount() {
		return InvestmentLoanCount;
	}
	public void setInvestmentLoanCount(Long investmentLoanCount) {
		InvestmentLoanCount = investmentLoanCount;
	}
	@Field(offset=264, length=37,align=Align.RIGHT, paddingChar='0')
	public String getFiller() {
		return filler;
	}
	public void setFiller(String filler) {
		this.filler = filler;
	}
	@Override
	public String toString() {
		return "PoolMonthlyQuartile [CreditScoreWa=" + CreditScoreWa
				+ ", CreditScoreMinimum=" + CreditScoreMinimum
				+ ", CreditScoreQuartile1=" + CreditScoreQuartile1
				+ ", CreditScoreQuartile2=" + CreditScoreQuartile2
				+ ", CreditScoreQuartile3=" + CreditScoreQuartile3
				+ ", CreditScoreQuartile4=" + CreditScoreQuartile4
				+ ", CreditScoreQuartilePercentMissing="
				+ CreditScoreQuartilePercentMissing + ", LtvWa=" + LtvWa
				+ ", LtvMinimum=" + LtvMinimum + ", LtvQuartile1="
				+ LtvQuartile1 + ", LtvQuartile2=" + LtvQuartile2
				+ ", LtvQuartile3=" + LtvQuartile3 + ", LtvQuartile4="
				+ LtvQuartile4 + ", LtvPercentMissing=" + LtvPercentMissing
				+ ", LtvLoanCountMissing=" + LtvLoanCountMissing
				+ ", OneUnitUpb=" + OneUnitUpb + ", OneUnitPercent="
				+ OneUnitPercent + ", OneUnitLoanCount=" + OneUnitLoanCount
				+ ", Two_FourUnitUpb=" + Two_FourUnitUpb
				+ ", Tow_FourUnitPercent=" + Tow_FourUnitPercent
				+ ", Two_FourUnitLoanCount=" + Two_FourUnitLoanCount
				+ ", PurchaseUpb=" + PurchaseUpb + ", PurchasePercent="
				+ PurchasePercent + ", PurchaseLoanCount=" + PurchaseLoanCount
				+ ", RefinanceUpb=" + RefinanceUpb + ", RefinancePercent="
				+ RefinancePercent + ", RefinanceLoanCount="
				+ RefinanceLoanCount + ", PrincipalUpb=" + PrincipalUpb
				+ ", PrincipalPercent=" + PrincipalPercent
				+ ", PrincipalLoanCount=" + PrincipalLoanCount + ", SecondUpb="
				+ SecondUpb + ", SecondPercent=" + SecondPercent
				+ ", SecondLoanCount=" + SecondLoanCount + ", InvestmentUpb="
				+ InvestmentUpb + ", InvestmentPercent=" + InvestmentPercent
				+ ", InvestmentLoanCount=" + InvestmentLoanCount + "]";
	}

	
}
