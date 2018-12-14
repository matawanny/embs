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
import com.yieldbook.mortgage.bigdata.bean.EmbsFnmaPool;

public class GenerateFnmaPoolAvsc {

	public static void main(String[] args) throws IOException {

		//String fileName = "src/main/resources/fnma_pool_data_daily_1.csv";
		String fileName = "src/main/resources/fnma_pool_arm_reset_daily_1.csv";
		Path myPath = Paths.get(fileName);
		System.out.println(fileName);
		try (BufferedReader br = Files.newBufferedReader(myPath,
				StandardCharsets.UTF_8)) {

			ColumnPositionMappingStrategy strategy = new ColumnPositionMappingStrategy();
			strategy.setType(EmbsFnmaPool.class);
			String[] fields = { "columnName", "format" };
			strategy.setColumnMapping(fields);

			CsvToBean csvToBean = new CsvToBeanBuilder(br)
					.withType(EmbsFnmaPool.class).withMappingStrategy(strategy)
					.withIgnoreLeadingWhiteSpace(true).build();

			List<EmbsFnmaPool> fnmaPool = csvToBean.parse();
			StringBuilder sb = new StringBuilder();
			
			Set<String> dic =new HashSet<>();
			dic.add("pool_number");
			dic.add("cusip");
			
			sb.append("{\n\"type\" : \"record\",\n")
				.append("\"name\" : \"fnma_pool\",\n")
				.append("\"fields\" : [ {\n");
			for (EmbsFnmaPool loan : fnmaPool) {
				String name = loan.getColumnName().trim().toLowerCase();
				String type = loan.getFormat().trim().toLowerCase();
				if(type.equals("bigint"))
					type = "long";


				if (dic.contains(name)||type.equals("string")) {
					sb.append("\t\"name\" : \"").append(name).append("\",\n")
							.append("\t\"type\" : \"").append("string")
							.append("\" \n").append("}, {");
				} else if (name.equals("as_of_date")||name.equals("net_life_floor_low")) {
					sb.append("\"name\" : \"").append(name).append("\",\n")
							.append("\t\"type\" : \"").append("string")
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