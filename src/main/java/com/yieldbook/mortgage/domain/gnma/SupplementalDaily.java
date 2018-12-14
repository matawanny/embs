package com.yieldbook.mortgage.domain.gnma;

import com.ancientprogramming.fixedformat4j.annotation.Field;
import com.ancientprogramming.fixedformat4j.annotation.Record;

@Record
public class SupplementalDaily {
	private String cusip;
	private String poolNumber;
	private String poolIndicator;
	private String poolType;
	private String recrodType;
	private Integer issuer;
	
	
	private Quartile3 creditScore;
	private StatsElement24 creditScoreNotAvailable;
	private Quartile4 debtIncomeRatio;
	private StatsElement24 debtIncomeRatioNotAvailable;
	private StatsElement24 downPaymentAssistance;
	private StatsElement24 withoutPaymentAssistance;
	private StatsElement24 fhaPurchase;
	private StatsElement24 fhaRefinance;
	private StatsElement24 fhaHampModified;
	private StatsElement24 fhaNonHampModified;
	private StatsElement24 fhaPurposeNotAvailable;
	private StatsElement24 vaPurchase;
	private StatsElement24 vaRefinance;
	private StatsElement24 vaHampModified;
	private StatsElement24 vaNonHampModified;
	private StatsElement24 vaPurposeNotAvailable;
	private StatsElement24 pihPurchase;
	private StatsElement24 pihRefinance;
	private StatsElement24 pihHampModified;
	private StatsElement24 pihNonHampModified;
	private StatsElement24 pihPurposeNotAvailable;
	private StatsElement24 rdPurchase;
	private StatsElement24 rdRefinance;
	private StatsElement24 rdHampModified;
	private StatsElement24 rdNonHampModified;
	private StatsElement24 rdPurposeNotAvailable;
	private StatsElement24 nonStreamlinedRefi;
	private StatsElement24 cashOutRefi;
	private StatsElement24 streamlinedRefi;
	private StatsElement24 fhaShortRefinance;
	private StatsElement24 refinanceTypeNotAvailable;
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
	@Field(offset=20, length=4)
	public Integer getIssuer() {
		return issuer;
	}
	public void setIssuer(Integer issuer) {
		this.issuer = issuer;
	}
	@Field(offset=24, length=18)
	public Quartile3 getCreditScore() {
		return creditScore;
	}
	public void setCreditScore(Quartile3 creditScore) {
		this.creditScore = creditScore;
	}
	@Field(offset=42, length=24)
	public StatsElement24 getCreditScoreNotAvailable() {
		return creditScoreNotAvailable;
	}
	public void setCreditScoreNotAvailable(StatsElement24 creditScoreNotAvailable) {
		this.creditScoreNotAvailable = creditScoreNotAvailable;
	}
	@Field(offset=66, length=24)
	public Quartile4 getDebtIncomeRatio() {
		return debtIncomeRatio;
	}
	public void setDebtIncomeRatio(Quartile4 debtIncomeRatio) {
		this.debtIncomeRatio = debtIncomeRatio;
	}
	@Field(offset=90, length=24)
	public StatsElement24 getDebtIncomeRatioNotAvailable() {
		return debtIncomeRatioNotAvailable;
	}
	public void setDebtIncomeRatioNotAvailable(
			StatsElement24 debtIncomeRatioNotAvailable) {
		this.debtIncomeRatioNotAvailable = debtIncomeRatioNotAvailable;
	}
	@Field(offset=114, length=24)
	public StatsElement24 getDownPaymentAssistance() {
		return downPaymentAssistance;
	}
	public void setDownPaymentAssistance(StatsElement24 downPaymentAssistance) {
		this.downPaymentAssistance = downPaymentAssistance;
	}
	@Field(offset=138, length=24)
	public StatsElement24 getWithoutPaymentAssistance() {
		return withoutPaymentAssistance;
	}
	public void setWithoutPaymentAssistance(StatsElement24 withoutPaymentAssistance) {
		this.withoutPaymentAssistance = withoutPaymentAssistance;
	}
	@Field(offset=162, length=24)
	public StatsElement24 getFhaPurchase() {
		return fhaPurchase;
	}
	public void setFhaPurchase(StatsElement24 fhaPurchase) {
		this.fhaPurchase = fhaPurchase;
	}
	@Field(offset=186, length=24)
	public StatsElement24 getFhaRefinance() {
		return fhaRefinance;
	}
	public void setFhaRefinance(StatsElement24 fhaRefinance) {
		this.fhaRefinance = fhaRefinance;
	}
	@Field(offset=210, length=24)
	public StatsElement24 getFhaHampModified() {
		return fhaHampModified;
	}
	public void setFhaHampModified(StatsElement24 fhaHampModified) {
		this.fhaHampModified = fhaHampModified;
	}
	@Field(offset=234, length=24)
	public StatsElement24 getFhaNonHampModified() {
		return fhaNonHampModified;
	}
	public void setFhaNonHampModified(StatsElement24 fhaNonHampModified) {
		this.fhaNonHampModified = fhaNonHampModified;
	}
	@Field(offset=258, length=24)
	public StatsElement24 getFhaPurposeNotAvailable() {
		return fhaPurposeNotAvailable;
	}
	public void setFhaPurposeNotAvailable(StatsElement24 fhaPurposeNotAvailable) {
		this.fhaPurposeNotAvailable = fhaPurposeNotAvailable;
	}
	@Field(offset=282, length=24)
	public StatsElement24 getVaPurchase() {
		return vaPurchase;
	}
	public void setVaPurchase(StatsElement24 vaPurchase) {
		this.vaPurchase = vaPurchase;
	}
	@Field(offset=306, length=24)
	public StatsElement24 getVaRefinance() {
		return vaRefinance;
	}
	public void setVaRefinance(StatsElement24 vaRefinance) {
		this.vaRefinance = vaRefinance;
	}
	@Field(offset=330, length=24)
	public StatsElement24 getVaHampModified() {
		return vaHampModified;
	}
	public void setVaHampModified(StatsElement24 vaHampModified) {
		this.vaHampModified = vaHampModified;
	}
	@Field(offset=354, length=24)
	public StatsElement24 getVaNonHampModified() {
		return vaNonHampModified;
	}
	public void setVaNonHampModified(StatsElement24 vaNonHampModified) {
		this.vaNonHampModified = vaNonHampModified;
	}
	@Field(offset=378, length=24)
	public StatsElement24 getVaPurposeNotAvailable() {
		return vaPurposeNotAvailable;
	}
	public void setVaPurposeNotAvailable(StatsElement24 vaPurposeNotAvailable) {
		this.vaPurposeNotAvailable = vaPurposeNotAvailable;
	}
	@Field(offset=402, length=24)
	public StatsElement24 getPihPurchase() {
		return pihPurchase;
	}
	public void setPihPurchase(StatsElement24 pihPurchase) {
		this.pihPurchase = pihPurchase;
	}
	@Field(offset=426, length=24)
	public StatsElement24 getPihRefinance() {
		return pihRefinance;
	}
	public void setPihRefinance(StatsElement24 pihRefinance) {
		this.pihRefinance = pihRefinance;
	}
	@Field(offset=450, length=24)
	public StatsElement24 getPihHampModified() {
		return pihHampModified;
	}
	public void setPihHampModified(StatsElement24 pihHampModified) {
		this.pihHampModified = pihHampModified;
	}
	@Field(offset=474, length=24)
	public StatsElement24 getPihNonHampModified() {
		return pihNonHampModified;
	}
	public void setPihNonHampModified(StatsElement24 pihNonHampModified) {
		this.pihNonHampModified = pihNonHampModified;
	}
	@Field(offset=498, length=24)
	public StatsElement24 getPihPurposeNotAvailable() {
		return pihPurposeNotAvailable;
	}
	public void setPihPurposeNotAvailable(StatsElement24 pihPurposeNotAvailable) {
		this.pihPurposeNotAvailable = pihPurposeNotAvailable;
	}
	@Field(offset=522, length=24)
	public StatsElement24 getRdPurchase() {
		return rdPurchase;
	}
	public void setRdPurchase(StatsElement24 rdPurchase) {
		this.rdPurchase = rdPurchase;
	}
	@Field(offset=546, length=24)
	public StatsElement24 getRdRefinance() {
		return rdRefinance;
	}
	public void setRdRefinance(StatsElement24 rdRefinance) {
		this.rdRefinance = rdRefinance;
	}
	@Field(offset=570, length=24)
	public StatsElement24 getRdHampModified() {
		return rdHampModified;
	}
	public void setRdHampModified(StatsElement24 rdHampModified) {
		this.rdHampModified = rdHampModified;
	}
	@Field(offset=594, length=24)
	public StatsElement24 getRdNonHampModified() {
		return rdNonHampModified;
	}
	public void setRdNonHampModified(StatsElement24 rdNonHampModified) {
		this.rdNonHampModified = rdNonHampModified;
	}
	@Field(offset=618, length=24)
	public StatsElement24 getRdPurposeNotAvailable() {
		return rdPurposeNotAvailable;
	}
	public void setRdPurposeNotAvailable(StatsElement24 rdPurposeNotAvailable) {
		this.rdPurposeNotAvailable = rdPurposeNotAvailable;
	}
	@Field(offset=642, length=24)
	public StatsElement24 getNonStreamlinedRefi() {
		return nonStreamlinedRefi;
	}
	public void setNonStreamlinedRefi(StatsElement24 nonStreamlinedRefi) {
		this.nonStreamlinedRefi = nonStreamlinedRefi;
	}
	@Field(offset=666, length=24)
	public StatsElement24 getCashOutRefi() {
		return cashOutRefi;
	}
	public void setCashOutRefi(StatsElement24 cashOutRefi) {
		this.cashOutRefi = cashOutRefi;
	}
	@Field(offset=690, length=24)
	public StatsElement24 getStreamlinedRefi() {
		return streamlinedRefi;
	}
	public void setStreamlinedRefi(StatsElement24 streamlinedRefi) {
		this.streamlinedRefi = streamlinedRefi;
	}
	@Field(offset=714, length=24)
	public StatsElement24 getFhaShortRefinance() {
		return fhaShortRefinance;
	}
	public void setFhaShortRefinance(StatsElement24 fhaShortRefinance) {
		this.fhaShortRefinance = fhaShortRefinance;
	}
	@Field(offset=738, length=24)
	public StatsElement24 getRefinanceTypeNotAvailable() {
		return refinanceTypeNotAvailable;
	}
	public void setRefinanceTypeNotAvailable(
			StatsElement24 refinanceTypeNotAvailable) {
		this.refinanceTypeNotAvailable = refinanceTypeNotAvailable;
	}
	@Field(offset=762, length=6)
	public Integer getAsOfDate() {
		return asOfDate;
	}
	public void setAsOfDate(Integer asOfDate) {
		this.asOfDate = asOfDate;
	}
	@Override
	public String toString() {
		return "SupplementalDaily [poolNumber=" + poolNumber + ", creditScore="
				+ creditScore + ", creditScoreNotAvailable="
				+ creditScoreNotAvailable + ", debtIncomeRatio="
				+ debtIncomeRatio + ", debIncomeRatioNotAvailable="
				+ debtIncomeRatioNotAvailable + ", downPaymentAssistance="
				+ downPaymentAssistance + ", withoutPaymentAssistance="
				+ withoutPaymentAssistance + ", fhaPurchase=" + fhaPurchase
				+ ", fhaRefinance=" + fhaRefinance + ", fhaHampModified="
				+ fhaHampModified + ", fhaNonHampModified="
				+ fhaNonHampModified + ", fhaPurposeNotAvailable="
				+ fhaPurposeNotAvailable + ", vaPurchase=" + vaPurchase
				+ ", vaRefinance=" + vaRefinance + ", vaHampModified="
				+ vaHampModified + ", vaNonHampModified=" + vaNonHampModified
				+ ", vaPurposeNotAvailable=" + vaPurposeNotAvailable
				+ ", pihPurchase=" + pihPurchase + ", pihRefinance="
				+ pihRefinance + ", pihHampModified=" + pihHampModified
				+ ", pihNonHampModified=" + pihNonHampModified
				+ ", pihPurposeNotAvailable=" + pihPurposeNotAvailable
				+ ", rdPurchase=" + rdPurchase + ", rdRefinance=" + rdRefinance
				+ ", rdHampModified=" + rdHampModified + ", rdNonHampModified="
				+ rdNonHampModified + ", rdPurposeNotAvailable="
				+ rdPurposeNotAvailable + ", nonStreamlinedRefi="
				+ nonStreamlinedRefi + ", cashOutRefi=" + cashOutRefi
				+ ", streamlinedRefi=" + streamlinedRefi
				+ ", fhaShortRefinance=" + fhaShortRefinance
				+ ", refinanceTypeNotAvailable=" + refinanceTypeNotAvailable
				+ "]";
	}

}
