package com.yieldbook.mortgage.domain.fnma;
import java.math.BigDecimal;
import java.util.Date;

import com.ancientprogramming.fixedformat4j.annotation.Align;
import com.ancientprogramming.fixedformat4j.annotation.Field;
import com.ancientprogramming.fixedformat4j.annotation.FixedFormatDecimal;
import com.ancientprogramming.fixedformat4j.annotation.FixedFormatPattern;
import com.ancientprogramming.fixedformat4j.annotation.Record;

@Record
public class ArmResetDaily {
	private String record_type;
	private String filler1;
	private String pool_number;
	private String filler2;
	private Date next_rate_chg_date;
	private String filler3;
	private Integer pct_balance;
	private String filler4;
	private BigDecimal mbs_margin_high;
	private String filler5;
	private BigDecimal mbs_margin_low;
	private String filler6;
	private BigDecimal wtd_avg_mbs_margin;
	private String filler7;
	private BigDecimal accrual_net_coupon_high;
	private String filler8;
	private BigDecimal accrual_net_coupon_low;
	private String filler9;
	private BigDecimal wtd_avg_net_coupon;
	private String filler10;
	private BigDecimal net_life_cap_high;
	private String filler11;
	private BigDecimal net_life_cap_low;
	private String filler12;
	private BigDecimal net_life_floor_high;
	private String filler13;
	private BigDecimal net_life_floor_low;
	private String filler14;

	@Field(offset=1, length=1)
	public String getRecord_type() {
		return record_type;
	}
	public void setRecord_type(String record_type) {
		this.record_type=record_type;
	}
	@Field(offset=2, length=1)
	public String getFiller1() {
		return filler1;
	}
	public void setFiller1(String filler1) {
		this.filler1=filler1;
	}
	@Field(offset=3, length=6)
	public String getPool_number() {
		return pool_number;
	}
	public void setPool_number(String pool_number) {
		this.pool_number=pool_number;
	}
	@Field(offset=9, length=1)
	public String getFiller2() {
		return filler2;
	}
	public void setFiller2(String filler2) {
		this.filler2=filler2;
	}
	@Field(offset=10, length=8)
	@FixedFormatPattern("MM/dd/yy")
	public Date getNext_rate_chg_date() {
		return next_rate_chg_date;
	}
	public void setNext_rate_chg_date(Date next_rate_chg_date) {
		this.next_rate_chg_date=next_rate_chg_date;
	}
	@Field(offset=18, length=1)
	public String getFiller3() {
		return filler3;
	}
	public void setFiller3(String filler3) {
		this.filler3=filler3;
	}
	@Field(offset=19, length=3, align=Align.RIGHT, paddingChar=' ')
	public Integer getPct_balance() {
		return pct_balance;
	}
	public void setPct_balance(Integer pct_balance) {
		this.pct_balance=pct_balance;
	}
	@Field(offset=22, length=1)
	public String getFiller4() {
		return filler4;
	}
	public void setFiller4(String filler4) {
		this.filler4=filler4;
	}
	@Field(offset=23, length=9, align=Align.RIGHT, paddingChar=' ')
	@FixedFormatDecimal (decimals=4, useDecimalDelimiter=true)
	public BigDecimal getMbs_margin_high() {
		return mbs_margin_high;
	}

