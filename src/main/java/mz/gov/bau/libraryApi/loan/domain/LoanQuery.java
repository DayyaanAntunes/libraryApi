package mz.gov.bau.libraryApi.loan.domain;

import lombok.Getter;

@Getter
public class LoanQuery {
    private Long bookId;
    private Long clientId;
    private Boolean activeLoans;
}