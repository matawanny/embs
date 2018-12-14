package com.yieldbook.beanio.annotiation;
import org.beanio.annotation.Field; 
import org.beanio.annotation.Fields;

@Fields({ 
    @Field(at=0, name="floor", literal="hardwood") 
}) 
public class AnnotatedFloor { 
 
    @Field(at=1) 
    public int width; 
     
    @Field(at=2) 
    public int height;

	@Override
	public String toString() {
		return "AnnotatedFloor [width=" + width + ", height=" + height + "]";
	} 
    
}