package com.yieldbook.mortgage.bigdata.utility;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

import com.yieldbook.mortgage.domain.fnma.ArmPoolMonthly;
import com.yieldbook.mortgage.domain.fnma.FirstPaymentDateMonthly;
import com.yieldbook.mortgage.domain.fnma.NextRateChangeDateDaily;
import com.yieldbook.mortgage.domain.fnma.NextRateChangeDateMonthly;
import com.yieldbook.mortgage.domain.fnma.PoolDailyElement;
import com.yieldbook.mortgage.domain.fnma.PoolDailyQuartile;
import com.yieldbook.mortgage.domain.fnma.PoolMonthlyInterestOnly;
import com.yieldbook.mortgage.domain.fnma.PoolMonthlyQuartile;
import com.yieldbook.mortgage.domain.fnma.PoolMonthlyServicer;
import com.yieldbook.mortgage.domain.fnma.PoolMonthlyStats;
import com.yieldbook.mortgage.domain.fnma.PoolMonthlySupplement;
import com.yieldbook.mortgage.domain.fnma.PoolMonthlyThirdPartyOriginationType;
import com.yieldbook.mortgage.domain.fnma.WaForNextRateChangeDateDaily;
import com.yieldbook.mortgage.domain.fnma.csv.FnmaPoolStatistics;
import com.yieldbook.mortgage.domain.gnma.PoolDetailsDaily;
import com.yieldbook.mortgage.domain.gnma.StatsElement24;

public class GenerateGnmaAvroFromClassMemberFields {

	public static void main(String[] args) throws Exception {

		FnmaPoolStatistics abc = new FnmaPoolStatistics();

		System.out.println("_______________________________________________________");
		System.out.println(generateStatsElement24());
		System.out.println("_______________________________________________________");
		System.out.println(generatePoolDetailsDaily());
		
	}
	
	static String convertType(String classType){
       String type = new String("");;
		switch(classType){
		case "class java.lang.String":
			type="string";
			break;
		case "class java.math.BigDecimal":
			type="double";
			break;				
		case "class java.util.Date":
			type="int";
			break;			
		case "class java.lang.Integer":
			type="int";
			break;
		case "class java.lang.Long":
			type="long";
			break;
		case "int":
			type="int";
			break;				
		default:
			String[] types=classType.split("\\.");
			if (types.length>1)
				type=types[types.length-1];
			else
				type=classType;
			break;
		
		}
	   return type;
	
	}
	
	static int  firstUpperIndex(String str)
    {
        for (int i = 0; i < str.length(); i++)
            if (Character.isUpperCase(str.charAt(i))&&(i!=0))
                return i;
        return 0;
    }
	
	static int  SecondUpperIndex(String str, int firstUpperIndex)
    {
        for (int i = firstUpperIndex+1; i < str.length(); i++)
            if (Character.isUpperCase(str.charAt(i)))
                return i;
        return 0;
    }
	
	
	
	static String converString(String input){
		int index = firstUpperIndex(input);
		
		if(index==0){
			return input;
		}else{
			String first = input.substring(0, index).toLowerCase();
			String second = input.substring(index, input.length());
			second = second.substring(0,1).toLowerCase()+second.substring(1,second.length());
			if(first.length()==1)
				return first+ converString(second);
			else
				return first+ "_" + converString(second);
		}
				
	}
	

	static String generateItem(){
		StringBuilder sb = new StringBuilder();
		Set<String> dic =new HashSet<>();
		dic.add("pool_number");
		dic.add("record_type");
		
		Set<String> dic1 =new HashSet<>();
		dic1.add("number_of_loans");
		dic1.add("percent_of_upb");
		dic1.add("aggregate_upb");
		
		PoolDailyElement def = new PoolDailyElement();
		
		sb.append("{\n\"type\": \"record\",\n\"name\": \"element\",\n");
		sb.append("\"fields\": [{\n");
		for (Field field : def.getClass().getDeclaredFields()){
			field.setAccessible(true);
			String name = field.getName();
			name = converString(name);
			String classType = field.getType().toString();
			String type=convertType(classType);
		
			if (!dic.contains(name)) {
				if(!name.equals("aggregate_upb")){
					
					if(!dic1.contains(name) || type.equals("string")){
					
					sb.append("\t\"name\" : \"").append(name).append("\", ")
						.append("\t\"type\" : \"").append(type)
						.append("\" \n").append("}, {");
					}else{
						sb.append("\"name\" : \"").append(name).append("\",\n")
						.append("\t\"type\" : [ \"null\", \"").append(type)
						.append("\" ]\n").append("}, {");
					}
				}else{
					sb.append("\t\"name\" : \"").append(name).append("\", ")
					.append("\t\"type\" : [ \"null\", \"").append(type)
					.append("\" ]\n").append("} ]\n}\n");
					
				}
			} 
		}
		
		return sb.toString();
	}
	
