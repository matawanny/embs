package com.yieldbook.mortgage.domain.fnma;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ancientprogramming.fixedformat4j.annotation.Align;
import com.ancientprogramming.fixedformat4j.annotation.Field;
import com.ancientprogramming.fixedformat4j.annotation.FixedFormatDecimal;
import com.ancientprogramming.fixedformat4j.annotation.FixedFormatPattern;
import com.ancientprogramming.fixedformat4j.annotation.Record;

@Record
public class PoolDaily {
	private String record_type;
	private String filler1;
	private String pool_number;
	private String filler2;
	private String prefix;
	private String filler3;
	private String cusip_number;
	private String filler4;
	private Date issue_date;
	private String filler5;
	private BigDecimal pass_thru_rate;
	private String filler6;
	private BigDecimal original_wac;
	private String filler7;
	private BigDecimal original_wam;
	private String filler8;
	private Date maturity_date;
	private String filler9;
	private BigDecimal original_balance;
	private String filler10;
	private BigDecimal smp_percent;
	private String filler11;
	private BigDecimal margin;
	private String filler12;
	private String subtype;
	private String transfer_type;
	private String filler13;
	private Date first_rate_chg_date;
	private String filler14;
	private Date first_paymt_chg_date;
	private String filler15;
	private BigDecimal orig_net_life_cap;
	private String filler16;
	private BigDecimal orig_net_life_floor;
	private String filler17;
	private Integer months_to_next_rt_chg;
	
