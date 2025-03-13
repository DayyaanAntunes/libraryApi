package mz.gov.bau.libraryApi.loan.service;


import lombok.RequiredArgsConstructor;
import mz.gov.bau.libraryApi.loan.domain.LoanQuery;
import mz.gov.bau.libraryApi.loan.domain.model.Loan;
import mz.gov.bau.libraryApi.loan.presistence.LoanRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class LoanSpecification {
    private final LoanRepository repository;

    Page<Loan> executeQuery(LoanQuery loanQuery, Pageable pageable) {
        return repository.findAll(toSpecification(loanQuery), pageable);
    }

    List<Loan> executeQuery(LoanQuery loanQuery) {
        return repository.findAll(toSpecification(loanQuery));
    }

    private Specification<Loan> toSpecification(LoanQuery loanQuery) {
        return findByBook(loanQuery.getBookId())
                .and(findByClient(loanQuery.getClientId()))
                .and(findActiveLoans(loanQuery.getActiveLoans()));
    }

    private Specification<Loan> findByBook(Long bookId) {
        return (root, cq, cb) -> bookId == null ? cb.and() : cb.equal(root.get("book").get("id"), bookId);
    }

    private Specification<Loan> findByClient(Long clientId) {
        return (root, cq, cb) -> clientId == null ? cb.and() : cb.equal(root.get("client").get("id"), clientId);
    }

    private Specification<Loan> findActiveLoans(Boolean activeLoans) {
        return (root, cq, cb) -> (Boolean.FALSE.equals(activeLoans)) ? cb.and() : cb.isNull(root.get("returnedAt"));
    }

}