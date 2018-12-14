package com.yieldbook.mortgage.domain.gnma;

import com.ancientprogramming.fixedformat4j.annotation.Align;
import com.ancientprogramming.fixedformat4j.annotation.Field;
import com.ancientprogramming.fixedformat4j.annotation.Record;

@Record
public class MultiIssuer {
	private String cusip;
	private String poolNumber;
	private String poolIndicator;
	private String poolType;
	private String recrodType;
	private String issuerNumber;
	private String issuerName;
	private StatsElement24 issuer;
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
	@Field(offset=20, length=4, align=Align.RIGHT, paddingChar='0')
	public String getIssuerNumber() {
		return issuerNumber;
	}
	public void setIssuerNumber(String issuerNumber) {
		this.issuerNumber = issuerNumber;
	}
	@Field(offset=24, length=40)
	public String getIssuerName() {
		return issuerName;
	}
	public void setIssuerName(String issuerName) {
		this.issuerName = issuerName;
	}
	@Field(offset=64, length=24)
	public StatsElement24 getIssuer() {
		return issuer;
	}
	public void setIssuer(StatsElement24 issuer) {
		this.issuer = issuer;
	}
	@Field(offset=88, length=6)
	public Integer getAsOfDate() {
		return asOfDate;
	}
	public void setAsOfDate(Integer asOfDate) {
		this.asOfDate = asOfDate;
	}	
	@Override
	public String toString() {
		return "MultiIssuer [poolNumber=" + poolNumber + ", issuerNumber="
				+ issuerNumber + ", issuerName=" + issuerName + ", issuer="
				+ issuer + "]";
	}
	

}
