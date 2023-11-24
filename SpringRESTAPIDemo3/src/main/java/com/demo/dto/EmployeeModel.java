package com.demo.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.lang.NonNull;

public class EmployeeModel {
	
	@NotEmpty
	@Size(min = 3,max=255)
	private String name;
	
	@NotEmpty
	@Size(min = 3,max=255)
	private String city;
	
	@NonNull
	@Min(value=0)
	private double salary;
	
	public EmployeeModel() {
		super();
	}


	public EmployeeModel(String name, String city, double salary) {
		super();
		
		this.name = name;
		this.city = city;
		this.salary = salary;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public double getSalary() {
		return salary;
	}


	public void setSalary(double salary) {
		this.salary = salary;
	}
	

}
