package com.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;




@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
//	Card findByCardNumber(long cardNumber);
	Card findByAccountNumber(long accountNumber);

}
