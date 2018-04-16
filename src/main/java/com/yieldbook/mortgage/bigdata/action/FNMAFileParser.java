package com.yieldbook.mortgage.bigdata.action;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;

import org.apache.commons.lang3.StringUtils;

import com.opencsv.CSVParser;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.ICSVParser;
import com.yieldbook.mortgage.bigdata.utility.YBTimeDateCurrencyUtilities;

public class FNMAFileParser extends BaseFileParser {

	String inputFileName;
	String outputFileName;
	Long epochMilliSeconds;
	Long epochMilliSecondsAsOfDate;
	StringBuilder sb = new StringBuilder();

	CSVWriter loanPen;

	public FNMAFileParser(String inputFileName, String asOfDate, String outputFileName) {
		super();
		this.inputFileName = inputFileName;
		try {
			this.epochMilliSecondsAsOfDate=YBTimeDateCurrencyUtilities
					.getMillionSeconds(asOfDate);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}		
		this.outputFileName = outputFileName;
		
		try {
			loanPen = new CSVWriter(new FileWriter(this.outputFileName, true),
					'|', CSVWriter.NO_QUOTE_CHARACTER,
					CSVWriter.DEFAULT_ESCAPE_CHARACTER,
					CSVWriter.DEFAULT_LINE_END);
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

	public void printLine(String[] line){
		if (line.length==1){
			System.out.println(line[0]);
		}else{
			for (int i=0; i<line.length-2; i++)
				System.out.print(line[i] + "|");
			
			System.out.println(line[line.length-1]);
		}// end of else
	}

	@Override
	public void parseAndWriteFile() {

		CSVReader reader = null;
		String[] line = null;
		try {
			reader = new CSVReader(new FileReader(inputFileName), '|',
					CSVParser.DEFAULT_QUOTE_CHARACTER,
					ICSVParser.DEFAULT_ESCAPE_CHARACTER,
					CSVReader.DEFAULT_SKIP_LINES,
					ICSVParser.DEFAULT_STRICT_QUOTES,
					ICSVParser.DEFAULT_IGNORE_LEADING_WHITESPACE);
			
			
			String cusip = "";
			String issueDate = "";
			String poolCorrectionIndicate = "";
            
			while ((line = reader.readNext()) != null) {
				if (StringUtils.isEmpty(line[0])
						|| RecordTypeFNMA.from(Integer.valueOf(line[0])) == null)
					continue;
				RecordTypeFNMA recordType = RecordTypeFNMA.from(Integer
						.valueOf(line[0]));

				switch (recordType) {
				case POOL_LEVEL:
					cusip = line[3];
					issueDate = line[4];
					poolCorrectionIndicate = line[5]==null?null:line[5].trim();
					
					epochMilliSeconds = YBTimeDateCurrencyUtilities
							.getMillionSeconds(issueDate);
					break;
				case LOAN_LEVEL:
					String[] entries = new String[line.length+3];
					entries[0] = cusip;
					entries[1] = epochMilliSeconds + "";
					entries[2] = poolCorrectionIndicate;
					entries[3] = epochMilliSecondsAsOfDate + "";
					if(!StringUtils.isEmpty(line[13].trim()))
						line[13]=YBTimeDateCurrencyUtilities
							.getMonthYearMillionSeconds(line[13]) + "";
					if(!StringUtils.isEmpty(line[16].trim()))
						line[16]=YBTimeDateCurrencyUtilities
							.getMonthYearMillionSeconds(line[16]) + "";					
					if(!StringUtils.isEmpty(line[32].trim()))
						line[32]=YBTimeDateCurrencyUtilities
							.getMonthYearMillionSeconds(line[32]) + "";
					if(!StringUtils.isEmpty(line[42].trim()))
						line[42]=YBTimeDateCurrencyUtilities
							.getMonthYearMillionSeconds(line[42]) + "";
					if(!StringUtils.isEmpty(line[65].trim()))
						line[32]=YBTimeDateCurrencyUtilities
							.getMonthYearMillionSeconds(line[65]) + "";
					if(!StringUtils.isEmpty(line[68].trim()))
						line[32]=YBTimeDateCurrencyUtilities
							.getMonthYearMillionSeconds(line[68]) + "";
					if(!StringUtils.isEmpty(line[74].trim()))
						line[32]=YBTimeDateCurrencyUtilities
							.getMonthYearMillionSeconds(line[74]) + "";	
					if(!StringUtils.isEmpty(line[75].trim()))
						line[32]=YBTimeDateCurrencyUtilities
							.getMonthYearMillionSeconds(line[75]) + "";						
					System.arraycopy(line, 1, entries, 4, line.length-1);
					loanPen.writeNext(entries);
					break;
				default:
					break;
				} // end of switch
			} // end of while
			if (loanPen!=null) loanPen.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e1) {
			
			System.out
					.println("MM/YYYY cannot parse to timestamp");
			
			printLine(line);

		}

	}

}
