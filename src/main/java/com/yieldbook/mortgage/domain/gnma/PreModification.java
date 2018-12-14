package com.yieldbook.mortgage.domain.gnma;

import com.ancientprogramming.fixedformat4j.annotation.Field;
import com.ancientprogramming.fixedformat4j.annotation.Record;

@Record
public class PreModification {
	private String cusip;
	private String poolNumber;
	private String poolIndicator;
	private String poolType;
	private String recrodType;
	private Integer issuer;
	
	private Quartile3 lad;
	private StatsItem29 PreModifiedFirstPaymentDate;
	private Quartile10 preModifiedOls;
	private StatsItem29 withPreModifiedOpb;
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
	public Quartile3 getLad() {
		return lad;
	}
	public void setLad(Quartile3 lad) {
		this.lad = lad;
	}
	@Field(offset=42, length=29)
	public StatsItem29 getPreModifiedFirstPaymentDate() {
		return PreModifiedFirstPaymentDate;
	}
	public void setPreModifiedFirstPaymentDate(
			StatsItem29 preModifiedFirstPaymentDate) {
		PreModifiedFirstPaymentDate = preModifiedFirstPaymentDate;
	}
	@Field(offset=71, length=60)
	public Quartile10 getPreModifiedOls() {
		return preModifiedOls;
	}
	public void setPreModifiedOls(Quartile10 preModifiedOls) {
		this.preModifiedOls = preModifiedOls;
	}
	@Field(offset=131, length=29)
	public StatsItem29 getWithPreModifiedOpb() {
		return withPreModifiedOpb;
	}
	public void setWithPreModifiedOpb(StatsItem29 withPreModifiedOpb) {
		this.withPreModifiedOpb = withPreModifiedOpb;
	}
	@Field(offset=160, length=6)
	public Integer getAsOfDate() {
		return asOfDate;
	}
	public void setAsOfDate(Integer asOfDate) {
		this.asOfDate = asOfDate;
	}
	@Override
	public String toString() {
		return "PreModification [poolNumber=" + poolNumber + ", lad=" + lad
				+ ", PreModifiedFirstPaymentDate="
				+ PreModifiedFirstPaymentDate + ", preModifiedAols="
				+ preModifiedOls + ", withPreModifiedOpb="
				+ withPreModifiedOpb + "]";
	}


}
