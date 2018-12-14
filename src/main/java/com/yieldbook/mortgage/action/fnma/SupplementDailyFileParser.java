package com.yieldbook.mortgage.action.fnma;

import static com.yieldbook.mortgage.utility.YieldBookUtilities.getyyyyMMddFromDate;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.avro.generic.GenericData.Record;
import org.apache.avro.generic.GenericRecordBuilder;
import org.beanio.BeanReader;
import org.beanio.InvalidRecordException;
import org.beanio.StreamFactory;
import org.beanio.UnidentifiedRecordException;
import org.kitesdk.data.Dataset;
import org.kitesdk.data.DatasetDescriptor;
import org.kitesdk.data.DatasetWriter;
import org.kitesdk.data.Datasets;
import org.kitesdk.data.Formats;

import com.yieldbook.mortgage.action.BaseFileParser;
import com.yieldbook.mortgage.domain.fnma.PoolDailyElement;
import com.yieldbook.mortgage.domain.fnma.NextRateChangeDateDaily;
import com.yieldbook.mortgage.domain.fnma.PoolDailySupplement;
import com.yieldbook.mortgage.domain.fnma.PoolDailyQuartile;
import com.yieldbook.mortgage.domain.fnma.WaForNextRateChangeDateDaily;

public class SupplementDailyFileParser extends BaseFileParser {

	private String inputFileName;
	private Integer asOfDate;
	private String mappingUrl;
	
	private GenericRecordBuilder supplementDataBuilder;
	private GenericRecordBuilder quartileBuilder;
	private GenericRecordBuilder elementBuilder;
	private GenericRecordBuilder nextRateChangeDateBuilder;
	private GenericRecordBuilder waForNextRateChangeDateBuilder;
	private List<PoolDailySupplement> supplements;

	DatasetWriter<Record> writer = null;
	StringBuilder sb = new StringBuilder();

	public SupplementDailyFileParser(String inputFileName, String asOfDateStr, String mappingUrl) throws IOException{
		super();
		this.inputFileName=inputFileName;
		this.asOfDate=Integer.parseInt(asOfDateStr);
		this.mappingUrl = mappingUrl;
		this.supplements = new ArrayList<>();
		
		DatasetDescriptor supplementDescriptor = new DatasetDescriptor.Builder()
        .schemaUri("resource:fnma/fnma_daily_pool_supplement.avsc")
        .format(Formats.PARQUET)
        .build();
		supplementDataBuilder = new GenericRecordBuilder(supplementDescriptor.getSchema()); 
		
		DatasetDescriptor quartileDescriptor = new DatasetDescriptor.Builder()
	    .schemaUri("resource:fnma/fnma_daily_pool_quartile.avsc")
	    .format(Formats.PARQUET)
	    .build();
		quartileBuilder = new GenericRecordBuilder(quartileDescriptor.getSchema()); 
		
		DatasetDescriptor elementDescriptor = new DatasetDescriptor.Builder()
	    .schemaUri("resource:fnma/fnma_daily_pool_element.avsc")
	    .format(Formats.PARQUET)
	    .build();
		elementBuilder = new GenericRecordBuilder(elementDescriptor.getSchema()); 
		
		DatasetDescriptor nextRateChangeDateDescriptor = new DatasetDescriptor.Builder()
	    .schemaUri("resource:fnma/fnma_daily_next_rate_change_date.avsc")
	    .format(Formats.PARQUET)
	    .build();
		nextRateChangeDateBuilder = new GenericRecordBuilder(nextRateChangeDateDescriptor.getSchema());
		
		DatasetDescriptor waForNextRageChangeDateDescriptor = new DatasetDescriptor.Builder()
	    .schemaUri("resource:fnma/fnma_daily_wa_for_next_rate_change_date.avsc")
	    .format(Formats.PARQUET)
	    .build();
		waForNextRateChangeDateBuilder = new GenericRecordBuilder(waForNextRageChangeDateDescriptor.getSchema()); 
	}
	
