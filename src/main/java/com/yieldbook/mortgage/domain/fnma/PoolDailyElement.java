package com.yieldbook.mortgage.domain.fnma;

import java.math.BigDecimal;

public class PoolDailyElement {
    public String poolNumber;
    public String recordType;
    public String elementName;
    public String entityName;
    public String entityValue;
    public String numberOfLoans;
    public BigDecimal percentOfUPB;
    public BigDecimal aggregateUPB;
	@Override
	public String toString() {
		return "Item [itemName=" + elementName + ", entityName=" + entityName
				+ ", entityValue=" + entityValue + ", numberOfLoans="
				+ numberOfLoans + ", percentOfUPB=" + percentOfUPB
				+ ", aggregateUPB=" + aggregateUPB + "]";
	}

    
}
