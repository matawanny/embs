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

public class GenerateGnmaDailyMappingCSV {

	public static void main(String[] args) throws IOException {

		String[] fileNames = new String[] {
				"src/main/resources/gnma_loan_1.csv",
				"src/main/resources/gnma_arm_loan_1.csv", };

		for (String fileName : fileNames) {

			Path myPath = Paths.get(fileName);
			System.out.println(fileName);
			try (BufferedReader br = Files.newBufferedReader(myPath,
					StandardCharsets.UTF_8)) {

				ColumnPositionMappingStrategy strategy = new ColumnPositionMappingStrategy();
				strategy.setType(EmbsLoan.class);
				String[] fields = { "columnName", "type", "mapping" };
				strategy.setColumnMapping(fields);

				CsvToBean csvToBean = new CsvToBeanBuilder(br)
						.withType(EmbsLoan.class).withMappingStrategy(strategy)
						.withIgnoreLeadingWhiteSpace(true).build();

				List<EmbsLoan> gnmaLoan = csvToBean.parse();
				StringBuilder sb = new StringBuilder();

				int i = 0;

				for (EmbsLoan loan : gnmaLoan) {

					String name = loan.getColumnName();
					String type = loan.getType();
					int mapping = loan.getMapping();
					int key;

					key = mapping;
					if (mapping == 999) {
						sb.append("des[").append(i).append("]=\"\"; //")
								.append(name).append("\n");
					} else {
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