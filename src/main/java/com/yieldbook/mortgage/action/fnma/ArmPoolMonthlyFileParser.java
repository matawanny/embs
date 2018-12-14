package com.yieldbook.mortgage.action.fnma;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
import com.yieldbook.mortgage.domain.fnma.ArmPoolMonthly;
import com.yieldbook.mortgage.domain.fnma.FirstPaymentDateMonthly;
import com.yieldbook.mortgage.domain.fnma.NextRateChangeDateMonthly;
import com.yieldbook.mortgage.domain.fnma.RecordTypeFnmaMonthlyArmPool;

public class ArmPoolMonthlyFileParser extends BaseFileParser {

	private String inputFileName;
	private Integer asOfMonthDate;
	private Integer lastChgDate;
	private GenericRecordBuilder armPoolDataBuilder, nextRateChangeDateBuilder, firstPaymentDateBuilder;
	private static FixedFormatManager manager= new FixedFormatManagerImpl();
	private Map<String, ArmPoolMonthly> fnmaArmPoolMap = null;
	private Dataset<Record> armPools;
	private DatasetWriter<Record> writer;
	
	public ArmPoolMonthlyFileParser(String inputFileName, String asOfDateStr) throws IOException{
		super();
		this.inputFileName=inputFileName;
		this.lastChgDate=Integer.parseInt(asOfDateStr);
		this.asOfMonthDate=Integer.parseInt(asOfDateStr.substring(0,6).concat("00"));
		
		DatasetDescriptor poolDescriptor = new DatasetDescriptor.Builder()
        .schemaUri("resource:fnma/fnma_monthly_arm_pool.avsc")
        .format(Formats.PARQUET)
        .build();
		armPoolDataBuilder = new GenericRecordBuilder(poolDescriptor.getSchema());
		
		DatasetDescriptor nextRateChangeDateDescriptor = new DatasetDescriptor.Builder()
        .schemaUri("resource:fnma/fnma_monthly_next_rate_change_date.avsc")
        .format(Formats.PARQUET)
        .build();
		nextRateChangeDateBuilder = new GenericRecordBuilder(nextRateChangeDateDescriptor.getSchema());
		
		DatasetDescriptor firstPaymentDateDescriptor = new DatasetDescriptor.Builder()
        .schemaUri("resource:fnma/fnma_monthly_first_payment_date.avsc")
        .format(Formats.PARQUET)
        .build();
		firstPaymentDateBuilder = new GenericRecordBuilder(firstPaymentDateDescriptor.getSchema());		
		fnmaArmPoolMap = new HashMap<>();

	}

	@Override
	public void parseAndWriteFile() {
		armPools = Datasets.load("dataset:hive:/prd/fnma_monthly_arm_pool", Record.class);	
		writer = null;
		try {
			writer = armPools.newWriter();
			Files.lines(new File(inputFileName).toPath())
					.skip(1)
					.forEach(s -> ProcessEachLine(s));
		} catch (Exception e) {
			e.printStackTrace();
		} /*finally{
			  if (writer != null) {
		          writer.close();
		        }		
		}*/

		 if (writer != null) {
	          writer.close();
	     }		
	}
	
