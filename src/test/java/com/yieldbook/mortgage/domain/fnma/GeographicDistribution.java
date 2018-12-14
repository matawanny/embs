package com.yieldbook.mortgage.domain.fnma;

import java.math.BigDecimal;

public class GeographicDistribution {
    public String poolNumber;
    public String recordType;
    public String state;
    public Integer numberOfLoans;
    public BigDecimal percentOfUPB;
    public BigDecimal aggregateUPB;

    @Override
	public String toString() {
		return "GeographicDistribution [state=" + state + ", numberOfLoans="
				+ numberOfLoans + ", percentOfUPB=" + percentOfUPB
				+ ", aggregateUPB=" + aggregateUPB + "]";
	}

}
