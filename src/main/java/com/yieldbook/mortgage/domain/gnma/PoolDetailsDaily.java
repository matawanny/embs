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
public class PoolDetailsDaily {
	private String cusip;
	private String poolNumber;
	private String poolIndicator;
	private String poolType;
	private String recrodType;
	private BigDecimal securityInterestRate;
	private Date poolIssueDate;
	private Date poolMaturityDate;
	private BigDecimal originalAggregateAmount;
	private Integer issueNumber;
	private String issueName;
	private BigDecimal securityMargin;
	private Date interestAdjustmentDate;
	private Date paymentAdjustmentDate;
	private Integer numberOfLoansInPool;
	private QuartileElement10 aols;
	private QuartileElement5 wac;
	private QuartileElement3 warm;
	private QuartileElement3 wala;
	private QuartileElement3 waolt;
	private QuartileElement5 wagm;
	private QuartileOriginal3 ltv;
	private StatsElement24 ltvNotAvailable;
	private StatsElement24 purchase;
	private StatsElement24 refinance;
	private StatsElement24 hampModified;
	private StatsElement24 nonHampModified;
	private StatsElement24 loanPurposeNotAvailable;
	private QuartileOriginal3 cltv;
	private BigDecimal weightedAverageOriginalLoanSize;
	private Integer lookBackPeriod;
	private Integer filler1;
	private Integer filler2;
	private Integer filler3;
	private Integer filler4;
	private Integer filler5;
	private Integer filler6;
	private Integer filler7;
	private StatsElement24  oneUnit;
	private StatsElement24 oneToFourUnit;
	private StatsElement24 propertyTypeNotAvailable;

	private BigDecimal poolUpb;
	private String filler8;
	
