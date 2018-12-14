package com.yieldbook.mortgage.action.gnma;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

import com.ancientprogramming.fixedformat4j.format.FixedFormatManager;
import com.ancientprogramming.fixedformat4j.format.impl.FixedFormatManagerImpl;
import com.opencsv.CSVWriter;
import com.yieldbook.mortgage.action.BaseFileParser;
import com.yieldbook.mortgage.domain.gnma.GnmaLoan;
import com.yieldbook.mortgage.domain.gnma.GnmaLoanHeader;
import com.yieldbook.mortgage.domain.gnma.RecordTypeGnmaLoan;

public class LoanFileParser extends BaseFileParser {

	private String inputFileName;
	private String outputFileName;

	StringBuilder sb = new StringBuilder();

	CSVWriter loanPen;
	
	private Map<String, String> cusipDic = null;
	private static FixedFormatManager manager= new FixedFormatManagerImpl();

	public LoanFileParser(String inputFileName, String outputFileName) {
		super();
		this.inputFileName=inputFileName;
		this.outputFileName = outputFileName;
		cusipDic = new HashMap<>();
		
		try {
			loanPen = new CSVWriter(new FileWriter(this.outputFileName, true),
					'|', CSVWriter.NO_QUOTE_CHARACTER,
					CSVWriter.DEFAULT_ESCAPE_CHARACTER,
					CSVWriter.DEFAULT_LINE_END);
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

	@Override
	public void parseAndWriteFile() {
		try {
			Files.lines(new File(inputFileName).toPath())
				.filter(s -> !s.isEmpty()||s.startsWith("P")||s.startsWith("L"))
				.forEach(s -> ProcessPoolAndLoan(s));
			if (loanPen!=null){
				loanPen.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	private void ProcessPoolAndLoan(String input){
		RecordTypeGnmaLoan recordType = RecordTypeGnmaLoan.from(input.substring(0,1)); 
		switch (recordType) {
		case POOL_HEADER:
			 GnmaLoanHeader pool = manager.load(GnmaLoanHeader.class, input);
			 cusipDic.put(pool.getPoolId(), pool.getCusip());
			break;
		case LOAN_LEVEL:
			GnmaLoan loan = manager.load(GnmaLoan.class, input);
			String cusip = cusipDic.get(loan.getPoolID());
			String[] entries = loan.toLoanArray(cusip);
			loanPen.writeNext(entries);
			break;
		default: 
		    break;
		} //end of switch
	}

}
