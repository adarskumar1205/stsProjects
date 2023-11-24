package com.demo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.demo.entities.Employee;


public class TestMain {
	public static void main(String[] args) {
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("JPAProjectDemo1");
		EntityManager entityManager =factory.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		
//		Employee emp1 = new Employee();
//		emp1.setName("Adarsh");
//		emp1.setCity("Guwahati");
//		emp1.setSalary(12000.00);
		Employee emp = entityManager.find(Employee.class, 1);
	
	    if(emp!=null)
	    	System.out.println(emp);
	    else 
	    	System.out.println("Not found");
	    
//	    transaction.begin();
//	    emp.setCity("Mumbai");
//	    emp.setSalary(2000.00);
//	    transaction.commit();
//	    
//	    entityManager.remove(emp);
//	    
//		transaction.begin();
//		entityManager.persist(emp1);
//		transaction.commit();
		
	}

}
