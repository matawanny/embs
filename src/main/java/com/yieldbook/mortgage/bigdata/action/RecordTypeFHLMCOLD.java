package com.yieldbook.mortgage.bigdata.action;

import java.util.HashMap;
import java.util.Map;

public enum RecordTypeFHLMCOLD {
	FILE_HEADER(0),
	POOL_DETAIL_HEADER(1),
	MODIFIED_LOAN(4),
	LOAN(5),
	POOL_DETAIL_TRAILER(8),
	FILE_TRAILER(9);

	private final int value;

    RecordTypeFHLMCOLD(final int newValue) {
        value = newValue;
    }

    public int getValue() { return value; }	
    
    // Mapping int to enum
    private static final Map<Integer, RecordTypeFHLMCOLD> _map = new HashMap<Integer, RecordTypeFHLMCOLD>();
    static
    {
        for (RecordTypeFHLMCOLD loanType : RecordTypeFHLMCOLD.values())
            _map.put(loanType.value, loanType);
    }

    /**
    * Get RecordType from value
    * @param value Value
    * @return LOANTYPE
    */
    public static RecordTypeFHLMCOLD from(int value)
    {
        return _map.get(value);
    }   
    
}
