package com.yieldbook.mortgage.domain.fnma;

import java.math.BigDecimal;

public class PropertyType {
    public String poolNumber;
    public String recordType;
    public Integer numberOfUnits;
    public Integer numberOfLoans;
    public BigDecimal percentOfUPB;
    public BigDecimal aggregateUPB;

    @Override
	public String toString() {
		return "PropertyType [poolNumber=" + poolNumber + ", recordType="
				+ recordType + ", numberOfUnits=" + numberOfUnits
				+ ", numberOfLoans=" + numberOfLoans + ", percentOfUPB="
				+ percentOfUPB + ", aggregateUPB=" + aggregateUPB + "]";
	}

}
