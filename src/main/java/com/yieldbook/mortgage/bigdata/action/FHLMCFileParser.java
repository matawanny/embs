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

public class FHLMCFileParser extends BaseFileParser {

	String inputFileName;
	String outputFileName;
	StringBuilder sb = new StringBuilder();
	CSVWriter loanPen;


	public FHLMCFileParser(String inputFileName, String outputFileName) {
		super();
		this.inputFileName = inputFileName;
		this.outputFileName = outputFileName;

		try {
			loanPen = new CSVWriter(new FileWriter(outputFileName, true),
					'|', CSVWriter.NO_QUOTE_CHARACTER,
					CSVWriter.DEFAULT_ESCAPE_CHARACTER,
					CSVWriter.DEFAULT_LINE_END);
		} catch (IOException e) {
			// TODO Auto-generated catch block
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
		String[] line;
		try {
			reader = new CSVReader(new FileReader(inputFileName), '|',
					CSVParser.DEFAULT_QUOTE_CHARACTER,
					ICSVParser.DEFAULT_ESCAPE_CHARACTER,
					CSVReader.DEFAULT_SKIP_LINES,
					ICSVParser.DEFAULT_STRICT_QUOTES,
					ICSVParser.DEFAULT_IGNORE_LEADING_WHITESPACE);
            
			while ((line = reader.readNext()) != null) {
				if (StringUtils.isEmpty(line[0]))
					continue;
				String[] entries = new String[line.length];
				if(!StringUtils.isEmpty(line[14]))
					line[14]=YBTimeDateCurrencyUtilities
						.getMonthYearMillionSecsFHLM(line[14]);
				if(!StringUtils.isEmpty(line[15]))
					line[15]=YBTimeDateCurrencyUtilities
						.getMonthYearMillionSecsFHLM(line[15]);					
				if(!StringUtils.isEmpty(line[41]))
					line[41]=YBTimeDateCurrencyUtilities
						.getMonthYearMillionSecsFHLM(line[41]);
				if(!StringUtils.isEmpty(line[54]))
					line[54]=YBTimeDateCurrencyUtilities
						.getMonthYearMillionSecsFHLM(line[54]);					
				if(!StringUtils.isEmpty(line[81]))
					line[81]=YBTimeDateCurrencyUtilities
						.getMonthYearMillionSecsFHLM(line[81]);
				if(!StringUtils.isEmpty(line[83]))
					line[83]=YBTimeDateCurrencyUtilities
						.getMonthYearMillionSecsFHLM(line[83]);
				if(!StringUtils.isEmpty(line[90]))
					line[90]=YBTimeDateCurrencyUtilities
						.getMonthYearMillionSecsFHLM(line[90]);
				if(!StringUtils.isEmpty(line[91]))
					line[91]=YBTimeDateCurrencyUtilities
						.getMonthYearMillionSecsFHLM(line[91]);	
				System.arraycopy(line, 0, entries, 0, line.length);
				loanPen.writeNext(entries);
			}
			if (loanPen!=null) loanPen.close();	
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
