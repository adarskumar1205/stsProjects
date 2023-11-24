package com.demo;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="LOAN-SERVICE")
public interface LoanApiClient {
	@GetMapping("/api/loans/{accountNumber}")
	public LoanDto getLoan(@PathVariable("accountNumber") long accounNumber);

}
