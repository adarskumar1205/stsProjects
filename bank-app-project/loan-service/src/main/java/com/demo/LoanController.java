package com.demo;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/loans")
public class LoanController {
	@Autowired
	private LoanRepository loanRepository;
	
	@Autowired
	private ModelMapper mapper;
	
	

	@GetMapping("/{accountNumber}")
	public LoanDto getLoan(@PathVariable("accountNumber") long accounNumber){
		Loan loan =loanRepository.findByAccountNumber(accounNumber);
		
		LoanDto response =mapper.map(loan,LoanDto.class);
		
		
		return response;
	}

}
