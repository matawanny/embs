package com.yieldbook.mortgage.domain.fnma;

import java.util.HashMap;
import java.util.Map;

public enum RecordTypeFnmaMonthlyPoolSupplement {
	HEADER(1),
	QUARTILE(2),
	SERVICER(3),
	THIRDPARTYORIGINATIONTYPE(4);
	

	private final int value;

    RecordTypeFnmaMonthlyPoolSupplement(final int newValue) {
        value = newValue;
    }

    public int getValue() { return value; }	
    
    // Mapping int to enum
    private static final Map<Integer, RecordTypeFnmaMonthlyPoolSupplement> _map = new HashMap<Integer, RecordTypeFnmaMonthlyPoolSupplement>();
    static
    {
        for (RecordTypeFnmaMonthlyPoolSupplement loanType : RecordTypeFnmaMonthlyPoolSupplement.values())
            _map.put(loanType.value, loanType);
    }

    /**
    * Get RecordType from value
    * @param value Value
    * @return LOANTYPE
    */
    public static RecordTypeFnmaMonthlyPoolSupplement from(int value)
    {
        return _map.get(value);
    }   
    
}
