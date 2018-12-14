package com.yieldbook.mortgage.domain.gnma;
import com.ancientprogramming.fixedformat4j.annotation.Field;
import com.ancientprogramming.fixedformat4j.annotation.Record;
/**
 * @author fx36019
 *
 */
@Record
public class GnmaLoanHeader {

	private String recordType;
	private String cusip;
	private String poolId;
	private String issueType;
	private String poolType;
	private String poolIssueDate;
	private String issueId;
	private String asOfDate;

	@Field(offset = 1, length = 1)	
	public String getRecordType() {
		return recordType;
	}

	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}
	
	@Field(offset = 2, length = 9)	
	public String getCusip() {
		return cusip;
	}
	
	public void setCusip(String cusip) {
		this.cusip = cusip;
	}
	
	@Field(offset = 11, length = 6)	
	public String getPoolId() {
		return poolId;
	}
	
	public void setPoolId(String poolId) {
		this.poolId = poolId;
	}
	
	@Field(offset = 17, length = 1)	
	public String getIssueType() {
		return issueType;
	}

	public void setIssueType(String issueType) {
		this.issueType = issueType;
	}

	@Field(offset = 18, length = 2)	
	public String getPoolType() {
		return poolType;
	}

	public void setPoolType(String poolType) {
		this.poolType = poolType;
	}
	
	@Field(offset = 20, length = 8)	
	public String getPoolIssueDate() {
		return poolIssueDate;
	}
	
	public void setPoolIssueDate(String poolIssueDate) {
		this.poolIssueDate = poolIssueDate;
	}
	
	@Field(offset = 28, length = 4)	
	public String getIssueId() {
		return issueId;
	}
	public void setIssueId(String issueId) {
		this.issueId = issueId;
	}

	@Field(offset = 32, length = 6)	
	public String getAsOfDate() {
		return asOfDate;
	}
	
	public void setAsOfDate(String asOfDate) {
		this.asOfDate = asOfDate;
	}
	
	@Override
	public String toString() {
		
		return  cusip + "|" + poolId + "|" + issueType
				+ "|" + poolType + "|" + poolIssueDate
				+ "|" + issueId + "|" + asOfDate+"01";
	}
	

	
}
