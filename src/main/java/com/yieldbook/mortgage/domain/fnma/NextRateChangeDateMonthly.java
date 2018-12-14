package com.yieldbook.mortgage.domain.fnma;

import static com.yieldbook.mortgage.utility.YieldBookUtilities.convertStringToBigDecimal;
import static com.yieldbook.mortgage.utility.YieldBookUtilities.convertStringToLong;

import java.math.BigDecimal;

import com.ancientprogramming.fixedformat4j.annotation.Align;
import com.ancientprogramming.fixedformat4j.annotation.Field;
import com.ancientprogramming.fixedformat4j.annotation.FixedFormatDecimal;
import com.ancientprogramming.fixedformat4j.annotation.Record;


@Record
public class NextRateChangeDateMonthly {
	private String poolNumber;
	private String poolPrefix;
	private Integer recordType;
	private Integer nextRateChangeDate;
	private String originalNoteRate;
	private BigDecimal percentSecurityBalance;
	private String mbsMarginHigh;
	private String mbsMarginLow;
	private BigDecimal passThroughRateCapHigh;
	private BigDecimal passThroughRateCapLow;
	private String passThroughRateFloorHigh;
	private String passThroughRateFloorLow;
	private BigDecimal passThroughRateHigh;
	private BigDecimal passThroughRateLow;
	private BigDecimal crossCouponHigh;
	private BigDecimal crossCouponLow;
	private String waMBSMargin;
	private BigDecimal waPassThroughRate;
	private BigDecimal waCoupon;
	private BigDecimal noteRateCap;
	private String loanCount;
	private String zeroFilled;
	private String filler;
	
	@Field(offset=1, length=6,align=Align.RIGHT, paddingChar='0')  
	public String getPoolNumber() {
		return poolNumber;
	}
	public void setPoolNumber(String poolNumber) {
		this.poolNumber = poolNumber;
	}
	@Field(offset=7, length=3, align=Align.RIGHT, paddingChar=' ')  
	public String getPoolPrefix() {
		return poolPrefix;
	}
	public void setPoolPrefix(String poolPrefix) {
		this.poolPrefix = poolPrefix;
	}
	@Field(offset=10, length=1)  
	public Integer getRecordType() {
		return recordType;
	}
	public void setRecordType(Integer recordType) {
		this.recordType = recordType;
	}
	@Field(offset=11, length=8) 
	public Integer getNextRateChangeDate() {
		return nextRateChangeDate;
	}
	public void setNextRateChangeDate(Integer nextRateChangeDate) {
		this.nextRateChangeDate = nextRateChangeDate;
	}
	
	@Field(offset=19, length=7, align=Align.LEFT, paddingChar='{')
	//@FixedFormatDecimal (decimals=4, useDecimalDelimiter=false)
	public String getOriginalNoteRate() {
		return originalNoteRate;
	}
	
	public BigDecimal getOriginalNoteRate1() {
		return convertStringToBigDecimal(originalNoteRate,4);
	}	
	public void setOriginalNoteRate(String originalNoteRate) {
		this.originalNoteRate = originalNoteRate;
	}
	
	@Field(offset=26, length=7, align=Align.LEFT, paddingChar='{')
	@FixedFormatDecimal (decimals=4, useDecimalDelimiter=false)
	public BigDecimal getPercentSecurityBalance() {
		return percentSecurityBalance;
	}
	public void setPercentSecurityBalance(BigDecimal percentSecurityBalance) {
		this.percentSecurityBalance = percentSecurityBalance;
	}
	
	@Field(offset=33, length=7, align=Align.LEFT, paddingChar='{')
	//@FixedFormatDecimal (decimals=4, useDecimalDelimiter=false)
	public String getMBSMarginHigh() {
		return mbsMarginHigh;
	}
	
	public BigDecimal getMBSMarginHigh1() {
		return convertStringToBigDecimal(mbsMarginHigh,4);
	}
	
	public void setMBSMarginHigh(String mbsMarginHigh) {
		this.mbsMarginHigh = mbsMarginHigh;
	}
	
