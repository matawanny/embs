package com.yieldbook.mortgage.domain.fnma;

import static com.yieldbook.mortgage.utility.YieldBookUtilities.convertStringToBigDecimal;
import static com.yieldbook.mortgage.utility.YieldBookUtilities.convertStringToInteger;
import static com.yieldbook.mortgage.utility.YieldBookUtilities.convertStringToLong;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.ancientprogramming.fixedformat4j.annotation.Align;
import com.ancientprogramming.fixedformat4j.annotation.Field;
import com.ancientprogramming.fixedformat4j.annotation.FixedFormatDecimal;
import com.ancientprogramming.fixedformat4j.annotation.Record;


@Record
public class ArmPoolMonthly {
	private String poolNumber;
	private String poolPrefix;
	private Integer recordType;
	private String filler1;
	private String cusipNumber;
	private Integer poolIssueDate;
	private Integer poolMaturityDate;
	private String lenderIssuer;
	private String lenderCity;
	private String state;
	private String subtype;
	private String transferType;
	private String passThroughRateStructure;
	private String convertibleFlag;
	private String deferredInterestAllowed;
	private String originalSecurityBalance;
	private String originalNumberOfLoans;
	private String originalWACoupon;
	private String originalWAMaturity;
	private Integer firstRateChangeDate;
	private Integer firstPaymentChangeDate;
	private BigDecimal issueAccrualRate;
	private Integer securityBalanceMlnm;
	private Integer securityBalanceYear;
	private Integer securityBalanceMonth;
	private String currentSecurityBalance;
	private BigDecimal currentTradingFactor;
	private BigDecimal currentWACoupon;
	private String currentWAMaturity;
	private BigDecimal currentDeferredInterest;
	private BigDecimal returnDeferredInterest;
	private String waMonthsToRateChange;
	private String waMBSMargin;
	private BigDecimal waLPTLifeCap;
	private String waLPTLifeLife;
	private String waLoanMargin;
	private BigDecimal waNegativeAmortizationLimit;
	private BigDecimal publishedPassThroughRate;
	private String rateDifferenceFlag;
	private String accrualRate;
	private String lookbackRateChange;
	private String lookbackPaymentChange;
	private String perAdjustmentRateCap;
	private String rateAdjustmentFrequency;
	private String paymentChangeFrequency;
	private Integer amortizationRecastFrequency;
	private String accrualRateRoundingMethodCode;
	private String paymentCap;
	private String minimumIndexMovement;
	private String maximumAccrualRate;
	private String minimumAccrualRate;
	private String filler2;
	
	protected List<NextRateChangeDateMonthly> nextRateChangeDates = new ArrayList<>();
	protected List<FirstPaymentDateMonthly> firstPaymentDates = new ArrayList<>();
	
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
	@Field(offset=11, length=22)  
	public String getFiller1() {
		return filler1;
	}
	public void setFiller1(String filler1) {
		this.filler1 = filler1;
	}
	@Field(offset=33, length=9)  
	public String getCusipNumber() {
		return cusipNumber;
	}
	public void setCusipNumber(String cusipNumber) {
		this.cusipNumber = cusipNumber;
	}
	@Field(offset=42, length=8)
	public Integer getPoolIssueDate() {
		return poolIssueDate;
	}
	public void setPoolIssueDate(Integer poolIssueDate) {
		this.poolIssueDate = poolIssueDate;
	}
	@Field(offset=50, length=8)
	public Integer getPoolMaturityDate() {
		return poolMaturityDate;
	}
	public void setPoolMaturityDate(Integer poolMaturityDate) {
		this.poolMaturityDate = poolMaturityDate;
	}
	@Field(offset=58, length=20)
	public String getLenderIssuer() {
		return lenderIssuer;
	}
	public void setLenderIssuer(String lenderIssuer) {
		this.lenderIssuer = lenderIssuer;
	}
	@Field(offset=78, length=15)
	public String getLenderCity() {
		return lenderCity;
	}
	public void setLenderCity(String lenderCity) {
		this.lenderCity = lenderCity;
	}
	@Field(offset=93, length=2)
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	@Field(offset=95, length=4)
	public String getSubtype() {
		return subtype;
	}
	public void setSubtype(String subtype) {
		this.subtype = subtype;
	}
	@Field(offset=99, length=1)
	public String getTransferType() {
		return transferType;
	}
	public void setTransferType(String transferType) {
		this.transferType = transferType;
	}
	@Field(offset=100, length=1)
	public String getPassThroughRateStructure() {
		return passThroughRateStructure;
	}
	public void setPassThroughRateStructure(String passThroughRateStructure) {
		this.passThroughRateStructure = passThroughRateStructure;
	}
	@Field(offset=101, length=1)
	public String getConvertibleFlag() {
		return convertibleFlag;
	}
	public void setConvertibleFlag(String convertibleFlag) {
		this.convertibleFlag = convertibleFlag;
	}
	@Field(offset=102, length=1)
	public String getDeferredInterestAllowed() {
		return deferredInterestAllowed;
	}
	public void setDeferredInterestAllowed(String deferredInterestAllowed) {
		this.deferredInterestAllowed = deferredInterestAllowed;
	}

