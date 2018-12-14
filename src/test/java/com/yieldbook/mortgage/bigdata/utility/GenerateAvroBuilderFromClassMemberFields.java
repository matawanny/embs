package com.yieldbook.mortgage.bigdata.utility;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

import com.yieldbook.mortgage.domain.fnma.PoolMonthlyInterestOnly;
import com.yieldbook.mortgage.domain.fnma.PoolMonthlyQuartile;
import com.yieldbook.mortgage.domain.fnma.PoolMonthlyServicer;
import com.yieldbook.mortgage.domain.fnma.PoolMonthlySupplement;
import com.yieldbook.mortgage.domain.fnma.PoolMonthlyThirdPartyOriginationType;
import com.yieldbook.mortgage.domain.fnma.csv.FnmaPoolStatistics;

public class GenerateAvroBuilderFromClassMemberFields {

	public static void main(String[] args) throws Exception {

		FnmaPoolStatistics abc = new FnmaPoolStatistics();

/*		System.out.println(generateItem());

		System.out.println("_______________________________________________________");
		
		System.out.println(generateQuartile());
		System.out.println("_______________________________________________________");
		
		System.out.println(generateNextRateChangeDate());
		System.out.println("_______________________________________________________");
		
		System.out.println(generateWAForNextRateChangeDate());
		
		System.out.println("_______________________________________________________");
		
		System.out.println(generateFnmaPoolMonthly());			
		
		System.out.println("_______________________________________________________");
		System.out.println(generateFnmaPoolNextRateChangeDateMonthly());
		
		System.out.println("_______________________________________________________");
		System.out.println(generateFnmaPoolFirstPaymentDateMonthly());
		
		System.out.println("_______________________________________________________");
		System.out.println(generateFnmaArmPoolMonthly());		*/
		
		System.out.println("_______________________________________________________");
		System.out.println(generateFnmaPoolSupplementMonthly());
		
		System.out.println("_______________________________________________________");
		System.out.println(generateFnmaPoolQuartileMonthly());			
		System.out.println("_______________________________________________________");
		System.out.println(generateFnmaPoolServicerMonthly());			
		System.out.println("_______________________________________________________");
		System.out.println(generateThirdPartyOriginationTypeMonthly());
		System.out.println("_______________________________________________________");
		System.out.println(generateInterestOnlyMonthly());		
		
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
	static String buildMethodName(String className, String fiendName) {
		String instant = className.substring(0,1).toLowerCase()+className.substring(1,className.length());
		String[] elements = instant.split("\\.");
		String instantName=elements[elements.length-1];
		String instantName1=instantName.substring(0,1).toLowerCase()+instantName.substring(1,instantName.length());
		
		String fieldName1 = fiendName.substring(0,1).toUpperCase()+fiendName.substring(1,fiendName.length());
		String methodName = instantName1.concat(".get").concat(fieldName1).concat("()");
		return methodName;
	}
	
	static String generateFnmaPoolSupplementMonthly(){
		StringBuilder sb = new StringBuilder();
		Set<String> dic =new HashSet<>();
		dic.add("record_type");

		dic.add("filler");
/*		dic.add("quartile");
		dic.add("servicers");
		dic.add("third_party_origination_type");
		dic.add("interest_only");
*/
		
		PoolMonthlySupplement def = new PoolMonthlySupplement();
		

		for (Field field : def.getClass().getDeclaredFields()){
			field.setAccessible(true);
			String name = field.getName();
			String methodName = buildMethodName(def.getClass().getName(), name);
			name = converString(name);
			String classType = field.getType().toString();
			String type=convertType(classType);
		
			if (!dic.contains(name)) {
					sb.append(".set(\"").append(name).append("\", ")
						.append(methodName).append(")\n");
			} 
		}
		
		return sb.toString();
	}
	
	static String generateFnmaPoolQuartileMonthly(){
		StringBuilder sb = new StringBuilder();
		Set<String> dic =new HashSet<>();
		dic.add("record_type");
		dic.add("pool_number");
		dic.add("filler");
		
		
		PoolMonthlyQuartile def = new PoolMonthlyQuartile();
		

		for (Field field : def.getClass().getDeclaredFields()){
			field.setAccessible(true);
			String name = field.getName();
			String methodName = buildMethodName(def.getClass().getName(), name);
			name = converString(name);
			String classType = field.getType().toString();
			String type=convertType(classType);
		
			if (!dic.contains(name)) {
					sb.append(".set(\"").append(name).append("\", ")
						.append(methodName).append(")\n");
			} 
		}
		
		return sb.toString();
	}
	
	static String generateFnmaPoolServicerMonthly(){
		StringBuilder sb = new StringBuilder();
		Set<String> dic =new HashSet<>();
		dic.add("record_type");
		dic.add("pool_number");
		dic.add("filler");
		
		PoolMonthlyServicer def = new PoolMonthlyServicer();
		

		for (Field field : def.getClass().getDeclaredFields()){
			field.setAccessible(true);
			String name = field.getName();
			String methodName = buildMethodName(def.getClass().getName(), name);
			name = converString(name);
			String classType = field.getType().toString();
			String type=convertType(classType);
		
			if (!dic.contains(name)) {
					sb.append(".set(\"").append(name).append("\", ")
						.append(methodName).append(")\n");
			} 
		}
		
		return sb.toString();
	}
	
	static String generateThirdPartyOriginationTypeMonthly(){
		StringBuilder sb = new StringBuilder();
		Set<String> dic =new HashSet<>();
		dic.add("record_type");
		dic.add("pool_number");
		dic.add("filler");
		
		
		PoolMonthlyThirdPartyOriginationType def = new PoolMonthlyThirdPartyOriginationType();
		

		for (Field field : def.getClass().getDeclaredFields()){
			field.setAccessible(true);
			String name = field.getName();
			String methodName = buildMethodName(def.getClass().getName(), name);
			name = converString(name);
			String classType = field.getType().toString();
			String type=convertType(classType);
		
			if (!dic.contains(name)) {
					sb.append(".set(\"").append(name).append("\", ")
						.append(methodName).append(")\n");
			} 
		}
		
		return sb.toString();
	}
	
	static String generateInterestOnlyMonthly(){
		StringBuilder sb = new StringBuilder();
		Set<String> dic =new HashSet<>();
		dic.add("record_type");
		dic.add("pool_number");
		dic.add("filler");
		
		
		PoolMonthlyInterestOnly def = new PoolMonthlyInterestOnly();
		

		for (Field field : def.getClass().getDeclaredFields()){
			field.setAccessible(true);
			String name = field.getName();
			String methodName = buildMethodName(def.getClass().getName(), name);
			name = converString(name);
			String classType = field.getType().toString();
			String type=convertType(classType);
		
			if (!dic.contains(name)) {
					sb.append(".set(\"").append(name).append("\", ")
						.append(methodName).append(")\n");
			} 
		}
		
		return sb.toString();
	}	
	
}
