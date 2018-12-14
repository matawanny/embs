package com.yieldbook.beanio.annotiation;

import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.beanio.annotation.Field;
import org.beanio.annotation.Fields;
import org.beanio.annotation.Record;
import org.beanio.builder.Align;
import org.beanio.builder.XmlType;
 
/**
 * Sample annotated object. 
 */ 
@Record(xmlName="user", xmlNamespace="http://org.beanio.test") 
@Fields({ 
    @Field(name="type", at=0, rid=true, literal="USER", xmlType=XmlType.ATTRIBUTE) 
}) 
public class AnnotatedUser extends AnnotatedUserSupport implements AnnotatedUserInterface { 
 
    private String[] hands; 
 
    @Field(at=6, type=Integer.class, padding='0', align=Align.RIGHT, length=4) 
    private Object age; 
     
    @Field(at=5, format="yyyy-MM-dd") 
    public Date birthDate; 
 
    @Field(at=7, minOccurs=2) 
    public List<Character> letters; 
     
    @SuppressWarnings("rawtypes") 
    @Field(at=9, until=-1, type=Integer.class, collection=LinkedList.class, minOccurs=1, maxOccurs=-1) 
    public List numbers; 
     
    @Field(at=-1) 
    public String end; 
     
    public String[] getHands() { 
        return hands; 
    } 
    public void setHands(String[] hands) { 
        this.hands = hands; 
    } 
     
    public Object getAge() { 
        return age; 
    } 
    public void setAge(Object age) { 
        this.age = age; 
    }
	@Override
	public String toString() {
		return "AnnotatedUser [hands=" + Arrays.toString(hands) +", firstName="  + getFirstName()
				+", surName=" + getSurname()
				+", age="	+ age + ", birthDate=" + birthDate + ", letters=" + letters
				+ ", numbers=" + numbers + ", end=" + end + "]";
	} 
    
    
}