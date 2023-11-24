package com.demo.repositories;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.entities.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long>,QuerydslPredicateExecutor<Product> {
//	List<Product> findAllByPriceGreaterThan(BigDecimal price);
//	List<Product> findAllByPriceGreaterThanAndPriceLessThan(BigDecimal max,BigDecimal min);
	Product findBySku(String sku);

	Product findByName(String string);
	
	
//	@Query("SELECT p FROM Product p WHERE p.price > ?1")
//	List<Product> getAllProductsByPrice(BigDecimal price);
	
//	@Query("SELECT p FROM Product p WHERE p.price >= ?1 and p.price <= ?2")
//	List<Product> getAllProductsByPrice(BigDecimal min,BigDecimal max);
	
//	@Query("SELECT p FROM Product p WHERE p.price >= :min and p.price <= :max")
//	List<Product> getAllProductsByPrice(@Param("min") BigDecimal min,@Param("max") BigDecimal max);
	
//	@Query(name = "SELECT p FROM products p WHERE p.price > ?1", nativeQuery = true)
//	List<Product> getAllProductsByPrice(BigDecimal price);
//	
	
//	List<Product> GetProducts();
}
