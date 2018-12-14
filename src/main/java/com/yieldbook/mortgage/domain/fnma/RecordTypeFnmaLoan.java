package com.yieldbook.mortgage.domain.fnma;

import java.util.HashMap;
import java.util.Map;

public enum RecordTypeFnmaLoan {
	POOL_LEVEL(1),
	LOAN_LEVEL(2);

	private final int value;

    RecordTypeFnmaLoan(final int newValue) {
        value = newValue;
    }

    public int getValue() { return value; }	
    
    // Mapping int to enum
    private static final Map<Integer, RecordTypeFnmaLoan> _map = new HashMap<Integer, RecordTypeFnmaLoan>();
    static
    {
        for (RecordTypeFnmaLoan loanType : RecordTypeFnmaLoan.values())
            _map.put(loanType.value, loanType);
    }

    /**
    * Get RecordType from value
    * @param value Value
    * @return LOANTYPE
    */
    public static RecordTypeFnmaLoan from(int value)
    {
        return _map.get(value);
    }   
    
}
