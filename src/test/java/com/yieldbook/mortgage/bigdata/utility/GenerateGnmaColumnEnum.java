package com.yieldbook.mortgage.bigdata.utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.yieldbook.mortgage.bigdata.bean.EmbsLoan;

public class GenerateGnmaColumnEnum {

    public static void main(String[] args) throws IOException {

        String fileName = "src/main/resources/gnma_loan_1.csv";
        Path myPath = Paths.get(fileName);
        System.out.println(fileName);
        try (BufferedReader br = Files.newBufferedReader(myPath,
                StandardCharsets.UTF_8)) {

            ColumnPositionMappingStrategy strategy = new ColumnPositionMappingStrategy();
            strategy.setType(EmbsLoan.class);
            String[] fields = {"columnName","type","mapping"};
            strategy.setColumnMapping(fields);

            CsvToBean csvToBean = new CsvToBeanBuilder(br)
                    .withType(EmbsLoan.class)
                    .withMappingStrategy(strategy)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            List<EmbsLoan> gnmaLoan = csvToBean.parse();
            StringBuilder sb = new StringBuilder();
            for(EmbsLoan loan: gnmaLoan){
            	String name = loan.getColumnName();
            	sb.setLength(0);
            	sb.append(name.toUpperCase())
            	  .append("(\"").append(name)
            	  .append("\".getBytes()),");
            	System.out.println(sb.toString());
            }
        }
        System.out.println("_________________________________________");

        fileName = "src/main/resources/gnma_arm_loan_1.csv";
        myPath = Paths.get(fileName);
        System.out.println(fileName);
        try (BufferedReader br = Files.newBufferedReader(myPath,
                StandardCharsets.UTF_8)) {

            ColumnPositionMappingStrategy strategy = new ColumnPositionMappingStrategy();
            strategy.setType(EmbsLoan.class);
            String[] fields = {"columnName","type","mapping"};
            strategy.setColumnMapping(fields);

            CsvToBean csvToBean = new CsvToBeanBuilder(br)
                    .withType(EmbsLoan.class)
                    .withMappingStrategy(strategy)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            List<EmbsLoan> gnmaLoan = csvToBean.parse();
            StringBuilder sb = new StringBuilder();
            for(EmbsLoan loan: gnmaLoan){
            	String name = loan.getColumnName();
            	sb.setLength(0);
            	sb.append(name.toUpperCase())
            	  .append("(\"").append(name)
            	  .append("\".getBytes()),");
            	System.out.println(sb.toString());
            }
        }
        
    }
}   