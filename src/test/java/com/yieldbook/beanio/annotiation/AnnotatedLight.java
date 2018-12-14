package com.yieldbook.beanio.annotiation;
import java.util.LinkedList;
import java.util.List;

import org.beanio.annotation.Field;
import org.beanio.annotation.Segment;
import org.beanio.builder.XmlType;

public class AnnotatedLight {

    @Field(at=0, xmlType=XmlType.ATTRIBUTE) 
    public int quantity; 
     
    @Segment(at=1, collection=LinkedList.class, minOccurs=2, maxOccurs=2) 
    public List<AnnotatedBulb> bulbs;

	@Override
	public String toString() {
		return "AnnotatedLight [quantity=" + quantity + ", bulbs=" + bulbs
				+ "]";
	} 	
    
    
}
