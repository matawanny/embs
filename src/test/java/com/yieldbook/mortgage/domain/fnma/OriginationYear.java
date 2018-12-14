package com.yieldbook.mortgage.domain.fnma;

import java.math.BigDecimal;

public class OriginationYear {
    public String poolNumber;
    public String recordType;
    public Integer year;
    public Integer numberOfLoans;
    public BigDecimal percentOfUPB;
    public BigDecimal aggregateUPB;

    @Override
	public String toString() {
		return "OriginationYear [year=" + year + ", numberOfLoans="
				+ numberOfLoans + ", percentOfUPB=" + percentOfUPB
				+ ", aggregateUPB=" + aggregateUPB + "]";
	}

}
