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

public class GenerateGnmaImpalaQuery {

	public static void main(String[] args) throws IOException {

		String[] fileNames = new String[] {
				"src/main/resources/gnma_loan_1.csv",
				"src/main/resources/gnma_arm_loan_1.csv" };
		for (String fileName : fileNames) {

			Path myPath = Paths.get(fileName);
			System.out.println(fileName);
			try (BufferedReader br = Files.newBufferedReader(myPath,
					StandardCharsets.UTF_8)) {

				ColumnPositionMappingStrategy strategy = new ColumnPositionMappingStrategy();
				strategy.setType(EmbsLoan.class);
				String[] fields = {"columnName", "type",  "mapping" };
				strategy.setColumnMapping(fields);

				CsvToBean csvToBean = new CsvToBeanBuilder(br)
						.withType(EmbsLoan.class)
						.withMappingStrategy(strategy)
						.withIgnoreLeadingWhiteSpace(true).build();

				List<EmbsLoan> gnmaLoan = csvToBean.parse();
				StringBuilder sb = new StringBuilder();
				
				Set<String> dic =new HashSet<>();
				dic.add("cusip");


				sb.append("impala-shell -B -i ybgdev79.ny.ssmb.com -q 'select ");
				int i = 1;
				for (EmbsLoan loan : gnmaLoan) {
					String name = loan.getColumnName().trim().toLowerCase();
					String type = loan.getType().trim().toLowerCase();
					if (!name.equals("as_of_date")) {
						if (i % 5 == 0) {
							if(type.equals("string")&&!dic.contains(name)){
								sb.append("NVL(").append(name).append(", \"\") as ")
								  .append(name).append(", \n");
							}else{
								sb.append(name).append(", \n");
							}	
						} else {
							if(type.equals("string")&&!dic.contains(name)){
								sb.append("NVL(").append(name).append(", \"\") as ")
								  .append(name).append(", ");
							}else{
								sb.append(name).append(", ");
							}
						}
					} else {
						sb.append(name).append(" ").append(" \n");
					}
					i++;
				}
				if(fileName.equals("src/main/resources/gnma_loan_1.csv")){
					sb.append("from gnma_loan_daily ' -o gnma_loan_daily.dat --print_header --output_delimiter=\\|");

				}else{
					sb.append("from gnma_arm_loan_daily ' -o gnma_arm_loan_daily.dat --print_header --output_delimiter=\\|");					
				}

				System.out.println(sb.toString());
				System.out.println("____________________________________________________");

			}
		}
	}
}