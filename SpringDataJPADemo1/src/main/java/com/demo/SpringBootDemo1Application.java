package com.demo;

import java.math.BigDecimal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.demo.entities.Employee;
import com.demo.entities.Product;
import com.demo.services.EmployeeService;
import com.demo.services.ProductService;

@SpringBootApplication
public class SpringBootDemo1Application {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(SpringBootDemo1Application.class, args);
		
//		EmployeeService empService = context.getBean(EmployeeService.class);
//		
//		Employee emp1 = new Employee(101,"Adarsh","Guwahati",2000.00);
//		empService.saveEmployee(emp1);
		
//		ProductService productService = context.getBean(ProductService.class);
//		Product product = new Product("a","mobile",new BigDecimal(10000));
//		productService.saveProduct(product);
		
	}

}
