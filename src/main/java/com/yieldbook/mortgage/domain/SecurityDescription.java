package com.yieldbook.mortgage.domain;

public class SecurityDescription {
	
	public String securityType;
	public String interestRate;
	public String poolPrefix;
	public String poolNumber;
	@Override
	public String toString() {
		return "SecurityDescription [securityType=" + securityType
				+ ", interestRate=" + interestRate + ", poolPrefix="
				+ poolPrefix + ", poolNumber=" + poolNumber + "]";
	}
	
}
