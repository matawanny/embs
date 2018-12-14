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

public class OpenCSVReadBeansEx5 {

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

			List<EmbsLoan> fhlmcLoan = csvToBean.parse();
			StringBuilder sb = new StringBuilder();

			sb.append("create view daily.fhlmc_loan_view_2018_5 as \n")
			.append("select substr(loan_seq_num, 8) as loan_seq_num, p.product_id as product_id, \n");
			int i=1;
			for (EmbsLoan loan : fhlmcLoan) {
				String name = loan.getColumnName();
	
				if (!name.equals("loan_sqe_num")&& !name.equals("cusip")
						&&!name.equals("prepay_term") && !name.equals("maturity_date")){
					if (i % 5==0){
						sb.append(name).append(",\n");
					}else{ 
						sb.append(name).append(",");
					}	
					i++;
				}else if(name.equals("prepay_term")){
					sb.append(name).append(" ");
				}else if(name.equals("maturity_date")){
					sb.append("f.").append(name).append(" as ").append(name).append(",");
				}	
			}

			sb.append("\nfrom default.fhlmc_loan f left outer join daily.pool_sqoop p \n")
				.append("on (f.cusip=p.cusip)\n");
			System.out.println(sb.toString());

		}
	}
}