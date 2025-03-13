package mz.gov.bau.libraryApi.loan.domain.command;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoanCommand {
    private Long bookId;
    private LocalDateTime dueAt;
    private Long clientId;
}