package mz.gov.bau.libraryApi.config.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
public class ResponseException extends RuntimeException {
    private final String message;
    private final Integer statusCode;
    private final LocalDateTime timestamp;

    public ResponseException(String message, HttpStatus httpStatus) {
        this.message = message;
        this.statusCode = httpStatus.value();
        this.timestamp = LocalDateTime.now();
    }
}