	private MortgageInsurance mortgageInsurance;
	private Msa msa;
	private OriginationYear originationYear;
	private SupplementalDaily supplemental;
	protected List<State> states=new ArrayList<>();
	protected List<MultiIssuer> multiIssuers=new ArrayList<>();
	protected List<RemovalByIssuer> removalsByIssuer=new ArrayList<>();
	private VariousData variousData;
	private PreModification preModification;
	private TransferActivity transferActivity;
	private AdjustableRateMortgage adjustableRateMortgage;
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
	@Field(offset=20, length=5, align=Align.RIGHT, paddingChar='0')
	@FixedFormatDecimal (decimals=3, useDecimalDelimiter=false)
	public BigDecimal getSecurityInterestRate() {
		return securityInterestRate;
	}
	public void setSecurityInterestRate(BigDecimal securityInterestRate) {
		this.securityInterestRate = securityInterestRate;
	}
	@Field(offset=25, length=8, align=Align.RIGHT, paddingChar='9')
	@FixedFormatPattern("yyyyMMdd")
	public Date getPoolIssueDate() {
		return poolIssueDate;
	}
	public void setPoolIssueDate(Date poolIssueDate) {
		this.poolIssueDate = poolIssueDate;
	}
	@Field(offset=33, length=8, align=Align.RIGHT, paddingChar='9')
	@FixedFormatPattern("yyyyMMdd")
	public Date getPoolMaturityDate() {
		return poolMaturityDate;
	}
	public void setPoolMaturityDate(Date poolMaturityDate) {
		this.poolMaturityDate = poolMaturityDate;
	}
	@Field(offset=41, length=15, align=Align.RIGHT, paddingChar='0')
	@FixedFormatDecimal (decimals=2, useDecimalDelimiter=false)	
	public BigDecimal getOriginalAggregateAmount() {
		return originalAggregateAmount;
	}
	public void setOriginalAggregateAmount(BigDecimal originalAggregateAmount) {
		this.originalAggregateAmount = originalAggregateAmount;
	}
	@Field(offset=56, length=4)
	public Integer getIssueNumber() {
		return issueNumber;
	}
	public void setIssueNumber(Integer issueNumber) {
		this.issueNumber = issueNumber;
	}
	@Field(offset=60, length=40)
	public String getIssueName() {
		return issueName;
	}
	public void setIssueName(String issueName) {
		this.issueName = issueName;
	}
	@Field(offset=100, length=4, align=Align.RIGHT, paddingChar='0')
	@FixedFormatDecimal (decimals=3, useDecimalDelimiter=false)		
	public BigDecimal getSecurityMargin() {
		return securityMargin;
	}
	public void setSecurityMargin(BigDecimal securityMargin) {
		this.securityMargin = securityMargin;
	}
	@Field(offset=104, length=8, align=Align.RIGHT, paddingChar='9')
	@FixedFormatPattern("yyyyMMdd")
	public Date getInterestAdjustmentDate() {
		return interestAdjustmentDate;
	}
	public void setInterestAdjustmentDate(Date interestAdjustmentDate) {
		this.interestAdjustmentDate = interestAdjustmentDate;
	}
	@Field(offset=112, length=8, align=Align.RIGHT, paddingChar='9')
	@FixedFormatPattern("yyyyMMdd")
	public Date getPaymentAdjustmentDate() {
		return paymentAdjustmentDate;
	}
	public void setPaymentAdjustmentDate(Date paymentAdjustmentDate) {
		this.paymentAdjustmentDate = paymentAdjustmentDate;
	}
	@Field(offset=120, length=6)
	public Integer getNumberOfLoansInPool() {
		return numberOfLoansInPool;
	}
	public void setNumberOfLoansInPool(Integer numberOfLoansInPool) {
		this.numberOfLoansInPool = numberOfLoansInPool;
	}
	@Field(offset=126, length=60)
	public QuartileElement10 getAols() {
		return aols;
	}
	public void setAols(QuartileElement10 aols) {
		this.aols = aols;
	}
	@Field(offset=186, length=30)
	public QuartileElement5 getWac() {
		return wac;
	}
	public void setWac(QuartileElement5 wac) {
		this.wac = wac;
	}
	@Field(offset=216, length=18)
	public QuartileElement3 getWarm() {
		return warm;
	}
	public void setWarm(QuartileElement3 warm) {
		this.warm = warm;
	}
	@Field(offset=234, length=18)
	public QuartileElement3 getWala() {
		return wala;
	}
	public void setWala(QuartileElement3 wala) {
		this.wala = wala;
	}
	@Field(offset=252, length=18)
	public QuartileElement3 getWaolt() {
		return waolt;
	}
	public void setWaolt(QuartileElement3 waolt) {
		this.waolt = waolt;
	}
	@Field(offset=270, length=30)
	public QuartileElement5 getWagm() {
		return wagm;
	}
	public void setWagm(QuartileElement5 wagm) {
		this.wagm = wagm;
	}
	@Field(offset=300, length=18)
	public QuartileOriginal3 getLtv() {
		return ltv;
	}
	public void setLtv(QuartileOriginal3 ltv) {
		this.ltv = ltv;
	}

	@Field(offset=318, length=24)	
	public StatsElement24 getLtvNotAvailable() {
		return ltvNotAvailable;
	}
	public void setLtvNotAvailable(StatsElement24 ltvNotAvailable) {
		this.ltvNotAvailable = ltvNotAvailable;
	}
	@Field(offset=342, length=24)
	public StatsElement24 getPurchase() {
		return purchase;
	}
	public void setPurchase(StatsElement24 purchase) {
		this.purchase = purchase;
	}
	@Field(offset=366, length=24)
	public StatsElement24 getRefinance() {
		return refinance;
	}
	public void setRefinance(StatsElement24 refinance) {
		this.refinance = refinance;
	}
	@Field(offset=390, length=24)	
	public StatsElement24 getHampModified() {
		return hampModified;
	}
	public void setHampModified(StatsElement24 hampModified) {
		this.hampModified = hampModified;
	}
	@Field(offset=414, length=24)
	public StatsElement24 getNonHampModified() {
		return nonHampModified;
	}
	public void setNonHampModified(StatsElement24 nonHampModified) {
		this.nonHampModified = nonHampModified;
	}
	@Field(offset=438, length=24)
	public StatsElement24 getLoanPurposeNotAvailable() {
		return loanPurposeNotAvailable;
	}
	public void setLoanPurposeNotAvailable(StatsElement24 loanPurposeNotAvailable) {
		this.loanPurposeNotAvailable = loanPurposeNotAvailable;
	}
	@Field(offset=462, length=18)
	public QuartileOriginal3 getCltv() {
		return cltv;
	}
	public void setCltv(QuartileOriginal3 cltv) {
		this.cltv = cltv;
	}
	@Field(offset=480, length=10, align=Align.RIGHT, paddingChar='0')
	@FixedFormatDecimal (decimals=2, useDecimalDelimiter=false)	
	public BigDecimal getWeightedAverageOriginalLoanSize() {
		return weightedAverageOriginalLoanSize;
	}
	public void setWeightedAverageOriginalLoanSize(
			BigDecimal weightedAverageOriginalLoanSize) {
		this.weightedAverageOriginalLoanSize = weightedAverageOriginalLoanSize;
	}
	@Field(offset=490, length=1, align=Align.RIGHT, paddingChar='0')
	public Integer getLookBackPeriod() {
		return lookBackPeriod;
	}
	public void setLookBackPeriod(Integer lookBackPeriod) {
		this.lookBackPeriod = lookBackPeriod;
	}
	@Field(offset=491, length=1, align=Align.RIGHT, paddingChar='0')
	public Integer getFiller1() {
		return filler1;
	}
	public void setFiller1(Integer filler1) {
		this.filler1 = filler1;
	}
	@Field(offset=492, length=4, align=Align.RIGHT, paddingChar='0')

