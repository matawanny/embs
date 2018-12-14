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

public class GenerateGnmaArmLoanMapReduceCode {

	public static void main(String[] args) throws IOException {

		String fileName = "src/main/resources/gnma_arm_loan_1.csv";
		Path myPath = Paths.get(fileName);
		System.out.println(fileName);
		try (BufferedReader br = Files.newBufferedReader(myPath,
				StandardCharsets.UTF_8)) {

			ColumnPositionMappingStrategy strategy = new ColumnPositionMappingStrategy();
			strategy.setType(EmbsLoan.class);
			String[] fields = {"columnName","type", "mapping" };
			strategy.setColumnMapping(fields);

			CsvToBean csvToBean = new CsvToBeanBuilder(br)
					.withType(EmbsLoan.class).withMappingStrategy(strategy)
					.withIgnoreLeadingWhiteSpace(true).build();

			List<EmbsLoan> gnmaLoan = csvToBean.parse();
			gnmaLoan.sort(Comparator.comparing(EmbsLoan::getMapping));
			StringBuilder sb = new StringBuilder();
			int i = 0;
			Set<Integer> dictionary = new HashSet<>();
/*            dictionary.add(16);
            dictionary.add(35);
            dictionary.add(19);
            
            dictionary.add(45);*/

			for (EmbsLoan loan : gnmaLoan) {
				int mapping = loan.getMapping();
				String name = loan.getColumnName();
				sb.setLength(0);
				if (mapping == 999)
					continue;
				if (i == 0) {
					sb.append("\t\thKey.set(String.format(\"%s\", fields[")
							.append(i+1).append("]).getBytes());\n");
					System.out.println(sb.toString());
				} else{
					int key = mapping ;
					sb.append("\t\tif (!StringUtils.isEmpty(fields[")
							.append(key).append("])) {\n");
					if (dictionary.contains(key)) {
						sb.append("\t\t\tfields[")
								.append(key)
								.append("]=YBTimeDateCurrencyUtilities\n")
								.append("\t\t\t\t.getMonthYearMillionSecsEmbs(fields[")
								.append(key).append("]);\n");
					}
					sb.append("\t\t\tkv = new KeyValue(hKey.get(), COL_FAM,\n")
							.append("\t\t\t\t\tGnmaArmLoanColumnEnum.")
							.append(name.toUpperCase())
							.append(".getColumnName(), fields[").append(key)
							.append("].getBytes());\n")
							.append("\t\t\tcontext.write(hKey, kv);\n")
							.append("\t\t}");
					System.out.println(sb.toString());
				}
				i++;
			}

			System.out.println("_________________________________________");
			sb.setLength(0);
			i = 0;
			for (EmbsLoan loan : gnmaLoan) {
				int mapping = loan.getMapping();
				String name = loan.getColumnName();
				int key = mapping;
				if (mapping == 999)
					continue;
				if (i == 0) {
					sb.append("Put put=new Put(Bytes.toBytes(fields[0]));\n");
				} else if (!name.equals("last_chg_date")) {
					sb.append("put.addColumn(COL_FAM, GnmaArmLoanColumnEnum.")
							.append(name.toUpperCase())
							.append(".getColumnName(), fields[").append(key)
							.append("].getBytes());\n");
				}

				i++;
			} // end of for
			System.out.println(sb.toString());
		}
	}

}