	static String generateQuartile(){
		StringBuilder sb = new StringBuilder();
		Set<String> dic =new HashSet<>();
		dic.add("pool_number");
		dic.add("record_type");
		
		Set<String> dic1 =new HashSet<>();
		dic1.add("number_of_loans");
		dic1.add("percent_of_upb");
		dic1.add("aggregate_upb");
		
		PoolDailyQuartile def = new PoolDailyQuartile();
		
		sb.append("{\n\"type\": \"record\",\n\"name\": \"quartile\",\n");
		sb.append("\"fields\": [{\n");
		for (Field field : def.getClass().getDeclaredFields()){
			field.setAccessible(true);
			String name = field.getName();
			name = converString(name);
			String classType = field.getType().toString();
			String type=convertType(classType);
		
			if (!dic.contains(name)) {
				if(!name.equals("remaining_maturity")){
					
					if(type.equals("string")){
					
					sb.append("\t\"name\" : \"").append(name).append("\", ")
						.append("\t\"type\" : \"").append(type)
						.append("\" \n").append("}, {");
					}else{
						sb.append("\"name\" : \"").append(name).append("\",\n")
						.append("\t\"type\" : [ \"null\", \"").append(type)
						.append("\" ]\n").append("}, {");
					}
				}else{
					sb.append("\t\"name\" : \"").append(name).append("\", ")
					.append("\t\"type\" : [ \"null\", \"").append(type)
					.append("\" ]\n").append("} ]\n}\n");
					
				}
			} 
		}
		
		return sb.toString();
	}
	
	static String generateNextRateChangeDate(){
		StringBuilder sb = new StringBuilder();
		Set<String> dic =new HashSet<>();
		dic.add("pool_number");
		dic.add("record_type");
		
		Set<String> dic1 =new HashSet<>();
		dic1.add("number_of_loans");
		dic1.add("percent_of_upb");
		dic1.add("aggregate_upb");
		
		NextRateChangeDateDaily def = new NextRateChangeDateDaily();
		
		sb.append("{\n\"type\": \"record\",\n\"name\": \"next_rate_change_date\",\n");
		sb.append("\"fields\": [{\n");
		for (Field field : def.getClass().getDeclaredFields()){
			field.setAccessible(true);
			String name = field.getName();
			name = converString(name);
			String classType = field.getType().toString();
			String type=convertType(classType);
		
			if (!dic.contains(name)) {
				if(!name.equals("net_life_floor_low")){
					
					if(type.equals("string")){
					
					sb.append("\t\"name\" : \"").append(name).append("\", ")
						.append("\t\"type\" : \"").append(type)
						.append("\" \n").append("}, {");
					}else{
						sb.append("\"name\" : \"").append(name).append("\",\n")
						.append("\t\"type\" : [ \"null\", \"").append(type)
						.append("\" ]\n").append("}, {");
					}
				}else{
					sb.append("\t\"name\" : \"").append(name).append("\", ")
					.append("\t\"type\" : [ \"null\", \"").append(type)
					.append("\" ]\n").append("} ]\n}\n");
					
				}
			} 
		}
		
		return sb.toString();
	}	

