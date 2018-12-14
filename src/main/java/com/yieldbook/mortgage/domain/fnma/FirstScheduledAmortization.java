package com.yieldbook.mortgage.domain.fnma;

import java.math.BigDecimal;
import java.util.Date;

public class FirstScheduledAmortization {
    public String poolNumber;
    public String recordType;
    public Date date;
    public Integer numberOfLoans;
    public BigDecimal percentOfUPB;
    public BigDecimal aggregateUPB;

    @Override
	public String toString() {
		return "FirstScheduledAmortization [date=" + date
				+ ", numberOfLoans=" + numberOfLoans + ", percentOfUPB="
				+ percentOfUPB + ", aggregateUPB=" + aggregateUPB + "]";
	}

}