	public Integer getFiller2() {
		return filler2;
	}
	public void setFiller2(Integer filler2) {
		this.filler2 = filler2;
	}
	@Field(offset=496, length=7, align=Align.RIGHT, paddingChar='0')
	public Integer getFiller3() {
		return filler3;
	}
	public void setFiller3(Integer filler3) {
		this.filler3 = filler3;
	}
	@Field(offset=503, length=7, align=Align.RIGHT, paddingChar='0')
	public Integer getFiller4() {
		return filler4;
	}
	public void setFiller4(Integer filler4) {
		this.filler4 = filler4;
	}
	@Field(offset=510, length=6, align=Align.RIGHT, paddingChar='0')
	public Integer getFiller5() {
		return filler5;
	}
	public void setFiller5(Integer filler5) {
		this.filler5 = filler5;
	}
	@Field(offset=516, length=13, align=Align.RIGHT, paddingChar='0')
	@FixedFormatDecimal (decimals=2, useDecimalDelimiter=false)	

	public Integer getFiller6() {
		return filler6;
	}
	public void setFiller6(Integer filler6) {
		this.filler6 = filler6;
	}
	@Field(offset=529, length=5, align=Align.RIGHT, paddingChar='0')
	@FixedFormatDecimal (decimals=2, useDecimalDelimiter=false)	
	public Integer getFiller7() {
		return filler7;
	}
	public void setFiller7(Integer filler7) {
		this.filler7 = filler7;
	}

	@Field(offset=534, length=24)
	public StatsElement24 getOneUnit() {
		return oneUnit;
	}
	public void setOneUnit(StatsElement24 oneUnit) {
		this.oneUnit = oneUnit;
	}
	@Field(offset=558, length=24)
	public StatsElement24 getOneToFourUnit() {
		return oneToFourUnit;
	}
	public void setOneToFourUnit(StatsElement24 oneToFourUnit) {
		this.oneToFourUnit = oneToFourUnit;
	}
	@Field(offset=582, length=24)
	public StatsElement24 getPropertyTypeNotAvailable() {
		return propertyTypeNotAvailable;
	}
	public void setPropertyTypeNotAvailable(StatsElement24 propertyTypeNotAvailable) {
		this.propertyTypeNotAvailable = propertyTypeNotAvailable;
	}
	@Field(offset=606, length=15, align=Align.RIGHT, paddingChar='0')
	@FixedFormatDecimal (decimals=2, useDecimalDelimiter=false)	
	public BigDecimal getPoolUpb() {
		return poolUpb;
	}
	public void setPoolUpb(BigDecimal poolUpb) {
		this.poolUpb = poolUpb;
	}
	@Field(offset=621, length=9, align=Align.RIGHT, paddingChar=' ')
	public String getFiller8() {
		return filler8;
	}
	public void setFiller8(String filler8) {
		this.filler8 = filler8;
	}
	@Field(offset=630, length=8, align=Align.RIGHT, paddingChar='0')
	public Integer getAsOfDate() {
		return asOfDate;
	}
	public void setAsOfDate(Integer asOfDate) {
		this.asOfDate = asOfDate;
	}
	
