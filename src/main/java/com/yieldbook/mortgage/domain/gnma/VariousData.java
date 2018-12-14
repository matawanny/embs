package com.yieldbook.mortgage.domain.gnma;

import com.ancientprogramming.fixedformat4j.annotation.Field;
import com.ancientprogramming.fixedformat4j.annotation.Record;

@Record
public class VariousData {
	private String cusip;
	private String poolNumber;
	private String poolIndicator;
	private String poolType;
	private String recrodType;
	
	private StatsElement42 fha;
	private StatsElement42 va;
	private StatsElement42 rd;
	private StatsElement42 pih;
	
	
	private StatsElement24 paidOff;
	private StatsElement24 repurchasedDelinquent;
	private StatsElement24 foreclosedWithClaimPayment;
	private StatsElement24 repurchasedLossMitigation;
	private StatsElement24 repurchasedSubstitution;
	private StatsElement24 otherRemovalRepurchased;
	private StatsElement24 fhaBuydown;
	private StatsElement24 vaBuydown;
	
	private StatsElement24 filler1;
	private StatsElement24 filler2;
	private StatsElement24 filler3;
	private StatsElement24 filler4;
	
	private StatsElement24 thirtyDaysDelinquent;
	private StatsElement24 sixtyDaysDelinquent;
	private StatsElement24 ninetyPlusDaysDelinquent;
	private StatsElement24 fhaThirtyDaysDelinquent;
	private StatsElement24 fhaSixtyDaysDelinquent;
	private StatsElement24 fhaNinetyPlusDaysDelinquent;
	private StatsElement24 vaThirtyDaysDelinquent;
	private StatsElement24 vaSixtyDaysDelinquent;
	private StatsElement24 vaNinetyPlusDaysDelinquent;
	private StatsElement24 rdThirtyDaysDelinquent;
	private StatsElement24 rdSixtyDaysDelinquent;
	private StatsElement24 rdNinetyPlusDaysDelinquent;
	private StatsElement24 pihThirtyDaysDelinquent;
	private StatsElement24 pihSixtyDaysDelinquent;
	private StatsElement24 pihNinetyPlusDaysDelinquent;
	
