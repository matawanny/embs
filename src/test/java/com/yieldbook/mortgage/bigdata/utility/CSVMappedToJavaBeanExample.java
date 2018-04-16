package com.yieldbook.mortgage.bigdata.utility;

import java.io.FileReader;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.yieldbook.mortgage.bigdata.bo.Employee;

public class CSVMappedToJavaBeanExample {
	   @SuppressWarnings({"rawtypes", "unchecked"})
	   public static void main(String[] args) throws Exception
	   {
	      CsvToBean csv = new CsvToBean();
	       
	      String csvFilename = "data.csv";
	      CSVReader csvReader = new CSVReader(new FileReader(csvFilename));
	       
	      //Set column mapping strategy
	      List list = csv.parse(setColumMapping(), csvReader);
	       
	      for (Object object : list) {
	          Employee employee = (Employee) object;
	          System.out.println(employee);
	      }
	   }
	    
	   @SuppressWarnings({"rawtypes", "unchecked"})
	   private static ColumnPositionMappingStrategy setColumMapping()
	   {
	      ColumnPositionMappingStrategy strategy = new ColumnPositionMappingStrategy();
	      strategy.setType(Employee.class);
	      String[] columns = new String[] {"id", "firstName", "lastName", "country", "age"};
	      strategy.setColumnMapping(columns);
	      return strategy;
	   }
}
