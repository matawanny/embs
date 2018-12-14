package com.yieldbook.beanio.annotiation;
import org.beanio.StreamFactory;
import org.beanio.Unmarshaller;
import org.beanio.builder.DelimitedParserBuilder;
import org.beanio.builder.StreamBuilder;
public class AnnotationFnmaPoolStatisticsTest {
	
	 public static void main(String[] args) throws Exception {
	        // create a StreamFactory
	        StreamFactory factory = StreamFactory.newInstance();
	        factory.define(new StreamBuilder("s1") 
            .format("delimited")
            .parser(new DelimitedParserBuilder('|')) 
            .addRecord(AnnotatedUser.class)); 

	        String [] input = new String[] { 
	                // CSV input: 
	                "USER|joe|smith|left|right|1970-01-01|0028|A|B|1|END",
	                "USER|Philip|Xiao|left|right|1970-01-01|0028|A|B|1|END"
	        };
	        
	        Unmarshaller u = factory.createUnmarshaller("s1");
	        for (int i=0; i<input.length; i++){
	        	AnnotatedUser user = (AnnotatedUser) u.unmarshal(input[i]);
	        	System.out.println(user);
	        
	        }
	   }
	
}