	@Field(offset=40, length=7, align=Align.LEFT, paddingChar='{')
	//@FixedFormatDecimal (decimals=4, useDecimalDelimiter=false)
	public String getMBSMarginLow() {
		return mbsMarginLow;
	}
	public BigDecimal getMBSMarginLow1() {
		return convertStringToBigDecimal(mbsMarginLow, 4);
	}	
	public void setMBSMarginLow(String mbsMarginLow) {
		this.mbsMarginLow = mbsMarginLow;
	}
	
	@Field(offset=47, length=7, align=Align.LEFT, paddingChar='{')
	@FixedFormatDecimal (decimals=4, useDecimalDelimiter=false)
	public BigDecimal getPassThroughRateCapHigh() {
		return passThroughRateCapHigh;
	}
	public void setPassThroughRateCapHigh(BigDecimal passThroughRateCapHigh) {
		this.passThroughRateCapHigh = passThroughRateCapHigh;
	}
	
	@Field(offset=54, length=7, align=Align.LEFT, paddingChar='{')
	@FixedFormatDecimal (decimals=4, useDecimalDelimiter=false)
	public BigDecimal getPassThroughRateCapLow() {
		return passThroughRateCapLow;
	}
	public void setPassThroughRateCapLow(BigDecimal passThroughRateCapLow) {
		this.passThroughRateCapLow = passThroughRateCapLow;
	}
	
	@Field(offset=61, length=7, align=Align.LEFT, paddingChar='{')
	public String getPassThroughRateFloorHigh() {
		return passThroughRateFloorHigh;
	}
	public BigDecimal getPassThroughRateFloorHigh1() {
		return convertStringToBigDecimal(passThroughRateFloorHigh,4);
	}
	
	public void setPassThroughRateFloorHigh(String passThroughRateFloorHigh) {
		this.passThroughRateFloorHigh = passThroughRateFloorHigh;
	}
	@Field(offset=68, length=7, align=Align.LEFT, paddingChar='{')
	public String getPassThroughRateFloorLow() {
		return passThroughRateFloorLow;
	}
	public BigDecimal getPassThroughRateFloorLow1() {
		return convertStringToBigDecimal(passThroughRateFloorLow,4);
	}	
	public void setPassThroughRateFloorLow(String passThroughRateFloorLow) {
		this.passThroughRateFloorLow = passThroughRateFloorLow;
	}
	@Field(offset=75, length=7, align=Align.LEFT, paddingChar='{')
	@FixedFormatDecimal (decimals=4, useDecimalDelimiter=false)	
	public BigDecimal getPassThroughRateHigh() {
		return passThroughRateHigh;
	}
	public void setPassThroughRateHigh(BigDecimal passThroughRateHigh) {
		this.passThroughRateHigh = passThroughRateHigh;
	}
	
	@Field(offset=82, length=7, align=Align.LEFT, paddingChar='{')
	@FixedFormatDecimal (decimals=4, useDecimalDelimiter=false)
	public BigDecimal getPassThroughRateLow() {
		return passThroughRateLow;
	}
	public void setPassThroughRateLow(BigDecimal passThroughRateLow) {
		this.passThroughRateLow = passThroughRateLow;
	}
	
	@Field(offset=89, length=7, align=Align.LEFT, paddingChar='{')
	@FixedFormatDecimal (decimals=4, useDecimalDelimiter=false)
	public BigDecimal getCrossCouponHigh() {
		return crossCouponHigh;
	}
	public void setCrossCouponHigh(BigDecimal crossCouponHigh) {
		this.crossCouponHigh = crossCouponHigh;
	}
	
	@Field(offset=96, length=7, align=Align.LEFT, paddingChar='{')
	@FixedFormatDecimal (decimals=4, useDecimalDelimiter=false)
	public BigDecimal getCrossCouponLow() {
		return crossCouponLow;
	}
	public void setCrossCouponLow(BigDecimal crossCouponLow) {
		this.crossCouponLow = crossCouponLow;
	}
	
