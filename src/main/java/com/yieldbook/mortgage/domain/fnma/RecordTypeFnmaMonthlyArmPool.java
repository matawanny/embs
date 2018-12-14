package com.yieldbook.mortgage.domain.fnma;

import java.util.HashMap;
import java.util.Map;

public enum RecordTypeFnmaMonthlyArmPool {
	ARM_POOL_DATA(1),
	NEXT_RATE_CHANGE_DATE(2),
	FIRST_PAYMENT_DATE(3);
	

	private final int value;

    RecordTypeFnmaMonthlyArmPool(final int newValue) {
        value = newValue;
    }

    public int getValue() { return value; }	
    
    // Mapping int to enum
    private static final Map<Integer, RecordTypeFnmaMonthlyArmPool> _map = new HashMap<Integer, RecordTypeFnmaMonthlyArmPool>();
    static
    {
        for (RecordTypeFnmaMonthlyArmPool loanType : RecordTypeFnmaMonthlyArmPool.values())
            _map.put(loanType.value, loanType);
    }

    /**
    * Get RecordType from value
    * @param value Value
    * @return LOANTYPE
    */
    public static RecordTypeFnmaMonthlyArmPool from(int value)
    {
        return _map.get(value);
    }   
    
}