	@Field(offset=103, length=15, align=Align.LEFT, paddingChar='{')
	public String getOriginalSecurityBalance(){
		//value is 00000262603592A
		return originalSecurityBalance;
	}
	
	public BigDecimal getOriginalSecurityBalance1(){
		//value is 00000262603592A
		return convertStringToBigDecimal(originalSecurityBalance,2);
	}	
	
	public void setOriginalSecurityBalance(String originalSecurityBalance) {
		this.originalSecurityBalance = originalSecurityBalance;
	}
	
	@Field(offset=118, length=11, align=Align.LEFT, paddingChar='{')
	public String getOriginalNumberOfLoans() {
		return originalNumberOfLoans;
	}
	public Long getOriginalNumberOfLoans1() {
		return convertStringToLong(originalNumberOfLoans);
	}	
	public void setOriginalNumberOfLoans(String originalNumberOfLoans) {
		this.originalNumberOfLoans = originalNumberOfLoans;
	}
	@Field(offset=129, length=7, align=Align.LEFT, paddingChar='{')
	//@FixedFormatDecimal (decimals=4, useDecimalDelimiter=false)
	public String getOriginalWACoupon() {
		//get value 005174G
		return originalWACoupon;
	}
	
	public BigDecimal getOriginalWACoupon1() {
		return convertStringToBigDecimal(originalWACoupon,4);
	}
	
	public void setOriginalWACoupon(String originalWACoupon) {
		this.originalWACoupon = originalWACoupon;
	}

	@Field(offset=136, length=3, align=Align.LEFT, paddingChar='{')
	public String getOriginalWAMaturity() {
		//get value like 38A, 38H
		return originalWAMaturity;
	}
	
	public Integer getOriginalWAMaturity1() {
		//get value like 38A, 38H
		return convertStringToInteger(originalWAMaturity);
	}
	
	public void setOriginalWAMaturity(String originalWAMaturity) {
		this.originalWAMaturity = originalWAMaturity;
	}
	@Field(offset=139, length=8)
	public Integer getFirstRateChangeDate() {
		return firstRateChangeDate;
	}
	public void setFirstRateChangeDate(Integer firstRateChangeDate) {
		this.firstRateChangeDate = firstRateChangeDate;
	}
	@Field(offset=147, length=8)
	public Integer getFirstPaymentChangeDate() {
		return firstPaymentChangeDate;
	}
	public void setFirstPaymentChangeDate(Integer firstPaymentChangeDate) {
		this.firstPaymentChangeDate = firstPaymentChangeDate;
	}
	@Field(offset=155, length=7, align=Align.LEFT, paddingChar='{')
	@FixedFormatDecimal (decimals=4, useDecimalDelimiter=false)	
	public BigDecimal getIssueAccrualRate() {
		return issueAccrualRate;
	}
	public void setIssueAccrualRate(BigDecimal issueAccrualRate) {
		this.issueAccrualRate = issueAccrualRate;
	}
	@Field(offset=162, length=2)
	public Integer getSecurityBalanceMlnm() {
		return securityBalanceMlnm;
	}
	public void setSecurityBalanceMlnm(Integer securityBalanceMlnm) {
		this.securityBalanceMlnm = securityBalanceMlnm;
	}
	@Field(offset=164, length=2)
	public Integer getSecurityBalanceYear() {
		return securityBalanceYear;
	}
	public void setSecurityBalanceYear(Integer securityBalanceYear) {
		this.securityBalanceYear = securityBalanceYear;
	}
	@Field(offset=166, length=2)
	public Integer getSecurityBalanceMonth() {
		return securityBalanceMonth;
	}
	public void setSecurityBalanceMonth(Integer securityBalanceMonth) {
		this.securityBalanceMonth = securityBalanceMonth;
	}
	@Field(offset=168, length=15, align=Align.LEFT, paddingChar='{')
	//@FixedFormatDecimal (decimals=2, useDecimalDelimiter=false)
	public String getCurrentSecurityBalance() {
		//get value like 00000000176656B, 00000000044674C 
		return currentSecurityBalance;
	}
	
