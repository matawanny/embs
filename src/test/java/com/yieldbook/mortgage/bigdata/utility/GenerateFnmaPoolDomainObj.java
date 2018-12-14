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
import com.yieldbook.mortgage.bigdata.bean.EmbsFnmaPool;

public class GenerateFnmaPoolDomainObj {

	public static void main(String[] args) throws IOException {

		String fileName = "src/main/resources/fnma_pool_data_daily.csv";
		//String fileName = "src/main/resources/fnma_pool_arm_reset_daily.csv";
		Path myPath = Paths.get(fileName);
		System.out.println(fileName);
		try (BufferedReader br = Files.newBufferedReader(myPath,
				StandardCharsets.UTF_8)) {

			ColumnPositionMappingStrategy strategy = new ColumnPositionMappingStrategy();
			strategy.setType(EmbsFnmaPool.class);
			String[] fields = { "columnName", "format", "start", "length" };
			strategy.setColumnMapping(fields);

			CsvToBean csvToBean = new CsvToBeanBuilder(br)
					.withType(EmbsFnmaPool.class).withMappingStrategy(strategy)
					.withIgnoreLeadingWhiteSpace(true).build();

			List<EmbsFnmaPool> fnmaPool = csvToBean.parse();
			StringBuilder sb = new StringBuilder();
			int k=0;
			for (EmbsFnmaPool pool : fnmaPool) {
				String columnName = pool.getColumnName().trim();
				columnName = columnName.replace(" ", "_").replace("-","_").toLowerCase();
				String format = pool.getFormat().trim();
				int length = pool.getLength();
			
				if (columnName.equals("filler")){
					k++;
					columnName = columnName + k;
				}	

				pool.setColumnName(columnName);
					
                
				sb.append("private String ").append(columnName).append(";\n");
			}
			System.out.println(sb.toString());

			sb.setLength(0);
			int offset = 1;
			for (EmbsFnmaPool pool : fnmaPool) {
				String columnName = pool.getColumnName().trim();
				String format = pool.getFormat().trim();
				int length = pool.getLength();


				sb.append("@Field(offset=").append(offset).append(", length=")
						.append(length).append(")\n")
						.append("public String get").append(columnName.substring(0,1).toUpperCase())
						.append(columnName.substring(1,columnName.length()))
						.append("() {\n")
						.append("\treturn ").append(columnName).append(";\n")
						.append("}\n");
				sb.append("public void set").append(columnName.substring(0,1).toUpperCase())
				.append(columnName.substring(1,columnName.length()))
				.append("(String ").append(columnName).append(") {\n")
				.append("\tthis.").append(columnName).append(" = ").append(columnName).append(";\n")
				.append("}\n");				
				
				offset=offset+length;

			}
			System.out.println(sb.toString());
			
			sb.setLength(0);
			sb.append("@Override\n").append("public String toString() {\n").append("\treturn ");
			int i=1;
			for (EmbsFnmaPool pool : fnmaPool) {
				String columnName = pool.getColumnName().trim();
				String format = pool.getFormat().trim();
				int length = pool.getLength();

				
				String originalName = columnName;
				
				if(columnName.equalsIgnoreCase("recordType")||columnName.startsWith("filler")){
					
				}else if(columnName.equalsIgnoreCase("months_to_next_rt_chg")
						||columnName.equalsIgnoreCase("net_life_floor_low")){
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
			
/*			int j=1;
			sb.setLength(0);
			for (EmbsFnmaPool pool : fnmaPool) {
				String columnName = pool.getColumnName().trim();
				columnName = columnName.replace("_", "");
				columnName = columnName.replace("-", "");
				String format = pool.getFormat().trim();
				int length = pool.getLength();

				
				String originalName = columnName;
				
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
			System.out.println(sb.toString());	*/
			sb.setLength(0);
			for (EmbsFnmaPool pool : fnmaPool) {
				String columnName = pool.getColumnName().trim();
				String format = pool.getFormat().trim();
				int length = pool.getLength();
				if(!columnName.startsWith("fill")){
					if(columnName.endsWith("date"))
						sb.append(columnName).append(", int\n");
					else if(columnName.endsWith("rate")||columnName.endsWith("wac")
							||columnName.endsWith("wam")||columnName.endsWith("balance")
							||columnName.endsWith("margin")||columnName.endsWith("percent")
							||columnName.endsWith("high")||columnName.endsWith("low"))
						sb.append(columnName).append(", double\n");
					else
						sb.append(columnName).append(", string\n");
				}
			}

			System.out.println(sb.toString());
			
			sb.setLength(0);
			for (EmbsFnmaPool pool : fnmaPool) {
				String columnName = pool.getColumnName().trim();
				String format = pool.getFormat().trim();
				int length = pool.getLength();
				if(!columnName.startsWith("fill")
						&&!columnName.startsWith("record")){
					sb.append(".set(\"").append(columnName)
					.append("\", poolData.get").append(columnName.substring(0,1).toUpperCase())
					.append(columnName.substring(1,columnName.length())).append("())\n");
				}
			}
		
			System.out.println(sb.toString());				
			
		}
	}
}