	@Field(offset=103, length=7, align=Align.LEFT, paddingChar='{')
	//@FixedFormatDecimal (decimals=4, useDecimalDelimiter=false)
	public String getWaMBSMargin() {
		return waMBSMargin;
	}
	public BigDecimal getWaMBSMargin1() {
		return convertStringToBigDecimal(waMBSMargin,4);
	}
	public void setWaMBSMargin(String waMBSMargin) {
		this.waMBSMargin = waMBSMargin;
	}
	
	@Field(offset=110, length=7, align=Align.LEFT, paddingChar='{')
	@FixedFormatDecimal (decimals=4, useDecimalDelimiter=false)
	public BigDecimal getWaPassThroughRate() {
		return waPassThroughRate;
	}
	public void setWaPassThroughRate(BigDecimal waPassThroughRate) {
		this.waPassThroughRate = waPassThroughRate;
	}
	
	@Field(offset=117, length=7, align=Align.LEFT, paddingChar='{')
	@FixedFormatDecimal (decimals=4, useDecimalDelimiter=false)
	public BigDecimal getWaCoupon() {
		return waCoupon;
	}
	public void setWaCoupon(BigDecimal waCoupon) {
		this.waCoupon = waCoupon;
	}
	
	@Field(offset=124, length=7, align=Align.LEFT, paddingChar='{')
	@FixedFormatDecimal (decimals=4, useDecimalDelimiter=false)
	public BigDecimal getNoteRateCap() {
		return noteRateCap;
	}
	public void setNoteRateCap(BigDecimal noteRateCap) {
		this.noteRateCap = noteRateCap;
	}
	
	@Field(offset=131, length=11, align=Align.LEFT, paddingChar='{')
	public String getLoanCount() {
		//get valuel 0000000000A
		return loanCount;
	}
	public void setLoanCount(String loanCount) {
		this.loanCount = loanCount;
	}
	
	public Long getLoanCount1() {
		return convertStringToLong(loanCount);
	}
	
	@Field(offset=142, length=152)
	public String getZeroFilled() {
		return zeroFilled;
	}
	public void setZeroFilled(String zeroFilled) {
		this.zeroFilled = zeroFilled;
	}
	
	@Field(offset=294, length=1077)
	public String getFiller() {
		return filler;
	}
	public void setFiller(String filler) {
		this.filler = filler;
	}
	@Override
	public String toString() {
		return "NextRateChangeDateMonthly [poolNumber=" + poolNumber
				+ ", poolPrefix=" + poolPrefix + ", recordType=" + recordType
				+ ", nextRateChangeDate=" + nextRateChangeDate
				+ ", originalNoteRate=" + originalNoteRate
				+ ", originalNoteRate1=" + getOriginalNoteRate1()
				+ ", percentSecurityBalance=" + percentSecurityBalance
				+ ", mbsMarginHigh=" + mbsMarginHigh
				+ ", mbsMarginHigh1=" + getMBSMarginHigh1() + ", mbsMarginLow="
				+ mbsMarginLow + ", mbsMarginLow1="
						+ getMBSMarginLow1() +", passThroughRateCapHigh="
				+ passThroughRateCapHigh  + ", passThroughRateCapLow="
				+ passThroughRateCapLow + ", passThroughRateFloorHigh="
				+ passThroughRateFloorHigh +  ", passThroughRateFloorHigh1="
						+ getPassThroughRateFloorHigh1()
				+ ", passThroughRateFloorLow=" + passThroughRateFloorLow
				+ ", passThroughRateFloorLow1=" + getPassThroughRateFloorLow1() 
				+ ", passThroughRateHigh="
				+ passThroughRateHigh + ", passThroughRateLow="
				+ passThroughRateLow + ", crossCouponHigh=" + crossCouponHigh
				+ ", crossCouponLow=" + crossCouponLow + ", waMBSMargin="
				+ waMBSMargin + ", waMBSMargin1="
				+ getWaMBSMargin1() + ", waPassThroughRate=" + waPassThroughRate
				+ ", waCoupon=" + waCoupon + ", noteRateCap=" + noteRateCap
				+ ", loanCount=" + loanCount + ", loanCount1=" + getLoanCount1() + ", zeroFilled=" + zeroFilled
				+ ", filler=" + filler + "]";
	}
	
}
