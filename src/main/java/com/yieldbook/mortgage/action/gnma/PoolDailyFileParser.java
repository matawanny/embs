package com.yieldbook.mortgage.action.gnma;

import static com.yieldbook.mortgage.utility.YieldBookUtilities.getMethodName;
import static com.yieldbook.mortgage.utility.YieldBookUtilities.getyyyyMMddFromDate;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.avro.generic.GenericData.Record;
import org.apache.avro.util.Utf8;
import org.kitesdk.data.Dataset;
import org.kitesdk.data.DatasetDescriptor;
import org.kitesdk.data.DatasetWriter;
import org.kitesdk.data.Datasets;
import org.kitesdk.data.View;

import com.ancientprogramming.fixedformat4j.format.FixedFormatManager;
import com.ancientprogramming.fixedformat4j.format.impl.FixedFormatManagerImpl;
import com.yieldbook.mortgage.action.BaseFileParser;
import com.yieldbook.mortgage.domain.gnma.AdjustableRateMortgage;
import com.yieldbook.mortgage.domain.gnma.MortgageInsurance;
import com.yieldbook.mortgage.domain.gnma.Msa;
import com.yieldbook.mortgage.domain.gnma.MultiIssuer;
import com.yieldbook.mortgage.domain.gnma.OriginationYear;
import com.yieldbook.mortgage.domain.gnma.PoolDetailsDaily;
import com.yieldbook.mortgage.domain.gnma.PreModification;
import com.yieldbook.mortgage.domain.gnma.RecordTypeGnmaPoolDaily;
import com.yieldbook.mortgage.domain.gnma.RemovalByIssuer;
import com.yieldbook.mortgage.domain.gnma.State;
import com.yieldbook.mortgage.domain.gnma.StatsElement24;
import com.yieldbook.mortgage.domain.gnma.StatsElement29;
import com.yieldbook.mortgage.domain.gnma.StatsElement33;
import com.yieldbook.mortgage.domain.gnma.StatsElement42;
import com.yieldbook.mortgage.domain.gnma.StatsItem29;
import com.yieldbook.mortgage.domain.gnma.SupplementalDaily;
import com.yieldbook.mortgage.domain.gnma.TransferActivity;
import com.yieldbook.mortgage.domain.gnma.VariousData;
import com.yieldbook.mortgage.domain.gnma.data.EntityStatsFactor;
import com.yieldbook.mortgage.domain.gnma.data.EntityStringStatsFactor;
import com.yieldbook.mortgage.domain.gnma.data.GnmaDailyPoolDetail;
import com.yieldbook.mortgage.domain.gnma.data.GnmaDailyPoolDetail.Builder;
import com.yieldbook.mortgage.domain.gnma.data.MultiIssuerType;
import com.yieldbook.mortgage.domain.gnma.data.PreModificationOneType;
import com.yieldbook.mortgage.domain.gnma.data.PreModificationTwoType;
import com.yieldbook.mortgage.domain.gnma.data.QuartileDouble;
import com.yieldbook.mortgage.domain.gnma.data.QuartileInt;
import com.yieldbook.mortgage.domain.gnma.data.QuartileWaOrigInt;
import com.yieldbook.mortgage.domain.gnma.data.RemovalByIssuerType;
import com.yieldbook.mortgage.domain.gnma.data.StateType;
import com.yieldbook.mortgage.domain.gnma.data.StatsFactor;
import com.yieldbook.mortgage.domain.gnma.data.StatsFactorSimple;
import com.yieldbook.mortgage.domain.gnma.data.SupplementalOneType;
import com.yieldbook.mortgage.domain.gnma.data.TransferActivityType;
import com.yieldbook.mortgage.domain.gnma.data.ArmType;
import com.yieldbook.mortgage.domain.gnma.data.VariousDataOneType;
public class PoolDailyFileParser extends BaseFileParser {

	private String inputFileName;
	private Integer asOfDate;
	private static FixedFormatManager manager= new FixedFormatManagerImpl();
	private Map<String, PoolDetailsDaily> poolMap = null;
	private Dataset<Record> poolDetails;
	private View<GnmaDailyPoolDetail> poolDetailView;
	private DatasetWriter<GnmaDailyPoolDetail> writer;
    String outputUri = "dataset:hive:/prd/gnma_daily_pool_detail";
	
	public PoolDailyFileParser(String inputFileName, String asOfDateStr) throws IOException{
		super();
		this.inputFileName=inputFileName;
		this.asOfDate=Integer.parseInt(asOfDateStr);
		
	    if (!Datasets.exists(outputUri)) {
	    	System.setProperty("user.name", "oozie");
	        Datasets.create(outputUri, new DatasetDescriptor.Builder()
	            .format("parquet")
	            .schema(GnmaDailyPoolDetail.class)
	            .partitionStrategyUri("resource:gnma/embs_daily_partition.json")
	            .build());
	     }else{
/*	    	 System.setProperty("user.name", "oozie");
	         boolean success = Datasets.delete(outputUri);
		        Datasets.create(outputUri, new DatasetDescriptor.Builder()
	            .format("parquet")
	            .schema(GnmaDailyPoolDetail.class)
	            .partitionStrategyUri("resource:gnma/embs_daily_partition.json")
	            .build());	  */       
	     }
		
/*		DatasetDescriptor poolDetailDescriptor = new DatasetDescriptor.Builder()
        .schema(GnmaDailyPoolDetail.class).build();*/
		poolDetailView = Datasets.load(outputUri, GnmaDailyPoolDetail.class);
		poolMap = new HashMap<>();
	}
	

