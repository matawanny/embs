package com.yieldbook.mortgage.domain.fnma;

import java.math.BigDecimal;

import com.yieldbook.mortgage.domain.Address;
import com.yieldbook.mortgage.domain.EmbsDate;
import com.yieldbook.mortgage.domain.EmbsMonth;
import com.yieldbook.mortgage.domain.SecurityDescription;

public class PoolMonthlyStats1 {
	
	public String cusip;
	public String poolPrefix;
    public int poolNumber;
    public String poolType;
    public BigDecimal originalBalance;
    public EmbsMonth currentDate;
    public BigDecimal currentBalance;
    public BigDecimal currentFactor;
    public BigDecimal passThroughRate;
    public EmbsDate issueDate;
    public EmbsDate maturityDate;
    public BigDecimal originalWAMaturity;
    public String sellerName;
    public Address sellerAddress;
    public BigDecimal originalWACoupon;
    public SecurityDescription securityDescription;
    public BigDecimal currentWACoupon;
    public BigDecimal currentWAMaturity;
    public String filler;
    
	@Override
	public String toString() {
		return "PoolMonthlyStats [cusip=" + cusip + ", poolPrefix="
				+ poolPrefix + ", poolNumber=" + poolNumber + ", poolType="
				+ poolType + ", originalBalance=" + originalBalance
				+ ", currentDate=" + currentDate + ", currentBalance="
				+ currentBalance + ", currentFactor=" + currentFactor
				+ ", passThroughRate=" + passThroughRate + ", issueDate="
				+ issueDate + ", maturityDate=" + maturityDate
				+ ", originalWAMaturity=" + originalWAMaturity
				+ ", sellerName=" + sellerName + ", sellerAddress="
				+ sellerAddress + ", originalWACoupon=" + originalWACoupon
				+ ", securityDescription=" + securityDescription
				+ ", currentWACoupon=" + currentWACoupon
				+ ", currentWAMaturity=" + currentWAMaturity + ", filler="
				+ filler + "]";
	}

    
    
}
