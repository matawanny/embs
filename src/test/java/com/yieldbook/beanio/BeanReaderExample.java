package com.yieldbook.beanio;

import java.io.File;

import org.beanio.BeanReader;
import org.beanio.StreamFactory;

public class BeanReaderExample {
	 public static void main(String[] args) throws Exception {
	        // create a StreamFactory
	        StreamFactory factory = StreamFactory.newInstance();
	        // load the mapping file
	        factory.load("src/test/resources/mapping.xml");
	        
	        // use a StreamFactory to create a BeanReader
	        BeanReader in = factory.createReader("employeeFile", new File("src/test/resources/employee.csv"));
	        Employee employee;
	        while ((employee = (Employee) in.read()) != null) {
	            System.out.println(employee);
	        }
	        in.close();
	    }
}
