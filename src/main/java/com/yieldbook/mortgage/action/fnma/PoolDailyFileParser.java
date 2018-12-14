package com.yieldbook.mortgage.action.fnma;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.avro.generic.GenericData.Record;
import org.apache.avro.generic.GenericRecordBuilder;
import org.kitesdk.data.Dataset;
import org.kitesdk.data.DatasetDescriptor;
import org.kitesdk.data.DatasetWriter;
import org.kitesdk.data.Datasets;
import org.kitesdk.data.Formats;

import com.ancientprogramming.fixedformat4j.format.FixedFormatManager;
import com.ancientprogramming.fixedformat4j.format.impl.FixedFormatManagerImpl;
import com.yieldbook.mortgage.action.BaseFileParser;
import com.yieldbook.mortgage.domain.fnma.ArmResetDaily;
import com.yieldbook.mortgage.domain.fnma.PoolDaily;
import com.yieldbook.mortgage.domain.fnma.RecordTypeFnmaMonthlyPool;

import static com.yieldbook.mortgage.utility.YieldBookUtilities.*;

public class PoolDailyFileParser extends BaseFileParser {

	private String inputFileName;
	private Integer asOfDate;
	private GenericRecordBuilder poolDataBuilder;
	private GenericRecordBuilder armResetBuilder;
	private Map<String, PoolDaily> fnmaPools = null;
	private static FixedFormatManager manager= new FixedFormatManagerImpl();

	public PoolDailyFileParser(String inputFileName, String asOfDateStr) throws IOException{
		super();
		this.inputFileName=inputFileName;
		this.asOfDate=Integer.parseInt(asOfDateStr);
		fnmaPools = new HashMap<>();
		
		DatasetDescriptor poolDescriptor = new DatasetDescriptor.Builder()
        .schemaUri("resource:fnma/fnma_daily_pool_detail.avsc")
        .format(Formats.PARQUET)
        .build();
		poolDataBuilder = new GenericRecordBuilder(poolDescriptor.getSchema()); 
		
		DatasetDescriptor armResetDescriptor = new DatasetDescriptor.Builder()
	    .schemaUri("resource:fnma/fnma_daily_arm_reset.avsc")
	    .format(Formats.PARQUET)
	    .build();
		armResetBuilder = new GenericRecordBuilder(armResetDescriptor.getSchema()); 
	}

	@Override
	public void parseAndWriteFile() {
		try {

			Files.lines(new File(inputFileName).toPath())
					.filter(s -> !s.isEmpty() || s.startsWith("1")
							|| s.startsWith("2"))
					.forEach(s -> ProcessEachLine(s));
		} catch (IOException e) {
			e.printStackTrace();
		} 
		WriteToDataset();
	}
	
	private void ProcessEachLine(String input){
		RecordTypeFnmaMonthlyPool recordType = RecordTypeFnmaMonthlyPool.from(Integer.parseInt(input.substring(0,1))); 
		switch (recordType) {
		case POOL_DATA:
			 PoolDaily poolData = manager.load(PoolDaily.class, input);
			 fnmaPools.put(poolData.getPool_number(), poolData);
			break;
		case ARM_RESET:
			ArmResetDaily armRest = manager.load(ArmResetDaily.class, input);
			PoolDaily parentPool = fnmaPools.get(armRest.getPool_number());
			parentPool.getArmResets().add(armRest);
			break;
		default: 
		    break;
		} //end of switch
	}
	
	private void WriteToDataset(){
	    // Load the fnma_daily_pool dataset
	    Dataset<Record> pools = Datasets.load("dataset:hive:/prd/fnma_daily_pool_detail", Record.class);	
	    DatasetWriter<Record> writer = null;
	    try {
	        writer = pools.newWriter();

	        for (PoolDaily poolData : fnmaPools.values()) {
		        if(poolData.getArmResets().size()>0){
			        List<Record> armResetRecords = new ArrayList<>();
		        	for (ArmResetDaily armReset: poolData.getArmResets()){
		        	Record armRestRecord = armResetBuilder.set("next_rate_chg_date", getyyyyMMddFromDate(armReset.getNext_rate_chg_date()))
		        			  .set("pct_balance", armReset.getPct_balance())
		        			  .set("mbs_margin_high", armReset.getMbs_margin_high())
		        			  .set("mbs_margin_low", armReset.getMbs_margin_low())
		        			  .set("wtd_avg_mbs_margin", armReset.getWtd_avg_mbs_margin())
		        			  .set("accrual_net_coupon_high", armReset.getAccrual_net_coupon_high())
		        			  .set("accrual_net_coupon_low", armReset.getAccrual_net_coupon_low())
		        			  .set("wtd_avg_net_coupon", armReset.getWtd_avg_net_coupon())
		        			  .set("net_life_cap_high", armReset.getNet_life_cap_high())
		        			  .set("net_life_cap_low", armReset.getNet_life_cap_low())
		        			  .set("net_life_floor_high", armReset.getNet_life_floor_high())
		        			  .set("net_life_floor_low", armReset.getNet_life_floor_low()).build();
		        	  armResetRecords.add(armRestRecord);
		        	}
		        	poolDataBuilder.set("arm_resets", armResetRecords);
		        }	
	        	Record poolDataRecord = poolDataBuilder.set("pool_number", poolData.getPool_number())
		        	.set("prefix", poolData.getPrefix())
		        	.set("cusip_number", poolData.getCusip_number())
		        	.set("issue_date", getyyyyMMddFromDate(poolData.getIssue_date()))
		        	.set("pass_thru_rate", poolData.getPass_thru_rate())
		        	.set("original_wac", poolData.getOriginal_wac())
		        	.set("original_wam", poolData.getOriginal_wam())
		        	.set("maturity_date", getyyyyMMddFromDate(poolData.getMaturity_date()))
		        	.set("original_balance", poolData.getOriginal_balance())
		        	.set("smp_percent", poolData.getSmp_percent())
		        	.set("margin", poolData.getMargin())
		        	.set("subtype", poolData.getSubtype())
		        	.set("transfer_type", poolData.getTransfer_type())
		        	.set("first_rate_chg_date", getyyyyMMddFromDate(poolData.getFirst_rate_chg_date()))
		        	.set("first_paymt_chg_date", getyyyyMMddFromDate(poolData.getFirst_paymt_chg_date()))
		        	.set("orig_net_life_cap", poolData.getOrig_net_life_cap())
		        	.set("orig_net_life_floor", poolData.getOrig_net_life_floor())
		        	.set("months_to_next_rt_chg", poolData.getMonths_to_next_rt_chg())
		        	.set("as_of_date", this.asOfDate).build();
	            writer.write(poolDataRecord);	
	        }
	      }catch(Exception e){  
	           e.printStackTrace(System.out);
	      } finally {
	        if (writer != null) {
	          writer.close();
	        }
	      }	    
	}

}
