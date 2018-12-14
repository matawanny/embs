package com.yieldbook.mortgage.concurrency;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

import org.apache.avro.generic.GenericData.Record;
import org.apache.avro.generic.GenericRecordBuilder;
import org.kitesdk.data.Dataset;
import org.kitesdk.data.DatasetDescriptor;
import org.kitesdk.data.DatasetWriter;
import org.kitesdk.data.Datasets;
import org.kitesdk.data.Formats;

import com.yieldbook.mortgage.domain.fnma.ArmPoolMonthly;
import com.yieldbook.mortgage.domain.fnma.FirstPaymentDateMonthly;
import com.yieldbook.mortgage.domain.fnma.NextRateChangeDateMonthly;

public class FnmaArmSupplementConsumer implements Runnable {
	
    private int id;
    private BlockingQueue<Message> queue;
	private Integer asOfMonthDate;
	private Integer lastChgDate;
	private GenericRecordBuilder armPoolDataBuilder, nextRateChangeDateBuilder, firstPaymentDateBuilder;
	private Dataset<Record> armPools;
	private DatasetWriter<Record> writer;
	
	public FnmaArmSupplementConsumer(int id, BlockingQueue<Message> queue, String asOfDateStr) throws IOException {
		super();
		this.id = id;
		this.queue = queue;
		this.lastChgDate=Integer.parseInt(asOfDateStr);
		this.asOfMonthDate=Integer.parseInt(asOfDateStr.substring(0,6).concat("01"));		

		DatasetDescriptor poolDescriptor = new DatasetDescriptor.Builder()
        .schemaUri("resource:fnma_monthly_arm_pool.avsc")
        .format(Formats.PARQUET)
        .build();
		armPoolDataBuilder = new GenericRecordBuilder(poolDescriptor.getSchema());
		
		DatasetDescriptor nextRateChangeDateDescriptor = new DatasetDescriptor.Builder()
        .schemaUri("resource:fnma_monthly_next_rate_change_date.avsc")
        .format(Formats.PARQUET)
        .build();
		nextRateChangeDateBuilder = new GenericRecordBuilder(nextRateChangeDateDescriptor.getSchema());
		
		DatasetDescriptor firstPaymentDateDescriptor = new DatasetDescriptor.Builder()
        .schemaUri("resource:fnma_monthly_first_payment_date.avsc")
        .format(Formats.PARQUET)
        .build();
		firstPaymentDateBuilder = new GenericRecordBuilder(firstPaymentDateDescriptor.getSchema());	
		

	}
    
    @Override
    public void run() {
        Message msg;
        try {
            while (!(msg = queue.take()).getId().equals("-1")) {
        		armPools = Datasets.load("dataset:hive:/prd/fnma_arm_pool_monthly", Record.class);	
        		writer = null;
                System.out.printf("Consumer messageid = " + msg.getId());
                WriteToDataset(msg.getPayload());
                //Thread.sleep((int) (Math.random() * 100));
            }
            if (writer != null) {
  	          writer.close();
  	        }
        } catch (InterruptedException e) {
            e.printStackTrace();
            if (writer != null) {
  	          writer.close();
  	        }
        }
    } 
    
	private void WriteToDataset(ArmPoolMonthly armPoolData){
		
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
		 
		 List<Record> firstPaymentDateRecords = new ArrayList<>();
		 for (FirstPaymentDateMonthly firstPaymentDate: armPoolData.getFirstPaymentDates()){
			 Record firstPaymentDateRecord = firstPaymentDateBuilder.set("first_payment_date", firstPaymentDate.getFirstPaymentDate())
       			  .set("original_note_rate", firstPaymentDate.getOriginalNoteRate1())
       			  .set("percent_security_balance", firstPaymentDate.getPercentSecurityBalance())
       			  .set("loan_survival_ratio", firstPaymentDate.getLoanSurvivalRatio()).build();
			 firstPaymentDateRecords.add(firstPaymentDateRecord); 
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
	        	.set("next_rate_change_dates", nextRateChangeDateRecords)
	        	.set("first_payment_dates", firstPaymentDateRecords)
	        	.set("last_chg_date", this.lastChgDate)
	        	.set("as_of_date", this.asOfMonthDate).build();
            writer.write(poolDataRecord);		 
	}
    

}
