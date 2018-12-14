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

public class GenerateFhlmcColumnEnum {

    public static void main(String[] args) throws IOException {

        String fileName = "src/main/resources/fhlmc_loan.csv";
        Path myPath = Paths.get(fileName);
        System.out.println(fileName);
        try (BufferedReader br = Files.newBufferedReader(myPath,
                StandardCharsets.UTF_8)) {

            ColumnPositionMappingStrategy strategy = new ColumnPositionMappingStrategy();
            strategy.setType(EmbsLoan.class);
            String[] fields = {"mapping", "columnName"};
            strategy.setColumnMapping(fields);

            CsvToBean csvToBean = new CsvToBeanBuilder(br)
                    .withType(EmbsLoan.class)
                    .withMappingStrategy(strategy)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            List<EmbsLoan> fhlmcLoan = csvToBean.parse();
            StringBuilder sb = new StringBuilder();
            for(EmbsLoan loan: fhlmcLoan){
            	String name = loan.getColumnName();
            	sb.setLength(0);
            	sb.append(name.toUpperCase())
            	  .append("(\"").append(name)
            	  .append("\".getBytes()),");
            	System.out.println(sb.toString());
            }
        }
        System.out.println("_________________________________________");

        fileName = "src/main/resources/fhlmc_arm_loan.csv";
        myPath = Paths.get(fileName);
        System.out.println(fileName);
        try (BufferedReader br = Files.newBufferedReader(myPath,
                StandardCharsets.UTF_8)) {

            ColumnPositionMappingStrategy strategy = new ColumnPositionMappingStrategy();
            strategy.setType(EmbsLoan.class);
            String[] fields = {"mapping", "columnName"};
            strategy.setColumnMapping(fields);

            CsvToBean csvToBean = new CsvToBeanBuilder(br)
                    .withType(EmbsLoan.class)
                    .withMappingStrategy(strategy)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            List<EmbsLoan> fhlmcLoan = csvToBean.parse();
            StringBuilder sb = new StringBuilder();
            for(EmbsLoan loan: fhlmcLoan){
            	String name = loan.getColumnName();
            	sb.setLength(0);
            	sb.append(name.toUpperCase())
            	  .append("(\"").append(name)
            	  .append("\".getBytes()),");
            	System.out.println(sb.toString());
            }
        }
        System.out.println("_________________________________________");
        
        fileName = "src/main/resources/fhlmc_mod_loan.csv";
        myPath = Paths.get(fileName);
        System.out.println(fileName);
        try (BufferedReader br = Files.newBufferedReader(myPath,
                StandardCharsets.UTF_8)) {

            ColumnPositionMappingStrategy strategy = new ColumnPositionMappingStrategy();
            strategy.setType(EmbsLoan.class);
            String[] fields = {"mapping", "columnName"};
            strategy.setColumnMapping(fields);

            CsvToBean csvToBean = new CsvToBeanBuilder(br)
                    .withType(EmbsLoan.class)
                    .withMappingStrategy(strategy)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            List<EmbsLoan> fhlmcLoan = csvToBean.parse();
            StringBuilder sb = new StringBuilder();
            for(EmbsLoan loan: fhlmcLoan){
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