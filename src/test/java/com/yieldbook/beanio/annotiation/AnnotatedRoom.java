package com.yieldbook.beanio.annotiation;

import org.beanio.annotation.Field;
import org.beanio.annotation.Record;
import org.beanio.annotation.Segment;

@Record 
public class AnnotatedRoom { 
 
    @Segment(at=0, type=AnnotatedLight.class, getter="getLightFixture", setter="setLightFixture") 
    private Object light; 
     
    @Field(at=5) 
    public String name; 
     
    private AnnotatedFloor floor; 
     
    public AnnotatedLight getLightFixture() { 
        return (AnnotatedLight) light; 
    } 
 
    public void setLightFixture(AnnotatedLight light) { 
        this.light = light; 
    } 
 
    @Segment(at=6) 
    public AnnotatedFloor getFlooring() { 
        return floor; 
    } 
 
    public void setFlooring(AnnotatedFloor floor) { 
        this.floor = floor; 
    }

	@Override
	public String toString() {
		return "AnnotatedRoom [light=" + light + ", name=" + name + ", floor="
				+ floor + "]";
	} 
    
}