	public BigDecimal getCurrentSecurityBalance1() {
		return convertStringToBigDecimal(currentSecurityBalance,2);
	}	

	public void setCurrentSecurityBalance(String currentSecurityBalance) {
		this.currentSecurityBalance = currentSecurityBalance;
	}
	@Field(offset=183, length=11, align=Align.LEFT, paddingChar='{')
	@FixedFormatDecimal (decimals=10, useDecimalDelimiter=false)
	public BigDecimal getCurrentTradingFactor() {
		return currentTradingFactor;
	}
	public void setCurrentTradingFactor(BigDecimal currentTradingFactor) {
		this.currentTradingFactor = currentTradingFactor;
	}
	@Field(offset=194, length=7, align=Align.LEFT, paddingChar='{')
	@FixedFormatDecimal (decimals=4, useDecimalDelimiter=false)
	public BigDecimal getCurrentWACoupon() {
		return currentWACoupon;
	}
	public void setCurrentWACoupon(BigDecimal currentWACoupon) {
		this.currentWACoupon = currentWACoupon;
	}
	@Field(offset=201, length=3, align=Align.LEFT, paddingChar='{')
	public String getCurrentWAMaturity() {
		//get value like 06B, 06
		return currentWAMaturity;
	}
	public Integer getCurrentWAMaturity1() {
		return convertStringToInteger(currentWAMaturity);
	}
	public void setCurrentWAMaturity(String currentWAMaturity) {
		this.currentWAMaturity = currentWAMaturity;
	}
	@Field(offset=204, length=13, align=Align.LEFT, paddingChar='{')
	@FixedFormatDecimal (decimals=2, useDecimalDelimiter=false)
	public BigDecimal getCurrentDeferredInterest() {
		return currentDeferredInterest;
	}
	public void setCurrentDeferredInterest(BigDecimal currentDeferredInterest) {
		this.currentDeferredInterest = currentDeferredInterest;
	}
	@Field(offset=217, length=13, align=Align.LEFT, paddingChar='{')
	@FixedFormatDecimal (decimals=2, useDecimalDelimiter=false)	
	public BigDecimal getReturnDeferredInterest() {
		return returnDeferredInterest;
	}
	public void setReturnDeferredInterest(BigDecimal returnDeferredInterest) {
		this.returnDeferredInterest = returnDeferredInterest;
	}
	@Field(offset=230, length=3, align=Align.LEFT, paddingChar='{')
	public String getWaMonthsToRateChange() {
		//get value like 00A
		return waMonthsToRateChange;
	}
	