	static String generateWAForNextRateChangeDate(){
		StringBuilder sb = new StringBuilder();
		Set<String> dic =new HashSet<>();
		dic.add("pool_number");
		dic.add("record_type");
		
		Set<String> dic1 =new HashSet<>();
		dic1.add("number_of_loans");
		dic1.add("percent_of_upb");
		dic1.add("aggregate_upb");
		
		WaForNextRateChangeDateDaily def = new WaForNextRateChangeDateDaily();
		
		sb.append("{\n\"type\": \"record\",\n\"name\": \"wa_for_next_rate_change_date\",\n");
		sb.append("\"fields\": [{\n");
		for (Field field : def.getClass().getDeclaredFields()){
			field.setAccessible(true);
			String name = field.getName();
			name = converString(name);
			String classType = field.getType().toString();
			String type=convertType(classType);
		
			if (!dic.contains(name)) {
				if(!name.equals("wa_net_life_floor")){
					
					if(type.equals("string")){
					
					sb.append("\t\"name\" : \"").append(name).append("\", ")
						.append("\t\"type\" : \"").append(type)
						.append("\" \n").append("}, {");
					}else{
						sb.append("\"name\" : \"").append(name).append("\",\n")
						.append("\t\"type\" : [ \"null\", \"").append(type)
						.append("\" ]\n").append("}, {");
					}
				}else{
					sb.append("\t\"name\" : \"").append(name).append("\", ")
					.append("\t\"type\" : [ \"null\", \"").append(type)
					.append("\" ]\n").append("} ]\n}\n");
					
				}
			} 
		}
		
		return sb.toString();
	}
	
	static String generateFnmaPoolMonthly(){
		StringBuilder sb = new StringBuilder();
		Set<String> dic =new HashSet<>();
		dic.add("filler");
		
		Set<String> dic1 =new HashSet<>();
		dic1.add("cusip");
		dic1.add("pool_prefix");
		dic1.add("pool_number");
		
		PoolMonthlyStats def = new PoolMonthlyStats();
		
		sb.append("{\n\"type\": \"record\",\n\"name\": \"fnma_pool_monthly\",\n");
		sb.append("\"fields\": [{\n");
		for (Field field : def.getClass().getDeclaredFields()){
			field.setAccessible(true);
			String name = field.getName();
			name = converString(name);
			String classType = field.getType().toString();
			String type=convertType(classType);
		
			if (!dic.contains(name)) {
				if(!name.equals("current_wamaturity")){
					
					if(dic1.contains(name) || type.equals("string")){
					
					sb.append("\t\"name\" : \"").append(name).append("\", ")
						.append("\t\"type\" : \"").append(type)
						.append("\" \n").append("}, {");
					}else{
						sb.append("\"name\" : \"").append(name).append("\",\n")
						.append("\t\"type\" : [ \"null\", \"").append(type)
						.append("\" ]\n").append("}, {");
					}
				}else{
					sb.append("\t\"name\" : \"").append(name).append("\", ")
					.append("\t\"type\" : [ \"null\", \"").append(type)
					.append("\" ]\n").append("} ]\n}\n");
					
				}
			} 
		}
		
		return sb.toString();
	}
	
	static String generateFnmaPoolNextRateChangeDateMonthly(){
		StringBuilder sb = new StringBuilder();
		Set<String> dic =new HashSet<>();
		dic.add("record_type");
		dic.add("filler");
		dic.add("zero_filled");
		dic.add("pool_number");
		dic.add("pool_prefix");
		
		Set<String> dic1 =new HashSet<>();
		dic1.add("pool_number");
		dic1.add("pool_prefix");
		
		NextRateChangeDateMonthly def = new NextRateChangeDateMonthly();
		
		sb.append("{\n\"type\": \"record\",\n\"name\": \"fnma_next_rate_change_date_monthly\",\n");
		sb.append("\"fields\": [{\n");
		for (Field field : def.getClass().getDeclaredFields()){
			field.setAccessible(true);
			String name = field.getName();
			name = converString(name);
			String classType = field.getType().toString();
			String type=convertType(classType);
		
			if (!dic.contains(name)) {
				if(!name.equals("loan_count")){
					
					if(dic1.contains(name) || type.equals("string")){
					
					sb.append("\t\"name\" : \"").append(name).append("\", ")
						.append("\t\"type\" : \"").append(type)
						.append("\" \n").append("}, {");
					}else{
						sb.append("\"name\" : \"").append(name).append("\",\n")
						.append("\t\"type\" : [ \"null\", \"").append(type)
						.append("\" ]\n").append("}, {");
					}
				}else{
					sb.append("\t\"name\" : \"").append(name).append("\", ")
					.append("\t\"type\" : [ \"null\", \"").append(type)
					.append("\" ]\n").append("} ]\n}\n");
					
				}
			} 
		}
		
		return sb.toString();
	}	
	
