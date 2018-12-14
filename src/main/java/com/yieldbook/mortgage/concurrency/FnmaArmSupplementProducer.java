package com.yieldbook.mortgage.concurrency;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.BlockingQueue;

import com.ancientprogramming.fixedformat4j.format.FixedFormatManager;
import com.ancientprogramming.fixedformat4j.format.impl.FixedFormatManagerImpl;
import com.yieldbook.mortgage.domain.fnma.ArmPoolMonthly;
import com.yieldbook.mortgage.domain.fnma.FirstPaymentDateMonthly;
import com.yieldbook.mortgage.domain.fnma.NextRateChangeDateMonthly;
import com.yieldbook.mortgage.domain.fnma.RecordTypeFnmaMonthlyArmPool;

public class FnmaArmSupplementProducer implements Runnable {
    private int id;
    private BlockingQueue<Message> queue;
    private String inputFileName;
	private static FixedFormatManager manager= new FixedFormatManagerImpl();
	private Map<String, ArmPoolMonthly> fnmaArmPoolMap = null;
    
	public FnmaArmSupplementProducer(int id, BlockingQueue<Message> queue, String inputFileName) {
		super();
		this.id = id;
		this.queue = queue;
		this.inputFileName = inputFileName;
		fnmaArmPoolMap = new HashMap<>();
	}
    
    @Override
    public void run(){
		try {
			Files.lines(new File(inputFileName).toPath())
			.forEach(s -> ProcessEachLine(s));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    } 
    
	private void ProcessEachLine(String input) {
		
		RecordTypeFnmaMonthlyArmPool recordType = RecordTypeFnmaMonthlyArmPool.from(Integer.parseInt(input.substring(9,10))); 
		ArmPoolMonthly armPoolData = null;
		switch (recordType) {
		case ARM_POOL_DATA:

			Iterator it = fnmaArmPoolMap.entrySet().iterator();
		    while (it.hasNext()) {
		        Map.Entry<String, ArmPoolMonthly> pair = (Map.Entry<String, ArmPoolMonthly>)it.next();
		        //WriteToDataset(pair.getValue());
		        try {
					queue.put(new Message(pair.getKey(), pair.getValue()));
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        it.remove(); // avoids a ConcurrentModificationException
		    }			
			
			ArmPoolMonthly poolData = manager.load(ArmPoolMonthly.class, input);
			//System.out.println(poolData);
			fnmaArmPoolMap.put(poolData.getPoolNumber(), poolData);
			break;
		case NEXT_RATE_CHANGE_DATE:
			NextRateChangeDateMonthly nextRateChangeDate = manager.load(NextRateChangeDateMonthly.class, input);
			//System.out.println(nextRateChangeDate);
			armPoolData = fnmaArmPoolMap.get(nextRateChangeDate.getPoolNumber());
			if(armPoolData!=null){
				armPoolData.getNextRateChangeDates().add(nextRateChangeDate);
			}
			break;
		case FIRST_PAYMENT_DATE:
			FirstPaymentDateMonthly firstPaymentDate = manager.load(FirstPaymentDateMonthly.class, input);
			//System.out.println(firstPaymentDate);
			armPoolData = fnmaArmPoolMap.get(firstPaymentDate.getPoolNumber());
			if(armPoolData!=null){
				armPoolData.getFirstPaymentDates().add(firstPaymentDate);
			}
			break;
		default: 
		    break;
		} //end of switch
	}    

}
