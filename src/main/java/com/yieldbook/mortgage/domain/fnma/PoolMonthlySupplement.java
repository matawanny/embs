package com.yieldbook.mortgage.domain.fnma;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ancientprogramming.fixedformat4j.annotation.Align;
import com.ancientprogramming.fixedformat4j.annotation.Field;
import com.ancientprogramming.fixedformat4j.annotation.FixedFormatPattern;
import com.ancientprogramming.fixedformat4j.annotation.Record;

@Record
public class PoolMonthlySupplement {
	private Integer recordType;
	private String poolNumber;
	private String poolPrefix;
	private Integer reportingPeriod;
	private String cusipNumber;
	private Date issueDate;
	private Long currentUPB;
	private Integer totalActiveLoanCount;
	private String sellerName;
	private String servicerName;
	private String filler;
	private PoolMonthlyQuartile quartile;
	protected List<PoolMonthlyServicer> servicers = new ArrayList<>();
	private PoolMonthlyThirdPartyOriginationType thirdPartyOriginationType;
	private PoolMonthlyInterestOnly interestOnly;

	
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
	@Field(offset=8, length=3, align=Align.RIGHT, paddingChar=' ')  
	public String getPoolPrefix() {
		return poolPrefix;
	}
	public void setPoolPrefix(String poolPrefix) {
		this.poolPrefix = poolPrefix;
	}
	@Field(offset=11, length=6, align=Align.RIGHT, paddingChar=' ') 	
	public Integer getReportingPeriod() {
		return reportingPeriod;
	}
	public void setReportingPeriod(Integer reportingPeriod) {
		this.reportingPeriod = reportingPeriod;
	}
	@Field(offset=17, length=9)  
	public String getCusipNumber() {
		return cusipNumber;
	}
	public void setCusipNumber(String cusipNumber) {
		this.cusipNumber = cusipNumber;
	}	
	@Field(offset=26, length=6)
	@FixedFormatPattern("MMddyy")
	public Date getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}
	@Field(offset=32, length=15, align=Align.RIGHT, paddingChar='0') 
	public Long getCurrentUPB() {
		return currentUPB;
	}
	public void setCurrentUPB(Long currentUPB) {
		this.currentUPB = currentUPB;
	}
	@Field(offset=47, length=8, align=Align.RIGHT, paddingChar='0') 
	public Integer getTotalActiveLoanCount() {
		return totalActiveLoanCount;
	}
	public void setTotalActiveLoanCount(Integer totalActiveLoanCount) {
		this.totalActiveLoanCount = totalActiveLoanCount;
	}
	@Field(offset=55, length=40, align=Align.LEFT, paddingChar=' ') 
	public String getSellerName() {
		return sellerName;
	}
	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}
	@Field(offset=95, length=40, align=Align.LEFT, paddingChar=' ') 	
	public String getServicerName() {
		return servicerName;
	}
	public void setServicerName(String servicerName) {
		this.servicerName = servicerName;
	}
	@Field(offset=135, length=166, align=Align.LEFT, paddingChar=' ') 
	public String getFiller() {
		return filler;
	}
	public void setFiller(String filler) {
		this.filler = filler;
	}
	public PoolMonthlyQuartile getQuartile() {
		return quartile;
	}
	public void setQuartile(PoolMonthlyQuartile quartile) {
		this.quartile = quartile;
	}
	public List<PoolMonthlyServicer> getServicers() {
		return servicers;
	}
	public void setServicers(List<PoolMonthlyServicer> servicers) {
		this.servicers = servicers;
	}
	public PoolMonthlyThirdPartyOriginationType getThirdPartyOriginationType() {
		return thirdPartyOriginationType;
	}
	public void setThirdPartyOriginationType(
			PoolMonthlyThirdPartyOriginationType thirdPartyOriginationType) {
		this.thirdPartyOriginationType = thirdPartyOriginationType;
	}
	public PoolMonthlyInterestOnly getInterestOnly() {
		return interestOnly;
	}
	public void setInterestOnly(PoolMonthlyInterestOnly interestOnly) {
		this.interestOnly = interestOnly;
	}
	@Override
	public String toString() {
		return "PoolMonthlySupplement [poolNumber=" + poolNumber
				+ ", poolPrefix=" + poolPrefix + ", reportingPeriod="
				+ reportingPeriod + ", cusipNumber=" + cusipNumber
				+ ", issueDate=" + issueDate + ", currentUPB=" + currentUPB
				+ ", totalActiveLoanCount=" + totalActiveLoanCount
				+ ", sellerName=" + sellerName + ", servicerName="
				+ servicerName + ", quartile=" + quartile + ", servicers="
				+ servicers + ", thirdPartyOriginationType="
				+ thirdPartyOriginationType + ", interestOnly=" + interestOnly
				+ "]";
	}
	
}
