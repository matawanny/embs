package com.yieldbook.beanio.annotiation;
import org.beanio.StreamFactory;
import org.beanio.Unmarshaller;
import org.beanio.builder.StreamBuilder;
public class AnnotationRoomTest {
	
	 public static void main(String[] args) throws Exception {
	        // create a StreamFactory
	        StreamFactory factory = StreamFactory.newInstance();
	        factory.define(new StreamBuilder("s1") 
            .format("csv") 
            .addRecord(AnnotatedRoom.class)); 

	        String [] input = new String[] { 
	                // CSV input: 
	                "2,60,CFL,40,IC,Bath,hardwood,10,20", 
	                "3,60,CFL,40,IC,Bath,hardwood,10,30"
	        };
	        
	        Unmarshaller u = factory.createUnmarshaller("s1");
	        for (int i=0; i<input.length; i++){
	        	AnnotatedRoom room = (AnnotatedRoom) u.unmarshal(input[i]);
	        	System.out.println(room);
	        
	        }
	   }
	
}