	@Override
	public void parseAndWriteFile() {
		// create a StreamFactory
		StreamFactory factory = StreamFactory.newInstance();
		// load the mapping file
		factory.load(mappingUrl);
		// use a StreamFactory to create a BeanReader
		BeanReader in = factory.createReader("fnmaPoolSupplementDaily", new File(
				this.inputFileName));

		PoolDailySupplement poolSupplement;
		while ((poolSupplement = (PoolDailySupplement) in.read()) != null) {
			try {
				supplements.add(poolSupplement);
			} catch (InvalidRecordException | UnidentifiedRecordException ex) {
				continue;
			}
		}
		WriteToDataset();
	}

	private void WriteToDataset(){
	    Dataset<Record> supplementDataset = Datasets.load("dataset:hive:/prd/fnma_daily_pool_supplement", Record.class);	
	    DatasetWriter<Record> writer = null;        
	    try {
	        writer = supplementDataset.newWriter();
	        for (PoolDailySupplement poolSupplement : supplements) {
	            supplementDataBuilder.set("pool_number", poolSupplement.poolNumber)
			        	.set("cusip", poolSupplement.cusip)
			        	.set("issue_date", getyyyyMMddFromDate(poolSupplement.issueDate))
			        	.set("security_description", poolSupplement.securityDescription)
			        	.set("issue_amount", poolSupplement.issueAmount)
			        	.set("pass_through_rate", poolSupplement.passThroughRate)
			        	.set("initial_accrual_rate", poolSupplement.initialAccrualRate)
			        	.set("first_pi_date", getyyyyMMddFromDate(poolSupplement.firstPIDate))
			        	.set("seller", poolSupplement.seller)
			        	.set("servicer", poolSupplement.servicer)
			        	.set("number_of_loans", poolSupplement.numberOfLoans)
			        	.set("average_loan_size", poolSupplement.averageLoanSize)
			        	.set("maturity_date", getyyyyMMddFromDate(poolSupplement.maturityDate))
			        	.set("initial_interest_rate_change_date", getyyyyMMddFromDate(poolSupplement.initialInterestRateChangeDate))
			        	.set("wa_months_to_roll", poolSupplement.waMonthsToRoll)
			        	.set("sub_type", poolSupplement.subType)
			        	.set("convertible", poolSupplement.convertible)
			        	.set("transfer_type", poolSupplement.transferType)
			        	.set("pass_through_method", poolSupplement.passThroughMethod)
			        	.set("wa_coupon", poolSupplement.waCoupon)
			        	.set("wa_max_pool_accrual_rate", poolSupplement.waMaxPoolAccrualRate)
			        	.set("wa_min_pool_accrual_rate", poolSupplement.waMinPoolAccrualRate)
			        	.set("wa_loan_age", poolSupplement.waLoanAge)
			        	.set("wa_loan_term", poolSupplement.waLoanTerm)
			        	.set("wa_remaining_maturity_at_issuance", poolSupplement.waRemainingMaturityAtIssuance)
			        	.set("wa_ltv", poolSupplement.waLTV)
			        	.set("wa_credit_score", poolSupplement.waCreditScore)
			        	.set("percent_upb_without_credit_score", poolSupplement.percentUPBWithoutCreditScore)
			        	.set("percent_upb_with_interest_only", poolSupplement.percentUPBWithInterestOnly)	
			        	.set("percent_upb_with_fully_amortizing", poolSupplement.percentUPBWithFullyAmortizing)
			        	.set("prefix", poolSupplement.prefix)		
			        	.set("first_payment_change_date", getyyyyMMddFromDate(poolSupplement.firstPaymentChangeDate))
			        	.set("percent_upb_with_third_party_origination", poolSupplement.percentUPBWithThirdPartyOrigination)
			        	.set("wa_combined_loan_to_value_ratio", poolSupplement.waCombinedLoanToValueRatio)	
			        	.set("wa_original_loan_size", poolSupplement.waOriginalLoanSize)			        	
			        	.set("as_of_date", this.asOfDate);
	            if(poolSupplement.quartiles!=null && poolSupplement.quartiles.size()>0){
	            	List<Record> quartileRecords = new ArrayList<>();
	            	for(PoolDailyQuartile quartile: poolSupplement.quartiles){
	            		Record quartileRecord = quartileBuilder.set("quartile_level", quartile.quartileLevel)
	            				.set("loan_size", quartile.loanSize)
	            				.set("coupon", quartile.coupon)
	            				.set("ltv", quartile.ltv)
	            				.set("credit_score", quartile.creditScore)
	            				.set("loan_term", quartile.loanTerm)
	            				.set("loan_age", quartile.loanAge)
	            				.set("remaining_maturity", quartile.remainingMaturity).build();
	            		quartileRecords.add(quartileRecord);
	            	}
	            	supplementDataBuilder.set("quartiles", quartileRecords);
	            }
	            if(poolSupplement.elements!=null && poolSupplement.elements.size()>0){
	            	List<Record> elementRecords = new ArrayList<>();
	            	for(PoolDailyElement element: poolSupplement.elements){
	            		Record elementRecord = elementBuilder.set("element_name", element.elementName)
	            				.set("entity_name", element.entityName)
	            				.set("entity_value", element.entityValue)
	            				.set("number_of_loans", element.numberOfLoans)
	            				.set("percent_of_upb", element.percentOfUPB)
	            				.set("aggregate_upb", element.aggregateUPB).build();
	            		elementRecords.add(elementRecord);
	            	}
	            	supplementDataBuilder.set("elements", elementRecords);
	            }
	            if(poolSupplement.nextRateChangeDates!=null && poolSupplement.nextRateChangeDates.size()>0){
	            	List<Record> nextRateChangeDateRecords = new ArrayList<>();
	            	for(NextRateChangeDateDaily nextRateChangeDate: poolSupplement.nextRateChangeDates){
	            		Record nextRateChangeDateRecord = nextRateChangeDateBuilder
	            				.set("date", getyyyyMMddFromDate(nextRateChangeDate.date))
	            				.set("percent_of_balance", nextRateChangeDate.percentOfBalance)
	            				.set("mbs_margin_high", nextRateChangeDate.mbsMarginHigh)
	            				.set("mbs_margin_low", nextRateChangeDate.mbsMarginLow)
	            				.set("mbs_margin", nextRateChangeDate.mbsMargin)
	            				.set("net_coupon_high", nextRateChangeDate.netCouponHigh)
	            				.set("net_coupon_low", nextRateChangeDate.netCouponLow)
	            				.set("wa_net_coupon", nextRateChangeDate.waNetCoupon)
	            				.set("net_life_caps_high", nextRateChangeDate.netLifeCapsHigh)
	            				.set("net_life_caps_low", nextRateChangeDate.netLifeCapsLow)
	            				.set("net_life_floor_high", nextRateChangeDate.netLifeFloorHigh)
	            				.set("net_life_floor_low", nextRateChangeDate.netLifeFloorLow).build();
	            		nextRateChangeDateRecords.add(nextRateChangeDateRecord);
	            	}
	            	supplementDataBuilder.set("next_rate_change_dates",nextRateChangeDateRecords);
	            }
	            if(poolSupplement.waForNextRateChangeDates!=null && poolSupplement.waForNextRateChangeDates.size()>0){
	            	List<Record> waForNextRateChangeDateRecords = new ArrayList<>();
	            	for(WaForNextRateChangeDateDaily waForNextRateChangeDate: poolSupplement.waForNextRateChangeDates){
	            		Record waForNextRateChangeDateRecord = waForNextRateChangeDateBuilder
	            				.set("wa_mbs_margin", waForNextRateChangeDate.waMBSMargin)
	            				.set("wa_net_coupon", waForNextRateChangeDate.waNetCoupon)
	            				.set("wa_net_life_caps", waForNextRateChangeDate.waNetLifeCaps)
	            				.set("wa_net_life_floor", waForNextRateChangeDate.waNetLifeFloor).build();
	            		waForNextRateChangeDateRecords.add(waForNextRateChangeDateRecord);
	            	}
	            	supplementDataBuilder.set("wa_for_next_rate_change_dates",waForNextRateChangeDateRecords);
	            }	            
	            Record poolSupplmentRecord = supplementDataBuilder.build();
	            writer.write(poolSupplmentRecord);
	        }// end of for
	      }catch(Exception e){  
	           e.printStackTrace(System.out);
	      } finally {
		        if (writer != null) {
			          writer.close();
			    }
		  }	
	} // end of method
}