	public Integer getWaMonthsToRateChange1() {
		//get value like 00A
		return convertStringToInteger(waMonthsToRateChange);
	}	
	public void setWaMonthsToRateChange(String waMonthsToRateChange) {
		this.waMonthsToRateChange = waMonthsToRateChange;
	}
	@Field(offset=233, length=7, align=Align.LEFT, paddingChar='{')
	//@FixedFormatDecimal (decimals=4, useDecimalDelimiter=false)	
	public String getWaMBSMargin() {
		return waMBSMargin;
	}
	public BigDecimal getWaMBSMargin1() {
		return convertStringToBigDecimal(waMBSMargin,4);
	}	
	public void setWaMBSMargin(String waMBSMargin) {
		this.waMBSMargin = waMBSMargin;
	}
	@Field(offset=240, length=7, align=Align.LEFT, paddingChar='{')
	@FixedFormatDecimal (decimals=4, useDecimalDelimiter=false)	
	public BigDecimal getWaLPTLifeCap() {
		return waLPTLifeCap;
	}
	public void setWaLPTLifeCap(BigDecimal waLPTLifeCap) {
		this.waLPTLifeCap = waLPTLifeCap;
	}
	@Field(offset=247, length=7, align=Align.LEFT, paddingChar='{')
	//@FixedFormatDecimal (decimals=4, useDecimalDelimiter=false)	
	public String getWaLPTLifeLife() {
		return waLPTLifeLife;
	}
	public BigDecimal getWaLPTLifeLife1() {
		return convertStringToBigDecimal(waLPTLifeLife,4);
	}
	public void setWaLPTLifeLife(String waLPTLifeLife) {
		this.waLPTLifeLife = waLPTLifeLife;
	}
	@Field(offset=254, length=7, align=Align.LEFT, paddingChar='{')
	//@FixedFormatDecimal (decimals=4, useDecimalDelimiter=false)	
	public String getWaLoanMargin() {
		return waLoanMargin;
	}
	public BigDecimal getWaLoanMargin1() {
		return convertStringToBigDecimal(waLoanMargin, 4);
	}
	public void setWaLoanMargin(String waLoanMargin) {
		this.waLoanMargin = waLoanMargin;
	}
	@Field(offset=261, length=7, align=Align.LEFT, paddingChar='{')
	@FixedFormatDecimal (decimals=4, useDecimalDelimiter=false)	
	public BigDecimal getWaNegativeAmortizationLimit() {
		return waNegativeAmortizationLimit;
	}
	public void setWaNegativeAmortizationLimit(
			BigDecimal waNegativeAmortizationLimit) {
		this.waNegativeAmortizationLimit = waNegativeAmortizationLimit;
	}
	@Field(offset=268, length=7, align=Align.LEFT, paddingChar='{')
	@FixedFormatDecimal (decimals=4, useDecimalDelimiter=false)	
	public BigDecimal getPublishedPassThroughRate() {
		return publishedPassThroughRate;
	}
	public void setPublishedPassThroughRate(BigDecimal publishedPassThroughRate) {
		this.publishedPassThroughRate = publishedPassThroughRate;
	}
	@Field(offset=275, length=1)
	public String getRateDifferenceFlag() {
		//get value *
		return rateDifferenceFlag;
	}
	public void setRateDifferenceFlag(String rateDifferenceFlag) {
		this.rateDifferenceFlag = rateDifferenceFlag;
	}
	@Field(offset=276, length=7, align=Align.LEFT, paddingChar='{')
	//@FixedFormatDecimal (decimals=4, useDecimalDelimiter=false)		
	public String getAccrualRate() {
		return accrualRate;
	}
	public BigDecimal getAccrualRate1() {
		return convertStringToBigDecimal(accrualRate,4);
	}
	public void setAccrualRate(String accrualRate) {
		this.accrualRate = accrualRate;
	}
	@Field(offset=283, length=3, align=Align.LEFT, paddingChar='{')
	public String getLookbackRateChange() {
		//get value like 01E, 01F
		return lookbackRateChange;
	}
	public Integer getLookbackRateChange1() {
		return convertStringToInteger(lookbackRateChange);
	}
	public void setLookbackRateChange(String lookbackRateChange) {
		this.lookbackRateChange = lookbackRateChange;
	}
	@Field(offset=286, length=3, align=Align.LEFT, paddingChar='{')
	public String getLookbackPaymentChange() {
		//convert 99I to null
		return lookbackPaymentChange;
	}
	public Integer getLookbackPaymentChange1() {
		return convertStringToInteger(lookbackPaymentChange);
	}
	public void setLookbackPaymentChange(String lookbackPaymentChange) {
		this.lookbackPaymentChange = lookbackPaymentChange;
	}
	@Field(offset=289, length=7, align=Align.LEFT, paddingChar='{')
	//@FixedFormatDecimal (decimals=4, useDecimalDelimiter=false)		
	public String getPerAdjustmentRateCap() {
		return perAdjustmentRateCap;
	}
	public BigDecimal getPerAdjustmentRateCap1() {
		return convertStringToBigDecimal(perAdjustmentRateCap,4);
	}	
	public void setPerAdjustmentRateCap(String perAdjustmentRateCap) {
		this.perAdjustmentRateCap = perAdjustmentRateCap;
	}
	@Field(offset=296, length=3, align=Align.LEFT, paddingChar='{')
	public String getRateAdjustmentFrequency() {
		return rateAdjustmentFrequency;
	}
	public BigDecimal getRateAdjustmentFrequency1() {
		return convertStringToBigDecimal(rateAdjustmentFrequency,3);
	}
	public void setRateAdjustmentFrequency(String rateAdjustmentFrequency) {
		this.rateAdjustmentFrequency = rateAdjustmentFrequency;
	}
	@Field(offset=299, length=3, align=Align.LEFT, paddingChar='{')
	public String getPaymentChangeFrequency() {
		return paymentChangeFrequency;
	}
	public BigDecimal getPaymentChangeFrequency1() {
		return convertStringToBigDecimal(paymentChangeFrequency,3);
	}	
	
