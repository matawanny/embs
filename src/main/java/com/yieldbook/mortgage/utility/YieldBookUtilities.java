package com.yieldbook.mortgage.utility;

import static com.yieldbook.mortgage.utility.YieldBookUtilities.isInteger;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;

import com.ancientprogramming.fixedformat4j.annotation.Align;
import com.ancientprogramming.fixedformat4j.format.FormatInstructions;
import com.ancientprogramming.fixedformat4j.format.data.FixedFormatDecimalData;
import com.ancientprogramming.fixedformat4j.format.data.FixedFormatNumberData;
import com.ancientprogramming.fixedformat4j.format.impl.BigDecimalFormatter;
import com.ancientprogramming.fixedformat4j.format.impl.DoubleFormatter;

public class YieldBookUtilities {

	public static final DateFormat df = new SimpleDateFormat("yyyyMMdd");
	public static final DateFormat df2 = new SimpleDateFormat("yyyyMMMdd");
	public static final DateFormat dft = new SimpleDateFormat("yyyyMMddhh:mm");
	
	public static final DateFormat df1 = new SimpleDateFormat("MM/dd/yyyy");
	public static final DateFormat dft1 = new SimpleDateFormat("MM/dd/yyyy");	
	
	public static final DateFormat dfmonthyear = new SimpleDateFormat("MMyyyy");
	public static final DateFormat dfmonthyear1 = new SimpleDateFormat("MM/yyyy");
	public static final BigDecimalFormatter DecimalFormatter = new BigDecimalFormatter(); 
	public static final DoubleFormatter DoubleFormatter = new DoubleFormatter();
	

	public static Map<String, String> PositiveMap = Stream.of(new String[][] {
			{"{", "0"},
			{"A", "1"},
			{"B", "2"},
			{"C", "3"},
			{"D", "4"},
			{"E", "5"},
			{"F", "6"},
			{"G", "7"},
			{"H", "8"},
			{"I", "9"},
	}).collect(Collectors.toMap(data -> data[0], data -> data[1]));
	
	public static Map<String, String> NagetiveMap = Stream.of(new String[][] {
			{"}", "0"},
			{"J", "1"},
			{"K", "2"},
			{"L", "3"},
			{"M", "4"},
			{"N", "5"},
			{"O", "6"},
			{"P", "7"},
			{"Q", "8"},
			{"R", "9"},
	}).collect(Collectors.toMap(data -> data[0], data -> data[1]));
	
	public static long getMillionSeconds(String asOfDate) throws ParseException{
		
		Date date = null;
		
		if(asOfDate.contains("/")){
			if(asOfDate.contains(":"))
				date = dft1.parse(asOfDate);
			else
				date = df1.parse(asOfDate);
		}else{
			if(asOfDate.contains(":"))
				date = dft.parse(asOfDate);
			else
				date = df.parse(asOfDate);
		}	
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.getTimeInMillis();
	}
	
	public static long getMonthYearMillionSeconds(String asOfDate) throws ParseException{
		
		Date date = null;
		
		if(asOfDate.contains("/")){
			date = dfmonthyear1.parse(asOfDate);
		}else{
			date = dfmonthyear.parse(asOfDate);
		}	
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.getTimeInMillis();
	}
	
	public static String getMonthYearMillionSecsEmbs(String asOfDate){
		
		Date date = null;
		try {
			
			if(asOfDate.contains("/")){
				if(asOfDate.length()==6){
					asOfDate="0"+asOfDate;
				}
				date = dfmonthyear1.parse(asOfDate);
			}else{
				if(asOfDate.length()==5){
					asOfDate="0"+asOfDate;
				}
				date = dfmonthyear.parse(asOfDate);
			}			
		} catch (ParseException e) {
			return asOfDate;
		}
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.getTimeInMillis()+"";
	}
	
	public static String getMonthYearDayMillionSecsEmbs(String asOfDate){
		
		Date date = null;
		
		try {
			
			if(asOfDate.contains("/")){

				date = df1.parse(asOfDate);
			}else{
				date = df.parse(asOfDate);
			}			
		} catch (ParseException e) {
			return asOfDate;
		}
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.getTimeInMillis()+"";
	}
	
	public static String getMonthYearMillionSecsStringEmbs(String asOfDate){
		
		Date date = null;
		try {
			date = dfmonthyear.parse(asOfDate);
		} catch (ParseException e) {

		}
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.getTimeInMillis() + "";
	}
	
