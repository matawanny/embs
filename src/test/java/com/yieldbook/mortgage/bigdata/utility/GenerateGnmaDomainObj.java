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
import com.yieldbook.mortgage.bigdata.bean.EmbsGnmaLoan;

public class GenerateGnmaDomainObj {

    public static void main(String[] args) throws IOException {
        
        String fileName = "src/main/resources/gnma_embs_mapping.csv";
        Path myPath = Paths.get(fileName);
        System.out.println(fileName);
        try (BufferedReader br = Files.newBufferedReader(myPath,
                StandardCharsets.UTF_8)) {

            ColumnPositionMappingStrategy strategy = new ColumnPositionMappingStrategy();
            strategy.setType(EmbsGnmaLoan.class);
            String[] fields = {"columnName","type","length"};
            strategy.setColumnMapping(fields);

            CsvToBean csvToBean = new CsvToBeanBuilder(br)
                    .withType(EmbsGnmaLoan.class)
                    .withMappingStrategy(strategy)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            List<EmbsGnmaLoan> gnmaLoan = csvToBean.parse();
            StringBuilder sb = new StringBuilder();
            int i =1;
            for(EmbsGnmaLoan loan: gnmaLoan){
            	String columnName = loan.getColumnName().trim();
            	columnName=columnName.replace("_", "");
            	columnName=columnName.replace("-", "");
            	String type=loan.getType().trim();
            	int length=loan.getLength();
            	
            	if(type.equals("char"))
            		type="String";
            	if(columnName.contains("Date")){
            		type="LocalDate";
            	}else if(columnName.endsWith("ID") && type.equals("Numeric")){
            		type="String";
            	}else if(type.equals("Numeric") && length<=2){
            		type="String";
                }else if(type.equals("Numeric") && length==3){
            		type="int";
                }else if(type.equals("Numeric") && length>=4){
            		type="BigDecimal";
                }
            	
            	if(columnName.length()>3){
	            	String first=columnName.substring(0,1).toLowerCase();
	            	columnName = first + columnName.substring(1);
            	}
            	

            	sb.setLength(0);
            	
            	if(type.equals("LocalDate") && length==8){
                  sb.append("@DataField(pos=").append(i).append(", length=").append(length)
                    .append(", pattern=\"yyyyMMdd\"")
              	    .append(")\nprivate ").append(type).append(" ").append(columnName)
              	    .append(";\n");           		
            		
            	}else if(type.equals("LocalDate") && length==6){
                    sb.append("@DataField(pos=").append(i).append(", length=").append(length)
                    .append(", pattern=\"yyyyMM\"")
              	    .append(")\nprivate ").append(type).append(" ").append(columnName)
              	    .append(";\n"); 
            	}else if(type.equals("String") && length>2 && !columnName.contains("ID")){  
                    sb.append("@DataField(pos=").append(i).append(", length=").append(length)
                    .append(", trim=true,align=\"L\"")
              	    .append(")\nprivate ").append(type).append(" ").append(columnName)
              	    .append(";\n");           		
            	}else{
            	sb.append("@DataField(pos=").append(i).append(", length=").append(length)
            	  .append(")\nprivate ").append(type).append(" ").append(columnName)
            	  .append(";\n");
            	}
            	System.out.println(sb.toString());
            	i++;
            }
        }
        
    }
}