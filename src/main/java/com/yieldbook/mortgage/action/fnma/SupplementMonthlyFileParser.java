package com.yieldbook.mortgage.action.fnma;

import static com.yieldbook.mortgage.utility.YieldBookUtilities.DecimalFormatter;
import static com.yieldbook.mortgage.utility.YieldBookUtilities.getyyyyMMddFromDate;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
import com.yieldbook.mortgage.domain.fnma.PoolMonthlyInterestOnly;
import com.yieldbook.mortgage.domain.fnma.PoolMonthlyQuartile;
import com.yieldbook.mortgage.domain.fnma.PoolMonthlyServicer;
import com.yieldbook.mortgage.domain.fnma.PoolMonthlySupplement;
import com.yieldbook.mortgage.domain.fnma.PoolMonthlyThirdPartyOriginationType;
import com.yieldbook.mortgage.domain.fnma.RecordTypeFnmaMonthlyPoolInterestOnly;
import com.yieldbook.mortgage.domain.fnma.RecordTypeFnmaMonthlyPoolSupplement;
import com.yieldbook.mortgage.utility.YieldBookUtilities;

public class SupplementMonthlyFileParser extends BaseFileParser {

	private String inputFileName;
	private Integer asOfMonthDate;
	private Integer lastChgDate;
	private GenericRecordBuilder supplementBuilder, quartileBuilder, servicerBuilder, thirdPartyOriginationTypeBuilder, interestOnlyBuilder;
	private static FixedFormatManager manager= new FixedFormatManagerImpl();
	private Map<String, PoolMonthlySupplement> supplementMap = null;
	private Dataset<Record> supplements;
	private DatasetWriter<Record> writer;
	
	public SupplementMonthlyFileParser(String inputFileName, String asOfDateStr) throws IOException{
		super();
		this.inputFileName=inputFileName;
		this.lastChgDate=Integer.parseInt(asOfDateStr);
		this.asOfMonthDate=Integer.parseInt(asOfDateStr.substring(0,6).concat("00"));
 		
		DatasetDescriptor supplementDescriptor = new DatasetDescriptor.Builder()
        .schemaUri("resource:fnma/fnma_monthly_pool_supplement.avsc")
        .format(Formats.PARQUET)
        .build();
		supplementBuilder = new GenericRecordBuilder(supplementDescriptor.getSchema());
		
		DatasetDescriptor quartileDescriptor = new DatasetDescriptor.Builder()
        .schemaUri("resource:fnma/fnma_monthly_pool_quartile.avsc")
        .format(Formats.PARQUET)
        .build();
		quartileBuilder = new GenericRecordBuilder(quartileDescriptor.getSchema());
		
		DatasetDescriptor serivceDescriptor = new DatasetDescriptor.Builder()
        .schemaUri("resource:fnma/fnma_monthly_pool_servicer.avsc")
        .format(Formats.PARQUET)
        .build();
		servicerBuilder = new GenericRecordBuilder(serivceDescriptor.getSchema());
		
		DatasetDescriptor thirdPartyOriginationTypeDescriptor = new DatasetDescriptor.Builder()
        .schemaUri("resource:fnma/fnma_monthly_third_party_origination_type.avsc")
        .format(Formats.PARQUET)
        .build();
		thirdPartyOriginationTypeBuilder = new GenericRecordBuilder(thirdPartyOriginationTypeDescriptor.getSchema());
		
		DatasetDescriptor interestOnlyDescriptor = new DatasetDescriptor.Builder()
        .schemaUri("resource:fnma/fnma_monthly_interest_only.avsc")
        .format(Formats.PARQUET)
        .build();
		interestOnlyBuilder = new GenericRecordBuilder(interestOnlyDescriptor.getSchema());
		
		supplementMap = new HashMap<>();

	}
	
	static boolean isQualifiedLine(String line){
		if(StringUtils.isBlank(line)||line.length()<=7) return false;
		String first = line.substring(0,1);
		if(YieldBookUtilities.isInteger(first))
			return true;
		else 
			return false;
	}