	static String generateFnmaPoolFirstPaymentDateMonthly(){
		StringBuilder sb = new StringBuilder();
		Set<String> dic =new HashSet<>();
		dic.add("record_type");
		dic.add("filler");
		dic.add("pool_number");
		dic.add("pool_prefix");
		
		Set<String> dic1 =new HashSet<>();
		dic1.add("pool_number");
		dic1.add("pool_prefix");
		
		FirstPaymentDateMonthly def = new FirstPaymentDateMonthly();
		
		sb.append("{\n\"type\": \"record\",\n\"name\": \"fnma_first_payment_date_monthly\",\n");
		sb.append("\"fields\": [{\n");
		for (Field field : def.getClass().getDeclaredFields()){
			field.setAccessible(true);
			String name = field.getName();
			name = converString(name);
			String classType = field.getType().toString();
			String type=convertType(classType);
		
			if (!dic.contains(name)) {
				if(!name.equals("loan_survival_ratio")){
					
					if(dic1.contains(name) || type.equals("string")){
					
					sb.append("\t\"name\" : \"").append(name).append("\", ")
						.append("\t\"type\" : \"").append(type)
						.append("\" \n").append("}, {");
					}else{
						sb.append("\"name\" : \"").append(name).append("\",\n")
						.append("\t\"type\" : [ \"null\", \"").append(type)
						.append("\" ]\n").append("}, {");
					}
				}else{
					sb.append("\t\"name\" : \"").append(name).append("\", ")
					.append("\t\"type\" : [ \"null\", \"").append(type)
					.append("\" ]\n").append("} ]\n}\n");
					
				}
			} 
		}
		
		return sb.toString();
	}
	
	static String generateFnmaArmPoolMonthly(){
		StringBuilder sb = new StringBuilder();
		Set<String> dic =new HashSet<>();
		dic.add("record_type");
		dic.add("filler1");
		dic.add("filler2");
		dic.add("pool_number");
		dic.add("pool_prefix");
		
		Set<String> dic1 =new HashSet<>();
		dic1.add("pool_number");
		dic1.add("pool_prefix");
		
		ArmPoolMonthly def = new ArmPoolMonthly();
		
		sb.append("{\n\"type\": \"record\",\n\"name\": \"fnma_arm_pool_monthly\",\n");
		sb.append("\"fields\": [{\n");
		for (Field field : def.getClass().getDeclaredFields()){
			field.setAccessible(true);
			String name = field.getName();
			name = converString(name);
			String classType = field.getType().toString();
			String type=convertType(classType);
		
			if (!dic.contains(name)) {
				if(!name.equals("first_payment_date_monthly")){
					
					if(dic1.contains(name) || type.equals("string")){
					
					sb.append("\t\"name\" : \"").append(name).append("\", ")
						.append("\t\"type\" : \"").append(type)
						.append("\" \n").append("}, {");
					}else{
						sb.append("\"name\" : \"").append(name).append("\",\n")
						.append("\t\"type\" : [ \"null\", \"").append(type)
						.append("\" ]\n").append("}, {");
					}
				}else{
					sb.append("\t\"name\" : \"").append(name).append("\", ")
					.append("\t\"type\" : [ \"null\", \"").append(type)
					.append("\" ]\n").append("} ]\n}\n");
					
				}
			} 
		}
		
		return sb.toString();
	}	
	
