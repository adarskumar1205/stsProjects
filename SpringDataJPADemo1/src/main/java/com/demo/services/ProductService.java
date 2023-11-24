package com.demo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.entities.Product;
import com.demo.repositories.ProductRepository;

@Service
public class ProductService {
	@Autowired 
	private ProductRepository productRepo;

	public ProductService() {
		super();
	}
	
	public Product saveProduct(Product product) {
		return productRepo.save(product);
	}
	
	public Optional<Product> getProductById(long id){
		return productRepo.findById(id);
	}
	
	public Iterable<Product> getAllProducts(){
		return productRepo.findAll();
	}
	
	public void deleteProduct(long id) {
		productRepo.deleteById(id);
	}
}
