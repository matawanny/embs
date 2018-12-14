package com.yieldbook.mortgage.domain.fnma;

import java.math.BigDecimal;

public class NonStandardLoan {
    public String poolNumber;
    public String recordType;
    public String type;
    public Integer numberOfLoans;
    public BigDecimal percentOfUPB;
    public BigDecimal aggregateUPB;

    @Override
	public String toString() {
		return "OccupancyType [type=" + type + ", numberOfLoans="
				+ numberOfLoans + ", percentOfUPB=" + percentOfUPB
				+ ", aggregateUPB=" + aggregateUPB + "]";
	}

}
