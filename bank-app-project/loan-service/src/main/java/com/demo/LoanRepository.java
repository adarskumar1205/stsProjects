package com.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;



@Repository
public interface LoanRepository  extends JpaRepository<Loan, Long> {
//	Loan findByLoanNumber(String loanNumber);
	Loan findByAccountNumber(long accountNumber);
}
