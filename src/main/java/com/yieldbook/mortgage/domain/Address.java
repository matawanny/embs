package com.yieldbook.mortgage.domain;


public class Address {
    
	public String street;
    public String city;
    public String state;
    public String zip;
    
	@Override
	public String toString() {
		return "Address [street=" + street + ", city=" + city + ", state="
				+ state + ", zip=" + zip + "]";
	}
        
}
