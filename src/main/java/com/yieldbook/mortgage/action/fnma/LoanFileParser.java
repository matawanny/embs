package com.yieldbook.mortgage.action.fnma;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.commons.lang3.StringUtils;

import com.opencsv.CSVParser;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.ICSVParser;
import com.yieldbook.mortgage.action.BaseFileParser;
import com.yieldbook.mortgage.domain.fnma.RecordTypeFnmaLoan;

import static com.yieldbook.mortgage.utility.YieldBookUtilities.*;

public class LoanFileParser extends BaseFileParser {

	String inputFileName;
	String outputFileName;
	String asOfDate;
	StringBuilder sb = new StringBuilder();

	CSVWriter loanPen;

	public LoanFileParser(String inputFileName, String asOfDate, String outputFileName) {
		super();
		this.inputFileName=inputFileName;
		this.asOfDate=asOfDate;
	
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
						|| RecordTypeFnmaLoan.from(Integer.valueOf(line[0])) == null)
					continue;
				RecordTypeFnmaLoan recordType = RecordTypeFnmaLoan.from(Integer
						.valueOf(line[0]));

				switch (recordType) {
				case POOL_LEVEL:
					cusip = line[3];
					issueDate = line[4];
					poolCorrectionIndicate = line[5]==null?null:line[5].trim();
					issueDate=(issueDate==null)?null:convertDataFormat(issueDate);
					break;
				case LOAN_LEVEL:
					String[] entries = new String[line.length+3];
					entries[0] = cusip;
					entries[1] = issueDate;
					entries[2] = poolCorrectionIndicate;
					entries[3] = asOfDate;
					if(!StringUtils.isEmpty(line[13].trim()))
						line[13]=getMonthYearMillionSecsEmbs(line[13]);
					if(!StringUtils.isEmpty(line[16].trim()))
						line[16]=getMonthYearMillionSecsEmbs(line[16]);					
					if(!StringUtils.isEmpty(line[32].trim()))
						line[32]=getMonthYearMillionSecsEmbs(line[32]);
					if(!StringUtils.isEmpty(line[42].trim()))
						line[42]=getMonthYearMillionSecsEmbs(line[42]);
					if(!StringUtils.isEmpty(line[65].trim()))
						line[65]=getMonthYearMillionSecsEmbs(line[65]);
					if(!StringUtils.isEmpty(line[68].trim()))
						line[68]=getMonthYearMillionSecsEmbs(line[68]);
					if(!StringUtils.isEmpty(line[74].trim()))
						line[74]=getMonthYearMillionSecsEmbs(line[74]);	
					if(!StringUtils.isEmpty(line[75].trim()))
						line[75]=getMonthYearMillionSecsEmbs(line[75]);						
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
		} 

	}

}
