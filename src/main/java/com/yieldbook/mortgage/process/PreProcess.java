package com.yieldbook.mortgage.process;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.lang3.StringUtils;
import com.yieldbook.mortgage.action.BaseFileParser;


public class PreProcess {
	
	private BaseFileParser baseFileParser;

	
	public PreProcess(BaseFileParser baseFileParser) {
		super();
		this.baseFileParser = baseFileParser;
	}

	public void parseAndWriteFile(){
		baseFileParser.parseAndWriteFile();
	}

	public static void main(String[] args) throws Exception {

		  Options options = new Options();

		  Option input = new Option("i", "input", true, "input file to read data from");
		  input.setRequired(true);
		  input.setArgName("FILE INPUT PATH");
		  options.addOption(input);
		  
		  Option filetype = new Option("t", "filetype", true, "input file type, should be fhlmc_loan or fhlmc_loan_old or fnma_loan or fnma_daily_pool_detail or fnma_daily_supplement or fnma_monthly_supplement etc. ");
		  filetype.setRequired(true);
		  filetype.setArgName("FILE TYPE");
		  options.addOption(filetype);

		  Option output = new Option("o", "output", true, "output file to write the final result");
		  //output.setRequired(true);
		  output.setArgName("FILE OUT PATH");
		  options.addOption(output);

		  Option asofdate = new Option("d", "asofdate", true, "As Of Date");
		  //output.setRequired(true);
		  asofdate.setArgName("AS OF DATE");
		  options.addOption(asofdate);
		  

		  
		  CommandLineParser commandLineParser = new DefaultParser();
		  HelpFormatter helpFormatter = new HelpFormatter();
		  CommandLine commandLine;
		  String propFileName = "config.properties";
		   Properties prop = new Properties();
		   String mappingUrl="";

			try (InputStream inputStream = PreProcess.class.getClassLoader().getResourceAsStream(propFileName)) {
				// Loading the properties.
				prop.load(inputStream);
				// Getting properties
				mappingUrl = prop.getProperty("mapping.url");
			} catch (IOException ex) {
				System.out.println("Problem occurs when reading file !");
				ex.printStackTrace();
			} 	

		  try {
		   commandLine = commandLineParser.parse(options, args);
		  } catch (ParseException e) {
		   System.out.println(e.getMessage());
		   helpFormatter.printHelp("Preprocess", options);

		   System.exit(1);
		   return;
		  }

		  String inputFileStr = commandLine.getOptionValue("input");
		  String fileTypeStr = commandLine.getOptionValue("filetype");
		  String outputFileStr = commandLine.getOptionValue("output");
		  String asOfDateStr = commandLine.getOptionValue("asofdate");


		  System.out.println("Input File: " + inputFileStr);
		  System.out.println("filetype: " + fileTypeStr);
		  if(!StringUtils.isEmpty(outputFileStr))
			  System.out.println("Output File Path : " + outputFileStr);
		  if(!StringUtils.isEmpty(asOfDateStr))
			  System.out.println("As Of Date : " + asOfDateStr);
		  
		  fileTypeStr = fileTypeStr.toLowerCase();
		  
		  PreProcess preProcess=null;
		  
		  switch(fileTypeStr){
		  case "fhlmc_loan":
			  preProcess = new PreProcess(new com.yieldbook.mortgage.action.fhlmc.LoanFileParser(inputFileStr, outputFileStr));
			  preProcess.parseAndWriteFile();
			  break;
		  case "fhlmc_loan_old":
			  preProcess = new PreProcess(new com.yieldbook.mortgage.action.fhlmc.LoanOldFileParser(inputFileStr));
			  preProcess.parseAndWriteFile();
			  break;
		  case "fnma_loan":
			  preProcess = new PreProcess(new com.yieldbook.mortgage.action.fnma.LoanFileParser(inputFileStr, asOfDateStr, outputFileStr));
			  preProcess.parseAndWriteFile();
			  break;
		  case "fnma_daily_pool_detail":
			  preProcess = new PreProcess(new com.yieldbook.mortgage.action.fnma.PoolDailyFileParser(inputFileStr, asOfDateStr));
			  preProcess.parseAndWriteFile();
			  break;			  
		  case "fnma_daily_pool_supplement":
			  preProcess = new PreProcess(new com.yieldbook.mortgage.action.fnma.SupplementDailyFileParser(inputFileStr, asOfDateStr, mappingUrl));
			  preProcess.parseAndWriteFile();
			  break;
		  case "fnma_monthly_pool_detail":
			  preProcess = new PreProcess(new com.yieldbook.mortgage.action.fnma.PoolStatsMonthlyFileParser(inputFileStr, asOfDateStr));
			  preProcess.parseAndWriteFile();
			  break;
		  case "fnma_monthly_arm_pool":
			  preProcess = new PreProcess(new com.yieldbook.mortgage.action.fnma.ArmPoolMonthlyFileParser(inputFileStr, asOfDateStr));
			  preProcess.parseAndWriteFile();
			  break;
		  case "fnma_monthly_pool_supplement":
			  preProcess = new PreProcess(new com.yieldbook.mortgage.action.fnma.SupplementMonthlyFileParser(inputFileStr, asOfDateStr));
			  preProcess.parseAndWriteFile();
			  break;
		  case "gnma_loan":
			  preProcess = new PreProcess(new com.yieldbook.mortgage.action.gnma.LoanFileParser(inputFileStr, outputFileStr));
			  preProcess.parseAndWriteFile();
			  break;
		  case "gnma_daily_pool_detail":
			  preProcess = new PreProcess(new com.yieldbook.mortgage.action.gnma.PoolDailyFileParser(inputFileStr, asOfDateStr));
			  preProcess.parseAndWriteFile();
			  break;			  
		  default: 
			  System.out.println("File Type: " +  fileTypeStr + " is not supported. ");
		  
		  }

		 }
}
