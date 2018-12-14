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

public class GenerateFhlmcLoanAvsc {

	public static void main(String[] args) throws IOException {

		String fileName = "src/main/resources/fhlmc_loan.csv";
		Path myPath = Paths.get(fileName);
		System.out.println(fileName);
		try (BufferedReader br = Files.newBufferedReader(myPath,
				StandardCharsets.UTF_8)) {

			ColumnPositionMappingStrategy strategy = new ColumnPositionMappingStrategy();
			strategy.setType(EmbsLoan.class);
			String[] fields = { "mapping", "columnName", "type" };
			strategy.setColumnMapping(fields);

			CsvToBean csvToBean = new CsvToBeanBuilder(br)
					.withType(EmbsLoan.class).withMappingStrategy(strategy)
					.withIgnoreLeadingWhiteSpace(true).build();

			List<EmbsLoan> fnmaLoan = csvToBean.parse();
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
			dic.add("eff_date");

			sb.append("{\n\"type\" : \"record\",\n")
				.append("\"name\" : \"fnma_loan\",\n")
				.append("\"fields\" : [ {\n");
			for (EmbsLoan loan : fnmaLoan) {
				String name = loan.getColumnName();
				String type = loan.getType();
				if(type.equals("bigint"))
					type = "long";
				else if(type.equals("double"))
					type = "float";

				if (dic.contains(name)) {
					sb.append("\t\"name\" : \"").append(name).append("\",\n")
							.append("\t\"type\" : \"").append(type)
							.append("\" \n").append("}, {");
				} else if (name.equals("as_of_date")) {
					sb.append("\"name\" : \"").append(name).append("\",\n")
							.append("\t\"type\" : \"").append(type)
							.append("\"\n").append("} ]\n}\n");

				} else { 
					sb.append("\"name\" : \"").append(name).append("\",\n")
							.append("\t\"type\" : [ \"null\", \"").append(type)
							.append("\" ]\n").append("}, {");

				}
			}
			System.out.println(sb.toString());
			
		}

	}
}