package com.yieldbook.mortgage.domain.fnma;

import java.math.BigDecimal;

public class PoolDailyQuartile {
    public String poolNumber;
    public String recordType;
    public String quartileLevel;
    public BigDecimal loanSize;
    public BigDecimal coupon;
    public BigDecimal ltv;
    public BigDecimal creditScore;
    public String loanTerm;
    public Integer loanAge;
    public Integer remainingMaturity;
	@Override
	public String toString() {
		return "Quartile [quartileLevel="
				+ quartileLevel + ", loanSize=" + loanSize + ", coupon="
				+ coupon + ", ltv=" + ltv + ", creditScore=" + creditScore
				+ ", loanTerm=" + loanTerm + ", loanAge=" + loanAge
				+ ", remainingMaturity=" + remainingMaturity + "]";
	}

}
