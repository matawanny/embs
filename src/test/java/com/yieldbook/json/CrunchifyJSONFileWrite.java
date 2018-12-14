package com.yieldbook.json;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
/**
 * @author Crunchify.com
 */
 
public class CrunchifyJSONFileWrite {
 
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
 
		JSONObject obj = new JSONObject();
		obj.put("website", "crunchify.com");
		obj.put("author", "App Shah");
 
		JSONArray companys = new JSONArray();
		
		JSONObject ebay = new JSONObject();
		ebay.put("name", "ebay");
		ebay.put("age", new Integer(20));
		ebay.put("revenue", new Double(20000.02));
		
		JSONObject paypal = new JSONObject();
		paypal.put("name", "paypal");
		paypal.put("age", new Integer(30));
		paypal.put("revenue", new Double(30000.03));
		
		JSONObject google = new JSONObject();
		google.put("name", "google");
		google.put("age", new Integer(40));
		google.put("revenue", new Double(40000.04));	
		
		companys.add(ebay);
		companys.add(paypal);
		companys.add(google);
		obj.put("Company_List", companys);
 
		// try-with-resources statement based on post comment below :)
		try (FileWriter file = new FileWriter("user_json.txt")) {
			file.write(obj.toJSONString());
			System.out.println("Successfully Copied JSON Object to File...");
			System.out.println("\nJSON Object: \n" + obj);
		}
	}
}