	private StatsElement24 filler5;
	private StatsElement24 filler6;

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
	@Field(offset=20, length=42)
	public StatsElement42 getFha() {
		return fha;
	}
	public void setFha(StatsElement42 fha) {
		this.fha = fha;
	}
	@Field(offset=62, length=42)
	public StatsElement42 getVa() {
		return va;
	}
	public void setVa(StatsElement42 va) {
		this.va = va;
	}
	@Field(offset=104, length=42)
	public StatsElement42 getRd() {
		return rd;
	}
	public void setRd(StatsElement42 rd) {
		this.rd = rd;
	}
	@Field(offset=146, length=42)
	public StatsElement42 getPih() {
		return pih;
	}
	public void setPih(StatsElement42 pih) {
		this.pih = pih;
	}
	@Field(offset=188, length=24)
	public StatsElement24 getPaidOff() {
		return paidOff;
	}
	public void setPaidOff(StatsElement24 paidOff) {
		this.paidOff = paidOff;
	}
	@Field(offset=212, length=24)
	public StatsElement24 getRepurchasedDelinquent() {
		return repurchasedDelinquent;
	}
	public void setRepurchasedDelinquent(StatsElement24 repurchasedDelinquent) {
		this.repurchasedDelinquent = repurchasedDelinquent;
	}
	@Field(offset=236, length=24)
	public StatsElement24 getForeclosedWithClaimPayment() {
		return foreclosedWithClaimPayment;
	}
	public void setForeclosedWithClaimPayment(
			StatsElement24 foreclosedWithClaimPayment) {
		this.foreclosedWithClaimPayment = foreclosedWithClaimPayment;
	}
	@Field(offset=260, length=24)
	public StatsElement24 getRepurchasedLossMitigation() {
		return repurchasedLossMitigation;
	}
	public void setRepurchasedLossMitigation(
			StatsElement24 repurchasedLossMitigation) {
		this.repurchasedLossMitigation = repurchasedLossMitigation;
	}
	@Field(offset=284, length=24)
	public StatsElement24 getRepurchasedSubstitution() {
		return repurchasedSubstitution;
	}
	public void setRepurchasedSubstitution(StatsElement24 repurchasedSubstitution) {
		this.repurchasedSubstitution = repurchasedSubstitution;
	}
	@Field(offset=308, length=24)
	public StatsElement24 getOtherRemovalRepurchased() {
		return otherRemovalRepurchased;
	}
	public void setOtherRemovalRepurchased(StatsElement24 otherRemovalRepurchased) {
		this.otherRemovalRepurchased = otherRemovalRepurchased;
	}
	@Field(offset=332, length=24)
	public StatsElement24 getFhaBuydown() {
		return fhaBuydown;
	}
	public void setFhaBuydown(StatsElement24 fhaBuydown) {
		this.fhaBuydown = fhaBuydown;
	}
	@Field(offset=356, length=24)
	public StatsElement24 getVaBuydown() {
		return vaBuydown;
	}
	public void setVaBuydown(StatsElement24 vaBuydown) {
		this.vaBuydown = vaBuydown;
	}
	@Field(offset=380, length=24)
	public StatsElement24 getFiller1() {
		return filler1;
	}
	public void setFiller1(StatsElement24 filler1) {
		this.filler1 = filler1;
	}
	@Field(offset=404, length=24)
	public StatsElement24 getFiller2() {
		return filler2;
	}
	public void setFiller2(StatsElement24 filler2) {
		this.filler2 = filler2;
	}
	@Field(offset=428, length=24)
	public StatsElement24 getFiller3() {
		return filler3;
	}
	public void setFiller3(StatsElement24 filler3) {
		this.filler3 = filler3;
	}
	@Field(offset=452, length=24)
	public StatsElement24 getFiller4() {
		return filler4;
	}
	public void setFiller4(StatsElement24 filler4) {
		this.filler4 = filler4;
	}
	@Field(offset=476, length=24)
	public StatsElement24 getThirtyDaysDelinquent() {
		return thirtyDaysDelinquent;
	}
	public void setThirtyDaysDelinquent(StatsElement24 thirtyDaysDelinquent) {
		this.thirtyDaysDelinquent = thirtyDaysDelinquent;
	}
	@Field(offset=500, length=24)
	public StatsElement24 getSixtyDaysDelinquent() {
		return sixtyDaysDelinquent;
	}
	public void setSixtyDaysDelinquent(StatsElement24 sixtyDaysDelinquent) {
		this.sixtyDaysDelinquent = sixtyDaysDelinquent;
	}
	@Field(offset=524, length=24)
	public StatsElement24 getNinetyPlusDaysDelinquent() {
		return ninetyPlusDaysDelinquent;
	}
	public void setNinetyPlusDaysDelinquent(
			StatsElement24 ninetyPlusDaysDelinquent) {
		this.ninetyPlusDaysDelinquent = ninetyPlusDaysDelinquent;
	}
	@Field(offset=548, length=24)
	public StatsElement24 getFhaThirtyDaysDelinquent() {
		return fhaThirtyDaysDelinquent;
	}
	public void setFhaThirtyDaysDelinquent(StatsElement24 fhaThirtyDaysDelinquent) {
		this.fhaThirtyDaysDelinquent = fhaThirtyDaysDelinquent;
	}
	@Field(offset=572, length=24)
	public StatsElement24 getFhaSixtyDaysDelinquent() {
		return fhaSixtyDaysDelinquent;
	}
	public void setFhaSixtyDaysDelinquent(StatsElement24 fhaSixtyDaysDelinquent) {
		this.fhaSixtyDaysDelinquent = fhaSixtyDaysDelinquent;
	}
	@Field(offset=596, length=24)
	public StatsElement24 getFhaNinetyPlusDaysDelinquent() {
		return fhaNinetyPlusDaysDelinquent;
	}
	public void setFhaNinetyPlusDaysDelinquent(
			StatsElement24 fhaNinetyPlusDaysDelinquent) {
		this.fhaNinetyPlusDaysDelinquent = fhaNinetyPlusDaysDelinquent;
	}
	@Field(offset=620, length=24)
	public StatsElement24 getVaThirtyDaysDelinquent() {
		return vaThirtyDaysDelinquent;
	}
	public void setVaThirtyDaysDelinquent(StatsElement24 vaThirtyDaysDelinquent) {
		this.vaThirtyDaysDelinquent = vaThirtyDaysDelinquent;
	}
	@Field(offset=644, length=24)
	public StatsElement24 getVaSixtyDaysDelinquent() {
		return vaSixtyDaysDelinquent;
	}
	public void setVaSixtyDaysDelinquent(StatsElement24 vaSixtyDaysDelinquent) {
		this.vaSixtyDaysDelinquent = vaSixtyDaysDelinquent;
	}
	@Field(offset=668, length=24)
	public StatsElement24 getVaNinetyPlusDaysDelinquent() {
		return vaNinetyPlusDaysDelinquent;
	}
	public void setVaNinetyPlusDaysDelinquent(
			StatsElement24 vaNinetyPlusDaysDelinquent) {
		this.vaNinetyPlusDaysDelinquent = vaNinetyPlusDaysDelinquent;
	}
	@Field(offset=692, length=24)
	public StatsElement24 getRdThirtyDaysDelinquent() {
		return rdThirtyDaysDelinquent;
	}
	public void setRdThirtyDaysDelinquent(StatsElement24 rdThirtyDaysDelinquent) {
		this.rdThirtyDaysDelinquent = rdThirtyDaysDelinquent;
	}
	@Field(offset=716, length=24)
	public StatsElement24 getRdSixtyDaysDelinquent() {
		return rdSixtyDaysDelinquent;
	}
	public void setRdSixtyDaysDelinquent(StatsElement24 rdSixtyDaysDelinquent) {
		this.rdSixtyDaysDelinquent = rdSixtyDaysDelinquent;
	}
	@Field(offset=740, length=24)
	public StatsElement24 getRdNinetyPlusDaysDelinquent() {
		return rdNinetyPlusDaysDelinquent;
	}
	public void setRdNinetyPlusDaysDelinquent(
			StatsElement24 rdNinetyPlusDaysDelinquent) {
		this.rdNinetyPlusDaysDelinquent = rdNinetyPlusDaysDelinquent;
	}
	@Field(offset=764, length=24)
	public StatsElement24 getPihThirtyDaysDelinquent() {
		return pihThirtyDaysDelinquent;
	}
	public void setPihThirtyDaysDelinquent(StatsElement24 pihThirtyDaysDelinquent) {
		this.pihThirtyDaysDelinquent = pihThirtyDaysDelinquent;
	}
	@Field(offset=788, length=24)
	public StatsElement24 getPihSixtyDaysDelinquent() {
		return pihSixtyDaysDelinquent;
	}
	public void setPihSixtyDaysDelinquent(StatsElement24 pihSixtyDaysDelinquent) {
		this.pihSixtyDaysDelinquent = pihSixtyDaysDelinquent;
	}
	@Field(offset=812, length=24)
	public StatsElement24 getPihNinetyPlusDaysDelinquent() {
		return pihNinetyPlusDaysDelinquent;
	}
	public void setPihNinetyPlusDaysDelinquent(
			StatsElement24 pihNinetyPlusDaysDelinquent) {
		this.pihNinetyPlusDaysDelinquent = pihNinetyPlusDaysDelinquent;
	}
	@Field(offset=836, length=24)
	public StatsElement24 getFiller5() {
		return filler5;
	}
	public void setFiller5(StatsElement24 filler5) {
		this.filler5 = filler5;
	}
	@Field(offset=860, length=24)
	public StatsElement24 getFiller6() {
		return filler6;
	}
	public void setFiller6(StatsElement24 filler6) {
		this.filler6 = filler6;
	}
	@Field(offset=884, length=6)
	public Integer getAsOfDate() {
		return asOfDate;
	}
	public void setAsOfDate(Integer asOfDate) {
		this.asOfDate = asOfDate;
	}
	@Override
	public String toString() {
		return "VariousDaily [fha=" + fha + ", va=" + va + ", rd=" + rd
				+ ", pih=" + pih + ", paidOff=" + paidOff
				+ ", repurchasedDelinquent=" + repurchasedDelinquent
				+ ", foreclosedWithClaimPayment=" + foreclosedWithClaimPayment
				+ ", repurchasedLossMitigation=" + repurchasedLossMitigation
				+ ", repurchasedSubstitution=" + repurchasedSubstitution
				+ ", otherRemovalRepurchased=" + otherRemovalRepurchased
				+ ", fhaBuydown=" + fhaBuydown + ", vaBuydown=" + vaBuydown
				+ ", thirtyDaysDelinquent=" + thirtyDaysDelinquent
				+ ", sixtyDaysDelinquent=" + sixtyDaysDelinquent
				+ ", ninetyPlusDaysDelinquent="
				+ ninetyPlusDaysDelinquent + ", fhaThirtyDaysDelinquent="
				+ fhaThirtyDaysDelinquent + ", fhaSixtyDaysDelinquent="
				+ fhaSixtyDaysDelinquent + ", fhaNinetyPlusDaysDelinquent="
				+ fhaNinetyPlusDaysDelinquent + ", vaThirtyDaysDelinquent="
				+ vaThirtyDaysDelinquent + ", vaSixtyDaysDelinquent="
				+ vaSixtyDaysDelinquent + ", vaNinetyPlusDaysDelinquent="
				+ vaNinetyPlusDaysDelinquent + ", rdThirtyDaysDelinquent="
				+ rdThirtyDaysDelinquent + ", rdSixtyDaysDelinquent="
				+ rdSixtyDaysDelinquent + ", rdNinetyPlusDaysDelinquent="
				+ rdNinetyPlusDaysDelinquent + ", pihThirtyDaysDelinquent="
				+ pihThirtyDaysDelinquent + ", pihSixtyDaysDelinquent="
				+ pihSixtyDaysDelinquent + ", pihNinetyPlusDaysDelinquent="
				+ pihNinetyPlusDaysDelinquent + "]";
	}

}
