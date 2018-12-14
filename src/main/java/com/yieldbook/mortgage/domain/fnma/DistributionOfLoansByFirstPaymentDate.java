package com.yieldbook.mortgage.domain.fnma;

import java.math.BigDecimal;
import java.util.Date;

public class DistributionOfLoansByFirstPaymentDate {
    public String poolNumber;
    public String recordType;
    public Date date;
    public Integer numberOfLoans;
    public BigDecimal percentOfUPB;
    public BigDecimal aggregateUPB;

    @Override
	public String toString() {
		return "DistributionOfLoansByFirstPaymentDate [date=" + date
				+ ", numberOfLoans=" + numberOfLoans + ", percentOfUPB="
				+ percentOfUPB + ", aggregateUPB=" + aggregateUPB + "]";
	}

}
