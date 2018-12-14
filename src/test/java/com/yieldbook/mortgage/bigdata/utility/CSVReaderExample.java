package com.yieldbook.mortgage.bigdata.utility;

import java.io.FileReader;
import java.io.IOException;

import com.opencsv.CSVReader;

public class CSVReaderExample {

    public static void main(String[] args) {

        String csvFile = "C:/data/FHLMONLA_20170801.dat";

        CSVReader reader = null;
        
        try {
        	int i =1;
            reader = new CSVReader(new FileReader(csvFile));
            String[] line;
            while ((line = reader.readNext()) != null && i<=30) {
                //System.out.println("Country [id= " + line[0] + ", code= " + line[1] + " , name=" + line[2] + "]");
                for (String column: line){
                	System.out.print(column+"|");
                }
                System.out.println("");
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}