package com.yieldbook.json;

import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
 
/**
 * @author Crunchify.com
 */
 
public class MyJSONFileWrite {
 
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
 
		JSONObject obj = new JSONObject();
		obj.put("Name", "crunchify.com");
		obj.put("Author", "App Shah");
 
		JSONArray company = new JSONArray();
		company.add("eBay");
		company.add("Paypal");
		company.add("Google");
		obj.put("Company_List", company);
 
		// try-with-resources statement based on post comment below :)
		try (FileWriter file = new FileWriter("user_json.txt")) {
			file.write(obj.toJSONString());
			System.out.println("Successfully Copied JSON Object to File...");
			System.out.println("\nJSON Object: " + obj);
		}
	}
}