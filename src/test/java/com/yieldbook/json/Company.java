package com.yieldbook.json;

public class Company {
	
	private String name;
	private int year;
	private double revenue;
	public Company(String name, int year, double revenue) {
		super();
		this.name = name;
		this.year = year;
		this.revenue = revenue;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public double getRevenue() {
		return revenue;
	}
	public void setRevenue(double revenue) {
		this.revenue = revenue;
	}
	
	

}
