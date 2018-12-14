package com.yieldbook.mortgage.action.fhlmc;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.commons.lang3.StringUtils;

import com.opencsv.CSVParser;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.ICSVParser;
import com.yieldbook.mortgage.action.BaseFileParser;

import static com.yieldbook.mortgage.utility.YieldBookUtilities.*;

public class LoanFileParser extends BaseFileParser {

	String inputFileName;
	String outputFileName;
	StringBuilder sb = new StringBuilder();
	CSVWriter loanPen;


	public LoanFileParser(String inputFileName, String outputFileName) {
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
					line[14]=getMonthYearMillionSecsEmbs(line[14]);
				if(!StringUtils.isEmpty(line[15]))
					line[15]=getMonthYearMillionSecsEmbs(line[15]);					
				if(!StringUtils.isEmpty(line[41]))
					line[41]=getMonthYearMillionSecsEmbs(line[41]);
				if(!StringUtils.isEmpty(line[54]))
					line[54]=getMonthYearMillionSecsEmbs(line[54]);					
				if(!StringUtils.isEmpty(line[81]))
					line[81]=getMonthYearMillionSecsEmbs(line[81]);
				if(!StringUtils.isEmpty(line[83]))
					line[83]=getMonthYearMillionSecsEmbs(line[83]);
				if(!StringUtils.isEmpty(line[90]))
					line[90]=getMonthYearMillionSecsEmbs(line[90]);
				if(!StringUtils.isEmpty(line[91]))
					line[91]=getMonthYearMillionSecsEmbs(line[91]);	
				System.arraycopy(line, 0, entries, 0, line.length);
				loanPen.writeNext(entries);
			}
			if (loanPen!=null) loanPen.close();	
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
