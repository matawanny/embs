package com.yieldbook.beanio.annotiation;

import org.beanio.annotation.Field; 

public class AnnotatedBulb { 
     
    @Field(at=0) 
    public int watts; 
     
    @Field(at=1) 
    public String style;

	@Override
	public String toString() {
		return "AnnotatedBulb [watts=" + watts + ", style=" + style + "]";
	}
    
    
     
}