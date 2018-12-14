package com.yieldbook.mortgage.domain.gnma;

import com.ancientprogramming.fixedformat4j.annotation.Field;
import com.ancientprogramming.fixedformat4j.annotation.Record;

@Record
public class MortgageInsurance {
	private String cusip;
	private String poolNumber;
	private String poolIndicator;
	private String poolType;
	private String recrodType;
	private Integer issuer;
	
	private StatsElement24 upfrontMip100;
	private StatsElement24 upfrontMip125;
	private StatsElement24 upfrontMip150;
	private StatsElement24 upfrontMip175;
	private StatsElement24 upfrontMip200;
	private StatsElement24 upfrontMip225;

	private StatsElement24 upfrontMipNotAvailable;
	
	private StatsElement24 annualMip25;
	private StatsElement24 annualMip35;
	private StatsElement24 annualMip50;
	private StatsElement24 annualMip55;
	private StatsElement24 annualMip60;
	private StatsElement24 annualMip85;
	private StatsElement24 annualMip90;
	private StatsElement24 annualMip110;
	private StatsElement24 annualMip115;
	private StatsElement24 annualMip120;
	private StatsElement24 annualMip125;
	private StatsElement24 annualMip145;
	private StatsElement24 annualMip150;

	private StatsElement24 annualMipNotAvailable;
	private StatsElement24 firstHomeBuyer;
	private StatsElement24 notFirstHomeBuyer;
	private StatsElement24 firstHomeBuyerNotAvailable;
	private StatsElement24 originationTypeBroker;
	private StatsElement24 originationTypeCorrespondent;
	private StatsElement24 originationTypeRetail;
	private StatsElement24 originationTypeNotAvailable;
	
	private StatsElement24 upfrontMip000;
	private StatsElement24 upfrontMip001;
	private StatsElement24 upfrontMip300;
	private StatsElement24 upfrontMip380;
	private StatsElement24 annualMip000;
	private StatsElement24 otherMip;
	private StatsElement24 annualMip45;
	private StatsElement24 annualMip70;
	private StatsElement24 annualMip95;
	private StatsElement24 annualMip130;
	private StatsElement24 annualMip135;
	private StatsElement24 annualMip155;
	
	private StatsElement24 upfrontMip50;
	private StatsElement24 upfrontMip240;
	private StatsElement24 upfrontMip250;
	
	private StatsElement24 annualMip75;
	private StatsElement24 annualMip80;
	private StatsElement24 annualMip100;
	private StatsElement24 annualMip105;
	