	static String generateFnmaPoolSupplementMonthly(){
		StringBuilder sb = new StringBuilder();
		Set<String> dic =new HashSet<>();
		dic.add("record_type");
		dic.add("filler");
		dic.add("quartile");
		dic.add("servicers");
		dic.add("third_party_origination_type");
		dic.add("interest_only");
		
		Set<String> dic1 =new HashSet<>();
		dic1.add("pool_number");
		dic1.add("pool_prefix");
		
		PoolMonthlySupplement def = new PoolMonthlySupplement();
		
		sb.append("{\n\"type\": \"record\",\n\"name\": \"fnma_pool_supplement_monthly\",\n");
		sb.append("\"fields\": [{\n");
		for (Field field : def.getClass().getDeclaredFields()){
			field.setAccessible(true);
			String name = field.getName();
			name = converString(name);
			String classType = field.getType().toString();
			String type=convertType(classType);
		
			if (!dic.contains(name)) {
				if(!name.equals("servicer_name")){
					
					if(dic1.contains(name) || type.equals("string")){
					
					sb.append("\t\"name\" : \"").append(name).append("\", ")
						.append("\t\"type\" : \"").append(type)
						.append("\" \n").append("}, {");
					}else{
						sb.append("\"name\" : \"").append(name).append("\",\n")
						.append("\t\"type\" : [ \"null\", \"").append(type)
						.append("\" ]\n").append("}, {");
					}
				}else{
					if(!type.equals("string")){
						sb.append("\t\"name\" : \"").append(name).append("\", ")
						.append("\t\"type\" : [ \"null\", \"").append(type)
						.append("\" ]\n").append("} ]\n}\n");
					}else{
						sb.append("\t\"name\" : \"").append(name).append("\", ")
						.append("\t\"type\" : \"").append(type)
						.append("\" \n").append("} ]\n}\n");					
					}
					
				}
			} 
		}
		
		return sb.toString();
	}
	
	static String generateFnmaPoolQuartileMonthly(){
		StringBuilder sb = new StringBuilder();
		Set<String> dic =new HashSet<>();
		dic.add("record_type");
		dic.add("filler");
		
		Set<String> dic1 =new HashSet<>();
		dic1.add("pool_number");
		
		PoolMonthlyQuartile def = new PoolMonthlyQuartile();
		
		sb.append("{\n\"type\": \"record\",\n\"name\": \"fnma_pool_quartile_monthly\",\n");
		sb.append("\"fields\": [{\n");
		for (Field field : def.getClass().getDeclaredFields()){
			field.setAccessible(true);
			String name = field.getName();
			name = converString(name);
			String classType = field.getType().toString();
			String type=convertType(classType);
		
			if (!dic.contains(name)) {
				if(!name.equals("investment_loan_count")){
					
					if(dic1.contains(name) || type.equals("string")){
					
					sb.append("\t\"name\" : \"").append(name).append("\", ")
						.append("\t\"type\" : \"").append(type)
						.append("\" \n").append("}, {");
					}else{
						sb.append("\"name\" : \"").append(name).append("\",\n")
						.append("\t\"type\" : [ \"null\", \"").append(type)
						.append("\" ]\n").append("}, {");
					}
				}else{
					if(!type.equals("string")){
						sb.append("\t\"name\" : \"").append(name).append("\", ")
						.append("\t\"type\" : [ \"null\", \"").append(type)
						.append("\" ]\n").append("} ]\n}\n");
					}else{
						sb.append("\t\"name\" : \"").append(name).append("\", ")
						.append("\t\"type\" : \"").append(type)
						.append("\" \n").append("} ]\n}\n");					
					}
					
				}
			} 
		}
		
		return sb.toString();
	}
	
