package com.yieldbook.mortgage.domain.gnma;

import com.ancientprogramming.fixedformat4j.annotation.Field;
import com.ancientprogramming.fixedformat4j.annotation.Record;

@Record
public class Msa {
	private String cusip;
	private String poolNumber;
	private String poolIndicator;
	private String poolType;
	private String recrodType;
	private Integer issuer;
	
	private StatsElement29 highestMsa;
	private StatsElement29 highest2ndMsa;
	private StatsElement29 highest3thMsa;
	private StatsElement29 highest4thMsa;
	private StatsElement29 highest5thMsa;
	private StatsElement29 highest6thMsa;
	private StatsElement29 highest7thMsa;
	private StatsElement29 highest8thMsa;
	private StatsElement29 highest9thMsa;
	private StatsElement29 highest10thMsa;
	private StatsElement24 msaNotAvailable;
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
	@Field(offset=24, length=29)
	public StatsElement29 getHighestMsa() {
		return highestMsa;
	}
	public void setHighestMsa(StatsElement29 highestMsa) {
		this.highestMsa = highestMsa;
	}
	@Field(offset=53, length=29)
	public StatsElement29 getHighest2ndMsa() {
		return highest2ndMsa;
	}
	public void setHighest2ndMsa(StatsElement29 highest2ndMsa) {
		this.highest2ndMsa = highest2ndMsa;
	}
	@Field(offset=82, length=29)
	public StatsElement29 getHighest3thMsa() {
		return highest3thMsa;
	}
	public void setHighest3thMsa(StatsElement29 highest3thMsa) {
		this.highest3thMsa = highest3thMsa;
	}
	@Field(offset=111, length=29)
	public StatsElement29 getHighest4thMsa() {
		return highest4thMsa;
	}
	public void setHighest4thMsa(StatsElement29 highest4thMsa) {
		this.highest4thMsa = highest4thMsa;
	}
	@Field(offset=140, length=29)
	public StatsElement29 getHighest5thMsa() {
		return highest5thMsa;
	}
	public void setHighest5thMsa(StatsElement29 highest5thMsa) {
		this.highest5thMsa = highest5thMsa;
	}
	@Field(offset=169, length=29)
	public StatsElement29 getHighest6thMsa() {
		return highest6thMsa;
	}
	public void setHighest6thMsa(StatsElement29 highest6thMsa) {
		this.highest6thMsa = highest6thMsa;
	}
	@Field(offset=198, length=29)
	public StatsElement29 getHighest7thMsa() {
		return highest7thMsa;
	}
	public void setHighest7thMsa(StatsElement29 highest7thMsa) {
		this.highest7thMsa = highest7thMsa;
	}
	@Field(offset=227, length=29)
	public StatsElement29 getHighest8thMsa() {
		return highest8thMsa;
	}
	public void setHighest8thMsa(StatsElement29 highest8thMsa) {
		this.highest8thMsa = highest8thMsa;
	}
	@Field(offset=256, length=29)
	public StatsElement29 getHighest9thMsa() {
		return highest9thMsa;
	}
	public void setHighest9thMsa(StatsElement29 highest9thMsa) {
		this.highest9thMsa = highest9thMsa;
	}
	@Field(offset=285, length=29)
	public StatsElement29 getHighest10thMsa() {
		return highest10thMsa;
	}
	public void setHighest10thMsa(StatsElement29 highest10thMsa) {
		this.highest10thMsa = highest10thMsa;
	}
	@Field(offset=314, length=24)
	public StatsElement24 getMsaNotAvailable() {
		return msaNotAvailable;
	}
	public void setMsaNotAvailable(StatsElement24 msaNotAvailable) {
		this.msaNotAvailable = msaNotAvailable;
	}
	@Field(offset=338, length=6)
	public Integer getAsOfDate() {
		return asOfDate;
	}
	public void setAsOfDate(Integer asOfDate) {
		this.asOfDate = asOfDate;
	}
	@Override
	public String toString() {
		return "Msa [poolNumber=" + poolNumber + ", poolIndicator="
				+ poolIndicator + ", poolType=" + poolType + ", issuer="
				+ issuer + ", highestMsa=" + highestMsa + ", highest2ndMsa="
				+ highest2ndMsa + ", highest3thMsa=" + highest3thMsa
				+ ", highest4thMsa=" + highest4thMsa + ", highest5thMsa="
				+ highest5thMsa + ", highest6thMsa=" + highest6thMsa
				+ ", highest7thMsa=" + highest7thMsa + ", highest8thMsa="
				+ highest8thMsa + ", highest90thMsa=" + highest9thMsa
				+ ", highest10thMsa=" + highest10thMsa + ", msaNotAvailable="
				+ msaNotAvailable + ", asOfDate=" + asOfDate + "]";
	}

	
}
