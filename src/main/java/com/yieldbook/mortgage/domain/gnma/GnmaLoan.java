package com.yieldbook.mortgage.domain.gnma;

import static com.yieldbook.mortgage.utility.YieldBookUtilities.DecimalFormatter;
import static com.yieldbook.mortgage.utility.YieldBookUtilities.DoubleFormatter;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.apache.commons.lang3.StringUtils;

import com.ancientprogramming.fixedformat4j.annotation.Align;
import com.ancientprogramming.fixedformat4j.annotation.Field;
import com.ancientprogramming.fixedformat4j.annotation.FixedFormatDecimal;
import com.ancientprogramming.fixedformat4j.annotation.Record;
import com.ancientprogramming.fixedformat4j.format.FormatInstructions;
import com.ancientprogramming.fixedformat4j.format.data.FixedFormatDecimalData;
import com.ancientprogramming.fixedformat4j.format.data.FixedFormatNumberData;
/**
 * @author fx36019
 *
 */
@Record
public class GnmaLoan {
	private String recordType;
	private String poolID;
	private String disclosureSequenceNumber;
	private String issuerID;
	private String agency;
	private String loanPurpose;
	private String refinanceType;
	private String firstPaymentDate;
	private String maturityDateofLoan;
	private BigDecimal loanInterestRate;
	private Double originalPrincipalBalance;
	private String UPBatIssuance;
	private String UPB;
	private String originalLoanTerm;
	private String loanAge;
	private String remainingLoanTerm;
	private String monthsDelinquent;
	private String monthsPrepaid;
	private String loanGrossMargin;
	private String LTV;
	private String CLTV;
	private String totalDebtExpenseRatioPercent;
	private String creditScore;
	private String downPaymentAssistance;
	private String loanStatus;
	private String upfrontMIP;
	private String annualMIP;
	private String numberofBorrowers;
	private String firstTimeHomeBuyer;
	private String propertyType;
	private String state;
	private String MSA;
	private String TPOType;
	private String currentMonthLiquidationFlag;
	private String removalReason;
	private String asofDate;
	private String loanOriginationDate;
	private String sellerIssuer;
	private String indexType;
	private String lookbackPeriod;
	private String interestRateChangeDate;
	private String initialInterestRateCap;
	private String subsequentInterestRateCap;
	private String lifetimeInterestRateCap;
	private String nextInterestRateChangeCeiling;
	private String lifetimeInterestRateCeiling;
	private String lifetimeInterestRateFloor;
	private String prospectiveInterestRate;
	
	
	@Field(offset=1, length=1)
	public String getRecordType() {
		return recordType;
	}
	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}
	@Field(offset=2, length=6)
	public String getPoolID() {
		return poolID;
	}
	public void setPoolID(String poolID) {
		this.poolID = poolID;
	}
	@Field(offset=8, length=10)
	public String getDisclosureSequenceNumber() {
		return disclosureSequenceNumber;
	}
	public void setDisclosureSequenceNumber(String disclosureSequenceNumber) {
		this.disclosureSequenceNumber = disclosureSequenceNumber;
	}
	@Field(offset=18, length=4)
	public String getIssuerID() {
		return issuerID;
	}
	public void setIssuerID(String issuerID) {
		this.issuerID = issuerID;
	}
	@Field(offset=22, length=1)
	public String getAgency() {
		return agency;
	}
	public void setAgency(String agency) {
		this.agency = agency;
	}
	@Field(offset=23, length=1)
	public String getLoanPurpose() {
		return loanPurpose;
	}
	public void setLoanPurpose(String loanPurpose) {
		this.loanPurpose = loanPurpose;
	}
	@Field(offset=24, length=1)
	public String getRefinanceType() {
		return refinanceType;
	}
	public void setRefinanceType(String refinanceType) {
		this.refinanceType = refinanceType;
	}
	@Field(offset=25, length=8)
	public String getFirstPaymentDate() {
		return firstPaymentDate;
	}
	public void setFirstPaymentDate(String firstPaymentDate) {
		this.firstPaymentDate = firstPaymentDate;
	}
	@Field(offset=33, length=8)
	public String getMaturityDateofLoan() {
		return maturityDateofLoan;
	}
	public void setMaturityDateofLoan(String maturityDateofLoan) {
		this.maturityDateofLoan = maturityDateofLoan;
	}
	@Field(offset=41, length=5, align = Align.RIGHT, paddingChar = ' ')
	@FixedFormatDecimal (decimals=3, useDecimalDelimiter=false)
	public BigDecimal getLoanInterestRate() {
		return loanInterestRate;
	}
	public void setLoanInterestRate(BigDecimal loanInterestRate) {
		this.loanInterestRate = loanInterestRate;
	}
	@Field(offset=46, length=11, align = Align.RIGHT, paddingChar = ' ')
	@FixedFormatDecimal (decimals=2, useDecimalDelimiter=false)
	public Double getOriginalPrincipalBalance() {
		return originalPrincipalBalance;
	}
	public void setOriginalPrincipalBalance(Double originalPrincipalBalance) {
		this.originalPrincipalBalance = originalPrincipalBalance;
	}
	@Field(offset=57, length=11, align = Align.RIGHT, paddingChar = ' ')

	public String getUPBatIssuance() {
		return UPBatIssuance;
	}
	public void setUPBatIssuance(String UPBatIssuance) {
		this.UPBatIssuance = UPBatIssuance;
	}
	@Field(offset=68, length=11, align = Align.RIGHT, paddingChar = ' ')
	public String getUPB() {
		return UPB;
	}
	public void setUPB(String UPB) {
		this.UPB = UPB;
	}
	@Field(offset=79, length=3)
	public String getOriginalLoanTerm() {
		return originalLoanTerm;
	}
	public void setOriginalLoanTerm(String originalLoanTerm) {
		this.originalLoanTerm = originalLoanTerm;
	}
	@Field(offset=82, length=3)
	public String getLoanAge() {
		return loanAge;
	}
	public void setLoanAge(String loanAge) {
		this.loanAge = loanAge;
	}
	@Field(offset=85, length=3)
	public String getRemainingLoanTerm() {
		return remainingLoanTerm;
	}
	public void setRemainingLoanTerm(String remainingLoanTerm) {
		this.remainingLoanTerm = remainingLoanTerm;
	}
	@Field(offset=88, length=1)
	public String getMonthsDelinquent() {
		return monthsDelinquent;
	}
	public void setMonthsDelinquent(String monthsDelinquent) {
		this.monthsDelinquent = monthsDelinquent;
	}
	@Field(offset=89, length=1)
	public String getMonthsPrepaid() {
		return monthsPrepaid;
	}
	public void setMonthsPrepaid(String monthsPrepaid) {
		this.monthsPrepaid = monthsPrepaid;
	}
	@Field(offset=90, length=4)
	public String getLoanGrossMargin() {
		return loanGrossMargin;
	}
	public void setLoanGrossMargin(String loanGrossMargin) {
		this.loanGrossMargin = loanGrossMargin;
	}
	@Field(offset=94, length=5, align=Align.RIGHT, paddingChar=' ')
	public String getLTV() {
		return LTV;
	}
	public void setLTV(String LTV) {
		this.LTV = LTV;
	}
	@Field(offset=99, length=5, align=Align.RIGHT, paddingChar=' ')
	public String getCLTV() {
		return CLTV;
	}
	public void setCLTV(String CLTV) {
		this.CLTV = CLTV;
	}
	@Field(offset=104, length=5, align=Align.RIGHT, paddingChar=' ')
	public String getTotalDebtExpenseRatioPercent() {
		return totalDebtExpenseRatioPercent;
	}
	public void setTotalDebtExpenseRatioPercent(String totalDebtExpenseRatioPercent) {
		this.totalDebtExpenseRatioPercent = totalDebtExpenseRatioPercent;
	}
	@Field(offset=109, length=3)
	public String getCreditScore() {
		return creditScore;
	}
	public void setCreditScore(String creditScore) {
		this.creditScore = creditScore;
	}
	@Field(offset=112, length=1)
	public String getDownPaymentAssistance() {
		return downPaymentAssistance;
	}
	public void setDownPaymentAssistance(String downPaymentAssistance) {
		this.downPaymentAssistance = downPaymentAssistance;
	}
	@Field(offset=113, length=1)
	public String getLoanStatus() {
		return loanStatus;
	}
	public void setLoanStatus(String loanStatus) {
		this.loanStatus = loanStatus;
	}
	@Field(offset=114, length=5, align=Align.RIGHT, paddingChar=' ')
	public String getUpfrontMIP() {
		return upfrontMIP;
	}
	public void setUpfrontMIP(String upfrontMIP) {
		this.upfrontMIP = upfrontMIP;
	}
	@Field(offset=119, length=5,align=Align.RIGHT, paddingChar=' ')
	public String getAnnualMIP() {
		return annualMIP;
	}
	public void setAnnualMIP(String annualMIP) {
		this.annualMIP = annualMIP;
	}
	@Field(offset=124, length=1)
	public String getNumberofBorrowers() {
		return numberofBorrowers;
	}
	public void setNumberofBorrowers(String numberofBorrowers) {
		this.numberofBorrowers = numberofBorrowers;
	}
	@Field(offset=125, length=1)
	public String getFirstTimeHomeBuyer() {
		return firstTimeHomeBuyer;
	}
	public void setFirstTimeHomeBuyer(String firstTimeHomeBuyer) {
		this.firstTimeHomeBuyer = firstTimeHomeBuyer;
	}
	@Field(offset=126, length=1)
	public String getPropertyType() {
		return propertyType;
	}
	public void setPropertyType(String propertyType) {
		this.propertyType = propertyType;
	}
	@Field(offset=127, length=2)
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	@Field(offset=129, length=5)
	public String getMSA() {
		return MSA;
	}
	public void setMSA(String MSA) {
		this.MSA = MSA;
	}
	@Field(offset=134, length=1)
	public String getTPOType() {
		return TPOType;
	}
	public void setTPOType(String TPOType) {
		this.TPOType = TPOType;
	}
	@Field(offset=135, length=1)
	public String getCurrentMonthLiquidationFlag() {
		return currentMonthLiquidationFlag;
	}
	public void setCurrentMonthLiquidationFlag(String currentMonthLiquidationFlag) {
		this.currentMonthLiquidationFlag = currentMonthLiquidationFlag;
	}
	@Field(offset=136, length=1)
	public String getRemovalReason() {
		return removalReason;
	}
	public void setRemovalReason(String removalReason) {
		this.removalReason = removalReason;
	}
	@Field(offset=137, length=6)
	public String getAsofDate() {
		return asofDate;
	}
	public void setAsofDate(String asofDate) {
		this.asofDate = asofDate;
	}
	@Field(offset=143, length=8)
	public String getLoanOriginationDate() {
		return loanOriginationDate;
	}
	public void setLoanOriginationDate(String loanOriginationDate) {
		this.loanOriginationDate = loanOriginationDate;
	}
	@Field(offset=151, length=4)
	public String getSellerIssuer() {
		return sellerIssuer;
	}
	public void setSellerIssuer(String sellerIssuer) {
		this.sellerIssuer = sellerIssuer;
	}
	@Field(offset=155, length=5)
	public String getIndexType() {
		return indexType;
	}
	public void setIndexType(String indexType) {
		this.indexType = indexType;
	}
	@Field(offset=160, length=2)
	public String getLookbackPeriod() {
		return lookbackPeriod;
	}
	public void setLookbackPeriod(String lookbackPeriod) {
		this.lookbackPeriod = lookbackPeriod;
	}
	@Field(offset=162, length=8)
	public String getInterestRateChangeDate() {
		return interestRateChangeDate;
	}
	public void setInterestRateChangeDate(String interestRateChangeDate) {
		this.interestRateChangeDate = interestRateChangeDate;
	}
	@Field(offset=170, length=1)
	public String getInitialInterestRateCap() {
		return initialInterestRateCap;
	}
	public void setInitialInterestRateCap(String initialInterestRateCap) {
		this.initialInterestRateCap = initialInterestRateCap;
	}
	@Field(offset=171, length=1)
	public String getSubsequentInterestRateCap() {
		return subsequentInterestRateCap;
	}
	public void setSubsequentInterestRateCap(String subsequentInterestRateCap) {
		this.subsequentInterestRateCap = subsequentInterestRateCap;
	}
	@Field(offset=172, length=1)
	public String getLifetimeInterestRateCap() {
		return lifetimeInterestRateCap;
	}
	public void setLifetimeInterestRateCap(String lifetimeInterestRateCap) {
		this.lifetimeInterestRateCap = lifetimeInterestRateCap;
	}
	@Field(offset=173, length=5, align=Align.RIGHT, paddingChar=' ')
	public String getNextInterestRateChangeCeiling() {
		return nextInterestRateChangeCeiling;
	}
	public void setNextInterestRateChangeCeiling(String nextInterestRateChangeCeiling) {
		this.nextInterestRateChangeCeiling = nextInterestRateChangeCeiling;
	}
	@Field(offset=178, length=5, align=Align.RIGHT, paddingChar=' ')
	public String getLifetimeInterestRateCeiling() {
		return lifetimeInterestRateCeiling;
	}
	public void setLifetimeInterestRateCeiling(String lifetimeInterestRateCeiling) {
		this.lifetimeInterestRateCeiling = lifetimeInterestRateCeiling;
	}
	@Field(offset=183, length=5, align=Align.RIGHT, paddingChar=' ')
	public String getLifetimeInterestRateFloor() {
		return lifetimeInterestRateFloor;
	}
	public void setLifetimeInterestRateFloor(String lifetimeInterestRateFloor) {
		this.lifetimeInterestRateFloor = lifetimeInterestRateFloor;
	}
	@Field(offset=188, length=5, align=Align.RIGHT, paddingChar=' ')
	public String getProspectiveInterestRate() {
		return prospectiveInterestRate;
	}
	public void setProspectiveInterestRate(String prospectiveInterestRate) {
		this.prospectiveInterestRate = prospectiveInterestRate;
	}

	@Override
	public String toString() {
		return poolID + "|" + disclosureSequenceNumber + "|" + issuerID + "|" + agency + "|" + 
		loanPurpose + "|" + refinanceType + "|" + firstPaymentDate + "|" + maturityDateofLoan + "|" + loanInterestRate + "|" + 
		originalPrincipalBalance + "|" + UPBatIssuance + "|" + UPB + "|" + originalLoanTerm + "|" + loanAge + "|" + 
		remainingLoanTerm + "|" + monthsDelinquent + "|" + monthsPrepaid + "|" + loanGrossMargin + "|" + LTV + "|" + 
		CLTV + "|" + totalDebtExpenseRatioPercent + "|" + creditScore + "|" + downPaymentAssistance + "|" + loanStatus + "|" + 
		upfrontMIP + "|" + annualMIP + "|" + numberofBorrowers + "|" + firstTimeHomeBuyer + "|" + propertyType + "|" + 
		state + "|" + MSA + "|" + TPOType + "|" + currentMonthLiquidationFlag + "|" + removalReason + "|" + 
		asofDate + "|" + loanOriginationDate + "|" + sellerIssuer + "|" + indexType + "|" + lookbackPeriod + "|" + 
		interestRateChangeDate + "|" + initialInterestRateCap + "|" + subsequentInterestRateCap + "|" + lifetimeInterestRateCap + "|" + nextInterestRateChangeCeiling + "|" + 
		lifetimeInterestRateCeiling + "|" + lifetimeInterestRateFloor + "|" + prospectiveInterestRate;

	}
	
	
	public String[] toLoanArray(String cusip) {
		String[] entries = new String[47];
		entries[0] = disclosureSequenceNumber;
		entries[1] = cusip;
		entries[2] = issuerID;
		entries[3] = agency;
		entries[4] = loanPurpose;
		entries[5] = refinanceType;
		entries[6] = firstPaymentDate;
		entries[7] = maturityDateofLoan;
		entries[8] = loanInterestRate.toString();
		entries[9] = originalPrincipalBalance.toString();
		if(!StringUtils.isEmpty(UPBatIssuance)){
			entries[10] = DoubleFormatter.parse(UPBatIssuance, new FormatInstructions(11, Align.RIGHT, ' ', null, null, 
							FixedFormatNumberData.DEFAULT, new FixedFormatDecimalData(2, false, '.', RoundingMode.valueOf(BigDecimal.ROUND_HALF_UP)))).toString();
		}else{
			entries[10] = UPBatIssuance;
		}		
		if(!StringUtils.isEmpty(UPB)){
			entries[11] = DoubleFormatter.parse(UPB, new FormatInstructions(11, Align.RIGHT, ' ', null, null, 
							FixedFormatNumberData.DEFAULT, new FixedFormatDecimalData(2, false, '.', RoundingMode.valueOf(BigDecimal.ROUND_HALF_UP)))).toString();
		}else{
			entries[11] = UPB;
		}
		entries[12] = originalLoanTerm;
		entries[13] = loanAge;
		entries[14] = remainingLoanTerm;
		entries[15] = monthsDelinquent;
		entries[16] = monthsPrepaid;
		if(!StringUtils.isEmpty(loanGrossMargin)){
			entries[17] = DecimalFormatter.parse(loanGrossMargin, new FormatInstructions(4, Align.RIGHT, ' ', null, null, 
							FixedFormatNumberData.DEFAULT, new FixedFormatDecimalData(3, false, '.', RoundingMode.valueOf(BigDecimal.ROUND_HALF_UP)))).toString();
		}else{
			entries[17] = loanGrossMargin;
		}		
		if(!StringUtils.isEmpty(LTV)){
			entries[18] = DecimalFormatter.parse(LTV, new FormatInstructions(5, Align.RIGHT, ' ', null, null, 
							FixedFormatNumberData.DEFAULT, new FixedFormatDecimalData(2, false, '.', RoundingMode.valueOf(BigDecimal.ROUND_HALF_UP)))).toString();
		}else{
			entries[18] = LTV;
		}
		if(!StringUtils.isEmpty(CLTV)){
			entries[19] = DecimalFormatter.parse(CLTV, new FormatInstructions(5, Align.RIGHT, ' ', null, null, 
							FixedFormatNumberData.DEFAULT, new FixedFormatDecimalData(2, false, '.', RoundingMode.valueOf(BigDecimal.ROUND_HALF_UP)))).toString();
		}else{
			entries[19] = CLTV;
		}		
		if(!StringUtils.isEmpty(totalDebtExpenseRatioPercent)){
			entries[20] = DecimalFormatter.parse(totalDebtExpenseRatioPercent, new FormatInstructions(5, Align.RIGHT, ' ', null, null, 
							FixedFormatNumberData.DEFAULT, new FixedFormatDecimalData(2, false, '.', RoundingMode.valueOf(BigDecimal.ROUND_HALF_UP)))).toString();
		}else{
			entries[20] = totalDebtExpenseRatioPercent;
		}		
		entries[21] = creditScore;
		entries[22] = downPaymentAssistance;
		entries[23] = loanStatus;
		if(!StringUtils.isEmpty(upfrontMIP)){
			entries[24] = DecimalFormatter.parse(upfrontMIP, new FormatInstructions(5, Align.RIGHT, ' ', null, null, 
							FixedFormatNumberData.DEFAULT, new FixedFormatDecimalData(3, false, '.', RoundingMode.valueOf(BigDecimal.ROUND_HALF_UP)))).toString();
		}else{
			entries[24] = upfrontMIP;
		}		
		if(!StringUtils.isEmpty(annualMIP)){
			entries[25] = DecimalFormatter.parse(annualMIP, new FormatInstructions(5, Align.RIGHT, ' ', null, null, 
							FixedFormatNumberData.DEFAULT, new FixedFormatDecimalData(3, false, '.', RoundingMode.valueOf(BigDecimal.ROUND_HALF_UP)))).toString();
		}else{
			entries[25] = annualMIP;
		}
		entries[26] = numberofBorrowers;
		entries[27] = firstTimeHomeBuyer;
		entries[28] = propertyType;
		entries[29] = state;
		entries[30] = MSA;
		entries[31] = TPOType;
		entries[32] = currentMonthLiquidationFlag;
		entries[33] = removalReason;
		entries[34] = asofDate+"01";
		entries[35] = loanOriginationDate;
		entries[36] = sellerIssuer;
		entries[37] = indexType;
		entries[38] = lookbackPeriod;
		entries[39] = interestRateChangeDate;
		if(!StringUtils.isEmpty(initialInterestRateCap)){
			entries[40] = DecimalFormatter.parse(initialInterestRateCap, new FormatInstructions(1, Align.RIGHT, ' ', null, null, 
							FixedFormatNumberData.DEFAULT, new FixedFormatDecimalData(0, false, '.', RoundingMode.valueOf(BigDecimal.ROUND_HALF_UP)))).toString();
		}else{
			entries[40] = initialInterestRateCap;
		}
		if(!StringUtils.isEmpty(subsequentInterestRateCap)){
			entries[41] = DecimalFormatter.parse(subsequentInterestRateCap, new FormatInstructions(1, Align.RIGHT, ' ', null, null, 
							FixedFormatNumberData.DEFAULT, new FixedFormatDecimalData(0, false, '.', RoundingMode.valueOf(BigDecimal.ROUND_HALF_UP)))).toString();
		}else{
			entries[41] = subsequentInterestRateCap;
		}
		if(!StringUtils.isEmpty(lifetimeInterestRateCap)){
			entries[42] = DecimalFormatter.parse(lifetimeInterestRateCap, new FormatInstructions(1, Align.RIGHT, ' ', null, null, 
							FixedFormatNumberData.DEFAULT, new FixedFormatDecimalData(0, false, '.', RoundingMode.valueOf(BigDecimal.ROUND_HALF_UP)))).toString();
		}else{
			entries[42] = lifetimeInterestRateCap;
		}
		if(!StringUtils.isEmpty(nextInterestRateChangeCeiling)){
			entries[43] = DecimalFormatter.parse(nextInterestRateChangeCeiling, new FormatInstructions(5, Align.RIGHT, ' ', null, null, 
							FixedFormatNumberData.DEFAULT, new FixedFormatDecimalData(3, false, '.', RoundingMode.valueOf(BigDecimal.ROUND_HALF_UP)))).toString();
		}else{
			entries[43] = nextInterestRateChangeCeiling;
		}
		if(!StringUtils.isEmpty(lifetimeInterestRateCeiling)){
			entries[44] = DecimalFormatter.parse(lifetimeInterestRateCeiling, new FormatInstructions(5, Align.RIGHT, ' ', null, null, 
							FixedFormatNumberData.DEFAULT, new FixedFormatDecimalData(3, false, '.', RoundingMode.valueOf(BigDecimal.ROUND_HALF_UP)))).toString();
		}else{
			entries[44] = lifetimeInterestRateCeiling;
		}		
		if(!StringUtils.isEmpty(lifetimeInterestRateFloor)){
			entries[45] = DecimalFormatter.parse(lifetimeInterestRateFloor, new FormatInstructions(5, Align.RIGHT, ' ', null, null, 
							FixedFormatNumberData.DEFAULT, new FixedFormatDecimalData(3, false, '.', RoundingMode.valueOf(BigDecimal.ROUND_HALF_UP)))).toString();
		}else{
			entries[45] = lifetimeInterestRateFloor;
		}
		if(!StringUtils.isEmpty(prospectiveInterestRate)){
			entries[46] = DecimalFormatter.parse(prospectiveInterestRate, new FormatInstructions(5, Align.RIGHT, ' ', null, null, 
							FixedFormatNumberData.DEFAULT, new FixedFormatDecimalData(3, false, '.', RoundingMode.valueOf(BigDecimal.ROUND_HALF_UP)))).toString();
		}else{
			entries[46] = prospectiveInterestRate;
		}		
		return entries;
	} 

}