	static String generateFnmaPoolServicerMonthly(){
		StringBuilder sb = new StringBuilder();
		Set<String> dic =new HashSet<>();
		dic.add("record_type");
		dic.add("filler");

		
		Set<String> dic1 =new HashSet<>();
		dic1.add("pool_number");
		
		PoolMonthlyServicer def = new PoolMonthlyServicer();
		
		sb.append("{\n\"type\": \"record\",\n\"name\": \"fnma_pool_servicer_monthly\",\n");
		sb.append("\"fields\": [{\n");
		for (Field field : def.getClass().getDeclaredFields()){
			field.setAccessible(true);
			String name = field.getName();
			name = converString(name);
			String classType = field.getType().toString();
			String type=convertType(classType);
		
			if (!dic.contains(name)) {
				if(!name.equals("loan_count")){
					
					if(dic1.contains(name) || type.equals("string")){
					
					sb.append("\t\"name\" : \"").append(name).append("\", ")
						.append("\t\"type\" : \"").append(type)
						.append("\" \n").append("}, {");
					}else{
						sb.append("\"name\" : \"").append(name).append("\",\n")
						.append("\t\"type\" : [ \"null\", \"").append(type)
						.append("\" ]\n").append("}, {");
					}
				}else{
					if(!type.equals("string")){
						sb.append("\t\"name\" : \"").append(name).append("\", ")
						.append("\t\"type\" : [ \"null\", \"").append(type)
						.append("\" ]\n").append("} ]\n}\n");
					}else{
						sb.append("\t\"name\" : \"").append(name).append("\", ")
						.append("\t\"type\" : \"").append(type)
						.append("\" \n").append("} ]\n}\n");					
					}
					
				}
			} 
		}
		
		return sb.toString();
	}
	
	static String generateThirdPartyOriginationTypeMonthly(){
		StringBuilder sb = new StringBuilder();
		Set<String> dic =new HashSet<>();
		dic.add("record_type");
		dic.add("filler");

		
		Set<String> dic1 =new HashSet<>();
		dic1.add("pool_number");
		
		PoolMonthlyThirdPartyOriginationType def = new PoolMonthlyThirdPartyOriginationType();
		
		sb.append("{\n\"type\": \"record\",\n\"name\": \"fnma_third_party_origination_type_monthly\",\n");
		sb.append("\"fields\": [{\n");
		for (Field field : def.getClass().getDeclaredFields()){
			field.setAccessible(true);
			String name = field.getName();
			name = converString(name);
			String classType = field.getType().toString();
			String type=convertType(classType);
		
			if (!dic.contains(name)) {
				if(!name.equals("retail_upb_percent")){
					
					if(dic1.contains(name) || type.equals("string")){
					
					sb.append("\t\"name\" : \"").append(name).append("\", ")
						.append("\t\"type\" : \"").append(type)
						.append("\" \n").append("}, {");
					}else{
						sb.append("\"name\" : \"").append(name).append("\",\n")
						.append("\t\"type\" : [ \"null\", \"").append(type)
						.append("\" ]\n").append("}, {");
					}
				}else{
					if(!type.equals("string")){
						sb.append("\t\"name\" : \"").append(name).append("\", ")
						.append("\t\"type\" : [ \"null\", \"").append(type)
						.append("\" ]\n").append("} ]\n}\n");
					}else{
						sb.append("\t\"name\" : \"").append(name).append("\", ")
						.append("\t\"type\" : \"").append(type)
						.append("\" \n").append("} ]\n}\n");					
					}
					
				}
			} 
		}
		
		return sb.toString();
	}
	
