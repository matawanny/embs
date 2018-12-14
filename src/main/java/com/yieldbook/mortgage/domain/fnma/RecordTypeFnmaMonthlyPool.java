package com.yieldbook.mortgage.domain.fnma;

import java.util.HashMap;
import java.util.Map;

public enum RecordTypeFnmaMonthlyPool {
	POOL_DATA(1),
	ARM_RESET(2),
	SKIP(3);
	

	private final int value;

    RecordTypeFnmaMonthlyPool(final int newValue) {
        value = newValue;
    }

    public int getValue() { return value; }	
    
    // Mapping int to enum
    private static final Map<Integer, RecordTypeFnmaMonthlyPool> _map = new HashMap<Integer, RecordTypeFnmaMonthlyPool>();
    static
    {
        for (RecordTypeFnmaMonthlyPool loanType : RecordTypeFnmaMonthlyPool.values())
            _map.put(loanType.value, loanType);
    }

    /**
    * Get RecordType from value
    * @param value Value
    * @return LOANTYPE
    */
    public static RecordTypeFnmaMonthlyPool from(int value)
    {
        return _map.get(value);
    }   
    
}
