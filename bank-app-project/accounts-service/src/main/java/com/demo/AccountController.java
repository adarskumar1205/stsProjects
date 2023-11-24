package com.demo;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private LoanApiClient loanApiClient;
	
	@Autowired
	private CardApiClient cardApiClient;
	
	
	@GetMapping("/{accountNumber}")
	public AccountResponseDto getAccount(@PathVariable("accountNumber") long accountNumber){
		Account account = accountRepository.findByAccountNumber(accountNumber);
		
		LoanDto loanDto =loanApiClient.getLoan(accountNumber);
		
		CardDto cardDto = cardApiClient.getCard(accountNumber);
		
		
		AccountResponseDto response = new AccountResponseDto();
		response.setAccount(account);
		response.setLoanDto(loanDto);
		response.setCardDto(cardDto);
		
		return response;
	}

}