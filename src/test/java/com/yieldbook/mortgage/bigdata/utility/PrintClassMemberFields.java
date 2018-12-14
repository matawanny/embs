package com.yieldbook.mortgage.bigdata.utility;

import java.lang.reflect.Field;

import com.yieldbook.mortgage.domain.fnma.PoolDailyQuartile;
import com.yieldbook.mortgage.domain.fnma.csv.FnmaPoolStatistics;

public class PrintClassMemberFields {

	public static void main(String[] args) throws Exception {

		FnmaPoolStatistics abc = new FnmaPoolStatistics();
		/*
		 * for (Field field : abc.getClass().getDeclaredFields()) {
		 * field.setAccessible(true); String name = field.getName(); Object
		 * value = field.get(abc); System.out.printf("%s: %s%n", name, value); }
		 */
		StringBuilder sb = new StringBuilder();
/*		int i = 1;
		for (Field field : abc.getClass().getDeclaredFields()) {
			field.setAccessible(true);
			String name = field.getName();
			if (i%5 == 0) {
				sb.append("\"").append(name).append("\",\n");
			} else {
				sb.append("\"").append(name).append("\",");
			}
			i++;

		}
		System.out.printf(sb.toString());*/
		
		for (Field field : abc.getClass().getDeclaredFields()) {
			field.setAccessible(true);
			String name = field.getName();
			if(!name.toLowerCase().endsWith("date")){
				sb.append("<field name=\"").append(name).append("\" />\n");
			}else{
				
				sb.append("<field name=\"").append(name).append("\" format=\"MM/dd/yyyy\" />\n");				
			}
		}
		System.out.println(sb.toString());
		sb.setLength(0);
		System.out.println("_______________________________________________________");
		PoolDailyQuartile def = new PoolDailyQuartile();
		for (Field field : def.getClass().getDeclaredFields()) {
			field.setAccessible(true);
			String name = field.getName();
			if(!name.toLowerCase().endsWith("date")){
				sb.append("<field name=\"").append(name).append("\" />\n");
			}else{
				
				sb.append("<field name=\"").append(name).append("\" format=\"MM/dd/yyyy\" />\n");				
			}
		}
		System.out.printf(sb.toString());
		
	}
}