	@Override
	public void parseAndWriteFile() {
		//poolDetails = Datasets.load("dataset:hive:/prd/gnam_daily_pool", Record.class);
		writer = null;
		try {
		    writer = poolDetailView.newWriter();
			Files.lines(new File(inputFileName).toPath())
				.skip(1)	
				.forEach(s -> ProcessEachFixedLengthLine(s));
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
		
		RecordTypeGnmaPoolDaily recordType = RecordTypeGnmaPoolDaily.from(input.substring(18,19)); 
		PoolDetailsDaily poolDetailsDaily = null;
		switch (recordType) {
		case POOL_DETAIL:
			Iterator it = poolMap.entrySet().iterator();
		    while (it.hasNext()) {
		        Map.Entry<String, PoolDetailsDaily> pair = (Map.Entry)it.next();
		        try {
					WriteToDataset(pair.getValue());
				} catch (Exception e) {
				   e.printStackTrace();
				}
		        it.remove(); // avoids a ConcurrentModificationException
		    }		
			PoolDetailsDaily poolData = manager.load(PoolDetailsDaily.class, input);
			poolMap.put(poolData.getPoolNumber(), poolData);
			break;
		case MULTII_ISSUER:
			MultiIssuer multiIssuer = manager.load(MultiIssuer.class, input);
			poolDetailsDaily=poolMap.get(multiIssuer.getPoolNumber());
			if(poolDetailsDaily!=null){
				poolDetailsDaily.getMultiIssuers().add(multiIssuer);
			}	
			break;
		case MSA:
			Msa msa = manager.load(Msa.class, input);
			poolDetailsDaily=poolMap.get(msa.getPoolNumber());
			if(poolDetailsDaily!=null){
				poolDetailsDaily.setMsa(msa);
			}
			break;
		case ORIGINATION_YEAR:
			OriginationYear originationYear = manager.load(OriginationYear.class, input);
			poolDetailsDaily=poolMap.get(originationYear.getPoolNumber());
			if(poolDetailsDaily!=null){
				poolDetailsDaily.setOriginationYear(originationYear);
			}		
			break;
		case PRE_MODIFICATION:
			PreModification preModification = manager.load(PreModification.class, input);
			poolDetailsDaily=poolMap.get(preModification.getPoolNumber());
			if(poolDetailsDaily!=null){
				poolDetailsDaily.setPreModification(preModification);
			}			
			break;
		case REMOVALS_BY_ISSUER:
			RemovalByIssuer removalsByIssuer = manager.load(RemovalByIssuer.class, input);
			poolDetailsDaily=poolMap.get(removalsByIssuer.getPoolNumber());
			if(poolDetailsDaily!=null){
				poolDetailsDaily.getRemovalsByIssuer().add(removalsByIssuer);
			}	
			break;
		case STATE:
			State state = manager.load(State.class, input);
			poolDetailsDaily=poolMap.get(state.getPoolNumber());
			if(poolDetailsDaily!=null){
				poolDetailsDaily.getStates().add(state);
			}			
			break;
		case VARIOUS_DATA:
			VariousData variousData = manager.load(VariousData.class, input);
			poolDetailsDaily=poolMap.get(variousData.getPoolNumber());
			if(poolDetailsDaily!=null){
				poolDetailsDaily.setVariousData(variousData);
			}
			break;
		case SUPPLEMENTAL_DATA:
			SupplementalDaily supplement = manager.load(SupplementalDaily.class, input);
			poolDetailsDaily=poolMap.get(supplement.getPoolNumber());
			if(poolDetailsDaily!=null){
				poolDetailsDaily.setSupplement(supplement);
			}
			break;
		case MULTI_ISSUER_DELINQUENCY:
			System.out.println(input);
		    //applies only to the Monthly file. 
			break;
		case INSURANCE_PREMIUM_AND_OTHER_DATA:
			MortgageInsurance mortgageInsurance = manager.load(MortgageInsurance.class, input);
			poolDetailsDaily=poolMap.get(mortgageInsurance.getPoolNumber());
			if(poolDetailsDaily!=null){
				poolDetailsDaily.setMortgageInsurance(mortgageInsurance);
			}
			break;
		case TRANSFER_ACTIVITY:
			TransferActivity transferActivity = manager.load(TransferActivity.class, input);
			poolDetailsDaily=poolMap.get(transferActivity.getPoolNumber());
			if(poolDetailsDaily!=null){
				poolDetailsDaily.setTransferActivity(transferActivity);
			}
			break;
		case ADJUSTABLE_RATE_MORTGAGE:
			AdjustableRateMortgage adjustableRateMortgage = manager.load(AdjustableRateMortgage.class, input);
			poolDetailsDaily=poolMap.get(adjustableRateMortgage.getPoolNumber());
			if(poolDetailsDaily!=null){
				poolDetailsDaily.setAdjustableRateMortgage(adjustableRateMortgage);
			}
			break;	
		default: 
		    break;
		} //end of switch
		
	}
	
	
	private void WriteToDataset(PoolDetailsDaily poolDetailsDaily)throws InterruptedException{
		
		Builder poolDetailBuilder = GnmaDailyPoolDetail.newBuilder();
		QuartileDouble aolsRecord = QuartileDouble.newBuilder().setSelfValue(poolDetailsDaily.getAols().getElement().doubleValue())
				.setMaximum(poolDetailsDaily.getAols().getMaximum().doubleValue())
				.setSeventyFivePercent(poolDetailsDaily.getAols().getSeventyFivePercent().doubleValue())
				.setMedian(poolDetailsDaily.getAols().getMedian().doubleValue())
				.setTwentyFivePercent(poolDetailsDaily.getAols().getTwentyFivePercent().doubleValue())
				.setMinimum(poolDetailsDaily.getAols().getMinimum().doubleValue()).build();
	
		QuartileDouble wacRecord = QuartileDouble.newBuilder().setSelfValue(poolDetailsDaily.getWac().getElement().doubleValue())
				.setMaximum(poolDetailsDaily.getWac().getMaximum().doubleValue())
				.setSeventyFivePercent(poolDetailsDaily.getWac().getSeventyFivePercent().doubleValue())
				.setMedian(poolDetailsDaily.getWac().getMedian().doubleValue())
				.setTwentyFivePercent(poolDetailsDaily.getWac().getTwentyFivePercent().doubleValue())
				.setMinimum(poolDetailsDaily.getWac().getMinimum().doubleValue()).build();
		
		QuartileInt warmRecord = QuartileInt.newBuilder().setSelfValue(poolDetailsDaily.getWarm().getElement())
				.setMaximum(poolDetailsDaily.getWarm().getMaximum())
				.setSeventyFivePercent(poolDetailsDaily.getWarm().getSeventyFivePercent())
				.setMedian(poolDetailsDaily.getWarm().getMedian())
				.setTwentyFivePercent(poolDetailsDaily.getWarm().getTwentyFivePercent())
				.setMinimum(poolDetailsDaily.getWarm().getMinimum()).build();
		
		QuartileInt walaRecord = QuartileInt.newBuilder().setSelfValue(poolDetailsDaily.getWala().getElement())
				.setMaximum(poolDetailsDaily.getWala().getMaximum())
				.setSeventyFivePercent(poolDetailsDaily.getWala().getSeventyFivePercent())
				.setMedian(poolDetailsDaily.getWala().getMedian())
				.setTwentyFivePercent(poolDetailsDaily.getWala().getTwentyFivePercent())
				.setMinimum(poolDetailsDaily.getWala().getMinimum()).build();
		
		QuartileInt waoltRecord = QuartileInt.newBuilder().setSelfValue(poolDetailsDaily.getWaolt().getElement())
				.setMaximum(poolDetailsDaily.getWaolt().getMaximum())
				.setSeventyFivePercent(poolDetailsDaily.getWaolt().getSeventyFivePercent())
				.setMedian(poolDetailsDaily.getWaolt().getMedian())
				.setTwentyFivePercent(poolDetailsDaily.getWaolt().getTwentyFivePercent())
				.setMinimum(poolDetailsDaily.getWaolt().getMinimum()).build();
		
		QuartileDouble wagmRecord = QuartileDouble.newBuilder().setSelfValue(poolDetailsDaily.getWagm().getElement().doubleValue())
				.setMaximum(poolDetailsDaily.getWagm().getMaximum().doubleValue())
				.setSeventyFivePercent(poolDetailsDaily.getWagm().getSeventyFivePercent().doubleValue())
				.setMedian(poolDetailsDaily.getWagm().getMedian().doubleValue())
				.setTwentyFivePercent(poolDetailsDaily.getWagm().getTwentyFivePercent().doubleValue())
				.setMinimum(poolDetailsDaily.getWagm().getMinimum().doubleValue()).build();
		
		QuartileWaOrigInt ltvRecord = QuartileWaOrigInt.newBuilder().setWeightedAverageOriginal(poolDetailsDaily.getLtv().getWeightedAverageOriginal())
				.setMaximum(poolDetailsDaily.getLtv().getMaximum())
				.setSeventyFivePercent(poolDetailsDaily.getLtv().getSeventyFivePercent())
				.setMedian(poolDetailsDaily.getLtv().getMedian())
				.setTwentyFivePercent(poolDetailsDaily.getLtv().getTwentyFivePercent())
				.setMinimum(poolDetailsDaily.getLtv().getMinimum()).build();
		
		StatsFactor ltvNotAvailableRecord = StatsFactor.newBuilder()
				.setNumberOfLoans(poolDetailsDaily.getLtvNotAvailable().getNumberOfLoans())
				.setUpbOfLoans(poolDetailsDaily.getLtvNotAvailable().getUpbOfLoans().doubleValue())
				.setPercentOfPoolUpb(poolDetailsDaily.getLtvNotAvailable().getPercentOfPoolUpb().doubleValue()).build();
		
		StatsFactor purchaseRecord = StatsFactor.newBuilder()
				.setNumberOfLoans(poolDetailsDaily.getPurchase().getNumberOfLoans())
				.setUpbOfLoans(poolDetailsDaily.getPurchase().getUpbOfLoans().doubleValue())
				.setPercentOfPoolUpb(poolDetailsDaily.getPurchase().getPercentOfPoolUpb().doubleValue()).build();
		
		StatsFactor refinanceRecord = StatsFactor.newBuilder()
				.setNumberOfLoans(poolDetailsDaily.getRefinance().getNumberOfLoans())
				.setUpbOfLoans(poolDetailsDaily.getRefinance().getUpbOfLoans().doubleValue())
				.setPercentOfPoolUpb(poolDetailsDaily.getRefinance().getPercentOfPoolUpb().doubleValue()).build();		
		
		StatsFactor hampModifiedRecord = StatsFactor.newBuilder()
				.setNumberOfLoans(poolDetailsDaily.getHampModified().getNumberOfLoans())
				.setUpbOfLoans(poolDetailsDaily.getHampModified().getUpbOfLoans().doubleValue())
				.setPercentOfPoolUpb(poolDetailsDaily.getHampModified().getPercentOfPoolUpb().doubleValue()).build();
		
		StatsFactor nonHampModifiedRecord = StatsFactor.newBuilder()
				.setNumberOfLoans(poolDetailsDaily.getNonHampModified().getNumberOfLoans())
				.setUpbOfLoans(poolDetailsDaily.getNonHampModified().getUpbOfLoans().doubleValue())
				.setPercentOfPoolUpb(poolDetailsDaily.getNonHampModified().getPercentOfPoolUpb().doubleValue()).build();
		
		StatsFactor loanPurposeNotAvailableRecord = StatsFactor.newBuilder()
				.setNumberOfLoans(poolDetailsDaily.getLoanPurposeNotAvailable().getNumberOfLoans())
				.setUpbOfLoans(poolDetailsDaily.getLoanPurposeNotAvailable().getUpbOfLoans().doubleValue())
				.setPercentOfPoolUpb(poolDetailsDaily.getLoanPurposeNotAvailable().getPercentOfPoolUpb().doubleValue()).build();
		
		QuartileWaOrigInt cltvRecord = QuartileWaOrigInt.newBuilder().setWeightedAverageOriginal(poolDetailsDaily.getCltv().getWeightedAverageOriginal())
				.setMaximum(poolDetailsDaily.getCltv().getMaximum())
				.setSeventyFivePercent(poolDetailsDaily.getCltv().getSeventyFivePercent())
				.setMedian(poolDetailsDaily.getCltv().getMedian())
				.setTwentyFivePercent(poolDetailsDaily.getCltv().getTwentyFivePercent())
				.setMinimum(poolDetailsDaily.getCltv().getMinimum()).build();
		
		StatsFactor oneUnitRecord = StatsFactor.newBuilder()
				.setNumberOfLoans(poolDetailsDaily.getOneUnit().getNumberOfLoans())
				.setUpbOfLoans(poolDetailsDaily.getOneUnit().getUpbOfLoans().doubleValue())
				.setPercentOfPoolUpb(poolDetailsDaily.getOneUnit().getPercentOfPoolUpb().doubleValue()).build();
		
		StatsFactor oneToFourUnitRecord = StatsFactor.newBuilder()
				.setNumberOfLoans(poolDetailsDaily.getOneToFourUnit().getNumberOfLoans())
				.setUpbOfLoans(poolDetailsDaily.getOneToFourUnit().getUpbOfLoans().doubleValue())
				.setPercentOfPoolUpb(poolDetailsDaily.getOneToFourUnit().getPercentOfPoolUpb().doubleValue()).build();
		
		StatsFactor propertyTypeNotAvailableRecord = StatsFactor.newBuilder()
				.setNumberOfLoans(poolDetailsDaily.getPropertyTypeNotAvailable().getNumberOfLoans())
				.setUpbOfLoans(poolDetailsDaily.getPropertyTypeNotAvailable().getUpbOfLoans().doubleValue())
				.setPercentOfPoolUpb(poolDetailsDaily.getPropertyTypeNotAvailable().getPercentOfPoolUpb().doubleValue()).build();

		OriginationYear originationYear=poolDetailsDaily.getOriginationYear();
		if(originationYear!=null){
			List<EntityStringStatsFactor> originationYearList = new ArrayList<>();
			OriginationYearHelper("origination_year_1", originationYear, originationYearList);
			OriginationYearHelper("origination_year_2", originationYear, originationYearList);
			OriginationYearHelper("origination_year_3", originationYear, originationYearList);
			OriginationYearHelper("origination_year_4", originationYear, originationYearList);
			OriginationYearHelper("origination_year_all_others", originationYear, originationYearList);
			poolDetailBuilder.setOriginationYears(originationYearList);
			
		}
		PreModification preModification = poolDetailsDaily.getPreModification();
		if(preModification!=null){
			PreModificationOneType preModificationOne=PreModificationOneType.newBuilder()
					.setWeightedAverageLad(preModification.getLad().getWeightedAverage())
					.setMaximumLad(preModification.getLad().getMaximum())
					.setSeventyFivePercentLad(preModification.getLad().getSeventyFivePercent())
					.setMedianLad(preModification.getLad().getMedian())
					.setTwentyFivePercentLad(preModification.getLad().getTwentyFivePercent())
					.setMinimumLad(preModification.getLad().getMinimum())
					.setAveragePreModifiedOls(preModification.getPreModifiedOls().getAverage().doubleValue())
					.setMaximumPreModifiedOls(preModification.getPreModifiedOls().getMaximum().doubleValue())
					.setSeventyFivePercentPreModifiedOls(preModification.getPreModifiedOls().getSeventyFivePercent().doubleValue())
					.setMedianPreModifiedOls(preModification.getPreModifiedOls().getMedian().doubleValue())
					.setTwentyFivePercentPreModifiedOls(preModification.getPreModifiedOls().getTwentyFivePercent().doubleValue())
					.setMinimumPreModifiedOls(preModification.getPreModifiedOls().getMinimum().doubleValue())
					.build();
			poolDetailBuilder.setPreModificationOne(preModificationOne);
			List<PreModificationTwoType> preModificationTwoList = new ArrayList<>();
			PreModificationTwoHelper("pre_modified_first_payment_date", preModification, preModificationTwoList);
			PreModificationTwoHelper("with_pre_modified_opb", preModification, preModificationTwoList);
			poolDetailBuilder.setPreModificationTwo(preModificationTwoList);
		}
		
		MortgageInsurance insurance=poolDetailsDaily.getMortgageInsurance();
		if(insurance!=null){
			List<EntityStatsFactor> mortgageInsurances = new ArrayList<>();
			MortgageInsuranceHelper("upfront_mip_100", insurance, mortgageInsurances);
			MortgageInsuranceHelper("upfront_mip_125", insurance, mortgageInsurances);
			MortgageInsuranceHelper("upfront_mip_150", insurance, mortgageInsurances);
			MortgageInsuranceHelper("upfront_mip_175", insurance, mortgageInsurances);
			MortgageInsuranceHelper("upfront_mip_200", insurance, mortgageInsurances);
			MortgageInsuranceHelper("upfront_mip_225", insurance, mortgageInsurances);
			MortgageInsuranceHelper("annual_mip_25", insurance, mortgageInsurances);
			MortgageInsuranceHelper("annual_mip_35", insurance, mortgageInsurances);
			MortgageInsuranceHelper("annual_mip_50", insurance, mortgageInsurances);
			MortgageInsuranceHelper("annual_mip_55", insurance, mortgageInsurances);
			MortgageInsuranceHelper("annual_mip_60", insurance, mortgageInsurances);
			MortgageInsuranceHelper("annual_mip_85", insurance, mortgageInsurances);
			MortgageInsuranceHelper("annual_mip_90", insurance, mortgageInsurances);
			MortgageInsuranceHelper("annual_mip_110", insurance, mortgageInsurances);
			MortgageInsuranceHelper("annual_mip_115", insurance, mortgageInsurances);
			MortgageInsuranceHelper("annual_mip_120", insurance, mortgageInsurances);
			MortgageInsuranceHelper("annual_mip_125", insurance, mortgageInsurances);
			MortgageInsuranceHelper("annual_mip_145", insurance, mortgageInsurances);
			MortgageInsuranceHelper("annual_mip_150", insurance, mortgageInsurances);
			MortgageInsuranceHelper("annual_mip_not_available", insurance, mortgageInsurances);
			MortgageInsuranceHelper("first_home_buyer", insurance, mortgageInsurances);
			MortgageInsuranceHelper("not_first_home_buyer", insurance, mortgageInsurances);
			MortgageInsuranceHelper("first_home_buyer_not_available", insurance, mortgageInsurances);
			MortgageInsuranceHelper("origination_type_broker", insurance, mortgageInsurances);
			MortgageInsuranceHelper("origination_type_correspondent", insurance, mortgageInsurances);
			MortgageInsuranceHelper("origination_type_retail", insurance, mortgageInsurances);
			MortgageInsuranceHelper("origination_type_not_available", insurance, mortgageInsurances);
			MortgageInsuranceHelper("upfront_mip_000", insurance, mortgageInsurances);
			MortgageInsuranceHelper("upfront_mip_001", insurance, mortgageInsurances);
			MortgageInsuranceHelper("upfront_mip_300", insurance, mortgageInsurances);
			MortgageInsuranceHelper("upfront_mip_380", insurance, mortgageInsurances);
			MortgageInsuranceHelper("annual_mip_45", insurance, mortgageInsurances);
			MortgageInsuranceHelper("annual_mip_70", insurance, mortgageInsurances);
			MortgageInsuranceHelper("annual_mip_95", insurance, mortgageInsurances);
			MortgageInsuranceHelper("annual_mip_130", insurance, mortgageInsurances);
			MortgageInsuranceHelper("annual_mip_135", insurance, mortgageInsurances);
			MortgageInsuranceHelper("annual_mip_155", insurance, mortgageInsurances);
			MortgageInsuranceHelper("upfront_mip_50", insurance, mortgageInsurances);
			MortgageInsuranceHelper("upfront_mip_240", insurance, mortgageInsurances);
			MortgageInsuranceHelper("upfront_mip_250", insurance, mortgageInsurances);			
			MortgageInsuranceHelper("annual_mip_75", insurance, mortgageInsurances);
			MortgageInsuranceHelper("annual_mip_80", insurance, mortgageInsurances);
			MortgageInsuranceHelper("annual_mip_100", insurance, mortgageInsurances);
			MortgageInsuranceHelper("annual_mip_105", insurance, mortgageInsurances);
			poolDetailBuilder.setInsurancePremiumAndOtherData(mortgageInsurances);
		}
		
		Msa msa=poolDetailsDaily.getMsa();
		if(msa!=null){
			List<EntityStatsFactor> msaList = new ArrayList<>();
			MsaHelper("highest_msa", msa, msaList);
			MsaHelper("highest_2nd_msa", msa, msaList);
			MsaHelper("highest_3th_msa", msa, msaList);
			MsaHelper("highest_4th_msa", msa, msaList);
			MsaHelper("highest_5th_msa", msa, msaList);
			MsaHelper("highest_6th_msa", msa, msaList);
			MsaHelper("highest_7th_msa", msa, msaList);
			MsaHelper("highest_8th_msa", msa, msaList);
			MsaHelper("highest_9th_msa", msa, msaList);
			MsaHelper("highest_10th_msa", msa, msaList);
			MsaHelper("msa_not_available", msa, msaList);
			poolDetailBuilder.setMsa(msaList);
			
		}		
		
		if(poolDetailsDaily.getStates()!=null && poolDetailsDaily.getStates().size()>0){
			 List<StateType> stateRecords = new ArrayList<>();
			 for (State state: poolDetailsDaily.getStates()){
				 StateType stateTypeRecord = StateType.newBuilder()
						 .setStateAbbreviation(new Utf8(state.getStateAbbreviation()))
						 .setCurrentSecurityBalanceForState(state.getCurrentSecurityBalanceForState().doubleValue())
						 .setBalanceAsAPercent(state.getBalanceAsAPercent().doubleValue())
						 .setNumberOfLoansForState(state.getNumberOfLoansForState())
						 .setNumberOfLoansAsAPercent(state.getNumberOfLoansAsAPercent().doubleValue())
						 .setTotalLoans(state.getTotalLoans())
						 .setOriginalBalanceForSate(state.getOriginalBalanceForState().doubleValue()).build();
				 stateRecords.add(stateTypeRecord); 
			 }
			 poolDetailBuilder.setStates(stateRecords);
		}
		
		VariousData variousData = poolDetailsDaily.getVariousData();
		if(variousData!=null){
			List<VariousDataOneType> variousDataOneList = new ArrayList<>();
			VariousDataOneHelper("fha", variousData, variousDataOneList);
			VariousDataOneHelper("va", variousData, variousDataOneList);
			VariousDataOneHelper("rd", variousData, variousDataOneList);
			VariousDataOneHelper("pih", variousData, variousDataOneList);
			poolDetailBuilder.setVariousDataOne(variousDataOneList);

			List<StatsFactorSimple> variousDataTwoList = new ArrayList<>();
			StatsFactorSimpleHelper("paid_off", variousData, variousDataTwoList);
			StatsFactorSimpleHelper("repurchased_delinquent", variousData, variousDataTwoList);
			StatsFactorSimpleHelper("repurchased_lossMitigation", variousData, variousDataTwoList);
			StatsFactorSimpleHelper("repurchased_substitution", variousData, variousDataTwoList);
			StatsFactorSimpleHelper("other_removal_repurchased", variousData, variousDataTwoList);
			StatsFactorSimpleHelper("fha_buydown", variousData, variousDataTwoList);
			StatsFactorSimpleHelper("va_buydown", variousData, variousDataTwoList);
			StatsFactorSimpleHelper("thirty_days_delinquent", variousData, variousDataTwoList);
			StatsFactorSimpleHelper("sixty_days_delinquent", variousData, variousDataTwoList);
			StatsFactorSimpleHelper("ninety_plus_days_delinquent", variousData, variousDataTwoList);
			StatsFactorSimpleHelper("fha_thirty_days_delinquent", variousData, variousDataTwoList);
			StatsFactorSimpleHelper("fha_sixty_days_delinquent", variousData, variousDataTwoList);
			StatsFactorSimpleHelper("fha_ninety_plus_days_delinquent", variousData, variousDataTwoList);			
			StatsFactorSimpleHelper("va_thirty_days_delinquent", variousData, variousDataTwoList);
			StatsFactorSimpleHelper("va_sixty_days_delinquent", variousData, variousDataTwoList);
			StatsFactorSimpleHelper("va_ninety_plus_days_delinquent", variousData, variousDataTwoList);
			StatsFactorSimpleHelper("rd_thirty_days_delinquent", variousData, variousDataTwoList);
			StatsFactorSimpleHelper("rd_sixty_days_delinquent", variousData, variousDataTwoList);
			StatsFactorSimpleHelper("rd_ninety_plus_days_delinquent", variousData, variousDataTwoList);	
			StatsFactorSimpleHelper("pih_thirty_days_delinquent", variousData, variousDataTwoList);
			StatsFactorSimpleHelper("pih_sixty_days_delinquent", variousData, variousDataTwoList);
			StatsFactorSimpleHelper("pih_ninety_plus_days_delinquent", variousData, variousDataTwoList);			
			poolDetailBuilder.setVariousDataTwo(variousDataTwoList);
		}
		
		SupplementalDaily supplemental = poolDetailsDaily.getSupplemental();
		if(supplemental!=null){
			SupplementalOneType supplementalOne=SupplementalOneType.newBuilder()
					.setWeightedAverageCreditScore(supplemental.getCreditScore().getWeightedAverage())
					.setMaximumCreditScore(supplemental.getCreditScore().getMaximum())
					.setSeventyFivePercentCreditScore(supplemental.getCreditScore().getSeventyFivePercent())
					.setMedianCreditScore(supplemental.getCreditScore().getMedian())
					.setTwentyFivePercentCreditScore(supplemental.getCreditScore().getTwentyFivePercent())
					.setMinimumCreditScore(supplemental.getCreditScore().getMinimum())
					.setWeightedAverageDebtIncomeRatio(supplemental.getDebtIncomeRatio().getWeightedAverage().doubleValue())
					.setMaximumDebtIncomeRatio(supplemental.getDebtIncomeRatio().getMaximum().doubleValue())
					.setSeventyFivePercentDebtIncomeRatio(supplemental.getDebtIncomeRatio().getSeventyFivePercent().doubleValue())
					.setMedianDebtIncomeRatio(supplemental.getDebtIncomeRatio().getMedian().doubleValue())
					.setTwentyFivePercentDebtIncomeRatio(supplemental.getDebtIncomeRatio().getTwentyFivePercent().doubleValue())
					.setMinimumDebtIncomeRatio(supplemental.getDebtIncomeRatio().getMinimum().doubleValue())
					.build();
			poolDetailBuilder.setSupplementalOne(supplementalOne);
			List<StatsFactorSimple> supplementalTwoList = new ArrayList<>();
			StatsFactorSimpleHelper("debt_income_ratio_not_available", supplemental, supplementalTwoList);
			StatsFactorSimpleHelper("down_payment_assistance", supplemental, supplementalTwoList);
			StatsFactorSimpleHelper("without_payment_assistance", supplemental, supplementalTwoList);
			StatsFactorSimpleHelper("fha_purchase", supplemental, supplementalTwoList);
			StatsFactorSimpleHelper("fha_refinance", supplemental, supplementalTwoList);
			StatsFactorSimpleHelper("fha_hamp_modified", supplemental, supplementalTwoList);
			StatsFactorSimpleHelper("fha_purpose_not_available", supplemental, supplementalTwoList);
			StatsFactorSimpleHelper("va_purchase", supplemental, supplementalTwoList);
			StatsFactorSimpleHelper("va_refinance", supplemental, supplementalTwoList);
			StatsFactorSimpleHelper("va_hamp_modified", supplemental, supplementalTwoList);
			StatsFactorSimpleHelper("va_purpose_not_available", supplemental, supplementalTwoList);
			StatsFactorSimpleHelper("pih_purchase", supplemental, supplementalTwoList);
			StatsFactorSimpleHelper("pih_refinance", supplemental, supplementalTwoList);
			StatsFactorSimpleHelper("pih_hamp_modified", supplemental, supplementalTwoList);
			StatsFactorSimpleHelper("pih_purpose_not_available", supplemental, supplementalTwoList);
			StatsFactorSimpleHelper("rd_purchase", supplemental, supplementalTwoList);
			StatsFactorSimpleHelper("rd_refinance", supplemental, supplementalTwoList);
			StatsFactorSimpleHelper("rd_hamp_modified", supplemental, supplementalTwoList);
			StatsFactorSimpleHelper("rd_purpose_not_available", supplemental, supplementalTwoList);
			StatsFactorSimpleHelper("non_streamlined_refi", supplemental, supplementalTwoList);
			StatsFactorSimpleHelper("cash_out_refi", supplemental, supplementalTwoList);
			StatsFactorSimpleHelper("streamlined_refi", supplemental, supplementalTwoList);
			StatsFactorSimpleHelper("fha_short_refinance", supplemental, supplementalTwoList);
			StatsFactorSimpleHelper("refinance_type_not_available", supplemental, supplementalTwoList);
			poolDetailBuilder.setSupplementalTwo(supplementalTwoList);
		}
		
		
		if(poolDetailsDaily.getMultiIssuers()!=null && poolDetailsDaily.getMultiIssuers().size()>0){
			 List<MultiIssuerType> multiIssuers = new ArrayList<>();
			 for (MultiIssuer multipleIssuer: poolDetailsDaily.getMultiIssuers()){
				 MultiIssuerType MultiIssuerTypeRecord = MultiIssuerType.newBuilder()
						 .setIssuerNumber(new Utf8(multipleIssuer.getIssuerNumber()))
						 .setIssuerName(new Utf8(multipleIssuer.getIssuerName()))
						 .setNumberOfLoans(multipleIssuer.getIssuer().getNumberOfLoans())
						 .setIssuerUpbInMultipleIssuerPool(multipleIssuer.getIssuer().getUpbOfLoans().doubleValue())
						 .setIssuerPercentOfMultipleIssuerPool(multipleIssuer.getIssuer().getPercentOfPoolUpb().doubleValue()).build();
				 multiIssuers.add(MultiIssuerTypeRecord); 
			 }
			 poolDetailBuilder.setMultiIssusers(multiIssuers);

		}
		
		if(poolDetailsDaily.getRemovalsByIssuer()!=null && poolDetailsDaily.getRemovalsByIssuer().size()>0){
			 List<RemovalByIssuerType> removalsByIssuer = new ArrayList<>();
			 for (RemovalByIssuer removalByIssuer: poolDetailsDaily.getRemovalsByIssuer()){
				 RemovalByIssuerType removalByIsserTypeRecord = RemovalByIssuerType.newBuilder()
						 .setIssuer(removalByIssuer.getIssuer())
						 .setMortgagorPayoffNumberOfLoans(removalByIssuer.getMortgagorPayoff().getNumberOfLoans())
						 .setMortgagorPayoffUpbOfLoans(removalByIssuer.getMortgagorPayoff().getUpbOfLoans().doubleValue())
						 .setMortgagorPayoffPercentUpb(removalByIssuer.getMortgagorPayoff().getPercentOfPoolUpb().doubleValue())
						 
						 .setRepurchaseDelinquentNumberOfLoans(removalByIssuer.getRepurchaseDelinquent().getNumberOfLoans())
						 .setRepurchaseDelinquentUpbOfLoans(removalByIssuer.getRepurchaseDelinquent().getUpbOfLoans().doubleValue())
						 .setRepurchaseDelinquentPercentUpb(removalByIssuer.getRepurchaseDelinquent().getPercentOfPoolUpb().doubleValue())
						 .setForeclosureWithClaimPaymentNumberOfLoans(removalByIssuer.getForeclosureWithClaimPayment().getNumberOfLoans())
						 .setForeclosureWithClaimPaymentUpbOfLoans(removalByIssuer.getForeclosureWithClaimPayment().getUpbOfLoans().doubleValue())
						 .setForeclosureWithClaimPaymentPercentUpb(removalByIssuer.getForeclosureWithClaimPayment().getPercentOfPoolUpb().doubleValue())
						 .setRepurchaseLossMitigationNumberOfLoans(removalByIssuer.getRepurchaseLossMitigation().getNumberOfLoans())
						 .setRepurchaseLossMitigationUpbOfLoans(removalByIssuer.getRepurchaseLossMitigation().getUpbOfLoans().doubleValue())
						 .setRepurchaseLossMitigationPercentUpb(removalByIssuer.getRepurchaseLossMitigation().getPercentOfPoolUpb().doubleValue())
					     .setSubstitutionNumberOfLoans(removalByIssuer.getSubstitution().getNumberOfLoans())
						 .setSubstitutionUpbOfLoans(removalByIssuer.getSubstitution().getUpbOfLoans().doubleValue())
						 .setSubstitutionPercentUpb(removalByIssuer.getSubstitution().getPercentOfPoolUpb().doubleValue())
						 .setOtherRemovalNumberOfLoans(removalByIssuer.getOtherRemoval().getNumberOfLoans())
						 .setOtherRemovalUpbOfLoans(removalByIssuer.getOtherRemoval().getUpbOfLoans().doubleValue())
						 .setOtherRemovalPercentUpb(removalByIssuer.getOtherRemoval().getPercentOfPoolUpb().doubleValue())
						 
						 .build();
				 removalsByIssuer.add(removalByIsserTypeRecord); 
			 }
			 poolDetailBuilder.setRemovalsByIssuer(removalsByIssuer);

		}
		
		TransferActivity ta = poolDetailsDaily.getTransferActivity();
		if(ta!=null){
			TransferActivityType taType=TransferActivityType.newBuilder()
					.setTransferType(ta.getTransferType())
					.setSellingIssuerNumber(ta.getSellingIssuerNumber())
					.setBuyingIssuerNumber(ta.getBuyingIssuerNumber())
					.setNumberOfLoansTransferred(ta.getNumberOfLoansTransferred())
					.setUpbOfLoanTransferred(ta.getUpbOfLoansTransferred().doubleValue())
					.build();
			poolDetailBuilder.setTransferActivity(taType);
		}
		
		AdjustableRateMortgage arm = poolDetailsDaily.getAdjustableRateMortgage();
		if(arm!=null){
			ArmType armType=ArmType.newBuilder()
					.setLookBackPeriod(arm.getLookBackPeriod())
					.setIndexType(new Utf8(arm.getIndexType()))
					.setNextAdjustmentDate(getyyyyMMddFromDate(arm.getNextAdjustmentDate()))
					.setPriorAdjustmentDate(getyyyyMMddFromDate(arm.getPriorAdjustmentDate()))
					.setMonthsToAdjust(arm.getMonthsToAdjust())
					.setWeightedAverageMortgageMargin(arm.getWeightedAverageMortgageMargin().doubleValue())
					.setMaximumMortgageMargin(arm.getMaximumMortgageMargin().doubleValue())
					.setMinimumMortgageMargin(arm.getMinimumMortgageMargin().doubleValue())
					.setInitialInterestRateCap(arm.getInitialInterestRateCap())
					.setSubsequentInterestRateCap(arm.getSubsequentInterestRateCap())
					.setLifetimeInterestRateCap(arm.getLifetimeInterestRateCap())
					.setLifetimeInterestRateCeiling(arm.getLifetimeInterestRateCeiling().doubleValue())
					.setNextInterestRateCeiling(arm.getNextInterestRateCeiling().doubleValue())
					.setLifetimeInterestRateFloor(arm.getLifetimeInterestRateFloor().doubleValue())
					.build();
			poolDetailBuilder.setArm(armType);
		}
		
		GnmaDailyPoolDetail poolDetailRecord = poolDetailBuilder.setCusip(new Utf8(poolDetailsDaily.getCusip()))
    			.setPoolNumber(new Utf8(poolDetailsDaily.getPoolNumber()))
    			.setPoolIndicator(new Utf8(poolDetailsDaily.getPoolIndicator()))
    			.setPoolType(new Utf8(poolDetailsDaily.getPoolType()))
    			.setSecurityInterestRate(poolDetailsDaily.getSecurityInterestRate().doubleValue())
    			.setPoolIssueDate(getyyyyMMddFromDate(poolDetailsDaily.getPoolIssueDate()))
    			.setPoolMaturityDate(getyyyyMMddFromDate(poolDetailsDaily.getPoolMaturityDate()))
    			.setOriginalAggregateAmount(poolDetailsDaily.getOriginalAggregateAmount().doubleValue())
    			.setIssueNumber(poolDetailsDaily.getIssueNumber())
    			.setIssueName(new Utf8(poolDetailsDaily.getIssueName()))
    			.setSecurityMargin(poolDetailsDaily.getSecurityMargin().doubleValue())
    			.setInterestAdjustmentDate(getyyyyMMddFromDate(poolDetailsDaily.getInterestAdjustmentDate()))
    			.setPaymentAdjustmentDate(getyyyyMMddFromDate(poolDetailsDaily.getPaymentAdjustmentDate()))
    			.setNumberOfLoansInPool(poolDetailsDaily.getNumberOfLoansInPool())
    			.setAols(aolsRecord).setWac(wacRecord).setWarm(warmRecord).setWala(walaRecord)
    			.setWaolt(waoltRecord).setWagm(wagmRecord).setLtv(ltvRecord).setLtvNotAvailable(ltvNotAvailableRecord)
    			.setPurchase(purchaseRecord).setRefinance(refinanceRecord).setHampModified(hampModifiedRecord)
    			.setNonHampModified(nonHampModifiedRecord).setLoanPurposeNotAvailable(loanPurposeNotAvailableRecord)
    			.setCltv(cltvRecord).setWeightedAverageOriginalLoanSize(poolDetailsDaily.getWeightedAverageOriginalLoanSize().doubleValue())
    			.setLookBackPeriod(poolDetailsDaily.getLookBackPeriod())
    			.setOneUnit(oneUnitRecord).setOneToFourUnit(oneToFourUnitRecord)
    			.setPropertyTypeNotAvailable(propertyTypeNotAvailableRecord).setPoolUpb(poolDetailsDaily.getPoolUpb().doubleValue())
    			.setAsOfDate(this.asOfDate)
    			.build();		
    	
           writer.write(poolDetailRecord);		 
	}
	
	private void OriginationYearHelper(String key,
			OriginationYear originationYear,
			List<EntityStringStatsFactor> originationYearList) {
		StatsElement33 stats = null;
		String methodName = getMethodName(key, "\\_");
		try {
			Method method = originationYear.getClass().getMethod(methodName);
			stats = (StatsElement33) method.invoke(originationYear);
			EntityStringStatsFactor entityStringStatsFactor = EntityStringStatsFactor
					.newBuilder()
					.setEntityKey(new Utf8(key))
					.setEntityValue(new Utf8(stats.getEntityValue()))
					.setNumberOfLoans(stats.getNumberOfLoans())
					.setUpbOfLoans(stats.getUpb().doubleValue())
					.setPercentOfPoolUpb(stats.getPercentOfUpb().doubleValue()).build();
			originationYearList.add(entityStringStatsFactor);
		} catch (NoSuchMethodException | SecurityException
				| IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			e.printStackTrace(System.out);
		}

	}	
	
	private void MortgageInsuranceHelper(String key,
			MortgageInsurance insurance,
			List<EntityStatsFactor> mortgageInsurances) {
		StatsElement24 stats = null;
		String methodName = getMethodName(key, "\\_");
		try {
			Method method = insurance.getClass().getMethod(methodName);
			stats = (StatsElement24) method.invoke(insurance);
			EntityStatsFactor entityStatsFactor = EntityStatsFactor
					.newBuilder()
					.setEntityKey(new Utf8(key))
					.setEntityValue(null)
					.setNumberOfLoans(stats.getNumberOfLoans())
					.setUpbOfLoans(stats.getUpbOfLoans().doubleValue())
					.setPercentOfPoolUpb(
							stats.getPercentOfPoolUpb().doubleValue()).build();
			mortgageInsurances.add(entityStatsFactor);
		} catch (NoSuchMethodException | SecurityException
				| IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			e.printStackTrace(System.out);
		}

	}
	
	private void MsaHelper(String key, Msa msa, List<EntityStatsFactor> msaList) {
		StatsElement24 stats24 = null;
		StatsElement29 stats29 = null;
		String methodName = getMethodName(key, "\\_");
		try {
			EntityStatsFactor entityStatsFactor = null;
			Method method = msa.getClass().getMethod(methodName);
			if (methodName.equals("getMsaNotAvailable")) {
				stats24 = (StatsElement24) method.invoke(msa);
				entityStatsFactor = EntityStatsFactor
						.newBuilder()
						.setEntityKey(new Utf8(key))
						.setEntityValue(null)
						.setNumberOfLoans(stats24.getNumberOfLoans())
						.setUpbOfLoans(stats24.getUpbOfLoans().doubleValue())
						.setPercentOfPoolUpb(
								stats24.getPercentOfPoolUpb().doubleValue())
						.build();

			} else {
				stats29 = (StatsElement29) method.invoke(msa);
				entityStatsFactor = EntityStatsFactor
						.newBuilder()
						.setEntityKey(new Utf8(key))
						.setEntityValue(stats29.getElement())
						.setNumberOfLoans(stats29.getNumberOfLoans())
						.setUpbOfLoans(stats29.getUptOfLoans().doubleValue())
						.setPercentOfPoolUpb(
								stats29.getPercentOfPoolUpb().doubleValue())
						.build();
			}
			msaList.add(entityStatsFactor);
		} catch (NoSuchMethodException | SecurityException
				| IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace(System.out);
		}
	}
	
	private void VariousDataOneHelper(String key, VariousData variousData, List<VariousDataOneType> oneList) {
		StatsElement42 stats42 = null;

		String methodName = getMethodName(key, "\\_");
		try {
			VariousDataOneType variousDataOne = null;
			Method method = variousData.getClass().getMethod(methodName);
			stats42 = (StatsElement42) method.invoke(variousData);
			variousDataOne = VariousDataOneType
					.newBuilder()
					.setElementName(new Utf8(key))
					.setPoolUpb(stats42.getPoolUpb().doubleValue())
					.setPercentOfPoolUpb(stats42.getUpbAsAPercentOfTotalPool().doubleValue())
					.setNumberOfLoans(stats42.getNumberOfLoans())
					.setNumberOfLoansPercent(stats42.getNumberOfLoansAsAPercentOfTotalLoans().doubleValue())
					.setOriginalLoanBalance(stats42.getOriginalLoanBalance().doubleValue())
					.build();
			oneList.add(variousDataOne);
		} catch (NoSuchMethodException | SecurityException
				| IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace(System.out);
		}
	}
	
	private void StatsFactorSimpleHelper(String key, Object pojo, List<StatsFactorSimple> statsFactorSimpleList) {
		StatsElement24 stats24 = null;

		String methodName = getMethodName(key, "\\_");
		try {

			StatsFactorSimple variousDataTwo = null;
			Method method = pojo.getClass().getMethod(methodName);
			stats24 = (StatsElement24) method.invoke(pojo);
			variousDataTwo = StatsFactorSimple
					.newBuilder()
					.setElementName(new Utf8(key))
					.setNumberOfLoans(stats24.getNumberOfLoans())
					.setUpbOfLoans(stats24.getUpbOfLoans().doubleValue())
					.setPercentOfPoolUpb(stats24.getPercentOfPoolUpb().doubleValue())
					.build();
			statsFactorSimpleList.add(variousDataTwo);
		} catch (NoSuchMethodException | SecurityException
				| IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace(System.out);
		}
	}
	
	private void PreModificationTwoHelper(String key, Object pojo, List<PreModificationTwoType> preModificationTwoList) {
		StatsItem29 stats29 = null;

		String methodName = getMethodName(key, "\\_");
		try {

			PreModificationTwoType preModifiedTwo = null;
			Method method = pojo.getClass().getMethod(methodName);
			stats29 = (StatsItem29) method.invoke(pojo);
			preModifiedTwo = PreModificationTwoType
					.newBuilder()
					.setElementName(new Utf8(key))
					.setUpb(stats29.getUpb().doubleValue())
					.setPercentOfPoolUpb(stats29.getPercentOfPoolUpb().doubleValue())
					.setNumberOfLoans(stats29.getNumberOfLoans())
					.setPercentOfPoolLoansOfKind(stats29.getPercentOfPoolLoansOfKind().doubleValue())
					.build();
			preModificationTwoList.add(preModifiedTwo);
		} catch (NoSuchMethodException | SecurityException
				| IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace(System.out);
		}
	}

}
