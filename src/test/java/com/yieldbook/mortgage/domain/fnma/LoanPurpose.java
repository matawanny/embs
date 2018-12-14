package com.yieldbook.mortgage.domain.fnma;

import java.math.BigDecimal;

public class LoanPurpose {
    public String poolNumber;
    public String recordType;
    public String type;
    public Integer numberOfLoans;
    public BigDecimal percentOfUPB;
    public BigDecimal aggregateUPB;
	@Override
	public String toString() {
		return "LoanPurpose [type=" + type
				+ ", numberOfLoans=" + numberOfLoans + ", percentOfUPB="
				+ percentOfUPB + ", aggregateUPB=" + aggregateUPB + "]";
	}

}
