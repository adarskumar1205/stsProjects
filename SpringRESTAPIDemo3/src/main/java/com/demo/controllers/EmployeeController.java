package com.demo.controllers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.demo.dto.EmployeeModel;
import com.demo.entities.Employee;
import com.demo.exceptions.CustomFieldError;
import com.demo.exceptions.EmployeeNotFoundException;
import com.demo.exceptions.ErrorObject;
import com.demo.exceptions.InvalidRequestBodyError;
import com.demo.services.EmployeeService;

@RestController
public class EmployeeController {
	@Autowired
	private EmployeeService empService;
	
	@GetMapping("/employees")
	public List<Employee> getAllEmployees(){
		
		return empService.getAllEmployees();
	}
	

	@GetMapping("/employees/{empId}")
	public ResponseEntity<Employee> getEmployees(@PathVariable("empId") Long id) throws EmployeeNotFoundException{
		Employee emp = empService.getEmployee(id);
		
		if(emp!=null) {
			return new ResponseEntity<Employee>(emp,HttpStatus.OK);
		}
		else
			throw new EmployeeNotFoundException("Employee with  the given ID not found in the DB");
	}
	
	@ExceptionHandler(EmployeeNotFoundException.class)
	public ResponseEntity<ErrorObject> handleException(EmployeeNotFoundException ex){
		ErrorObject error = new ErrorObject();
		error.setMessage(ex.getMessage());
		error.setPath("api/employees/{empi}");
		error.setTimeStamp(LocalDateTime.now());
		error.setStatusCode(HttpStatus.NOT_FOUND.value());
		
		return new ResponseEntity<ErrorObject>(error,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<ErrorObject> handleMethodArgumentMismatchException(MethodArgumentTypeMismatchException ex){
		ErrorObject error = new ErrorObject();
		error.setMessage(ex.getMessage());
		error.setPath("/api/employees/{empId}");
		error.setTimeStamp(LocalDateTime.now());
		error.setStatusCode(HttpStatus.BAD_REQUEST.value());
		
		return new ResponseEntity<ErrorObject>(error, HttpStatus.BAD_REQUEST);
	}
	
	@PostMapping("/employees")
	public ResponseEntity<EmployeeModel> createEmployee(@Valid @RequestBody EmployeeModel empModel) {
		Employee emp = new Employee();
		emp.setName(empModel.getName());
		emp.setCity(empModel.getCity());
		emp.setSalary(empModel.getSalary());
		Employee empR = empService.saveEmployee(emp);
		
		EmployeeModel employee = new EmployeeModel(empR.getName(),empR.getCity(),empR.getSalary());
		
				
		return new ResponseEntity<EmployeeModel>(employee,HttpStatus.OK); 
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<InvalidRequestBodyError> handleInvalidRequestBodyException(MethodArgumentNotValidException ex){
		
		InvalidRequestBodyError invalidReqBodyError = new InvalidRequestBodyError();

		List<FieldError> errors =  ex.getFieldErrors();
		
		List<CustomFieldError> fieldErrors = errors.stream()
												.map(error -> new CustomFieldError(
														error.getField(),
														error.getDefaultMessage()
														))
												.collect(Collectors.toList());
		
		invalidReqBodyError.setFieldErrors(fieldErrors);
		invalidReqBodyError.setTimeStamp(LocalDateTime.now());
		invalidReqBodyError.setPath("/employee");
		invalidReqBodyError.setMessage(ex.getMessage());
		invalidReqBodyError.setStatusCode(HttpStatus.BAD_REQUEST.value());
		
		
		return new ResponseEntity<InvalidRequestBodyError>(invalidReqBodyError, HttpStatus.BAD_REQUEST);
	}
	
	@PutMapping("/employees/{id}")
	public Employee updateEmployee(@RequestBody Employee emp,@PathVariable Long id) {
		return empService.updateEmployee(emp,id);
	}
	
	@DeleteMapping("/employees/{id}")
	public Employee deleteteEmployee(@PathVariable Long id) {
		return empService.deleteEmployee(id);
	}
	
	@GetMapping("/employees/query")
	public List<Employee> searchEmployees(@RequestParam(value = "city",required = false) String city,
											@RequestParam(value = "salary",required = false) Double salary){
		
		if(city!=null) 
			return empService.getAllEmployeesByCity(city);
		else if(salary!=null)
			return empService.getAllEmployeesBySalary(salary);
		
		return null;
	}
	
}
