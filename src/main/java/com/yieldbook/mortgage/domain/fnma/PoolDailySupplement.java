package com.yieldbook.mortgage.domain.fnma;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
public class PoolDailySupplement {
    public String poolNumber;
    public String recordType;
    public String cusip;	    
    public Date issueDate;
    public String securityDescription;
    public BigDecimal issueAmount;
    public BigDecimal passThroughRate;
    public BigDecimal initialAccrualRate;
    public Date firstPIDate;
    public String seller;	    
    public String servicer;
    public Integer numberOfLoans;
    public BigDecimal averageLoanSize;
    public Date maturityDate;
    public Date initialInterestRateChangeDate;
    public Integer waMonthsToRoll;
    public String subType;
    public String convertible;   
    public String transferType;
    public String passThroughMethod;
    public BigDecimal waCoupon;
    public BigDecimal waMaxPoolAccrualRate;
    public BigDecimal waMinPoolAccrualRate;
    public Integer waLoanAge;
    public String waLoanTerm;
    public String waRemainingMaturityAtIssuance;
    public BigDecimal waLTV;
    public Integer waCreditScore;
    public BigDecimal percentUPBWithoutCreditScore;
    public BigDecimal percentUPBWithInterestOnly;
    public BigDecimal percentUPBWithFullyAmortizing;
    public String prefix;
    public Date firstPaymentChangeDate;
    public BigDecimal percentUPBWithThirdPartyOrigination;
    public BigDecimal waCombinedLoanToValueRatio;
    public BigDecimal waOriginalLoanSize;
    
    public List<PoolDailyQuartile> quartiles;
    public List<PoolDailyElement> elements;
    public List<NextRateChangeDateDaily> nextRateChangeDates;
    public List<WaForNextRateChangeDateDaily> waForNextRateChangeDates;
    
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PoolStatistics [poolNumber=" + poolNumber + ", cusip=" + cusip + ", issueDate=" + issueDate
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
				+ ", waOriginalLoanSize=" + waOriginalLoanSize + ", quartiles="
				+ quartiles + ", elements=" + elements + ", nextRateChangeDates="
				+ nextRateChangeDates + ", waForNextRateChangeDates="
				+ waForNextRateChangeDates + "]";
	}
    
    
}