	public void setMbs_margin_high(BigDecimal mbs_margin_high) {
		this.mbs_margin_high=mbs_margin_high;
	}
	@Field(offset=32, length=1)
	public String getFiller5() {
		return filler5;
	}
	public void setFiller5(String filler5) {
		this.filler5=filler5;
	}
	@Field(offset=33, length=9, align=Align.RIGHT, paddingChar=' ')
	@FixedFormatDecimal (decimals=4, useDecimalDelimiter=true)
	public BigDecimal getMbs_margin_low() {
		return mbs_margin_low;
	}
	public void setMbs_margin_low(BigDecimal mbs_margin_low) {
		this.mbs_margin_low=mbs_margin_low;
	}
	@Field(offset=42, length=1)
	public String getFiller6() {
		return filler6;
	}
	public void setFiller6(String filler6) {
		this.filler6=filler6;
	}
	@Field(offset=43, length=9, align=Align.RIGHT, paddingChar=' ')
	@FixedFormatDecimal (decimals=4, useDecimalDelimiter=true)
	public BigDecimal getWtd_avg_mbs_margin() {
		return wtd_avg_mbs_margin;
	}
	public void setWtd_avg_mbs_margin(BigDecimal wtd_avg_mbs_margin) {
		this.wtd_avg_mbs_margin=wtd_avg_mbs_margin;
	}
	@Field(offset=52, length=1)
	public String getFiller7() {
		return filler7;
	}
	public void setFiller7(String filler7) {
		this.filler7=filler7;
	}
	@Field(offset=53, length=8, align=Align.RIGHT, paddingChar=' ')
	@FixedFormatDecimal (decimals=4, useDecimalDelimiter=true)
	public BigDecimal getAccrual_net_coupon_high() {
		return accrual_net_coupon_high;
	}
	public void setAccrual_net_coupon_high(BigDecimal accrual_net_coupon_high) {
		this.accrual_net_coupon_high=accrual_net_coupon_high;
	}
	@Field(offset=61, length=1)
	public String getFiller8() {
		return filler8;
	}
	public void setFiller8(String filler8) {
		this.filler8=filler8;
	}
	@Field(offset=62, length=8, align=Align.RIGHT, paddingChar=' ')
	@FixedFormatDecimal (decimals=4, useDecimalDelimiter=true)
	public BigDecimal getAccrual_net_coupon_low() {
		return accrual_net_coupon_low;
	}
	public void setAccrual_net_coupon_low(BigDecimal accrual_net_coupon_low) {
		this.accrual_net_coupon_low=accrual_net_coupon_low;
	}
	@Field(offset=70, length=1)
	public String getFiller9() {
		return filler9;
	}
	public void setFiller9(String filler9) {
		this.filler9=filler9;
	}
	@Field(offset=71, length=8, align=Align.RIGHT, paddingChar=' ')
	@FixedFormatDecimal (decimals=4, useDecimalDelimiter=true)
	public BigDecimal getWtd_avg_net_coupon() {
		return wtd_avg_net_coupon;
	}
	public void setWtd_avg_net_coupon(BigDecimal wtd_avg_net_coupon) {
		this.wtd_avg_net_coupon=wtd_avg_net_coupon;
	}
	@Field(offset=79, length=1)
	public String getFiller10() {
		return filler10;
	}
	public void setFiller10(String filler10) {
		this.filler10=filler10;
	}
	@Field(offset=80, length=8, align=Align.RIGHT, paddingChar=' ')
	@FixedFormatDecimal (decimals=4, useDecimalDelimiter=true)
	public BigDecimal getNet_life_cap_high() {
		return net_life_cap_high;
	}
	public void setNet_life_cap_high(BigDecimal net_life_cap_high) {
		this.net_life_cap_high=net_life_cap_high;
	}
	@Field(offset=88, length=1)
	public String getFiller11() {
		return filler11;
	}
	public void setFiller11(String filler11) {
		this.filler11=filler11;
	}
	@Field(offset=89, length=8, align=Align.RIGHT, paddingChar=' ')
	@FixedFormatDecimal (decimals=4, useDecimalDelimiter=true)
	public BigDecimal getNet_life_cap_low() {
		return net_life_cap_low;
	}
	public void setNet_life_cap_low(BigDecimal net_life_cap_low) {
		this.net_life_cap_low=net_life_cap_low;
	}
	@Field(offset=97, length=1)
	public String getFiller12() {
		return filler12;
	}
	public void setFiller12(String filler12) {
		this.filler12=filler12;
	}
	@Field(offset=98, length=8, align=Align.RIGHT, paddingChar=' ')
	@FixedFormatDecimal (decimals=4, useDecimalDelimiter=true)
	public BigDecimal getNet_life_floor_high() {
		return net_life_floor_high;
	}
	public void setNet_life_floor_high(BigDecimal net_life_floor_high) {
		this.net_life_floor_high=net_life_floor_high;
	}
	@Field(offset=106, length=1)
	public String getFiller13() {
		return filler13;
	}
	public void setFiller13(String filler13) {
		this.filler13=filler13;
	}
	@Field(offset=107, length=8, align=Align.RIGHT, paddingChar=' ')
	@FixedFormatDecimal (decimals=4, useDecimalDelimiter=true)
	public BigDecimal getNet_life_floor_low() {
		return net_life_floor_low;
	}
	public void setNet_life_floor_low(BigDecimal net_life_floor_low) {
		this.net_life_floor_low=net_life_floor_low;
	}
	@Field(offset=115, length=24)
	public String getFiller14() {
		return filler14;
	}
	public void setFiller14(String filler14) {
		this.filler14=filler14;
	}

	@Override
	public String toString() {
		return record_type + "|" + pool_number + "|" + next_rate_chg_date + "|" + 
		pct_balance + "|" + mbs_margin_high + "|" + mbs_margin_low + "|" + wtd_avg_mbs_margin + "|" + accrual_net_coupon_high + "|" + 
		accrual_net_coupon_low + "|" + wtd_avg_net_coupon + "|" + net_life_cap_high + "|" + net_life_cap_low + "|" + net_life_floor_high + "|" + 
		net_life_floor_low;

	}

}
