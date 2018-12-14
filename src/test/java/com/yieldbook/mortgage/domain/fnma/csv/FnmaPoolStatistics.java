package com.yieldbook.mortgage.domain.fnma.csv;

import java.util.Date;

import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvDate;

public class FnmaPoolStatistics {
	@CsvBindByPosition(position = 0)
    private String poolNumber;

    @CsvBindByPosition(position = 1)
    private String recordType;
    
    @CsvBindByPosition(position = 2)
    private String cusip;	    

    @CsvBindByPosition(position = 3)
    @CsvDate("MM/dd/yyyy")
    private Date issueDate;
    
    @CsvBindByPosition(position = 4)
    private String securityDescription;
    
    @CsvBindByPosition(position = 5)
    private Double issueAmount;
    
    @CsvBindByPosition(position = 6)
    private Double passThroughRate;
    
    
	@CsvBindByPosition(position = 7)
    private Double initialAccrualRate;

    @CsvBindByPosition(position = 8)
    private String firstPIDate;
    
    @CsvBindByPosition(position = 9)
    private String seller;	    

    @CsvBindByPosition(position = 10)
    private String servicer;
    
    @CsvBindByPosition(position = 11)
    private Integer numberOfLoans;
    
    
    @CsvBindByPosition(position = 12)
    private Double averageLoanSize;
    
    
    @CsvBindByPosition(position = 13)
    @CsvDate("MM/dd/yyyy")
    private Date maturityDate;
    
    @CsvBindByPosition(position = 14)
    @CsvDate("MM/dd/yyyy")
    private Date initialInterestRateChangeDate;
    
    @CsvBindByPosition(position = 15)
    private Integer waMonthsToRoll;
    
    @CsvBindByPosition(position = 16)
    private String subType;
    
    @CsvBindByPosition(position = 17)
    private String convertible;   
  
    @CsvBindByPosition(position = 18)
    private String transferType;
    
    @CsvBindByPosition(position = 19)
    private String passThroughMethod;
    
    @CsvBindByPosition(position = 20)
    private Double waCoupon;
    
    @CsvBindByPosition(position = 21)
    private Double waMaxPoolAccrualRate;
    
    @CsvBindByPosition(position = 22)
    private Double waMinPoolAccrualRate;
    
    @CsvBindByPosition(position = 23)
    private Integer waLoanAge;
    
    @CsvBindByPosition(position = 24)
    private String waLoanTerm;
    
    @CsvBindByPosition(position = 25)
    private String waRemainingMaturityAtIssuance;
    
    @CsvBindByPosition(position = 26)
    private Double waLTV;
    
    @CsvBindByPosition(position = 27)
    private Integer waCreditScore;
    
    @CsvBindByPosition(position = 28)
    private Double percentUPBWithoutCreditScore;
    
    @CsvBindByPosition(position = 29)
    private Double percentUPBWithInterestOnly;
    
    @CsvBindByPosition(position = 30)
    private Double percentUPBWithFullyAmortizing;
    
    @CsvBindByPosition(position = 31)
    private String prefix;
    
    @CsvBindByPosition(position = 32)
    @CsvDate("MM/dd/yyyy")
    private Date firstPaymentChangeDate;
    
    @CsvBindByPosition(position = 33)
    private Double percentUPBWithThirdPartyOrigination;
    
    @CsvBindByPosition(position = 34)
    private Double waCombinedLoanToValueRatio;
    
    @CsvBindByPosition(position = 35)
    private Integer waOriginalLoanSize;

	public String getPoolNumber() {
		return poolNumber;
	}

	public void setPoolNumber(String poolNumber) {
		this.poolNumber = poolNumber;
	}

