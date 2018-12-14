package com.yieldbook.mortgage.domain.fnma;

import java.math.BigDecimal;

public class Servicer {
    public String poolNumber;
    public String recordType;
    public String servicerName;
    public Integer numberOfLoans;
    public BigDecimal percentOfUPB;
    public BigDecimal aggregateUPB;

    @Override
	public String toString() {
		return "Servicer [servicerName=" + servicerName + ", numberOfLoans="
				+ numberOfLoans + ", percentOfUPB=" + percentOfUPB
				+ ", aggregateUPB=" + aggregateUPB + "]";
	}

}
