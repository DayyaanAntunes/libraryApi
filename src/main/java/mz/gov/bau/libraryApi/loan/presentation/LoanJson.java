package mz.gov.bau.libraryApi.loan.presentation;

import lombok.Getter;
import lombok.Setter;
import mz.gov.bau.libraryApi.book.domain.model.Book;
import mz.gov.bau.libraryApi.user.domain.model.User;

import java.time.LocalDateTime;

@Setter
@Getter
public class LoanJson {
    private Long id;
    private Book book;
    private User user;
    private LocalDateTime loanedAt;
    private LocalDateTime dueAt;
    private LocalDateTime returnedAt;
    private String loanStatus;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

}