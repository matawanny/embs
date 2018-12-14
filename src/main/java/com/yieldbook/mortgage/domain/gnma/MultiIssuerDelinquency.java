package com.yieldbook.mortgage.domain.gnma;

import com.ancientprogramming.fixedformat4j.annotation.Field;
import com.ancientprogramming.fixedformat4j.annotation.Record;

@Record
public class MultiIssuerDelinquency {
	private String cusip;
	private String poolNumber;
	private String poolIndicator;
	private String poolType;
	private String recrodType;
	private Integer issuer;
	private StatsElement24 thirtyDaysDelinquent;
	private StatsElement24 sixtyDaysDelinquent;
	private StatsElement24 nightyPlusDaysDelinquent;
	private StatsElement24 fhaThirtyDaysDeliquent;
	private StatsElement24 fhaSixtyDaysDeliquent;
	private StatsElement24 fhaNinetyPlusDaysDeliquent;
	private StatsElement24 vaThirtyDaysDeliquent;
	private StatsElement24 vaSixtyDaysDeliquent;
	private StatsElement24 vaNinetyPlusDaysDeliquent;
	private StatsElement24 rdThirtyDaysDeliquent;
	private StatsElement24 rdSixtyDaysDeliquent;
	private StatsElement24 rdNinetyPlusDaysDeliquent;
	private StatsElement24 pihThirtyDaysDeliquent;
	private StatsElement24 pihSixtyDaysDeliquent;
	private StatsElement24 pifNinetyPlusDaysDeliquent;	
	
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
	@Field(offset=24, length=24)
	public StatsElement24 getThirtyDaysDelinquent() {
		return thirtyDaysDelinquent;
	}
	public void setThirtyDaysDelinquent(StatsElement24 thirtyDaysDelinquent) {
		this.thirtyDaysDelinquent = thirtyDaysDelinquent;
	}
	@Field(offset=48, length=24)
	public StatsElement24 getSixtyDaysDelinquent() {
		return sixtyDaysDelinquent;
	}
	public void setSixtyDaysDelinquent(StatsElement24 sixtyDaysDelinquent) {
		this.sixtyDaysDelinquent = sixtyDaysDelinquent;
	}
	@Field(offset=72, length=24)
	public StatsElement24 getNightyPlusDaysDelinquent() {
		return nightyPlusDaysDelinquent;
	}
	public void setNightyPlusDaysDelinquent(StatsElement24 nightyPlusDaysDelinquent) {
		this.nightyPlusDaysDelinquent = nightyPlusDaysDelinquent;
	}
	@Field(offset=96, length=24)
	public StatsElement24 getFhaThirtyDaysDeliquent() {
		return fhaThirtyDaysDeliquent;
	}
	public void setFhaThirtyDaysDeliquent(StatsElement24 fhaThirtyDaysDeliquent) {
		this.fhaThirtyDaysDeliquent = fhaThirtyDaysDeliquent;
	}
	@Field(offset=120, length=24)
	public StatsElement24 getFhaSixtyDaysDeliquent() {
		return fhaSixtyDaysDeliquent;
	}
	public void setFhaSixtyDaysDeliquent(StatsElement24 fhaSixtyDaysDeliquent) {
		this.fhaSixtyDaysDeliquent = fhaSixtyDaysDeliquent;
	}
	@Field(offset=144, length=24)
	public StatsElement24 getFhaNinetyPlusDaysDeliquent() {
		return fhaNinetyPlusDaysDeliquent;
	}
	public void setFhaNinetyPlusDaysDeliquent(
			StatsElement24 fhaNinetyPlusDaysDeliquent) {
		this.fhaNinetyPlusDaysDeliquent = fhaNinetyPlusDaysDeliquent;
	}
	@Field(offset=168, length=24)
	public StatsElement24 getVaThirtyDaysDeliquent() {
		return vaThirtyDaysDeliquent;
	}
	public void setVaThirtyDaysDeliquent(StatsElement24 vaThirtyDaysDeliquent) {
		this.vaThirtyDaysDeliquent = vaThirtyDaysDeliquent;
	}
	@Field(offset=192, length=24)
	public StatsElement24 getVaSixtyDaysDeliquent() {
		return vaSixtyDaysDeliquent;
	}
	public void setVaSixtyDaysDeliquent(StatsElement24 vaSixtyDaysDeliquent) {
		this.vaSixtyDaysDeliquent = vaSixtyDaysDeliquent;
	}
	@Field(offset=216, length=24)
	public StatsElement24 getVaNinetyPlusDaysDeliquent() {
		return vaNinetyPlusDaysDeliquent;
	}
	public void setVaNinetyPlusDaysDeliquent(
			StatsElement24 vaNinetyPlusDaysDeliquent) {
		this.vaNinetyPlusDaysDeliquent = vaNinetyPlusDaysDeliquent;
	}
	@Field(offset=240, length=24)
	public StatsElement24 getRdThirtyDaysDeliquent() {
		return rdThirtyDaysDeliquent;
	}
	public void setRdThirtyDaysDeliquent(StatsElement24 rdThirtyDaysDeliquent) {
		this.rdThirtyDaysDeliquent = rdThirtyDaysDeliquent;
	}
	@Field(offset=264, length=24)
	public StatsElement24 getRdSixtyDaysDeliquent() {
		return rdSixtyDaysDeliquent;
	}
	public void setRdSixtyDaysDeliquent(StatsElement24 rdSixtyDaysDeliquent) {
		this.rdSixtyDaysDeliquent = rdSixtyDaysDeliquent;
	}
	@Field(offset=288, length=24)
	public StatsElement24 getRdNinetyPlusDaysDeliquent() {
		return rdNinetyPlusDaysDeliquent;
	}
	public void setRdNinetyPlusDaysDeliquent(
			StatsElement24 rdNinetyPlusDaysDeliquent) {
		this.rdNinetyPlusDaysDeliquent = rdNinetyPlusDaysDeliquent;
	}
	@Field(offset=312, length=24)
	public StatsElement24 getPihThirtyDaysDeliquent() {
		return pihThirtyDaysDeliquent;
	}
	public void setPihThirtyDaysDeliquent(StatsElement24 pihThirtyDaysDeliquent) {
		this.pihThirtyDaysDeliquent = pihThirtyDaysDeliquent;
	}
	@Field(offset=336, length=24)
	public StatsElement24 getPihSixtyDaysDeliquent() {
		return pihSixtyDaysDeliquent;
	}
	public void setPihSixtyDaysDeliquent(StatsElement24 pihSixtyDaysDeliquent) {
		this.pihSixtyDaysDeliquent = pihSixtyDaysDeliquent;
	}
	@Field(offset=360, length=24)
	public StatsElement24 getPifNinetyPlusDaysDeliquent() {
		return pifNinetyPlusDaysDeliquent;
	}
	public void setPifNinetyPlusDaysDeliquent(
			StatsElement24 pifNinetyPlusDaysDeliquent) {
		this.pifNinetyPlusDaysDeliquent = pifNinetyPlusDaysDeliquent;
	}
	@Field(offset=384, length=24)
	public Integer getAsOfDate() {
		return asOfDate;
	}
	public void setAsOfDate(Integer asOfDate) {
		this.asOfDate = asOfDate;
	}
	@Override
	public String toString() {
		return "MultiIssuerDelinquency [issuer=" + issuer
				+ ", thirtyDaysDelinquent=" + thirtyDaysDelinquent
				+ ", sixtyDaysDelinquent=" + sixtyDaysDelinquent
				+ ", nightyPlusDaysDelinquent=" + nightyPlusDaysDelinquent
				+ ", fhaThirtyDaysDeliquent=" + fhaThirtyDaysDeliquent
				+ ", fhaSixtyDaysDeliquent=" + fhaSixtyDaysDeliquent
				+ ", fhaNinetyPlusDaysDeliquent=" + fhaNinetyPlusDaysDeliquent
				+ ", vaThirtyDaysDeliquent=" + vaThirtyDaysDeliquent
				+ ", vaSixtyDaysDeliquent=" + vaSixtyDaysDeliquent
				+ ", vaNinetyPlusDaysDeliquent=" + vaNinetyPlusDaysDeliquent
				+ ", rdThirtyDaysDeliquent=" + rdThirtyDaysDeliquent
				+ ", rdSixtyDaysDeliquent=" + rdSixtyDaysDeliquent
				+ ", rdNinetyPlusDaysDeliquent=" + rdNinetyPlusDaysDeliquent
				+ ", pihThirtyDaysDeliquent=" + pihThirtyDaysDeliquent
				+ ", pihSixtyDaysDeliquent=" + pihSixtyDaysDeliquent
				+ ", pifNinetyPlusDaysDeliquent=" + pifNinetyPlusDaysDeliquent
				+ ", asOfDate=" + asOfDate + "]";
	}

}
