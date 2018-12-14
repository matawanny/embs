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
import com.yieldbook.mortgage.bigdata.bean.EmbsGnmaLoan;

public class GenerateGnmaDomainObj1 {

	public static void main(String[] args) throws IOException {

		String fileName = "src/main/resources/gnma_embs_mapping.csv";
		Path myPath = Paths.get(fileName);
		System.out.println(fileName);
		try (BufferedReader br = Files.newBufferedReader(myPath,
				StandardCharsets.UTF_8)) {

			ColumnPositionMappingStrategy strategy = new ColumnPositionMappingStrategy();
			strategy.setType(EmbsGnmaLoan.class);
			String[] fields = { "columnName", "type", "length" };
			strategy.setColumnMapping(fields);

			CsvToBean csvToBean = new CsvToBeanBuilder(br)
					.withType(EmbsGnmaLoan.class).withMappingStrategy(strategy)
					.withIgnoreLeadingWhiteSpace(true).build();

			List<EmbsGnmaLoan> gnmaLoan = csvToBean.parse();
			StringBuilder sb = new StringBuilder();
			for (EmbsGnmaLoan loan : gnmaLoan) {
				String columnName = loan.getColumnName().trim();
				columnName = columnName.replace("_", "");
				columnName = columnName.replace("-", "");
				String type = loan.getType().trim();
				int length = loan.getLength();

				if (columnName.length() > 3) {
					String first = columnName.substring(0, 1);
					String second = columnName.substring(1, 2);
					boolean isUppercase = second.equals(second.toUpperCase());
					if (!isUppercase) {
						first = first.toLowerCase();
						columnName = first + columnName.substring(1);

					}
				}

				sb.append("private String ").append(columnName).append(";\n");
			}
			System.out.println(sb.toString());

			sb.setLength(0);
			int offset = 1;
			for (EmbsGnmaLoan loan : gnmaLoan) {
				String columnName = loan.getColumnName().trim();
				columnName = columnName.replace("_", "");
				columnName = columnName.replace("-", "");
				String type = loan.getType().trim();
				int length = loan.getLength();

				
				String originalName = columnName;
				if (columnName.length() > 3) {
					String first = columnName.substring(0, 1);
					String second = columnName.substring(1, 2);
					boolean isUppercase = second.equals(second.toUpperCase());
					if (!isUppercase) {
						first = first.toLowerCase();
						columnName = first + columnName.substring(1);

					}

				}
				sb.append("@Field(offset=").append(offset).append(", length=")
						.append(length).append(")\n")
						.append("public String get").append(originalName)
						.append("() {\n")
						.append("\treturn ").append(columnName).append(";\n")
						.append("}\n");
				sb.append("public void set").append(originalName)
				.append("(String ").append(columnName).append(") {\n")
				.append("\tthis.").append(columnName).append(" = ").append(columnName).append(";\n")
				.append("}\n");				
				offset=offset+length;

			}
			System.out.println(sb.toString());
			
			sb.setLength(0);
			sb.append("@Override\n").append("public String toString() {\n").append("\treturn ");
			int i=1;
			for (EmbsGnmaLoan loan : gnmaLoan) {
				String columnName = loan.getColumnName().trim();
				columnName = columnName.replace("_", "");
				columnName = columnName.replace("-", "");
				String type = loan.getType().trim();
				int length = loan.getLength();

				
				String originalName = columnName;
				if (columnName.length() > 3) {
					String first = columnName.substring(0, 1);
					String second = columnName.substring(1, 2);
					boolean isUppercase = second.equals(second.toUpperCase());
					if (!isUppercase) {
						first = first.toLowerCase();
						columnName = first + columnName.substring(1);

					}

				}
				if(columnName.equalsIgnoreCase("recordType")){
					
				}else if(columnName.equalsIgnoreCase("prospectiveInterestRate")){
					sb.append(columnName).append(";\n");
				}else if (i %5 ==0){
					sb.append(columnName).append(" + \"|\" + \n\t");
				}else{
					sb.append(columnName).append(" + \"|\" + ");
				}
				i++;
			}
			sb.append("\n}");
			System.out.println(sb.toString());	
			
			int j=1;
			sb.setLength(0);
			for (EmbsGnmaLoan loan : gnmaLoan) {
				String columnName = loan.getColumnName().trim();
				columnName = columnName.replace("_", "");
				columnName = columnName.replace("-", "");
				String type = loan.getType().trim();
				int length = loan.getLength();

				
				String originalName = columnName;
				if (columnName.length() > 3) {
					String first = columnName.substring(0, 1);
					String second = columnName.substring(1, 2);
					boolean isUppercase = second.equals(second.toUpperCase());
					if (!isUppercase) {
						first = first.toLowerCase();
						columnName = first + columnName.substring(1);

					}

				}
				if(j==1){
					sb.append("entries[").append(j-1).append("] = ").append("disclosureSequenceNumber").append(";\n");
				}else if(j==2){
					sb.append("entries[").append(j-1).append("] = ").append("cusip").append(";\n");
				}else if(!columnName.equals("poolID")&&!columnName.equals("disclosureSequenceNumber")){
					sb.append("entries[").append(j-2).append("] = ").append(columnName).append(";\n");
				}
				j++;
			}
			sb.append("\n}");
			System.out.println(sb.toString());	
			
		}
	}
}