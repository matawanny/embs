package com.yieldbook.mortgage.concurrency;

import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class FnmaArmSupplementProcess {
	
	private String inputFileName;
	private String asOfMonthDate;
    BlockingQueue<Message> queue = new LinkedBlockingQueue<>();

	
	public FnmaArmSupplementProcess(String inputFileName, String asOfMonthDate) {
		super();
		this.inputFileName = inputFileName;
		this.asOfMonthDate = asOfMonthDate;
	}
	
	public String getInputFileName() {
		return inputFileName;
	}
	public void setInputFileName(String inputFileName) {
		this.inputFileName = inputFileName;
	}
	public String getAsOfMonthDate() {
		return asOfMonthDate;
	}
	public void setAsOfMonthDate(String asOfMonthDate) {
		this.asOfMonthDate = asOfMonthDate;
	}
	
	public void fromFileToHive() throws IOException{
	    FnmaArmSupplementProducer p1 = new FnmaArmSupplementProducer(1, queue, inputFileName);
	    FnmaArmSupplementConsumer c1 = new FnmaArmSupplementConsumer(1, queue, asOfMonthDate);
	    
	    ExecutorService service = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
	    service.execute(p1);
	    service.execute(c1);
	    service.shutdown();
	}

}