	public static String getEffectiveDate(String dateInSeconds){
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(Long.parseLong(dateInSeconds));
		int mYear = calendar.get(Calendar.YEAR);
		int mMonth = calendar.get(Calendar.MONTH);
		int mDay = calendar.get(Calendar.DAY_OF_MONTH);
		StringBuilder sb = new StringBuilder();
		sb.append(mYear);
		if(mMonth<10)
			sb.append("0");
		sb.append(mMonth);
		if(mDay<10)
			sb.append("0");
		sb.append(mDay);
		return sb.toString();
	}
	
	public static String getAsOfDateMonthly(String yearMonthDate){
		StringBuilder sb = new StringBuilder();
		sb.append(yearMonthDate.substring(0,6));
		sb.append("00");
		return sb.toString();
	}
	
	public static String convertDataFormat(String asOfDate){
		Date date = null;
		try {
			if(asOfDate.contains("/")){
				date = df1.parse(asOfDate);
			}else{
				date = df.parse(asOfDate);
			}			
		} catch (ParseException e) {
			return asOfDate;
		}
		return df.format(date);
	}
	
	public static String convertMMyyyDataFormat(String asOfDate){
		Date date = null;
		try {
			if(asOfDate.contains("/")){
				date = dfmonthyear1.parse(asOfDate);
			}else{
				date = dfmonthyear.parse(asOfDate);
			}			
		} catch (ParseException e) {
			return "";
		}
		return df.format(date);
	}
	
	public static Integer getyyyyMMddFromDate(Date theDate){
		if(theDate==null)
			return null;
		else
			return Integer.parseInt(df.format(theDate));
	}
	
	public static Integer getyyyyMMdd(int month, int day, int year){
		
		Date date = null;
		StringBuffer sb = new StringBuffer();
		if (year<100){
			sb.append("19").append(year);
		}else{
			sb.append(year);
		}
		if(month<10){
			sb.append("0").append(month);
		}else{
			sb.append(month);
		}
		if(day<10){
			sb.append("0").append(day);
		}else{
			sb.append(day);
		}
		
		try {
			date = df.parse(sb.toString());
			return Integer.parseInt(df.format(date));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			return null;
		}
	}

	public static Integer getyyyyMMdd(String month, int year) {

		Date date = null;
		StringBuffer sb = new StringBuffer();
		if (year < 100) {
			sb.append("20").append(year);
		} else {
			sb.append(year);
		}

		sb.append(upCaseFirst(month)).append("01");

		try {
			date = df2.parse(sb.toString());
			String result=df.format(date); 
			return Integer.parseInt(result);
		} catch (ParseException e) {
			return null;
		}
	}
	
	public static String upCaseFirst(String source){
		
		StringBuffer sb = new StringBuffer();
		String temp = source.toLowerCase();
		sb.append(temp.substring(0, 1).toUpperCase());
		sb.append(temp.substring(1,temp.length()));
		return sb.toString();
		
	}
	
	public static Integer convertStringToInteger(String source){
		String end = source.substring(source.length()-1);
		String begin = source.substring(0,1);
		String sourceUpdate = null;
		if (StringUtils.isNumeric(source)){
			 return Integer.parseInt(source);
		}else if (!StringUtils.isNumeric(end)){
			String endInt = PositiveMap.get(end);
			if(endInt!=null){
			    sourceUpdate = (source.substring(0,source.length()-1)).concat(endInt);
			    return Integer.parseInt(sourceUpdate);
		    }else{
		    	endInt = NagetiveMap.get(end);
		    	sourceUpdate = (source.substring(0,source.length()-1)).concat(endInt);
			    return Integer.parseInt(sourceUpdate)*(-1);
		    }
		}else if(!StringUtils.isNumeric(begin)){
			String beginInt = PositiveMap.get(begin);
			if(beginInt!=null){
			    sourceUpdate = beginInt.concat(source.substring(1,source.length()-1));
			    return Integer.parseInt(sourceUpdate);
			}else{
				beginInt = NagetiveMap.get(begin);
			    sourceUpdate = beginInt.concat(source.substring(1,source.length()-1));
			    return Integer.parseInt(sourceUpdate)*(-1);				
			}
		}else{
			return null;
		}
	}	
	
