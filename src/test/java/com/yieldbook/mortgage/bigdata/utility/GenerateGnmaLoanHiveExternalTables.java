package com.yieldbook.mortgage.bigdata.utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.yieldbook.mortgage.bigdata.bean.EmbsLoan;

public class GenerateGnmaLoanHiveExternalTables {

	public static void main(String[] args) throws IOException {

		String fileName = "src/main/resources/gnma_loan_1.csv";
		Path myPath = Paths.get(fileName);
		System.out.println(fileName);
		try (BufferedReader br = Files.newBufferedReader(myPath,
				StandardCharsets.UTF_8)) {

			ColumnPositionMappingStrategy strategy = new ColumnPositionMappingStrategy();
			strategy.setType(EmbsLoan.class);
			String[] fields = {"columnName", "type", "mapping" };
			strategy.setColumnMapping(fields);

			CsvToBean csvToBean = new CsvToBeanBuilder(br)
					.withType(EmbsLoan.class).withMappingStrategy(strategy)
					.withIgnoreLeadingWhiteSpace(true).build();

			List<EmbsLoan> gnmaLoan = csvToBean.parse();
			StringBuilder sb = new StringBuilder();

			sb.append("CREATE EXTERNAL TABLE gnma_loan_monthly(\n");
			for (EmbsLoan loan : gnmaLoan) {
				String name = loan.getColumnName();
				String type = loan.getType();
				if (!name.equals("seller_issuer"))
					sb.append(name).append(" ").append(type).append(",");
				else
					sb.append(name).append(" ").append(type).append(")\n");
			}

			sb.append(
					"STORED BY \'org.apache.hadoop.hive.hbase.HBaseStorageHandler\'\n")
					.append("WITH SERDEPROPERTIES\n")
					.append("(\"hbase.columns.mapping\"=\":key,");

			for (EmbsLoan loan : gnmaLoan) {
				String name = loan.getColumnName();
				if (name.equals("loan_seq_num")) continue;
				if (!name.equals("as_of_date"))
					sb.append("m:").append(name).append(",");
				else
					sb.append("m:").append(name).append("\",\n");
			}

			sb.append("\"hbase.table.default.storage.type\"=\"binary\");");
			System.out.println(sb.toString());
			

		}
	}
}