	public String getRecordType() {
		return recordType;
	}

	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}

	public String getCusip() {
		return cusip;
	}

	public void setCusip(String cusip) {
		this.cusip = cusip;
	}

	public Date getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

	public String getSecurityDescription() {
		return securityDescription;
	}

	public void setSecurityDescription(String securityDescription) {
		this.securityDescription = securityDescription;
	}

	public Double getIssueAmount() {
		return issueAmount;
	}

	public void setIssueAmount(Double issueAmount) {
		this.issueAmount = issueAmount;
	}

	public Double getPassThroughRate() {
		return passThroughRate;
	}

	public void setPassThroughRate(Double passThroughRate) {
		this.passThroughRate = passThroughRate;
	}

	public Double getInitialAccrualRate() {
		return initialAccrualRate;
	}

	public void setInitialAccrualRate(Double initialAccrualRate) {
		this.initialAccrualRate = initialAccrualRate;
	}

	public String getFirstPIDate() {
		return firstPIDate;
	}

	public void setFirstPIDate(String firstPIDate) {
		this.firstPIDate = firstPIDate;
	}

	public String getSeller() {
		return seller;
	}

	public void setSeller(String seller) {
		this.seller = seller;
	}

	public String getServicer() {
		return servicer;
	}

	public void setServicer(String servicer) {
		this.servicer = servicer;
	}

	public Integer getNumberOfLoans() {
		return numberOfLoans;
	}

	public void setNumberOfLoans(Integer numberOfLoans) {
		this.numberOfLoans = numberOfLoans;
	}

	public Double getAverageLoanSize() {
		return averageLoanSize;
	}

	public void setAverageLoanSize(Double averageLoanSize) {
		this.averageLoanSize = averageLoanSize;
	}

	public Date getMaturityDate() {
		return maturityDate;
	}

	public void setMaturityDate(Date maturityDate) {
		this.maturityDate = maturityDate;
	}

	public Date getInitialInterestRateChangeDate() {
		return initialInterestRateChangeDate;
	}

	public void setInitialInterestRateChangeDate(Date initialInterestRateChangeDate) {
		this.initialInterestRateChangeDate = initialInterestRateChangeDate;
	}

	public Integer getWaMonthsToRoll() {
		return waMonthsToRoll;
	}

	public void setWaMonthsToRoll(Integer waMonthsToRoll) {
		this.waMonthsToRoll = waMonthsToRoll;
	}

	public String getSubType() {
		return subType;
	}

	public void setSubType(String subType) {
		this.subType = subType;
	}

	public String getConvertible() {
		return convertible;
	}

	public void setConvertible(String convertible) {
		this.convertible = convertible;
	}

	public String getTransferType() {
		return transferType;
	}

	public void setTransferType(String transferType) {
		this.transferType = transferType;
	}

	public String getPassThroughMethod() {
		return passThroughMethod;
	}

	public void setPassThroughMethod(String passThroughMethod) {
		this.passThroughMethod = passThroughMethod;
	}

	public Double getWaCoupon() {
		return waCoupon;
	}

	public void setWaCoupon(Double waCoupon) {
		this.waCoupon = waCoupon;
	}

	public Double getWaMaxPoolAccrualRate() {
		return waMaxPoolAccrualRate;
	}

	public void setWaMaxPoolAccrualRate(Double waMaxPoolAccrualRate) {
		this.waMaxPoolAccrualRate = waMaxPoolAccrualRate;
	}

	public Double getWaMinPoolAccrualRate() {
		return waMinPoolAccrualRate;
	}

	public void setWaMinPoolAccrualRate(Double waMinPoolAccrualRate) {
		this.waMinPoolAccrualRate = waMinPoolAccrualRate;
	}

	public Integer getWaLoanAge() {
		return waLoanAge;
	}

	public void setWaLoanAge(Integer waLoanAge) {
		this.waLoanAge = waLoanAge;
	}

	public String getWaLoanTerm() {
		return waLoanTerm;
	}

	public void setWaLoanTerm(String waLoanTerm) {
		this.waLoanTerm = waLoanTerm;
	}

	public String getWaRemainingMaturityAtIssuance() {
		return waRemainingMaturityAtIssuance;
	}

	public void setWaRemainingMaturityAtIssuance(
			String waRemainingMaturityAtIssuance) {
		this.waRemainingMaturityAtIssuance = waRemainingMaturityAtIssuance;
	}

	public Double getWaLTV() {
		return waLTV;
	}

	public void setWaLTV(Double waLTV) {
		this.waLTV = waLTV;
	}

	public Integer getWaCreditScore() {
		return waCreditScore;
	}

	public void setWaCreditScore(Integer waCreditScore) {
		this.waCreditScore = waCreditScore;
	}

	public Double getPercentUPBWithoutCreditScore() {
		return percentUPBWithoutCreditScore;
	}

	public void setPercentUPBWithoutCreditScore(Double percentUPBWithoutCreditScore) {
		this.percentUPBWithoutCreditScore = percentUPBWithoutCreditScore;
	}

	public Double getPercentUPBWithInterestOnly() {
		return percentUPBWithInterestOnly;
	}

	public void setPercentUPBWithInterestOnly(Double percentUPBWithInterestOnly) {
		this.percentUPBWithInterestOnly = percentUPBWithInterestOnly;
	}

	public Double getPercentUPBWithFullyAmortizing() {
		return percentUPBWithFullyAmortizing;
	}

	public void setPercentUPBWithFullyAmortizing(
			Double percentUPBWithFullyAmortizing) {
		this.percentUPBWithFullyAmortizing = percentUPBWithFullyAmortizing;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public Date getFirstPaymentChangeDate() {
		return firstPaymentChangeDate;
	}

	public void setFirstPaymentChangeDate(Date firstPaymentChangeDate) {
		this.firstPaymentChangeDate = firstPaymentChangeDate;
	}

	public Double getPercentUPBWithThirdPartyOrigination() {
		return percentUPBWithThirdPartyOrigination;
	}

	public void setPercentUPBWithThirdPartyOrigination(
			Double percentUPBWithThirdPartyOrigination) {
		this.percentUPBWithThirdPartyOrigination = percentUPBWithThirdPartyOrigination;
	}

	public Double getWaCombinedLoanToValueRatio() {
		return waCombinedLoanToValueRatio;
	}

	public void setWaCombinedLoanToValueRatio(Double waCombinedLoanToValueRatio) {
		this.waCombinedLoanToValueRatio = waCombinedLoanToValueRatio;
	}

	public Integer getWaOriginalLoanSize() {
		return waOriginalLoanSize;
	}

	public void setWaOriginalLoanSize(Integer waOriginalLoanSize) {
		this.waOriginalLoanSize = waOriginalLoanSize;
	}

	@Override
	public String toString() {
		return "FnmaPoolStatistics [poolNumber=" + poolNumber + ", recordType="
				+ recordType + ", cusip=" + cusip + ", issueDate=" + issueDate
				+ ", securityDescription=" + securityDescription
				+ ", issueAmount=" + issueAmount + ", passThroughRate="
				+ passThroughRate + ", initialAccrualRate="
				+ initialAccrualRate + ", firstPIDate=" + firstPIDate
				+ ", seller=" + seller + ", servicer=" + servicer
				+ ", numberOfLoans=" + numberOfLoans + ", averageLoanSize="
				+ averageLoanSize + ", maturityDate=" + maturityDate
				+ ", initialInterestRateChangeDate="
				+ initialInterestRateChangeDate + ", waMonthsToRoll="
				+ waMonthsToRoll + ", subType=" + subType + ", convertible="
				+ convertible + ", transferType=" + transferType
				+ ", passThroughMethod=" + passThroughMethod + ", waCoupon="
				+ waCoupon + ", waMaxPoolAccrualRate=" + waMaxPoolAccrualRate
				+ ", waMinPoolAccrualRate=" + waMinPoolAccrualRate
				+ ", waLoanAge=" + waLoanAge + ", waLoanTerm=" + waLoanTerm
				+ ", waRemainingMaturityAtIssuance="
				+ waRemainingMaturityAtIssuance + ", waLTV=" + waLTV
				+ ", waCreditScore=" + waCreditScore
				+ ", percentUPBWithoutCreditScore="
				+ percentUPBWithoutCreditScore
				+ ", percentUPBWithInterestOnly=" + percentUPBWithInterestOnly
				+ ", percentUPBWithFullyAmortizing="
				+ percentUPBWithFullyAmortizing + ", prefix=" + prefix
				+ ", firstPaymentChangeDate=" + firstPaymentChangeDate
				+ ", percentUPBWithThirdPartyOrigination="
				+ percentUPBWithThirdPartyOrigination
				+ ", waCombinedLoanToValueRatio=" + waCombinedLoanToValueRatio
				+ ", waOriginalLoanSize=" + waOriginalLoanSize + "]";
	}
 
    
}
