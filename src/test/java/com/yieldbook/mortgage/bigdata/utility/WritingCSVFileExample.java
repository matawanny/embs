package com.yieldbook.mortgage.bigdata.utility;

import java.io.FileWriter;

import com.opencsv.CSVWriter;

public class WritingCSVFileExample {

	   public static void main(String[] args) throws Exception
	   {
	      String csv = "src/main/resources/data.csv";
	      CSVWriter writer = new CSVWriter(new FileWriter(csv));
	        
	      //Create record
	      String [] record = "4,David,Miller,Australia,30".split(",");
	      //Write the record to file
	      writer.writeNext(record);
	        
	      //close the writer
	      writer.close();
	   }
}
