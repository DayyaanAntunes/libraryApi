package mz.gov.bau.libraryApi.config.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class ErrorResponse {
    private String message;
    private LocalDateTime timestamp;
}
