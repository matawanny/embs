package com.yieldbook.mortgage.action.fnma;

import static com.yieldbook.mortgage.utility.YieldBookUtilities.DecimalFormatter;
import static com.yieldbook.mortgage.utility.YieldBookUtilities.getyyyyMMdd;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.avro.generic.GenericData.Record;
import org.apache.avro.generic.GenericRecordBuilder;
import org.apache.commons.lang3.StringUtils;
import org.kitesdk.data.Dataset;
import org.kitesdk.data.DatasetDescriptor;
import org.kitesdk.data.DatasetWriter;
import org.kitesdk.data.Datasets;
import org.kitesdk.data.Formats;

import com.ancientprogramming.fixedformat4j.annotation.Align;
import com.ancientprogramming.fixedformat4j.format.FixedFormatManager;
import com.ancientprogramming.fixedformat4j.format.FormatInstructions;
import com.ancientprogramming.fixedformat4j.format.data.FixedFormatDecimalData;
import com.ancientprogramming.fixedformat4j.format.data.FixedFormatNumberData;
import com.ancientprogramming.fixedformat4j.format.impl.FixedFormatManagerImpl;
import com.yieldbook.mortgage.action.BaseFileParser;
import com.yieldbook.mortgage.domain.fnma.PoolMonthlyStats;

public class PoolStatsMonthlyFileParser extends BaseFileParser {

	private String inputFileName;
	private Integer asOfMonthDate;
	private Integer lastChgDate;
	private GenericRecordBuilder poolDataBuilder;
	private static FixedFormatManager manager= new FixedFormatManagerImpl();
	private Dataset<Record> pools = null;
	private DatasetWriter<Record> writer = null;

	public PoolStatsMonthlyFileParser(String inputFileName, String asOfDateStr) throws IOException{
		super();
		this.inputFileName=inputFileName;
		this.lastChgDate=Integer.parseInt(asOfDateStr);
		this.asOfMonthDate=Integer.parseInt(asOfDateStr.substring(0,6).concat("00"));
		
		DatasetDescriptor poolDescriptor = new DatasetDescriptor.Builder()
        .schemaUri("resource:fnma/fnma_monthly_pool_detail.avsc")
        .format(Formats.PARQUET)
        .build();
		poolDataBuilder = new GenericRecordBuilder(poolDescriptor.getSchema());
		

	}

	@Override
	public void parseAndWriteFile() {
	    
		// Load the fnma_monthly_pool dataset
	    pools = Datasets.load("dataset:hive:/prd/fnma_monthly_pool_detail", Record.class);	
	    writer = pools.newWriter();
	    
		try (Stream<String> stream = Files.lines(Paths.get(inputFileName))) {
			ArrayList<String> list =(ArrayList<String>) stream
					.map(w -> w.split("(?<=\\G.{220})")).flatMap(Arrays::stream)
					.collect(Collectors.toList());
			
			list.forEach(s -> ProcessEachLine(s));
			if (writer != null)	writer.close();	
		}catch (IOException e) {
			e.printStackTrace();
		}finally{
	           if (writer != null)	writer.close();		
		} 

	}
	
	private void ProcessEachLine(String input) {
		//System.out.println(input);
		PoolMonthlyStats poolStats = manager
				.load(PoolMonthlyStats.class, input);
		//System.out.println(poolStats);
		WriteToDataset(poolStats);
	}
	
	private void WriteToDataset(PoolMonthlyStats poolStats){
    	BigDecimal sdInterestRate = null;
        if(StringUtils.isBlank(poolStats.getSdInterestRate())){
        	sdInterestRate = null;
        }else if(poolStats.getSdInterestRate().startsWith("X")||
        		poolStats.getSdInterestRate().startsWith("x")){
        	sdInterestRate = null;
        }else{
        	sdInterestRate=DecimalFormatter.parse(poolStats.getSdInterestRate(), new FormatInstructions(6, Align.RIGHT, ' ', null, null, 
					FixedFormatNumberData.DEFAULT, new FixedFormatDecimalData(3, true, '.',RoundingMode.HALF_UP)));
        }	
    	
    	Record poolStatsRecord = poolDataBuilder.set("cusip", poolStats.getCusip())
	        	.set("pool_prefix", poolStats.getPoolPrefix())
	        	.set("pool_number", poolStats.getPoolNumber())
	        	.set("pool_type", poolStats.getPoolType())
	        	.set("original_balance", poolStats.getOriginalBalance())
	        	.set("cur_date", getyyyyMMdd(poolStats.getCurrentMonth(), poolStats.getCurrentYear()))
	        	.set("current_balance", poolStats.getCurrentBalance())
	        	.set("current_factor", poolStats.getCurrentFactor())
	        	.set("pass_through_rate", poolStats.getPassThroughRate())
	        	.set("issue_date", getyyyyMMdd(poolStats.getIssueMM(), poolStats.getIssueDD(), poolStats.getIssueYY()))
	        	.set("maturity_date", getyyyyMMdd(poolStats.getMaturityMM(),poolStats.getMaturityDD(),poolStats.getMaturityYY()))
	        	.set("original_wa_maturity", poolStats.getOriginalWAMaturity())
	        	.set("seller_name", poolStats.getSellerName())
	        	.set("seller_street", poolStats.getSellerStreet())
	        	.set("seller_city", poolStats.getSellerCity())
	        	.set("seller_state", poolStats.getSellerState())
	        	.set("seller_zip", poolStats.getSellerZip())
	        	.set("original_wa_coupon", poolStats.getOriginalWACoupon())
	        	.set("sd_security_type", poolStats.getSdSecurityType())
	        	.set("sd_interest_rate", sdInterestRate)
	        	.set("sd_pool_prefix", poolStats.getSdPoolPrefix())
	        	.set("sd_pool_number", poolStats.getSdPoolNumber())
	        	.set("current_wa_coupon", poolStats.getCurrentWACoupon())
	        	.set("current_wa_maturity", poolStats.getCurrentWAMaturity())
	        	.set("last_chg_date", this.lastChgDate)
	        	.set("as_of_date", this.asOfMonthDate).build();
            writer.write(poolStatsRecord);	
	}    

}