	private List<ArmResetDaily> armResets=new ArrayList<>();

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
	@Field(offset=10, length=2)
	public String getPrefix() {
		return prefix;
	}
	public void setPrefix(String prefix) {
		this.prefix=prefix;
	}
	@Field(offset=12, length=1)
	public String getFiller3() {
		return filler3;
	}
	public void setFiller3(String filler3) {
		this.filler3=filler3;
	}
	@Field(offset=13, length=9)
	public String getCusip_number() {
		return cusip_number;
	}
	public void setCusip_number(String cusip_number) {
		this.cusip_number=cusip_number;
	}
	@Field(offset=22, length=1)
	public String getFiller4() {
		return filler4;
	}
	public void setFiller4(String filler4) {
		this.filler4=filler4;
	}
	@Field(offset=23, length=8)
	@FixedFormatPattern("MM/dd/yy")
	public Date getIssue_date() {
		return issue_date;
	}
	public void setIssue_date(Date issue_date) {
		this.issue_date=issue_date;
	}
	@Field(offset=31, length=1)
	public String getFiller5() {
		return filler5;
	}
	public void setFiller5(String filler5) {
		this.filler5=filler5;
	}
	@Field(offset=32, length=7,align=Align.RIGHT, paddingChar=' ')
	@FixedFormatDecimal (decimals=3, useDecimalDelimiter=true)
	public BigDecimal getPass_thru_rate() {
		return pass_thru_rate;
	}
	public void setPass_thru_rate(BigDecimal pass_thru_rate) {
		this.pass_thru_rate=pass_thru_rate;
	}
	@Field(offset=39, length=1)
	public String getFiller6() {
		return filler6;
	}
	public void setFiller6(String filler6) {
		this.filler6=filler6;
	}
	@Field(offset=40, length=7, align=Align.RIGHT, paddingChar=' ')
	@FixedFormatDecimal (decimals=3, useDecimalDelimiter=true)
	public BigDecimal getOriginal_wac() {
		return original_wac;
	}
	public void setOriginal_wac(BigDecimal original_wac) {
		this.original_wac=original_wac;
	}
	@Field(offset=47, length=1)
	public String getFiller7() {
		return filler7;
	}
	public void setFiller7(String filler7) {
		this.filler7=filler7;
	}
	@Field(offset=48, length=3, align=Align.RIGHT, paddingChar=' ')
	@FixedFormatDecimal (decimals=3, useDecimalDelimiter=true)
	public BigDecimal getOriginal_wam() {
		return original_wam;
	}
	public void setOriginal_wam(BigDecimal original_wam) {
		this.original_wam=original_wam;
	}
	@Field(offset=51, length=1)
	public String getFiller8() {
		return filler8;
	}
	public void setFiller8(String filler8) {
		this.filler8=filler8;
	}
	@Field(offset=52, length=8)
	@FixedFormatPattern("MM/dd/yy")
	public Date getMaturity_date() {
		return maturity_date;
	}
	public void setMaturity_date(Date maturity_date) {
		this.maturity_date=maturity_date;
	}
	@Field(offset=60, length=1)
	public String getFiller9() {
		return filler9;
	}
	public void setFiller9(String filler9) {
		this.filler9=filler9;
	}
	@Field(offset=61, length=16, align=Align.RIGHT, paddingChar=' ')
	@FixedFormatDecimal (decimals=2, useDecimalDelimiter=true)
	public BigDecimal getOriginal_balance() {
		return original_balance;
	}
	public void setOriginal_balance(BigDecimal original_balance) {
		this.original_balance=original_balance;
	}
	@Field(offset=77, length=1)
	public String getFiller10() {
		return filler10;
	}
	public void setFiller10(String filler10) {
		this.filler10=filler10;
	}
	@Field(offset=78, length=6, align=Align.RIGHT, paddingChar=' ')
	@FixedFormatDecimal (decimals=2, useDecimalDelimiter=true)
	public BigDecimal getSmp_percent() {
		return smp_percent;
	}
	public void setSmp_percent(BigDecimal smp_percent) {
		this.smp_percent=smp_percent;
	}
	@Field(offset=84, length=1)
	public String getFiller11() {
		return filler11;
	}
	public void setFiller11(String filler11) {
		this.filler11=filler11;
	}
	@Field(offset=85, length=9, align=Align.RIGHT, paddingChar=' ')
	@FixedFormatDecimal (decimals=4, useDecimalDelimiter=true)
	public BigDecimal getMargin() {
		return margin;
	}
	public void setMargin(BigDecimal margin) {
		this.margin=margin;
	}
	@Field(offset=94, length=1)
	public String getFiller12() {
		return filler12;
	}
	public void setFiller12(String filler12) {
		this.filler12=filler12;
	}
	@Field(offset=95, length=3, align=Align.RIGHT, paddingChar=' ')
	public String getSubtype() {
		return subtype;
	}
	public void setSubtype(String subtype) {
		this.subtype=subtype;
	}
	@Field(offset=98, length=1)
	public String getTransfer_type() {
		return transfer_type;
	}
	public void setTransfer_type(String transfer_type) {
		this.transfer_type=transfer_type;
	}
	@Field(offset=99, length=1)
	public String getFiller13() {
		return filler13;
	}
	public void setFiller13(String filler13) {
		this.filler13=filler13;
	}
	@Field(offset=100, length=8)
	@FixedFormatPattern("MM/dd/yy")
	public Date getFirst_rate_chg_date() {
		return first_rate_chg_date;
	}
	public void setFirst_rate_chg_date(Date first_rate_chg_date) {
		this.first_rate_chg_date=first_rate_chg_date;
	}
	@Field(offset=108, length=1)
	public String getFiller14() {
		return filler14;
	}
	public void setFiller14(String filler14) {
		this.filler14=filler14;
	}
	@Field(offset=109, length=8)
	@FixedFormatPattern("MM/dd/yy")
	public Date getFirst_paymt_chg_date() {
		return first_paymt_chg_date;
	}
	public void setFirst_paymt_chg_date(Date first_paymt_chg_date) {
		this.first_paymt_chg_date=first_paymt_chg_date;
	}
	@Field(offset=117, length=1)
	public String getFiller15() {
		return filler15;
	}
	public void setFiller15(String filler15) {
		this.filler15=filler15;
	}
	@Field(offset=118, length=8, align=Align.RIGHT, paddingChar=' ')
	@FixedFormatDecimal (decimals=4, useDecimalDelimiter=true)
	public BigDecimal getOrig_net_life_cap() {
		return orig_net_life_cap;
	}
	public void setOrig_net_life_cap(BigDecimal orig_net_life_cap) {
		this.orig_net_life_cap=orig_net_life_cap;
	}
	@Field(offset=126, length=1)
	public String getFiller16() {
		return filler16;
	}
	public void setFiller16(String filler16) {
		this.filler16=filler16;
	}
	@Field(offset=127, length=8, align=Align.RIGHT, paddingChar=' ')
	@FixedFormatDecimal (decimals=4, useDecimalDelimiter=true)
	public BigDecimal getOrig_net_life_floor() {
		return orig_net_life_floor;
	}
	public void setOrig_net_life_floor(BigDecimal orig_net_life_floor) {
		this.orig_net_life_floor=orig_net_life_floor;
	}
	@Field(offset=135, length=1)
	public String getFiller17() {
		return filler17;
	}
	public void setFiller17(String filler17) {
		this.filler17=filler17;
	}
	@Field(offset=136, length=3, align=Align.RIGHT, paddingChar=' ')
	public Integer getMonths_to_next_rt_chg() {
		return months_to_next_rt_chg;
	}
	public void setMonths_to_next_rt_chg(Integer months_to_next_rt_chg) {
		this.months_to_next_rt_chg=months_to_next_rt_chg;
	}

	
	public List<ArmResetDaily> getArmResets() {
		return armResets;
	}
	public void setArmResets(List<ArmResetDaily> armResets) {
		this.armResets=armResets;
	}
	@Override
	public String toString() {
		return record_type + "|" + pool_number + "|" + prefix + "|" + 
		cusip_number + "|" + issue_date + "|" + pass_thru_rate + "|" + original_wac + "|" + original_wam + "|" + 
		maturity_date + "|" + original_balance + "|" + smp_percent + "|" + margin + "|" + subtype + "|" + 
		transfer_type + "|" + first_rate_chg_date + "|" + first_paymt_chg_date + "|" + 
		orig_net_life_cap + "|" + orig_net_life_floor + "|" + months_to_next_rt_chg;

	}
}
