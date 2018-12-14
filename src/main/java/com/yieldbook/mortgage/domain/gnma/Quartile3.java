package com.yieldbook.mortgage.domain.gnma;

import com.ancientprogramming.fixedformat4j.annotation.Align;
import com.ancientprogramming.fixedformat4j.annotation.Field;
import com.ancientprogramming.fixedformat4j.annotation.Record;

@Record
public class Quartile3 {
	private Integer weightedAverage;
	private Integer maximum;
	private Integer seventyFivePercent;
	private Integer median;
	private Integer twentyFivePercent;
	private Integer minimum;
	
	
	@Field(offset=1, length=3, align=Align.RIGHT, paddingChar='0')
	public Integer getWeightedAverage() {
		return weightedAverage;
	}
	public void setWeightedAverage(Integer weightedAverage) {
		this.weightedAverage = weightedAverage;
	}
	@Field(offset=4, length=3, align=Align.RIGHT, paddingChar='0')
	public Integer getMaximum() {
		return maximum;
	}
	public void setMaximum(Integer maximum) {
		this.maximum = maximum;
	}
	@Field(offset=7, length=3, align=Align.RIGHT, paddingChar='0')
	public Integer getSeventyFivePercent() {
		return seventyFivePercent;
	}
	public void setSeventyFivePercent(Integer seventyFivePercent) {
		this.seventyFivePercent = seventyFivePercent;
	}
	@Field(offset=10, length=3, align=Align.RIGHT, paddingChar='0')
	public Integer getMedian() {
		return median;
	}
	public void setMedian(Integer median) {
		this.median = median;
	}
	@Field(offset=13, length=3, align=Align.RIGHT, paddingChar='0')
	public Integer getTwentyFivePercent() {
		return twentyFivePercent;
	}
	public void setTwentyFivePercent(Integer twentyFivePercent) {
		this.twentyFivePercent = twentyFivePercent;
	}
	@Field(offset=16, length=3, align=Align.RIGHT, paddingChar='0')
	public Integer getMinimum() {
		return minimum;
	}
	public void setMinimum(Integer minimum) {
		this.minimum = minimum;
	}
	@Override
	public String toString() {
		return "Quartile3 [weightedAverage=" + weightedAverage + ", maximum="
				+ maximum + ", seventyFivePercent=" + seventyFivePercent
				+ ", median=" + median + ", twentyFivePercent="
				+ twentyFivePercent + ", minimum=" + minimum + "]";
	}

}
