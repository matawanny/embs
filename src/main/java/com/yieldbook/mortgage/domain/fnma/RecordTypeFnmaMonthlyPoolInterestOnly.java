package com.yieldbook.mortgage.domain.fnma;

import java.util.HashMap;
import java.util.Map;

public enum RecordTypeFnmaMonthlyPoolInterestOnly {
	ONE(1),
	TWO(2);

	private final int value;

    RecordTypeFnmaMonthlyPoolInterestOnly(final int newValue) {
        value = newValue;
    }

    public int getValue() { return value; }	
    
    // Mapping int to enum
    private static final Map<Integer, RecordTypeFnmaMonthlyPoolInterestOnly> _map = new HashMap<Integer, RecordTypeFnmaMonthlyPoolInterestOnly>();
    static
    {
        for (RecordTypeFnmaMonthlyPoolInterestOnly loanType : RecordTypeFnmaMonthlyPoolInterestOnly.values())
            _map.put(loanType.value, loanType);
    }

    /**
    * Get RecordType from value
    * @param value Value
    * @return LOANTYPE
    */
    public static RecordTypeFnmaMonthlyPoolInterestOnly from(int value)
    {
        return _map.get(value);
    }   
    
}
