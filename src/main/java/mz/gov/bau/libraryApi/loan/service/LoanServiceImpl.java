package mz.gov.bau.libraryApi.loan.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mz.gov.bau.libraryApi.book.service.BookService;
import mz.gov.bau.libraryApi.config.exception.ResponseException;
import mz.gov.bau.libraryApi.loan.domain.LoanQuery;
import mz.gov.bau.libraryApi.loan.domain.command.LoanCommand;
import mz.gov.bau.libraryApi.loan.domain.enums.LoanStatus;
import mz.gov.bau.libraryApi.loan.domain.mapper.LoanMapper;
import mz.gov.bau.libraryApi.loan.domain.model.Loan;
import mz.gov.bau.libraryApi.loan.presistence.LoanRepository;
import mz.gov.bau.libraryApi.user.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoanServiceImpl implements LoanService {
    private final LoanSpecification specification;
    private final BookService bookService;
    private final UserService userService;
    private final LoanRepository repository;

    @Override
    public Page<Loan> findAll(LoanQuery loanQuery, Pageable pageable, boolean unpaged) {
        return !unpaged ? specification.executeQuery(loanQuery, pageable)
                : new PageImpl<>(specification.executeQuery(loanQuery));
    }

    @Override
    public Loan save(LoanCommand loanCommand) {
        Loan loan = LoanMapper.INSTANCE.toModel(loanCommand, bookService, userService);
        Boolean exists = repository.existsLoanByUserAndBook(loanCommand.getClientId(), loanCommand.getBookId());
        if (exists)
            throw new ResponseException("loan/already-exists", HttpStatus.CONFLICT);
        loan.setLoanStatus(LoanStatus.ACTIVE);
        loan.setLoanedAt(LocalDateTime.now());
        return repository.save(loan);
    }
}