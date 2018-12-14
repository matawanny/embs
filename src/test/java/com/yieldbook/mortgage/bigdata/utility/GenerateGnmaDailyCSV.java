package com.yieldbook.mortgage.bigdata.utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.yieldbook.mortgage.bigdata.bean.EmbsLoan;

public class GenerateGnmaDailyCSV {

	public static void main(String[] args) throws IOException {

		String[] fileNames = new String[] {
				"src/main/resources/gnma_loan.csv",
				"src/main/resources/gnma_arm_loan.csv",
		 };
		for (String fileName : fileNames) {

			Path myPath = Paths.get(fileName);
			System.out.println(fileName);
			try (BufferedReader br = Files.newBufferedReader(myPath,
					StandardCharsets.UTF_8)) {

				ColumnPositionMappingStrategy strategy = new ColumnPositionMappingStrategy();
				strategy.setType(EmbsLoan.class);
				String[] fields = { "mapping", "columnName", "type" };
				strategy.setColumnMapping(fields);

				CsvToBean csvToBean = new CsvToBeanBuilder(br)
						.withType(EmbsLoan.class)
						.withMappingStrategy(strategy)
						.withIgnoreLeadingWhiteSpace(true).build();

				List<EmbsLoan> gnmaLoan = csvToBean.parse();
				StringBuilder sb = new StringBuilder();

				int i = 0;
				for (EmbsLoan loan : gnmaLoan) {
					String name = loan.getColumnName();
					String type = loan.getType();
					int mapping = loan.getMapping();
					int key;

					key=mapping-2;
					
					if(name.equals("last_chg_date")){
						name="lastChgDate";
						mapping=999+9;
					}
					if(type.equals("smalldatetime")
							||type.equals("datetime")){
						type="int";
					}else if(type.equals("float")){
						type="double";
					}else if (type.equals("char")){
						type="String";
					}else if(type.equals("smallint")||type.equals("tinyint")){
						type="int";
					}
					
					if(name.equals("loan_seq_num")){
						mapping =0;
					}else if(name.equals("cusip")){
						mapping=1;
					}else if(name.equals("gnma_issuer_num")){
						mapping=2;
					}else if(name.equals("eff_date")){
						mapping=34;
					}else{
						mapping=mapping-9;
					}
						
					
					loan.setColumnName(name);
					loan.setType(type);
					loan.setMapping(mapping);
					
					System.out.println(loan);
				}
				System.out.println("_______________________");
			}
		}

	}
}