package com.yieldbook.mortgage.domain.gnma;

import com.ancientprogramming.fixedformat4j.annotation.Field;
import com.ancientprogramming.fixedformat4j.annotation.Record;

@Record
public class OriginationYear {
	private String cusip;
	private String poolNumber;
	private String poolIndicator;
	private String poolType;
	private String recrodType;
	
	private StatsElement33 originationYear1;
	private StatsElement33 originationYear2;
	private StatsElement33 originationYear3;
	private StatsElement33 originationYear4;
	private StatsElement33 originationYearAllOthers;

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
	@Field(offset=20, length=33)
	public StatsElement33 getOriginationYear1() {
		return originationYear1;
	}
	public void setOriginationYear1(StatsElement33 originationYear1) {
		this.originationYear1 = originationYear1;
	}
	@Field(offset=53, length=33)
	public StatsElement33 getOriginationYear2() {
		return originationYear2;
	}
	public void setOriginationYear2(StatsElement33 originationYear2) {
		this.originationYear2 = originationYear2;
	}
	@Field(offset=86, length=33)
	public StatsElement33 getOriginationYear3() {
		return originationYear3;
	}
	public void setOriginationYear3(StatsElement33 originationYear3) {
		this.originationYear3 = originationYear3;
	}
	@Field(offset=119, length=33)
	public StatsElement33 getOriginationYear4() {
		return originationYear4;
	}
	public void setOriginationYear4(StatsElement33 originationYear4) {
		this.originationYear4 = originationYear4;
	}
	@Field(offset=152, length=33)
	public StatsElement33 getOriginationYearAllOthers() {
		return originationYearAllOthers;
	}
	public void setOriginationYearAllOthers(StatsElement33 originationYearAllOthers) {
		this.originationYearAllOthers = originationYearAllOthers;
	}
	@Field(offset=185, length=6)
	public Integer getAsOfDate() {
		return asOfDate;
	}
	public void setAsOfDate(Integer asOfDate) {
		this.asOfDate = asOfDate;
	}
	@Override
	public String toString() {
		return "OriginationYear [poolNumber=" + poolNumber
				+ ", originationYear1=" + originationYear1
				+ ", originationYear2=" + originationYear2
				+ ", originationYear3=" + originationYear3
				+ ", originationYear4=" + originationYear4
				+ ", originationYearAllOthers=" + originationYearAllOthers
				+ "]";
	}
	
}