	static String generateInterestOnlyMonthly(){
		StringBuilder sb = new StringBuilder();
		Set<String> dic =new HashSet<>();
		dic.add("record_type");

		Set<String> dic1 =new HashSet<>();
		dic1.add("pool_number");
		
		PoolMonthlyInterestOnly def = new PoolMonthlyInterestOnly();
		
		sb.append("{\n\"type\": \"record\",\n\"name\": \"fnma_interest_only_monthly\",\n");
		sb.append("\"fields\": [{\n");
		for (Field field : def.getClass().getDeclaredFields()){
			field.setAccessible(true);
			String name = field.getName();
			name = converString(name);
			String classType = field.getType().toString();
			String type=convertType(classType);
		
			if (!dic.contains(name)) {
				if(!name.equals("comment")){
					
					if(dic1.contains(name) || type.equals("string")){
					
					sb.append("\t\"name\" : \"").append(name).append("\", ")
						.append("\t\"type\" : \"").append(type)
						.append("\" \n").append("}, {");
					}else{
						sb.append("\"name\" : \"").append(name).append("\",\n")
						.append("\t\"type\" : [ \"null\", \"").append(type)
						.append("\" ]\n").append("}, {");
					}
				}else{
					if(!type.equals("string")){
						sb.append("\t\"name\" : \"").append(name).append("\", ")
						.append("\t\"type\" : [ \"null\", \"").append(type)
						.append("\" ]\n").append("} ]\n}\n");
					}else{
						sb.append("\t\"name\" : \"").append(name).append("\", ")
						.append("\t\"type\" : \"").append(type)
						.append("\" \n").append("} ]\n}\n");					
					}
					
				}
			} 
		}
		
		return sb.toString();
	}	
	
	
	static String generateStatsElement24(){
		StringBuilder sb = new StringBuilder();
		Set<String> dic =new HashSet<>();
		dic.add("record_type");

		Set<String> dic1 =new HashSet<>();
		dic1.add("pool_number");
		
		StatsElement24 def = new StatsElement24();
		
		sb.append("{\n\"type\": \"record\",\n\"name\": \"stats_element\",\n");
		sb.append("\"fields\": [{\n");
		for (Field field : def.getClass().getDeclaredFields()){
			field.setAccessible(true);
			String name = field.getName();
			name = converString(name);
			String classType = field.getType().toString();
			String type=convertType(classType);
		
			if (!dic.contains(name)) {
				if(!name.equals("percent_of_pool_upb")){
					
					if(dic1.contains(name) || type.equals("string")){
					
					sb.append("\t\"name\" : \"").append(name).append("\", ")
						.append("\t\"type\" : \"").append(type)
						.append("\" \n").append("}, {");
					}else{
						sb.append("\"name\" : \"").append(name).append("\",\n")
						.append("\t\"type\" : [ \"null\", \"").append(type)
						.append("\" ]\n").append("}, {");
					}
				}else{
					if(!type.equals("string")){
						sb.append("\t\"name\" : \"").append(name).append("\", ")
						.append("\t\"type\" : [ \"null\", \"").append(type)
						.append("\" ]\n").append("} ]\n}\n");
					}else{
						sb.append("\t\"name\" : \"").append(name).append("\", ")
						.append("\t\"type\" : \"").append(type)
						.append("\" \n").append("} ]\n}\n");					
					}
					
				}
			} 
		}
		
		return sb.toString();
	}	
	
	static String generatePoolDetailsDaily(){
		StringBuilder sb = new StringBuilder();
		Set<String> dic =new HashSet<>();
		dic.add("record_type");
		dic.add("filler1");
		dic.add("filler2");
		dic.add("filler3");
		dic.add("filler4");
		dic.add("filler5");
		dic.add("filler6");
		dic.add("filler7");
		dic.add("filler8");

		Set<String> dic1 =new HashSet<>();
		dic1.add("pool_number");
		dic1.add("cusip");
		dic1.add("pool_indicator");
		dic1.add("pool_type");
		dic1.add("as_of_date");
		
		PoolDetailsDaily def = new PoolDetailsDaily();
		
		sb.append("{\n\"type\": \"record\",\n\"name\": \"gnma_pool_daily\",\n");
		sb.append("\"fields\": [{\n");
		for (Field field : def.getClass().getDeclaredFields()){
			field.setAccessible(true);
			String name = field.getName();
			name = converString(name);
			String classType = field.getType().toString();
			String type=convertType(classType);
		
			if (!dic.contains(name)) {
				if(!name.equals("as_of_date")){
					
					if(dic1.contains(name) || type.equals("string")){
					
					sb.append("\t\"name\" : \"").append(name).append("\", ")
						.append("\t\"type\" : \"").append(type)
						.append("\" \n").append("}, {");
					}else{
						sb.append("\"name\" : \"").append(name).append("\",\n")
						.append("\t\"type\" : [ \"null\", \"").append(type)
						.append("\" ]\n").append("}, {");
					}
				}else{
					if(!type.equals("string")){
						sb.append("\t\"name\" : \"").append(name).append("\", ")
						.append("\t\"type\" : [ \"null\", \"").append(type)
						.append("\" ]\n").append("} ]\n}\n");
					}else{
						sb.append("\t\"name\" : \"").append(name).append("\", ")
						.append("\t\"type\" : \"").append(type)
						.append("\" \n").append("} ]\n}\n");					
					}
					
				}
			} 
		}
		
		return sb.toString();
	}	
}
