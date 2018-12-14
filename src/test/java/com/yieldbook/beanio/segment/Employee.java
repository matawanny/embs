package com.yieldbook.beanio.segment;

import java.util.Date;

public class Employee {
	String firstName;
	String lastName;
	String title;
	int salary;
	Date hireDate;
	Address mailingAddress;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public Date getHireDate() {
		return hireDate;
	}

	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}

	public Address getMailingAddress() {
		return mailingAddress;
	}

	public void setMailingAddress(Address mailingAddress) {
		this.mailingAddress = mailingAddress;
	}

	@Override
	public String toString() {
		return "Employee [firstName=" + firstName + ", lastName=" + lastName
				+ ", title=" + title + ", salary=" + salary + ", hireDate="
				+ hireDate + ", mailingAddress=" + mailingAddress + "]";
	}
	
	

}
