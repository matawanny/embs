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
import com.yieldbook.mortgage.bigdata.bean.FhlmcLoan;

public class GenerateFhlmcImpalaQuery {

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
				strategy.setType(FhlmcLoan.class);
				String[] fields = { "mapping", "columnName", "type" };
				strategy.setColumnMapping(fields);

				CsvToBean csvToBean = new CsvToBeanBuilder(br)
						.withType(FhlmcLoan.class)
						.withMappingStrategy(strategy)
						.withIgnoreLeadingWhiteSpace(true).build();

				List<FhlmcLoan> fhlmcLoan = csvToBean.parse();
				StringBuilder sb = new StringBuilder();
				
				Set<String> dic =new HashSet<>();
				dic.add("loan_identifier");
				dic.add("cusip");
				dic.add("prod_type_ind");
				dic.add("loan_purpose");
				dic.add("tpo_flag");
				dic.add("property_type");
				dic.add("occupancy_status");
				dic.add("num_units");
				dic.add("state");
				dic.add("product_type");

				sb.append("impala-shell -B -i ybgdev79.ny.ssmb.com -q 'select ");
				int i = 1;
				for (FhlmcLoan loan : fhlmcLoan) {
					String name = loan.getColumnName();
					String type = loan.getType();
					if (!name.equals("as_of_date")) {
						if (i % 6 == 0) {
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

				sb.append("from fhlmc_loan_hbase limit 100' -o fhlmc_loan_monthly.csv --print_header --output_delimiter=\\|");

				System.out.println(sb.toString());
				System.out.println("____________________________________________________");

			}
		}
	}
}