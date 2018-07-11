package com.yieldbook.mortgage.bigdata.utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.yieldbook.mortgage.bigdata.bean.FhlmcLoan;

public class GenerateFhlmcLoanMapReduceCode {

    public static void main(String[] args) throws IOException {

        String fileName = "src/main/resources/fhlmc_loan.csv";
        Path myPath = Paths.get(fileName);
        System.out.println(fileName);
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = Files.newBufferedReader(myPath,
                StandardCharsets.UTF_8)) {

            ColumnPositionMappingStrategy strategy = new ColumnPositionMappingStrategy();
            strategy.setType(FhlmcLoan.class);
            String[] fields = {"mapping", "columnName"};
            strategy.setColumnMapping(fields);

            CsvToBean csvToBean = new CsvToBeanBuilder(br)
                    .withType(FhlmcLoan.class)
                    .withMappingStrategy(strategy)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            List<FhlmcLoan> fhlmcLoan = csvToBean.parse();
            fhlmcLoan.sort(Comparator.comparing(FhlmcLoan::getMapping));

            int i =0;
            Set<Integer> dictionary = new HashSet<>();
            dictionary.add(14);
            dictionary.add(15);
            dictionary.add(41);
            dictionary.add(54);
            dictionary.add(81);
            dictionary.add(83);
            dictionary.add(90);
            dictionary.add(91);
            

            for(FhlmcLoan loan: fhlmcLoan){
            	int mapping = loan.getMapping();
            	String name = loan.getColumnName();
            	sb.setLength(0);
            	if(mapping==999) continue;
            	if(i==0){
	            	sb.append("\t\thKey.set(String.format(\"%s\", fields[")
	            	  .append(0)
	            	  .append("]).getBytes());\n");
	            	System.out.println(sb.toString());
            	}else{
            		int key=mapping-2;
            		sb.append("\t\tif (!StringUtils.isEmpty(fields[")
            		  .append(key)
            		  .append("])) {\n");

            		if(dictionary.contains(key)){
            			sb.append("\t\t\tfields[").append(key).append("]=YBTimeDateCurrencyUtilities\n")
            			  .append("\t\t\t\t.getMonthYearMillionSecsFHLM(fields[").append(key)
            			  .append("]);\n");
            		}            		
            		 sb.append("\t\t\tkv = new KeyValue(hKey.get(), COL_FAM,\n")
            		  .append("\t\t\t\t\tFhlmcLoanColumnEnum.")
            		  .append(name.toUpperCase())
            		  .append(".getColumnName(), fields[")
            		  .append(key)
            		  .append("].getBytes());\n")
            		  .append("\t\t\tcontext.write(hKey, kv);\n")
            		  .append("\t\t}");
            		System.out.println(sb.toString());
            	}
            	i++;
            }
            
            System.out.println("_________________________________________");
            sb.setLength(0);
            i=0;
            for(FhlmcLoan loan: fhlmcLoan){
            	int mapping = loan.getMapping();
            	String name = loan.getColumnName();
            	int key=mapping-2;
            	if(mapping==999)continue;
            	if(i==0){
            		sb.append("Put put=new Put(Bytes.toBytes(fields[0]));\n");
            	}else if(!name.equals("last_chg_date")){
	            	sb.append("put.addColumn(COL_FAM, FhlmcLoanColumnEnum.")
	            	.append(name.toUpperCase()).append(".getColumnName(), fields[")
	            	.append(key).append("].getBytes());\n");
            	}
            	
            	i++;
            } // end of for
            System.out.println(sb.toString());
        }
    }
}