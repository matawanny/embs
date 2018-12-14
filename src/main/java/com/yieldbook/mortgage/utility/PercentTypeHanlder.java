package com.yieldbook.mortgage.utility;

import org.beanio.types.TypeConversionException;
import org.beanio.types.TypeHandler;

public class PercentTypeHanlder implements TypeHandler {
	public Object parse(String text) throws TypeConversionException {
		if(text==null || text.trim().length()==0)
			return null;
	    int x = Integer.parseInt(text.substring(0,text.length()-2));
	    Float y = new Float(x /100);
		return y;
	}

	public String format(Object value) {
		int x = (int)((float)value * 100);
		String y = x + "%";
		return y;
	}

	public Class<?> getType() {
		return Float.class;
	}

}
