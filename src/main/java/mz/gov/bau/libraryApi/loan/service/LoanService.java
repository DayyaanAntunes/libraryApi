package mz.gov.bau.libraryApi.loan.service;

import mz.gov.bau.libraryApi.loan.domain.LoanQuery;
import mz.gov.bau.libraryApi.loan.domain.model.Loan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LoanService {
    Page<Loan> findAll(LoanQuery loanQuery, Pageable pageable, boolean unpaged);
}