	public static Long convertStringToLong(String source){
		String end = source.substring(source.length()-1);
		String begin = source.substring(0,1);
		String sourceUpdate = null;
		if (StringUtils.isNumeric(source)){
			 return Long.parseLong(source);
		}else if (!StringUtils.isNumeric(end)){
			String endInt = PositiveMap.get(end);
			if(endInt!=null){
			    sourceUpdate = (source.substring(0,source.length()-1)).concat(endInt);
			    return Long.parseLong(sourceUpdate);
		    }else{
		    	endInt = NagetiveMap.get(end);
		    	sourceUpdate = (source.substring(0,source.length()-1)).concat(endInt);
			    return Long.parseLong(sourceUpdate)*(-1);
		    }
		}else if(!StringUtils.isNumeric(begin)){
			String beginInt = PositiveMap.get(begin);
			if(beginInt!=null){
			    sourceUpdate = beginInt.concat(source.substring(1,source.length()-1));
			    return Long.parseLong(sourceUpdate);
			}else{
				beginInt = NagetiveMap.get(begin);
			    sourceUpdate = beginInt.concat(source.substring(1,source.length()-1));
			    return Long.parseLong(sourceUpdate)*(-1);				
			}
		}else{
			return null;
		}
	}
	
	public static BigDecimal convertStringToBigDecimal(String source, int decimals){
		String end = source.substring(source.length()-1);
		String begin = source.substring(0,1);
		String sourceUpdate = null;
		int length = source.length();
		if (StringUtils.isNumeric(source)){
				return DecimalFormatter.parse(source, new FormatInstructions(length, Align.RIGHT, ' ', null, null, 
					FixedFormatNumberData.DEFAULT, new FixedFormatDecimalData(decimals, false, '.', RoundingMode.valueOf(BigDecimal.ROUND_HALF_UP))));
		}else if (!StringUtils.isNumeric(end)){
			String endInt = PositiveMap.get(end);
			if(endInt!=null){
			    sourceUpdate = (source.substring(0,source.length()-1)).concat(endInt);
			    return DecimalFormatter.parse(sourceUpdate, new FormatInstructions(length, Align.RIGHT, ' ', null, null, 
						FixedFormatNumberData.DEFAULT, new FixedFormatDecimalData(decimals, false, '.', RoundingMode.valueOf(BigDecimal.ROUND_HALF_UP))));
		    }else{
		    	endInt = NagetiveMap.get(end);
		    	sourceUpdate = (source.substring(0,source.length()-1)).concat(endInt);
		    	return DecimalFormatter.parse(sourceUpdate, new FormatInstructions(length, Align.RIGHT, ' ', null, null, 
						FixedFormatNumberData.DEFAULT, new FixedFormatDecimalData(decimals, false, '.', RoundingMode.valueOf(BigDecimal.ROUND_HALF_UP)))).negate();
		    }
		}else if(!StringUtils.isNumeric(begin)){
			String beginInt = PositiveMap.get(begin);
			if(beginInt!=null){
			    sourceUpdate = beginInt.concat(source.substring(1,source.length()-1));
			    return DecimalFormatter.parse(sourceUpdate, new FormatInstructions(length, Align.RIGHT, ' ', null, null, 
						FixedFormatNumberData.DEFAULT, new FixedFormatDecimalData(decimals, false, '.', RoundingMode.valueOf(BigDecimal.ROUND_HALF_UP))));
			}else{
				beginInt = NagetiveMap.get(begin);
			    sourceUpdate = beginInt.concat(source.substring(1,source.length()-1));
			    return DecimalFormatter.parse(sourceUpdate, new FormatInstructions(length, Align.RIGHT, ' ', null, null, 
						FixedFormatNumberData.DEFAULT, new FixedFormatDecimalData(decimals, false, '.', RoundingMode.valueOf(BigDecimal.ROUND_HALF_UP)))).negate();				
			}
		}else{
			return null;
		}		
		
	}
	
	 public static boolean isInteger(String s) {
	      boolean isValidInteger = false;
	      try
	      {
	         Integer.parseInt(s);
	         isValidInteger = true;
	      }
	      catch (NumberFormatException ex)
	      {
	         // s is not an integer
	      }
	      return isValidInteger;
	   }
	 
	 public static String getMethodName(String s, String delimeter) {
		 
		  String[] elements = s.split(delimeter);
		  StringBuilder sb = new StringBuilder();
		  sb.append("get");
		  for (String element: elements){
			  if(isInteger(element.substring(0,1))){
				  sb.append(element);
			  }else{
				  sb.append(element.substring(0,1).toUpperCase())
				  	.append(element.substring(1,element.length()));
			  }
		  }
		  return (sb.toString());
	 }
}
