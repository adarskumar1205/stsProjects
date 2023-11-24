package com.demo;

import static org.junit.jupiter.api.DynamicTest.stream;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.demo.entities.Address;
import com.demo.entities.Employee;
import com.demo.entities.Order;
import com.demo.entities.OrderItem;
import com.demo.entities.Product;
import com.demo.entities.QProduct;
import com.demo.repositories.AddressRepository;
import com.demo.repositories.EmployeeRepository;
import com.demo.repositories.OrderRepository;
import com.demo.repositories.ProductRepository;
import com.demo.services.EmployeeService;
import com.demo.services.ProductService;
import com.querydsl.jpa.impl.JPAQuery;

@SpringBootTest
class SpringBootDemo1ApplicationTests {
	@Autowired
	private EmployeeService empService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private ProductRepository  productRepo;
	
	@Autowired
	private OrderRepository orderRepo;
	
	@Autowired
	private AddressRepository addRepo;
	
	
	@Test
	void contextLoads() {
	}
	
	@Test
	public void testQuerydsl() {
		QProduct qProduct = QProduct.product;
		JPAQuery<Product> query = new JPAQuery<Product>(entityManager);
//		query.from(qProduct).fetchAll();
		query.from(qProduct).where(qProduct.price.gt(new BigDecimal(1000)));
		List<Product> list= query.fetch();
		list.forEach(p->System.out.println(p));
	}
	
//	@Test
//	public void testCreateOrder() {
//		Order order = new Order();
//		order.setOrderTrackingNumber("123437");
//		order.setTotalQuantity(10);
//		
//		order.setStatus("In Progress");
//		
//		Address shipAdd = new Address();
//		shipAdd.setCity("Guwahati");
//		shipAdd.setCountry("India");
//		shipAdd.setState("Assam");
//		shipAdd.setZipCode("123447");
//		shipAdd.setStreet("NH");
//		
//
//		OrderItem orderItem1 = new OrderItem();
//		Product p1=productRepo.findBySku("1235");
//		orderItem1.setProduct(p1);
//		orderItem1.setQuantity(2);
//		
//		OrderItem orderItem2 = new OrderItem();
//		Product p2=productRepo.findBySku("1237");
//		orderItem2.setProduct(p2);
//		orderItem2.setQuantity(4);
//		
//		Set<OrderItem> items = new HashSet<>();
//		items.add(orderItem1);
//		items.add(orderItem2);
//		
//		order.setOrderItem(items);
//		order.setShippingAddress(shipAdd);
//			
//		order.setTotalPrice(new BigDecimal(1000.00));
//		
//		orderRepo.save(order);
//	}
	
//	@Test
//	public void testCreateOrder() {
//		Order order = new Order();
//		order.setOrderTrackingNumber("123457");
//		order.setTotalQuantity(10);
//		order.setTotalPrice(new BigDecimal(1000.00));
//		order.setStatus("In Progress");
//		
//		Address shipAdd = new Address();
//		shipAdd.setCity("Guwahati");
//		shipAdd.setCountry("India");
//		shipAdd.setState("Assam");
//		shipAdd.setZipCode("123447");
//		shipAdd.setStreet("NH");
//		
//		order.setShippingAddress(shipAdd);
//		
////		addRepo.save(shipAdd);
//		orderRepo.save(order);
//	}
	
//	@Test
//	@Transactional
//	public void findOrder(){
//		Order order = orderRepo.findByOrderTrackingNumber("123456");
//		
//		System.out.println("Order Id : " + order.getId());
//		System.out.println("Order Total :" + order.getTotalPrice());
//		System.out.println("Address  : " + order.getShippingAddress().getCity());
//
//	}
	
	
	
	
//	@Test
//	public void testCreateEmployee() {
//		empService.saveEmployee(new Employee(102,"Akash","Delhi",4000.00));
//	}
	
//	@Test
//	public void testGetEmployee() {
//		
//		Optional<Employee> optionalEmp = empService.getEmployeeById(3);
//		
//		if(optionalEmp.isPresent()) {
//			Employee emp = optionalEmp.get();
//			System.out.println(emp);
//		}
//		else {
//			System.out.println("employee not found");
//		}
//	}
	
//	@Test
//	public void testGetAllEmployee() {
//		
//		Iterable<Employee> iterableEmp = empService.getAllEmployees();
//		
//		System.out.println(iterableEmp.toString());
//		
//	}

	
//	@Test
//	public void testDeleteEmployee() {
//		
//		empService.deleteEmployee(2);
//
//	}
	
//	
//	@Test
//	public void findProduct(){
//		productRepo.findAllByPriceGreaterThan(new BigDecimal(6000.00))
//		.stream()
//		.forEach(p -> System.out.println(p));
		
//		productRepo.findAllByPriceGreaterThanAndPriceLessThan(new BigDecimal(6000.00),new BigDecimal(13000.00))
//		.stream()
//		.forEach(p -> System.out.println(p));
//		
//		System.out.println(productRepo.findBySku("1235"));
//
//		System.out.println(productRepo.findBySku("1236"));
//
//		System.out.println(productRepo.findBySku("1237"));
//
//		System.out.println(productRepo.findBySku("1238"));
		
//		productRepo.getAllProductsByPrice(new BigDecimal(10000.00))
//		.stream()
//		.forEach(p -> System.out.println(p));
		
//		productRepo.getAllProductsByPrice(new BigDecimal(10000.00),new BigDecimal(12000.00))
//		.stream()
//		.forEach(p -> System.out.println(p));
//		
//		productRepo.GetProducts()
//		.stream()
//		.forEach(p -> System.out.println(p));
//	}
	
	
//	@Test
//	public void testCreateProduct() {
//		Product product1 = new Product();
//		product1.setSku("1235");
//		product1.setName("mobile1");
//		product1.setDescription("description");
//		product1.setActive(true);
//		product1.setImageUrl("image");
//		product1.setPrice(new BigDecimal(1000.00));
//		
//		Product product2 = new Product();
//		product2.setSku("1236");
//		product2.setName("mobile2");
//		product2.setDescription("description");
//		product2.setActive(true);
//		product2.setImageUrl("image");
//		product2.setPrice(new BigDecimal(1000.00));
//		
//		Product product3 = new Product();
//		product3.setSku("1237");
//		product3.setName("mobile3");
//		product3.setDescription("description");
//		product3.setActive(true);
//		product3.setImageUrl("image");
//		product3.setPrice(new BigDecimal(1000.00));
//		
//		Product product4 = new Product();
//		product4.setSku("1238");
//		product4.setName("mobile4");
//		product4.setDescription("description");
//		product4.setActive(true);
//		product4.setImageUrl("image");
//		product4.setPrice(new BigDecimal(1000.00));
//		
//		List<Product> list = new ArrayList<>();
//		list.add(product1);
//		list.add(product2);
//		list.add(product3);
//		list.add(product4);
//
//		productRepo.saveAll(list);
//	}

	
//	@Test
//	public void testGetProduct() {
//		
//		Optional<Product> optionalProduct = productService.getProductById(10L);
//		
//		if(optionalProduct.isPresent()) {
//			Product product = optionalProduct.get();
//			System.out.println(product);
//		}
//		else {
//			System.out.println("product not found");
//		}
//	}
	
//	@Test
//	public void testGetAllProduct() {
//		
//		Iterable<Product> iterableProduct = productService.getAllProducts();
//		
//		System.out.println(iterableProduct.toString());
//		
//	}

	
//	@Test
//	public void testDeleteProduct() {
//		
//		productService.deleteProduct(8L);
//
//	}

}