	public MortgageInsurance getMortgageInsurance() {
		return mortgageInsurance;
	}
	public void setMortgageInsurance(MortgageInsurance mortgageInsurance) {
		this.mortgageInsurance = mortgageInsurance;
	}
	public Msa getMsa() {
		return msa;
	}
	public void setMsa(Msa msa) {
		this.msa = msa;
	}
	public OriginationYear getOriginationYear() {
		return originationYear;
	}
	public void setOriginationYear(OriginationYear originationYear) {
		this.originationYear = originationYear;
	}
	public List<State> getStates() {
		return states;
	}
	public void setStates(List<State> states) {
		this.states = states;
	}
	public SupplementalDaily getSupplemental() {
		return supplemental;
	}
	public void setSupplement(SupplementalDaily supplemental) {
		this.supplemental = supplemental;
	}
	
	public List<MultiIssuer> getMultiIssuers() {
		return multiIssuers;
	}
	public void setMultiIssuers(List<MultiIssuer> multiIssuers) {
		this.multiIssuers = multiIssuers;
	}
	public VariousData getVariousData() {
		return variousData;
	}
	public void setVariousData(VariousData variousData) {
		this.variousData = variousData;
	}
	
	public List<RemovalByIssuer> getRemovalsByIssuer() {
		return removalsByIssuer;
	}
	public void setRemovalsByIssuer(List<RemovalByIssuer> removalsByIssuer) {
		this.removalsByIssuer = removalsByIssuer;
	}
	public PreModification getPreModification() {
		return preModification;
	}
	public void setPreModification(PreModification preModification) {
		this.preModification = preModification;
	}
	
	public TransferActivity getTransferActivity() {
		return transferActivity;
	}
	public void setTransferActivity(TransferActivity transferActivity) {
		this.transferActivity = transferActivity;
	}
	public AdjustableRateMortgage getAdjustableRateMortgage() {
		return adjustableRateMortgage;
	}
	public void setAdjustableRateMortgage(
			AdjustableRateMortgage adjustableRateMortgage) {
		this.adjustableRateMortgage = adjustableRateMortgage;
	}
	@Override
	public String toString() {
		return "PoolDetailsDaily [cusip=" + cusip + ", poolNumber="
				+ poolNumber + ", poolIndicator=" + poolIndicator
				+ ", poolType=" + poolType + ", securityInterestRate="
				+ securityInterestRate + ", poolIssueDate=" + poolIssueDate
				+ ", poolMaturityDate=" + poolMaturityDate
				+ ", originalAggregateAmount=" + originalAggregateAmount
				+ ", issueNumber=" + issueNumber + ", issueName=" + issueName
				+ ", securityMargin=" + securityMargin
				+ ", interestAdjustmentDate=" + interestAdjustmentDate
				+ ", paymentAjustmentDate=" + paymentAdjustmentDate
				+ ", numberOfLoansInPool=" + numberOfLoansInPool + ", aols="
				+ aols + ", wac=" + wac + ", warm=" + warm + ", wala=" + wala
				+ ", waolt=" + waolt + ", wagm=" + wagm + ", ltv=" + ltv
				+ ", ltvNotAvailable=" + ltvNotAvailable + ", purchase="
				+ purchase + ", refinance=" + refinance + ", hampModified="
				+ hampModified + ", nonHampModified=" + nonHampModified
				+ ", loanPurposeNotAvailabe=" + loanPurposeNotAvailable
				+ ", cltv=" + cltv + ", weightedAverageOriginalLoanSize="
				+ weightedAverageOriginalLoanSize + ", lookBackPeriod="
				+ lookBackPeriod + ", oneUnit=" + oneUnit + ", oneToFourUnit="
				+ oneToFourUnit + ", propertyTypeNotAvailable="
				+ propertyTypeNotAvailable + ", poolUpb=" + poolUpb
				+ ", asOfDate=" + asOfDate + ", mortgageInsurance="
				+ mortgageInsurance + ", msa=" + msa + ", originationYear="
				+ originationYear + ", supplement=" + supplemental + ", states="
				+ states + "]";
	}

	

}
