package com.yieldbook.mortgage.action.fhlmc;

import static com.yieldbook.mortgage.utility.YieldBookUtilities.getMonthYearDayMillionSecsEmbs;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.commons.lang3.StringUtils;

import com.opencsv.CSVParser;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.ICSVParser;
import com.yieldbook.mortgage.action.BaseFileParser;
import com.yieldbook.mortgage.domain.fhlmc.RecordTypeFhlmcLoanOLD;

public class LoanOldFileParser extends BaseFileParser {

	String inputFileName;
	String outputFileName;
	String epochMilliSecondsStr;
	StringBuilder sb = new StringBuilder();
	CSVWriter modifiedLoanPen;
	CSVWriter loanPen;

	public CSVWriter getPen(RecordTypeFhlmcLoanOLD recordType) throws IOException {
		if (recordType == RecordTypeFhlmcLoanOLD.LOAN)
			return loanPen;
		else if (recordType == RecordTypeFhlmcLoanOLD.MODIFIED_LOAN)

			return modifiedLoanPen;
		else
			return null;
	}

	public LoanOldFileParser(String inputFileName) {
		super();
		this.inputFileName = inputFileName;

		String[] elements = inputFileName.split("\\.");

		sb.setLength(0);
		String outputLoanFileName = sb.append(elements[0]).append("_")
				.append(RecordTypeFhlmcLoanOLD.LOAN).append(".").append(elements[1])
				.toString();
		try {
			loanPen = new CSVWriter(new FileWriter(outputLoanFileName, true),
					'|', CSVWriter.NO_QUOTE_CHARACTER,
					CSVWriter.DEFAULT_ESCAPE_CHARACTER,
					CSVWriter.DEFAULT_LINE_END);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sb.setLength(0);
		outputLoanFileName = sb.append(elements[0]).append("_")
				.append(RecordTypeFhlmcLoanOLD.MODIFIED_LOAN).append(".").append(elements[1])
				.toString();
		try {
			modifiedLoanPen = new CSVWriter(new FileWriter(outputLoanFileName,
					true), '|', CSVWriter.NO_QUOTE_CHARACTER,
					CSVWriter.DEFAULT_ESCAPE_CHARACTER,
					CSVWriter.DEFAULT_LINE_END);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public LoanOldFileParser(String inputFileName, String outputFileName) {
		super();
		this.inputFileName = inputFileName;
		this.outputFileName = outputFileName;
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
		CSVWriter loanPen = null;
		CSVWriter modifiedLoanPen = null;
		try {
			reader = new CSVReader(new FileReader(inputFileName), '|',
					CSVParser.DEFAULT_QUOTE_CHARACTER,
					ICSVParser.DEFAULT_ESCAPE_CHARACTER,
					CSVReader.DEFAULT_SKIP_LINES,
					ICSVParser.DEFAULT_STRICT_QUOTES,
					ICSVParser.DEFAULT_IGNORE_LEADING_WHITESPACE);
            
			while ((line = reader.readNext()) != null) {
				if (StringUtils.isEmpty(line[0])
						|| RecordTypeFhlmcLoanOLD.from(Integer.valueOf(line[0])) == null)
					continue;
				RecordTypeFhlmcLoanOLD recordType = RecordTypeFhlmcLoanOLD.from(Integer
						.valueOf(line[0]));

				switch (recordType) {
				case FILE_HEADER:
					String asOfDate = line[3];
					epochMilliSecondsStr = getMonthYearDayMillionSecsEmbs(asOfDate);
					break;
				case MODIFIED_LOAN:
					//if(line.length!=39) printLine(line);
					String[] entries = new String[line.length];
					System.arraycopy(line, 1, entries, 0, line.length-1);
					entries[line.length-1]=epochMilliSecondsStr;
					modifiedLoanPen = getPen(recordType);
					getPen(recordType).writeNext(entries);
					break;
				case LOAN:
					//if (line.length!=40) printLine(line);
					entries = new String[line.length];
					System.arraycopy(line, 1, entries, 0, line.length-1);
					entries[line.length-1]=epochMilliSecondsStr;
					loanPen = getPen(recordType);
					getPen(recordType).writeNext(entries);
					break;
				default:
					break;
				} // end of switch
			} // end of while
			if (loanPen!=null) loanPen.close();
			if (modifiedLoanPen!=null) modifiedLoanPen.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