	private String filler;
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
	public StatsElement24 getUpfrontMip100() {
		return upfrontMip100;
	}
	public void setUpfrontMip100(StatsElement24 upfrontMip100) {
		this.upfrontMip100 = upfrontMip100;
	}
	@Field(offset=48, length=24)
	public StatsElement24 getUpfrontMip125() {
		return upfrontMip125;
	}
	public void setUpfrontMip125(StatsElement24 upfrontMip125) {
		this.upfrontMip125 = upfrontMip125;
	}
	@Field(offset=72, length=24)
	public StatsElement24 getUpfrontMip150() {
		return upfrontMip150;
	}
	public void setUpfrontMip150(StatsElement24 upfrontMip150) {
		this.upfrontMip150 = upfrontMip150;
	}
	@Field(offset=96, length=24)
	public StatsElement24 getUpfrontMip175() {
		return upfrontMip175;
	}
	public void setUpfrontMip175(StatsElement24 upfrontMip175) {
		this.upfrontMip175 = upfrontMip175;
	}
	@Field(offset=120, length=24)
	public StatsElement24 getUpfrontMip200() {
		return upfrontMip200;
	}
	public void setUpfrontMip200(StatsElement24 upfrontMip200) {
		this.upfrontMip200 = upfrontMip200;
	}
	@Field(offset=144, length=24)
	public StatsElement24 getUpfrontMip225() {
		return upfrontMip225;
	}
	public void setUpfrontMip225(StatsElement24 upfrontMip225) {
		this.upfrontMip225 = upfrontMip225;
	}
	@Field(offset=168, length=24)
	public StatsElement24 getUpfrontMipNotAvailable() {
		return upfrontMipNotAvailable;
	}
	public void setUpfrontMipNotAvailable(StatsElement24 upfrontMipNotAvailable) {
		this.upfrontMipNotAvailable = upfrontMipNotAvailable;
	}
	@Field(offset=192, length=24)
	public StatsElement24 getAnnualMip25() {
		return annualMip25;
	}
	public void setAnnualMip25(StatsElement24 annualMip25) {
		this.annualMip25 = annualMip25;
	}
	@Field(offset=216, length=24)
	public StatsElement24 getAnnualMip35() {
		return annualMip35;
	}
	public void setAnnualMip35(StatsElement24 annualMip35) {
		this.annualMip35 = annualMip35;
	}
	@Field(offset=240, length=24)
	public StatsElement24 getAnnualMip50() {
		return annualMip50;
	}
	public void setAnnualMip50(StatsElement24 annualMip50) {
		this.annualMip50 = annualMip50;
	}
	@Field(offset=264, length=24)
	public StatsElement24 getAnnualMip55() {
		return annualMip55;
	}
	public void setAnnualMip55(StatsElement24 annualMip55) {
		this.annualMip55 = annualMip55;
	}
	@Field(offset=288, length=24)
	public StatsElement24 getAnnualMip60() {
		return annualMip60;
	}
	public void setAnnualMip60(StatsElement24 annualMip60) {
		this.annualMip60 = annualMip60;
	}
	@Field(offset=312, length=24)
	public StatsElement24 getAnnualMip85() {
		return annualMip85;
	}
	public void setAnnualMip85(StatsElement24 annualMip85) {
		this.annualMip85 = annualMip85;
	}
	@Field(offset=336, length=24)
	public StatsElement24 getAnnualMip90() {
		return annualMip90;
	}
	public void setAnnualMip90(StatsElement24 annualMip90) {
		this.annualMip90 = annualMip90;
	}
	@Field(offset=360, length=24)
	public StatsElement24 getAnnualMip110() {
		return annualMip110;
	}
	public void setAnnualMip110(StatsElement24 annualMip110) {
		this.annualMip110 = annualMip110;
	}
	@Field(offset=384, length=24)
	public StatsElement24 getAnnualMip115() {
		return annualMip115;
	}
	public void setAnnualMip115(StatsElement24 annualMip115) {
		this.annualMip115 = annualMip115;
	}
	@Field(offset=408, length=24)
	public StatsElement24 getAnnualMip120() {
		return annualMip120;
	}
	public void setAnnualMip120(StatsElement24 annualMip120) {
		this.annualMip120 = annualMip120;
	}
	@Field(offset=432, length=24)
	public StatsElement24 getAnnualMip125() {
		return annualMip125;
	}
	public void setAnnualMip125(StatsElement24 annualMip125) {
		this.annualMip125 = annualMip125;
	}
	@Field(offset=456, length=24)
	public StatsElement24 getAnnualMip145() {
		return annualMip145;
	}
	public void setAnnualMip145(StatsElement24 annualMip145) {
		this.annualMip145 = annualMip145;
	}
	@Field(offset=480, length=24)
	public StatsElement24 getAnnualMip150() {
		return annualMip150;
	}
	public void setAnnualMip150(StatsElement24 annualMip150) {
		this.annualMip150 = annualMip150;
	}
	@Field(offset=504, length=24)
	public StatsElement24 getAnnualMipNotAvailable() {
		return annualMipNotAvailable;
	}
	public void setAnnualMipNotAvailable(StatsElement24 annualMipNotAvailable) {
		this.annualMipNotAvailable = annualMipNotAvailable;
	}
	@Field(offset=528, length=24)
	public StatsElement24 getFirstHomeBuyer() {
		return firstHomeBuyer;
	}
	public void setFirstHomeBuyer(StatsElement24 firstHomeBuyer) {
		this.firstHomeBuyer = firstHomeBuyer;
	}
	@Field(offset=552, length=24)
	public StatsElement24 getNotFirstHomeBuyer() {
		return notFirstHomeBuyer;
	}
	public void setNotFirstHomeBuyer(StatsElement24 notFirstHomeBuyer) {
		this.notFirstHomeBuyer = notFirstHomeBuyer;
	}
	@Field(offset=576, length=24)
	public StatsElement24 getFirstHomeBuyerNotAvailable() {
		return firstHomeBuyerNotAvailable;
	}
	public void setFirstHomeBuyerNotAvailable(
			StatsElement24 firstHomeBuyerNotAvailable) {
		this.firstHomeBuyerNotAvailable = firstHomeBuyerNotAvailable;
	}
	@Field(offset=600, length=24)
	public StatsElement24 getOriginationTypeBroker() {
		return originationTypeBroker;
	}
	public void setOriginationTypeBroker(StatsElement24 originationTypeBroker) {
		this.originationTypeBroker = originationTypeBroker;
	}
	@Field(offset=624, length=24)
	public StatsElement24 getOriginationTypeCorrespondent() {
		return originationTypeCorrespondent;
	}
	public void setOriginationTypeCorrespondent(
			StatsElement24 originationTypeCorrespondent) {
		this.originationTypeCorrespondent = originationTypeCorrespondent;
	}
	@Field(offset=648, length=24)
	public StatsElement24 getOriginationTypeRetail() {
		return originationTypeRetail;
	}
	public void setOriginationTypeRetail(StatsElement24 originationTypeRetail) {
		this.originationTypeRetail = originationTypeRetail;
	}
	@Field(offset=672, length=24)
	public StatsElement24 getOriginationTypeNotAvailable() {
		return originationTypeNotAvailable;
	}
	public void setOriginationTypeNotAvailable(
			StatsElement24 originationTypeNotAvailable) {
		this.originationTypeNotAvailable = originationTypeNotAvailable;
	}
	@Field(offset=696, length=24)
	public StatsElement24 getUpfrontMip000() {
		return upfrontMip000;
	}
	public void setUpfrontMip000(StatsElement24 upfrontMip000) {
		this.upfrontMip000 = upfrontMip000;
	}
	@Field(offset=720, length=24)
	public StatsElement24 getUpfrontMip001() {
		return upfrontMip001;
	}
	public void setUpfrontMip001(StatsElement24 upfrontMip001) {
		this.upfrontMip001 = upfrontMip001;
	}
	@Field(offset=744, length=24)
	public StatsElement24 getUpfrontMip300() {
		return upfrontMip300;
	}
	public void setUpfrontMip300(StatsElement24 upfrontMip300) {
		this.upfrontMip300 = upfrontMip300;
	}
	@Field(offset=768, length=24)
	public StatsElement24 getUpfrontMip380() {
		return upfrontMip380;
	}
	public void setUpfrontMip380(StatsElement24 upfrontMip380) {
		this.upfrontMip380 = upfrontMip380;
	}
	@Field(offset=792, length=24)
	public StatsElement24 getAnnualMip000() {
		return annualMip000;
	}
	public void setAnnualMip000(StatsElement24 annualMip000) {
		this.annualMip000 = annualMip000;
	}
	@Field(offset=816, length=24)
	public StatsElement24 getOtherMip() {
		return otherMip;
	}
	public void setOtherMip(StatsElement24 otherMip) {
		this.otherMip = otherMip;
	}
	@Field(offset=840, length=24)
	public StatsElement24 getAnnualMip45() {
		return annualMip45;
	}
	public void setAnnualMip45(StatsElement24 annualMip45) {
		this.annualMip45 = annualMip45;
	}
	@Field(offset=864, length=24)
	public StatsElement24 getAnnualMip70() {
		return annualMip70;
	}
	public void setAnnualMip70(StatsElement24 annualMip70) {
		this.annualMip70 = annualMip70;
	}
	@Field(offset=888, length=24)
	public StatsElement24 getAnnualMip95() {
		return annualMip95;
	}
	public void setAnnualMip95(StatsElement24 annualMip95) {
		this.annualMip95 = annualMip95;
	}
	@Field(offset=912, length=24)
	public StatsElement24 getAnnualMip130() {
		return annualMip130;
	}
	public void setAnnualMip130(StatsElement24 annualMip130) {
		this.annualMip130 = annualMip130;
	}
	@Field(offset=836, length=24)
	public StatsElement24 getAnnualMip135() {
		return annualMip135;
	}
	public void setAnnualMip135(StatsElement24 annualMip135) {
		this.annualMip135 = annualMip135;
	}
	@Field(offset=960, length=24)
	public StatsElement24 getAnnualMip155() {
		return annualMip155;
	}
	public void setAnnualMip155(StatsElement24 annualMip155) {
		this.annualMip155 = annualMip155;
	}
	@Field(offset=984, length=24)
	public StatsElement24 getUpfrontMip50() {
		return upfrontMip50;
	}
	public void setUpfrontMip50(StatsElement24 upfrontMip50) {
		this.upfrontMip50 = upfrontMip50;
	}
	@Field(offset=1008, length=24)
	public StatsElement24 getUpfrontMip240() {
		return upfrontMip240;
	}
	public void setUpfrontMip240(StatsElement24 upfrontMip240) {
		this.upfrontMip240 = upfrontMip240;
	}
	@Field(offset=1032, length=24)
	public StatsElement24 getUpfrontMip250() {
		return upfrontMip250;
	}
	public void setUpfrontMip250(StatsElement24 upfrontMip250) {
		this.upfrontMip250 = upfrontMip250;
	}
	@Field(offset=1056, length=24)
	public StatsElement24 getAnnualMip75() {
		return annualMip75;
	}
	public void setAnnualMip75(StatsElement24 annualMip75) {
		this.annualMip75 = annualMip75;
	}
	@Field(offset=1080, length=24)
	public StatsElement24 getAnnualMip80() {
		return annualMip80;
	}
	public void setAnnualMip80(StatsElement24 annualMip80) {
		this.annualMip80 = annualMip80;
	}
	@Field(offset=1104, length=24)
	public StatsElement24 getAnnualMip100() {
		return annualMip100;
	}
	public void setAnnualMip100(StatsElement24 annualMip100) {
		this.annualMip100 = annualMip100;
	}
	@Field(offset=1128, length=24)
	public StatsElement24 getAnnualMip105() {
		return annualMip105;
	}
	public void setAnnualMip105(StatsElement24 annualMip105) {
		this.annualMip105 = annualMip105;
	}
	@Field(offset=1152, length=312)
	public String getFiller() {
		return filler;
	}
	public void setFiller(String filler) {
		this.filler = filler;
	}
	@Field(offset=1464, length=6)
	public Integer getAsOfDate() {
		return asOfDate;
	}
	public void setAsOfDate(Integer asOfDate) {
		this.asOfDate = asOfDate;
	}
	@Override
	public String toString() {
		return "MortgageInsurance [poolNumber=" + poolNumber
				+ ", upfrontMip100=" + upfrontMip100 + ", upfrontMip125="
				+ upfrontMip125 + ", upfrontMip150=" + upfrontMip150
				+ ", upfrontMip175=" + upfrontMip175 + ", upfrontMip200="
				+ upfrontMip200 + ", upfrontMip225=" + upfrontMip225
				+ ", upfrontMipNotAvailable=" + upfrontMipNotAvailable
				+ ", annualMip25=" + annualMip25 + ", annualMip35="
				+ annualMip35 + ", annualMip50=" + annualMip50
				+ ", annualMip55=" + annualMip55 + ", annualMip60="
				+ annualMip60 + ", annualMip85=" + annualMip85
				+ ", annualMip90=" + annualMip90 + ", annualMip110="
				+ annualMip110 + ", annualMip115=" + annualMip115
				+ ", annualMip120=" + annualMip120 + ", annualMip125="
				+ annualMip125 + ", annualMip145=" + annualMip145
				+ ", annualMip150=" + annualMip150 + ", annualMipNotAvailable="
				+ annualMipNotAvailable + ", firstHomeBuyer=" + firstHomeBuyer
				+ ", notFirstHomeBuyer=" + notFirstHomeBuyer
				+ ", firstHomeBuyerNotAvailable=" + firstHomeBuyerNotAvailable
				+ ", originationTypeBroker=" + originationTypeBroker
				+ ", originationTypeCorrespondent="
				+ originationTypeCorrespondent + ", originationTypeRetail="
				+ originationTypeRetail + ", originationTypeNotAvailable="
				+ originationTypeNotAvailable + ", upfrontMip000="
				+ upfrontMip000 + ", upfrontMip001=" + upfrontMip001
				+ ", upfrontMip300=" + upfrontMip300 + ", upfrontMip380="
				+ upfrontMip380 + ", annualMip000=" + annualMip000
				+ ", otherMip=" + otherMip + ", annualMip45=" + annualMip45
				+ ", annualMip70=" + annualMip70 + ", annualMip95="
				+ annualMip95 + ", annualMip130=" + annualMip130
				+ ", annualMip135=" + annualMip135 + ", annualMip155="
				+ annualMip155 + ", upfrontMip50=" + upfrontMip50
				+ ", upfrontMip240=" + upfrontMip240 + ", upfrontMip250="
				+ upfrontMip250 + ", annualMip75=" + annualMip75
				+ ", annualMip80=" + annualMip80 + ", annualMip100="
				+ annualMip100 + ", annualMip105=" + annualMip105 + "]";
	}
	
	

	
}
