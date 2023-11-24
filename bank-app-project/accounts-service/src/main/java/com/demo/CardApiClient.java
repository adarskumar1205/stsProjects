package com.demo;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="CARDS-SERVICE")
public interface CardApiClient {
	@GetMapping("/api/cards/{accountNumber}")
	public CardDto getCard(@PathVariable("accountNumber") Long accountNumber);

}

