package mz.gov.bau.libraryApi.loan.domain.command;

import lombok.Getter;

@Getter
public class UpdateDevolutionDateCommand {
    private Long loanId;
}