	private void ProcessEachLine(String input) {
		
		RecordTypeFnmaMonthlyArmPool recordType = RecordTypeFnmaMonthlyArmPool.from(Integer.parseInt(input.substring(9,10))); 
		ArmPoolMonthly armPoolData = null;
		switch (recordType) {
		case ARM_POOL_DATA:
			Iterator it = fnmaArmPoolMap.entrySet().iterator();
		    while (it.hasNext()) {
		        Map.Entry<String, ArmPoolMonthly> pair = (Map.Entry)it.next();
		        try {
					WriteToDataset(pair.getValue());
				} catch (Exception e) {
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
	
	private void WriteToDataset(ArmPoolMonthly armPoolData) throws InterruptedException{
		if(armPoolData.getNextRateChangeDates()!=null && armPoolData.getNextRateChangeDates().size()>0){
			List<Record> nextRateChangeDateRecords = new ArrayList<>();
			for (NextRateChangeDateMonthly nextRateChangeDate: armPoolData.getNextRateChangeDates()){
				 Record nextRateChangeDateRecord = nextRateChangeDateBuilder.set("next_rate_change_date", nextRateChangeDate.getNextRateChangeDate())
	      			  .set("original_note_rate", nextRateChangeDate.getOriginalNoteRate1())
	      			  .set("percent_security_balance", nextRateChangeDate.getPercentSecurityBalance())
	      			  .set("mbs_margin_high", nextRateChangeDate.getMBSMarginHigh1())
	      			  .set("mbs_margin_low", nextRateChangeDate.getMBSMarginLow1())
	      			  .set("pass_through_rate_cap_high", nextRateChangeDate.getPassThroughRateCapHigh())
	      			  .set("pass_through_rate_cap_low", nextRateChangeDate.getPassThroughRateCapLow())
	      			  .set("pass_through_rate_floor_high", nextRateChangeDate.getPassThroughRateFloorHigh1())
	      			  .set("pass_through_rate_floor_low", nextRateChangeDate.getPassThroughRateFloorLow1())
	      			  .set("pass_through_rate_high", nextRateChangeDate.getPassThroughRateHigh())
	      			  .set("pass_through_rate_low", nextRateChangeDate.getPassThroughRateLow())
	      			  .set("cross_coupon_high", nextRateChangeDate.getCrossCouponHigh())
	      			  .set("cross_coupon_low", nextRateChangeDate.getCrossCouponLow())
	      			  .set("wa_mbs_margin", nextRateChangeDate.getWaMBSMargin1())
	      			  .set("wa_pass_through_rate", nextRateChangeDate.getWaPassThroughRate())
	      			  .set("wa_coupon", nextRateChangeDate.getWaCoupon())
	      			  .set("note_rate_cap", nextRateChangeDate.getNoteRateCap())
	      			  .set("loan_count", nextRateChangeDate.getLoanCount1()).build();
				 nextRateChangeDateRecords.add(nextRateChangeDateRecord); 
			 }
			 armPoolDataBuilder.set("next_rate_change_dates", nextRateChangeDateRecords);
		}
		if(armPoolData.getFirstPaymentDates()!=null && armPoolData.getFirstPaymentDates().size()>0){
			List<Record> firstPaymentDateRecords = new ArrayList<>();
			for (FirstPaymentDateMonthly firstPaymentDate: armPoolData.getFirstPaymentDates()){
				 Record firstPaymentDateRecord = firstPaymentDateBuilder.set("first_payment_date", firstPaymentDate.getFirstPaymentDate())
	      			  .set("original_note_rate", firstPaymentDate.getOriginalNoteRate1())
	      			  .set("percent_security_balance", firstPaymentDate.getPercentSecurityBalance())
	      			  .set("loan_survival_ratio", firstPaymentDate.getLoanSurvivalRatio()).build();
				 firstPaymentDateRecords.add(firstPaymentDateRecord); 
			}
			armPoolDataBuilder.set("first_payment_dates", firstPaymentDateRecords);
		}	 
    	Record poolDataRecord = armPoolDataBuilder.set("cusip_number", armPoolData.getPoolNumber())
	        	.set("pool_issue_date", armPoolData.getPoolIssueDate())
	        	.set("pool_maturity_date", armPoolData.getPoolMaturityDate())
	        	.set("lender_issuer", armPoolData.getLenderIssuer())
	        	.set("lender_city", armPoolData.getLenderCity())
	        	.set("state", armPoolData.getState())
	        	.set("subtype", armPoolData.getSubtype())
	        	.set("transfer_type", armPoolData.getTransferType())
	        	.set("pass_through_rate_structure", armPoolData.getPassThroughRateStructure())
	        	.set("convertible_flag", armPoolData.getConvertibleFlag())
	        	.set("deferred_interest_allowed", armPoolData.getDeferredInterestAllowed())
	        	.set("original_security_balance", armPoolData.getOriginalSecurityBalance1())
	        	.set("original_number_of_loans", armPoolData.getOriginalNumberOfLoans1())
	        	.set("original_wa_coupon", armPoolData.getOriginalWACoupon1())
	        	.set("original_wa_maturity", armPoolData.getOriginalWAMaturity1())
	        	.set("original_number_of_loans", armPoolData.getOriginalNumberOfLoans1())
	        	.set("first_rate_change_date", armPoolData.getFirstRateChangeDate())
	        	.set("first_payment_change_date", armPoolData.getFirstPaymentChangeDate())
	        	.set("issue_accrual_rate", armPoolData.getIssueAccrualRate())
	        	.set("security_balance_mlnm", armPoolData.getSecurityBalanceMlnm())
	        	.set("security_balance_year", armPoolData.getSecurityBalanceYear())
	        	.set("security_balance_month", armPoolData.getSecurityBalanceMonth())
	        	.set("current_security_balance", armPoolData.getCurrentSecurityBalance1())
	        	.set("current_trading_factor", armPoolData.getCurrentTradingFactor())
	        	.set("current_wa_coupon", armPoolData.getCurrentWACoupon())
	        	.set("current_wa_maturity", armPoolData.getCurrentWAMaturity1())
	        	.set("current_deferred_interest", armPoolData.getCurrentDeferredInterest())
	        	.set("return_deferred_interest", armPoolData.getReturnDeferredInterest())
	        	.set("wa_months_to_rate_change", armPoolData.getWaMonthsToRateChange1())
	        	.set("wa_mbs_margin", armPoolData.getWaMBSMargin1())
	        	.set("wa_lpt_life_cap", armPoolData.getWaLPTLifeCap())
	        	.set("wa_lpt_life_life", armPoolData.getWaLPTLifeLife1())
	        	.set("wa_loan_margin", armPoolData.getWaLoanMargin1())
	        	.set("wa_negative_amortization_limit", armPoolData.getWaNegativeAmortizationLimit())
	        	.set("published_pass_through_rate", armPoolData.getPublishedPassThroughRate())
	        	.set("rate_difference_flag", armPoolData.getRateDifferenceFlag())
	        	.set("accrual_rate", armPoolData.getAccrualRate1())
	        	.set("lookback_rate_change", armPoolData.getLookbackRateChange1())
	        	.set("lookback_payment_change", armPoolData.getLookbackPaymentChange1())
	        	.set("per_adjustment_rate_cap", armPoolData.getPerAdjustmentRateCap1())
	        	.set("rate_adjustment_frequency", armPoolData.getRateAdjustmentFrequency1())
	        	.set("payment_change_frequency", armPoolData.getPaymentChangeFrequency1())
	        	.set("amortization_recast_frequency", armPoolData.getAmortizationRecastFrequency())
	        	.set("accrual_rate_rounding_method_code", armPoolData.getAccrualRateRoundingMethodCode())
	        	.set("payment_cap", armPoolData.getPaymentCap1())
	        	.set("minimum_index_movement", armPoolData.getMinimumIndexMovement1())
	        	.set("maximum_accrual_rate", armPoolData.getMaximumAccrualRate1())
	        	.set("minimum_accrual_rate", armPoolData.getMinimumAccrualRate1())
	        	.set("last_chg_date", this.lastChgDate)
	        	.set("as_of_date", this.asOfMonthDate).build();
           writer.write(poolDataRecord);		 
	}
}
