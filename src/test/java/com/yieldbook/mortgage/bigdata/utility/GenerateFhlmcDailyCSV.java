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

public class GenerateFhlmcDailyCSV {

	public static void main(String[] args) throws IOException {

		String[] fileNames = new String[] {
				"src/main/resources/fhlmc_loan.csv",
				"src/main/resources/fhlmc_arm_loan.csv",
				"src/main/resources/fhlmc_mod_loan.csv" };
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

				List<EmbsLoan> fhlmcLoan = csvToBean.parse();
				StringBuilder sb = new StringBuilder();

				int i = 0;
				for (EmbsLoan loan : fhlmcLoan) {
					String name = loan.getColumnName();
					String type = loan.getType();
					int mapping = loan.getMapping();
					int key;
					if(name.equals("loan_identifier"))
						key=0;
					else if (name.equals("cusip"))
						key=4;
					else
						key=mapping-2;
					if(name.equals("last_chg_date")){
						sb.append("des[").append(i).append("]=lastChgDate+\"\"; //")
						  .append(name).append("\n");
					}else if(mapping==999){
						sb.append("des[").append(i).append("]=\"\"; //")
						  .append(name).append("\n");
					}else{
						sb.append("des[").append(i).append("]=src[")
							.append(key).append("]; //").append(name)
							.append("\n");
					}	
					i++;
				}
				System.out.println(sb.toString());
				System.out
						.println("_________________________________________________");

			}
		}

	}
}