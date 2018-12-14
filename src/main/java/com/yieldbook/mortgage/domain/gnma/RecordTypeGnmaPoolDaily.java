package com.yieldbook.mortgage.domain.gnma;

import java.util.HashMap;
import java.util.Map;

public enum RecordTypeGnmaPoolDaily {
	POOL_DETAIL("D"),
	MULTII_ISSUER("I"),
	MSA("M"),
	ORIGINATION_YEAR("O"),
	PRE_MODIFICATION("P"),
	REMOVALS_BY_ISSUER("R"),
	STATE("S"),
	VARIOUS_DATA("V"),
	SUPPLEMENTAL_DATA("U"),
	MULTI_ISSUER_DELINQUENCY("L"),
	INSURANCE_PREMIUM_AND_OTHER_DATA("F"),
	TRANSFER_ACTIVITY("X"),
	ADJUSTABLE_RATE_MORTGAGE("N");

	private final String value;

    RecordTypeGnmaPoolDaily(final String newValue) {
        value = newValue;
    }

    public String getValue() { return value; }	
    
    // Mapping String to enum
    private static final Map<String, RecordTypeGnmaPoolDaily> _map = new HashMap<>();
    static
    {
        for (RecordTypeGnmaPoolDaily loanType : RecordTypeGnmaPoolDaily.values())
            _map.put(loanType.value, loanType);
    }

    /**
    * Get RecordTypeGnamPool from value
    * @param String Value
    * @return RecordTypeGnamPool
    */
    public static RecordTypeGnmaPoolDaily from(String value)
    {
        return _map.get(value);
    }   
    
}
