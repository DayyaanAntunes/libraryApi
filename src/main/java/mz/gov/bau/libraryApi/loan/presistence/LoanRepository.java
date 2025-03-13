package mz.gov.bau.libraryApi.loan.presistence;

import mz.gov.bau.libraryApi.loan.domain.model.Loan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {
    Page<Loan> findAll(Specification<Loan> loanSpecification, Pageable pageable);
    List<Loan> findAll(Specification<Loan> loanSpecification);
}