	public void setPaymentChangeFrequency(String paymentChangeFrequency) {
		this.paymentChangeFrequency = paymentChangeFrequency;
	}
	@Field(offset=302, length=3, align=Align.LEFT, paddingChar='{')
	public Integer getAmortizationRecastFrequency() {
		return amortizationRecastFrequency;
	}
	public void setAmortizationRecastFrequency(Integer amortizationRecastFrequency) {
		this.amortizationRecastFrequency = amortizationRecastFrequency;
	}
	@Field(offset=305, length=2)
	public String getAccrualRateRoundingMethodCode() {
		return accrualRateRoundingMethodCode;
	}
	public void setAccrualRateRoundingMethodCode(
			String accrualRateRoundingMethodCode) {
		this.accrualRateRoundingMethodCode = accrualRateRoundingMethodCode;
	}

	@Field(offset=307, length=7, align=Align.LEFT, paddingChar='{')
	//@FixedFormatDecimal (decimals=4, useDecimalDelimiter=false)	
	public String getPaymentCap(){
		return paymentCap;
	}
	
	public BigDecimal getPaymentCap1(){
		return convertStringToBigDecimal(paymentCap,4);
	}
	
	public void setPaymentCap(String paymentCap) {
		this.paymentCap = paymentCap;
	}
	@Field(offset=314, length=7, align=Align.LEFT, paddingChar='{')
	//@FixedFormatDecimal (decimals=4, useDecimalDelimiter=false)		
	public String getMinimumIndexMovement() {
		return minimumIndexMovement;
	}
	public BigDecimal getMinimumIndexMovement1() {
		return convertStringToBigDecimal(minimumIndexMovement,4);
	}	
	public void setMinimumIndexMovement(String minimumIndexMovement) {
		this.minimumIndexMovement = minimumIndexMovement;
	}
	@Field(offset=321, length=7, align=Align.LEFT, paddingChar='{')
	public String getMaximumAccrualRate() {
		//get value 013679G
		return maximumAccrualRate;
	}
	public BigDecimal getMaximumAccrualRate1() {
		//get value 013679G
		return convertStringToBigDecimal(maximumAccrualRate,4);
	}	
	public void setMaximumAccrualRate(String maximumAccrualRate) {
		this.maximumAccrualRate = maximumAccrualRate;
	}
	@Field(offset=328, length=7, align=Align.LEFT, paddingChar='{')
	//@FixedFormatDecimal (decimals=4, useDecimalDelimiter=false)	
	public String getMinimumAccrualRate() {
		
		return minimumAccrualRate;
	}
	public BigDecimal getMinimumAccrualRate1() {
		
		return convertStringToBigDecimal(minimumAccrualRate,4);
	}	
	public void setMinimumAccrualRate(String minimumAccrualRate) {
		this.minimumAccrualRate = minimumAccrualRate;
	}
	@Field(offset=335, length=66)
	public String getFiller2() {
		return filler2;
	}

	public void setFiller2(String filler2) {
		this.filler2 = filler2;
	}
	
	public List<NextRateChangeDateMonthly> getNextRateChangeDates() {
		return nextRateChangeDates;
	}
	public void setNextRateChangeDates(
			List<NextRateChangeDateMonthly> nextRateChangeDates) {
		this.nextRateChangeDates = nextRateChangeDates;
	}
	public List<FirstPaymentDateMonthly> getFirstPaymentDates() {
		return firstPaymentDates;
	}
	public void setFirstPaymentDates(List<FirstPaymentDateMonthly> firstPaymentDates) {
		this.firstPaymentDates = firstPaymentDates;
	}
	
