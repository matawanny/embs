package com.yieldbook.mortgage.domain.gnma;

import java.math.BigDecimal;

import com.ancientprogramming.fixedformat4j.annotation.Align;
import com.ancientprogramming.fixedformat4j.annotation.Field;
import com.ancientprogramming.fixedformat4j.annotation.FixedFormatDecimal;
import com.ancientprogramming.fixedformat4j.annotation.Record;

@Record
public class QuartileElement4 {
	private BigDecimal element;
	private BigDecimal maximum;
	private BigDecimal seventyFivePercent;
	private BigDecimal median;
	private BigDecimal twentyFivePercent;
	private BigDecimal minimum;
	
	
	@Field(offset=1, length=4, align=Align.RIGHT, paddingChar='0')
	@FixedFormatDecimal (decimals=3, useDecimalDelimiter=false)		
	public BigDecimal getElement() {
		return element;
	}
	public void setElement(BigDecimal element) {
		this.element = element;
	}
	@Field(offset=5, length=4, align=Align.RIGHT, paddingChar='0')
	@FixedFormatDecimal (decimals=3, useDecimalDelimiter=false)		
	public BigDecimal getMaximum() {
		return maximum;
	}
	public void setMaximum(BigDecimal maximum) {
		this.maximum = maximum;
	}
	@Field(offset=9, length=4, align=Align.RIGHT, paddingChar='0')
	@FixedFormatDecimal (decimals=3, useDecimalDelimiter=false)	
	public BigDecimal getSeventyFivePercent() {
		return seventyFivePercent;
	}
	public void setSeventyFivePercent(BigDecimal seventyFivePercent) {
		this.seventyFivePercent = seventyFivePercent;
	}
	@Field(offset=13, length=4, align=Align.RIGHT, paddingChar='0')
	@FixedFormatDecimal (decimals=3, useDecimalDelimiter=false)	
	public BigDecimal getMedian() {
		return median;
	}
	public void setMedian(BigDecimal median) {
		this.median = median;
	}
	@Field(offset=17, length=4, align=Align.RIGHT, paddingChar='0')
	@FixedFormatDecimal (decimals=3, useDecimalDelimiter=false)	
	public BigDecimal getTwentyFivePercent() {
		return twentyFivePercent;
	}
	public void setTwentyFivePercent(BigDecimal twentyFivePercent) {
		this.twentyFivePercent = twentyFivePercent;
	}
	@Field(offset=21, length=4, align=Align.RIGHT, paddingChar='0')
	@FixedFormatDecimal (decimals=3, useDecimalDelimiter=false)	
	public BigDecimal getMinimum() {
		return minimum;
	}
	public void setMinimum(BigDecimal minimum) {
		this.minimum = minimum;
	}
	@Override
	public String toString() {
		return "QuartileElement [element=" + element + ", maximum=" + maximum
				+ ", seventyFivePercent=" + seventyFivePercent + ", median="
				+ median + ", twentyFivePercent=" + twentyFivePercent
				+ ", minimum=" + minimum + "]";
	}


}
