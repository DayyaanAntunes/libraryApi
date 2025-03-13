package mz.gov.bau.libraryApi.loan.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mz.gov.bau.libraryApi.loan.domain.LoanQuery;
import mz.gov.bau.libraryApi.loan.domain.model.Loan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoanServiceImpl implements LoanService {
    private final LoanSpecification specification;

    @Override
    public Page<Loan> findAll(LoanQuery loanQuery, Pageable pageable, boolean unpaged) {
        return !unpaged ? specification.executeQuery(loanQuery, pageable)
                : new PageImpl<>(specification.executeQuery(loanQuery));
    }
}