	@Override
	public String toString() {
		return "ArmPoolMonthly [poolNumber=" + poolNumber + ", poolPrefix="
				+ poolPrefix + ", recordType=" + recordType +  ", cusipNumber=" 
				+ cusipNumber + ", poolIssueDate="
				+ poolIssueDate + ", poolMaturityDate=" + poolMaturityDate
				+ ", lenderIssuer=" + lenderIssuer + ", lenderCity="
				+ lenderCity + ", state=" + state + ", subtype=" + subtype
				+ ", transferType=" + transferType
				+ ", passThroughRateStructure=" + passThroughRateStructure
				+ ", convertibleFlag=" + convertibleFlag
				+ ", deferredInterestAllowed=" + deferredInterestAllowed
				+ ", originalSecurityBalance=" + originalSecurityBalance
				+ ", originalSecurityBalance1=" + getOriginalSecurityBalance1()
				+ ", originalNumberOfLoans=" + originalNumberOfLoans
				+ ", originalNumberOfLoans1=" + getOriginalNumberOfLoans1()
				+ ", originalWACoupon=" + originalWACoupon
				+ ", originalWACoupon1=" + getOriginalWACoupon1()
				+ ", originalWAMaturity=" + originalWAMaturity
				+ ", originalWAMaturity1=" + getOriginalWAMaturity1()
				+ ", firstRateChangeDate=" + firstRateChangeDate
				+ ", firstPaymentChangeDate=" + firstPaymentChangeDate
				+ ", issueAccrualRate=" + issueAccrualRate
				+ ", securityBalanceMlnm=" + securityBalanceMlnm
				+ ", securityBalanceYear=" + securityBalanceYear
				+ ", securityBalanceMonth=" + securityBalanceMonth
				+ ", currentSecurityBalance=" + currentSecurityBalance
				+ ", currentSecurityBalance1=" + getCurrentSecurityBalance1()
				+ ", currentTradingFactor=" + currentTradingFactor
				+ ", currentWACoupon=" + currentWACoupon
				+ ", currentWAMaturity=" + currentWAMaturity
				+ ", currentWAMaturity1=" + getCurrentWAMaturity1()
				+ ", currentDeferredInterest=" + currentDeferredInterest
				+ ", returnDeferredInterest=" + returnDeferredInterest
				+ ", waMonthsToRateChange=" + waMonthsToRateChange
				+ ", waMonthsToRateChange1=" + getWaMonthsToRateChange1()
				+ ", waMBSMargin=" + waMBSMargin 
				+ ", waMBSMargin1=" + getWaMBSMargin1() 
				+ ", waLPTLifeCap="
				+ waLPTLifeCap + ", waLPTLifeLife=" + waLPTLifeLife
				+ ", waLPTLifeLife1=" + getWaLPTLifeLife1()
				+ ", waLoanMargin=" + waLoanMargin
				+ ", waLoanMargin1=" + getWaLoanMargin1()
				+ ", waNegativeAmortizationLimit="
				+ waNegativeAmortizationLimit + ", publishedPassThroughRate="
				+ publishedPassThroughRate + ", rateDifferenceFlag="
				+ rateDifferenceFlag + ", accrualRate=" + accrualRate
				+ ", accrualRate1=" + getAccrualRate1()
				+ ", lookbackRateChange=" + lookbackRateChange
				+ ", lookbackPaymentChange=" + lookbackPaymentChange
				+ ", perAdjustmentRateCap=" + perAdjustmentRateCap
				+ ", perAdjustmentRateCap1=" + getPerAdjustmentRateCap1()
				+ ", rateAdjustmentFrequency=" + rateAdjustmentFrequency
				+ ", paymentChangeFrequency=" + paymentChangeFrequency
				+ ", paymentChangeFrequency1=" + getPaymentChangeFrequency1()
				+ ", amortizationRecastFrequency="
				+ amortizationRecastFrequency
				+ ", accrualRateRoundingMethodCode="
				+ accrualRateRoundingMethodCode + ", paymentCap=" + paymentCap
				+ ", paymentCap1=" + getPaymentCap1()
				+ ", minimumIndexMovement=" + minimumIndexMovement
				+ ", minimumIndexMovement1=" + getMinimumIndexMovement1()
				+ ", maximumAccrualRate=" + maximumAccrualRate
				+ ", maximumAccrualRate1=" + getMaximumAccrualRate1()
				+ ", minimumAccrualRate=" + minimumAccrualRate 
				+ ", minimumAccrualRate1=" + getMinimumAccrualRate1() 
				+ ", filler2="
				+ filler2 + "]";
	}

	
}