	@Override
	public void parseAndWriteFile() {
		supplements = Datasets.load("dataset:hive:/prd/fnma_monthly_pool_supplement", Record.class);	
		writer = null;
		try {
			writer = supplements.newWriter();
			Files.lines(new File(inputFileName).toPath())
					.skip(1)
					.forEach(s -> {
						if (isQualifiedLine(s)) ProcessEachFixedLengthLine(s);
						else{
							ProcessEachPipeDelimitedLine(s);
						}	
					});
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
	
	private void ProcessEachFixedLengthLine(String input) {
		
		RecordTypeFnmaMonthlyPoolSupplement recordType = RecordTypeFnmaMonthlyPoolSupplement.from(Integer.parseInt(input.substring(0,1))); 
		PoolMonthlySupplement supplementData = null;
		switch (recordType) {
		case HEADER:
			Iterator it = supplementMap.entrySet().iterator();
		    while (it.hasNext()) {
		        Map.Entry<String, PoolMonthlySupplement> pair = (Map.Entry)it.next();
		        try {
					WriteToDataset(pair.getValue());
				} catch (Exception e) {
				   e.printStackTrace();
				}
		        it.remove(); // avoids a ConcurrentModificationException
		    }		
			
			PoolMonthlySupplement poolData = manager.load(PoolMonthlySupplement.class, input);
			//System.out.println(poolData);
			supplementMap.put(poolData.getPoolNumber(), poolData);
			break;
		case QUARTILE:
			PoolMonthlyQuartile quartile = manager.load(PoolMonthlyQuartile.class, input);
			//System.out.println(quartile);
			supplementData = supplementMap.get(quartile.getPoolNumber());
			if(supplementData!=null){
				supplementData.setQuartile(quartile);
			}
			break;
		case SERVICER:
			PoolMonthlyServicer servicer = manager.load(PoolMonthlyServicer.class, input);
			//System.out.println(servicer);
			supplementData = supplementMap.get(servicer.getPoolNumber());
			if(supplementData!=null){
				supplementData.getServicers().add(servicer);
			}
			break;
		case THIRDPARTYORIGINATIONTYPE:
			PoolMonthlyThirdPartyOriginationType originationType = manager.load(PoolMonthlyThirdPartyOriginationType.class, input);
			//System.out.println(originationType);
			supplementData = supplementMap.get(originationType.getPoolNumber());
			if(supplementData!=null){
				supplementData.setThirdPartyOriginationType(originationType);
			}			
			break;
		default: 
		    break;
		} //end of switch
		
	}
	
	private void ProcessEachPipeDelimitedLine(String input) {
		RecordTypeFnmaMonthlyPoolInterestOnly recordType = RecordTypeFnmaMonthlyPoolInterestOnly.from(Integer.parseInt(input.substring(1,2))); 
		String source = input.substring(2,input.length());
		String[] elements = source.split(";");
		String poolNumber=elements[0];
		PoolMonthlySupplement supplementData = supplementMap.get(poolNumber);
		PoolMonthlyInterestOnly interestOnly = supplementData.getInterestOnly();
		if(interestOnly==null){
			interestOnly = new PoolMonthlyInterestOnly();
			supplementData.setInterestOnly(interestOnly);
			interestOnly.setPoolNumber(elements[0]);
		}
		switch (recordType) {
			case ONE:
				interestOnly.setWaMonthsToAmortization(Integer.parseInt(elements[1]));
				interestOnly.setNumberOfLoansPerPool(Integer.parseInt(elements[2]));
				interestOnly.setPoolPrefix(elements[3]==null?null:elements[3].trim());
				interestOnly.setCusip(elements[4]);
				break;
			case TWO:
				interestOnly.setRemainingMonthsToAmortization(Integer.parseInt(elements[1]));
				interestOnly.setNumberOfLoansPerPoolPerRemainingMonthsToAmortization(Integer.parseInt(elements[2]));
				interestOnly.setUpbPercent(DecimalFormatter.parse(elements[3], new FormatInstructions(5, Align.RIGHT, ' ', null, null, 
					FixedFormatNumberData.DEFAULT, new FixedFormatDecimalData(2, false, '.', RoundingMode.valueOf(BigDecimal.ROUND_HALF_UP)))));
				interestOnly.setComment(StringUtils.isBlank(elements[4])?null:elements[4].trim());
				break;
			default:
				break;
		}
		//System.out.println(interestOnly);
	}
	
	private void WriteToDataset(PoolMonthlySupplement poolMonthlySupplement)throws InterruptedException{
		
		Record quartileRecord=null;
		PoolMonthlyQuartile poolMonthlyQuartile=poolMonthlySupplement.getQuartile();
		if(poolMonthlyQuartile!=null){
			quartileRecord = quartileBuilder.set("credit_score_wa", poolMonthlyQuartile.getCreditScoreWa())
					.set("credit_score_minimum", poolMonthlyQuartile.getCreditScoreMinimum())
					.set("credit_score_quartile1", poolMonthlyQuartile.getCreditScoreQuartile1())
					.set("credit_score_quartile2", poolMonthlyQuartile.getCreditScoreQuartile2())
					.set("credit_score_quartile3", poolMonthlyQuartile.getCreditScoreQuartile3())
					.set("credit_score_quartile4", poolMonthlyQuartile.getCreditScoreQuartile4())
					.set("credit_score_quartile_percent_missing", poolMonthlyQuartile.getCreditScoreQuartilePercentMissing())
					.set("ltv_wa", poolMonthlyQuartile.getLtvWa())
					.set("ltv_minimum", poolMonthlyQuartile.getLtvMinimum())
					.set("ltv_quartile1", poolMonthlyQuartile.getLtvQuartile1())
					.set("ltv_quartile2", poolMonthlyQuartile.getLtvQuartile2())
					.set("ltv_quartile3", poolMonthlyQuartile.getLtvQuartile3())
					.set("ltv_quartile4", poolMonthlyQuartile.getLtvQuartile4())
					.set("ltv_percent_missing", poolMonthlyQuartile.getLtvPercentMissing())
					.set("ltv_loan_count_missing", poolMonthlyQuartile.getLtvLoanCountMissing())
					.set("one_unit_upb", poolMonthlyQuartile.getOneUnitUpb())
					.set("one_unit_percent", poolMonthlyQuartile.getOneUnitPercent())
					.set("one_unit_loan_count", poolMonthlyQuartile.getOneUnitLoanCount())
					.set("two__four_unit_upb", poolMonthlyQuartile.getTwo_FourUnitUpb())
					.set("tow__four_unit_percent", poolMonthlyQuartile.getTow_FourUnitPercent())
					.set("two__four_unit_loan_count", poolMonthlyQuartile.getTwo_FourUnitLoanCount())
					.set("purchase_upb", poolMonthlyQuartile.getPurchaseUpb())
					.set("purchase_percent", poolMonthlyQuartile.getPurchasePercent())
					.set("purchase_loan_count", poolMonthlyQuartile.getPurchaseLoanCount())
					.set("refinance_upb", poolMonthlyQuartile.getRefinanceUpb())
					.set("refinance_percent", poolMonthlyQuartile.getRefinancePercent())
					.set("refinance_loan_count", poolMonthlyQuartile.getRefinanceLoanCount())
					.set("principal_upb", poolMonthlyQuartile.getPrincipalUpb())
					.set("principal_percent", poolMonthlyQuartile.getPrincipalPercent())
					.set("principal_loan_count", poolMonthlyQuartile.getPrincipalLoanCount())
					.set("second_upb", poolMonthlyQuartile.getSecondUpb())
					.set("second_percent", poolMonthlyQuartile.getSecondPercent())
					.set("second_loan_count", poolMonthlyQuartile.getSecondLoanCount())
					.set("investment_upb", poolMonthlyQuartile.getInvestmentUpb())
					.set("investment_percent", poolMonthlyQuartile.getInvestmentPercent())
					.set("investment_loan_count", poolMonthlyQuartile.getInvestmentLoanCount()).build();
		}
		if(poolMonthlySupplement.getServicers()!=null && poolMonthlySupplement.getServicers().size()>0){
			 List<Record> servicerRecords = new ArrayList<>();
			 for (PoolMonthlyServicer poolMonthlyServicer: poolMonthlySupplement.getServicers()){
				 Record servicerRecord = servicerBuilder.set("servicer_name", poolMonthlyServicer.getServicerName())
						 .set("servicer_upb", poolMonthlyServicer.getServicerUPB())
						 .set("servicer_percent", poolMonthlyServicer.getServicerPercent())
						 .set("loan_count", poolMonthlyServicer.getLoanCount()).build();
				 servicerRecords.add(servicerRecord); 
			 }
			 supplementBuilder.set("servicers", servicerRecords);
		}	 
		 
		Record thirdPartyOriginationTypeRecord=null;
		PoolMonthlyThirdPartyOriginationType poolMonthlyThirdPartyOriginationType=poolMonthlySupplement.getThirdPartyOriginationType();
		if(poolMonthlyThirdPartyOriginationType!=null){
			thirdPartyOriginationTypeRecord = thirdPartyOriginationTypeBuilder.set("third_party_origination_upb_percent", poolMonthlyThirdPartyOriginationType.getThirdPartyOriginationUpbPercent())
					.set("broker_loan_count", poolMonthlyThirdPartyOriginationType.getBrokerLoanCount())
					.set("broker_upb", poolMonthlyThirdPartyOriginationType.getBrokerUpb())
					.set("broker_upb_percent", poolMonthlyThirdPartyOriginationType.getBrokerUpbPercent())
					.set("correspondent_loan_count", poolMonthlyThirdPartyOriginationType.getCorrespondentLoanCount())
					.set("correspondent_upb", poolMonthlyThirdPartyOriginationType.getCorrespondentUpb())
					.set("correspondent_upb_percent", poolMonthlyThirdPartyOriginationType.getCorrespondentUpbPercent())
					.set("retail_loan_count", poolMonthlyThirdPartyOriginationType.getRetailLoanCount())
					.set("retail_upb", poolMonthlyThirdPartyOriginationType.getRetailUpb())
					.set("retail_upb_percent", poolMonthlyThirdPartyOriginationType.getRetailUpbPercent()).build();
		}
		
		Record interestOnlyRecord=null;
		PoolMonthlyInterestOnly poolMonthlyInterestOnly=poolMonthlySupplement.getInterestOnly();
		if(poolMonthlyInterestOnly!=null){
			interestOnlyRecord = interestOnlyBuilder.set("wa_months_to_amortization", poolMonthlyInterestOnly.getWaMonthsToAmortization())
					.set("number_of_loans_per_pool", poolMonthlyInterestOnly.getNumberOfLoansPerPool())
					.set("pool_prefix", poolMonthlyInterestOnly.getPoolPrefix())
					.set("cusip", poolMonthlyInterestOnly.getCusip())
					.set("remaining_months_to_amortization", poolMonthlyInterestOnly.getRemainingMonthsToAmortization())
					.set("number_of_loans_per_pool_per_remaining_months_to_amortization", poolMonthlyInterestOnly.getNumberOfLoansPerPoolPerRemainingMonthsToAmortization())
					.set("upb_percent", poolMonthlyInterestOnly.getUpbPercent())
					.set("comment", poolMonthlyInterestOnly.getComment()==null?"":poolMonthlyInterestOnly.getComment()).build();
		}		
		
		 
    	Record supplementRecord = supplementBuilder.set("pool_number", poolMonthlySupplement.getPoolNumber())
    			.set("pool_prefix", poolMonthlySupplement.getPoolPrefix())
    			.set("reporting_period", poolMonthlySupplement.getReportingPeriod())
    			.set("cusip_number", poolMonthlySupplement.getCusipNumber())
    			.set("issue_date", getyyyyMMddFromDate(poolMonthlySupplement.getIssueDate()))
    			.set("current_upb", poolMonthlySupplement.getCurrentUPB())
    			.set("total_active_loan_count", poolMonthlySupplement.getTotalActiveLoanCount())
    			.set("seller_name", poolMonthlySupplement.getSellerName())
    			.set("servicer_name", poolMonthlySupplement.getServicerName())
    			.set("quartile", quartileRecord)
    			.set("third_party_origination_type", thirdPartyOriginationTypeRecord)
    			.set("interest_only", interestOnlyRecord)
	        	.set("last_chg_date", this.lastChgDate)
	        	.set("as_of_date", this.asOfMonthDate).build();
    	
           writer.write(supplementRecord);		 
	}
}
