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

public class GenerateGnmaArmLoanAvsc {

	public static void main(String[] args) throws IOException {

		String fileName = "src/main/resources/gnma_arm_loan_1.csv";
		Path myPath = Paths.get(fileName);
		System.out.println(fileName);
		try (BufferedReader br = Files.newBufferedReader(myPath,
				StandardCharsets.UTF_8)) {

			ColumnPositionMappingStrategy strategy = new ColumnPositionMappingStrategy();
			strategy.setType(EmbsLoan.class);
			String[] fields = {"columnName", "type", "mapping"};
			strategy.setColumnMapping(fields);

			CsvToBean csvToBean = new CsvToBeanBuilder(br)
					.withType(EmbsLoan.class).withMappingStrategy(strategy)
					.withIgnoreLeadingWhiteSpace(true).build();

			List<EmbsLoan> gnmaLoan = csvToBean.parse();
			StringBuilder sb = new StringBuilder();
			
			Set<String> dic =new HashSet<>();
			dic.add("index_type");
			dic.add("cusip");
			dic.add("prod_type_ind");
			dic.add("loan_purpose");
			dic.add("tpo_flag");
			dic.add("property_type");
			dic.add("occupancy_status");
			dic.add("num_units");
			dic.add("state");
			dic.add("product_type");


			sb.append("{\n\"type\" : \"record\",\n")
				.append("\"name\" : \"gnma_arm_loan\",\n")
				.append("\"fields\" : [ {\n");
			for (EmbsLoan loan : gnmaLoan) {
				String name = loan.getColumnName().trim().toLowerCase();
				String type = loan.getType().trim().toLowerCase();
				if(type.equals("bigint"))
					type="long";

				if (dic.contains(name)||type.equals("string")) {
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