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
import com.yieldbook.mortgage.bigdata.bean.EmbsLoan;

public class GenerateFnmaLoanMapReduceCode {

    public static void main(String[] args) throws IOException {

        String fileName = "src/test/resources/fnma_loan.csv";
        Path myPath = Paths.get(fileName);
        System.out.println(fileName);
        StringBuilder sb = new StringBuilder();
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

            List<EmbsLoan> fnmaLoan = csvToBean.parse();
            fnmaLoan.sort(Comparator.comparing(EmbsLoan::getMapping));

            int i =0;
            Set<Integer> dictionary = new HashSet<>();
            dictionary.add(16);
            dictionary.add(35);
            dictionary.add(19);
            

            for(EmbsLoan loan: fnmaLoan){
            	int mapping = loan.getMapping();
            	String name = loan.getColumnName();
            	sb.setLength(0);
            	if(mapping==999) continue;
            	if(i==5){
	            	sb.append("\t\thKey.set(String.format(\"%s\", fields[")
	            	  .append(i+1)
	            	  .append("]).getBytes());\n");
	            	System.out.println(sb.toString());
            	}else if(mapping!=8){
            		int key=mapping-2;
            		sb.append("\t\tif (!StringUtils.isEmpty(fields[")
            		  .append(key)
            		  .append("])) {\n");

            		if(dictionary.contains(key)){
            			sb.append("\t\t\tfields[").append(key).append("]=YBTimeDateCurrencyUtilities\n")
            			  .append("\t\t\t\t.getMonthYearMillionSecsEmbs(fields[").append(key)
            			  .append("]);\n");
            		}            		
            		 sb.append("\t\t\tkv = new KeyValue(hKey.get(), COL_FAM,\n")
            		  .append("\t\t\t\t\tFnmaLoanColumnEnum.")
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
            for(EmbsLoan loan: fnmaLoan){
            	int mapping = loan.getMapping();
            	String name = loan.getColumnName();
            	int key=mapping-2;
            	if(mapping==999)continue;
            	if(i==0){
            		sb.append("Put put=new Put(Bytes.toBytes(fields[0]));\n");
            	}else if(!name.equals("last_chg_date")){
	            	sb.append("put.addColumn(COL_FAM, FnmaLoanColumnEnum.")
	            	.append(name.toUpperCase()).append(".getColumnName(), fields[")
	            	.append(key).append("].getBytes());\n");
            	}
            	
            	i++;
            } // end of for
            System.out.println(sb.toString());
            System.out.println("_________________________________________");
            String asOfDate="20181004";
            System.out.println("asOfDate="+asOfDate);
            String effDate = asOfDate.substring(0,asOfDate.length()-2).concat("01");
            System.out.println("effDate="+effDate);
        }
    }
}