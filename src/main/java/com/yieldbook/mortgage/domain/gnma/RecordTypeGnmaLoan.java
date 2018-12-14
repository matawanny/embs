package com.yieldbook.mortgage.domain.gnma;

import java.util.HashMap;
import java.util.Map;

public enum RecordTypeGnmaLoan {
	FILE_HEADER("H"),
	POOL_HEADER("P"),
	LOAN_LEVEL("L"),
	ROOL_TRAILER("T"),
	FILE_TRAILER("Z");

	private final String value;

    RecordTypeGnmaLoan(final String newValue) {
        value = newValue;
    }

    public String getValue() { return value; }	
    
    // Mapping String to enum
    private static final Map<String, RecordTypeGnmaLoan> _map = new HashMap<>();
    static
    {
        for (RecordTypeGnmaLoan loanType : RecordTypeGnmaLoan.values())
            _map.put(loanType.value, loanType);
    }

    /**
    * Get RecordTypeGnmaLoan from value
    * @param String Value
    * @return RecordTypeGnmaLoan
    */
    public static RecordTypeGnmaLoan from(String value)
    {
        return _map.get(value);
    }   
    
}
