package mz.gov.bau.libraryApi.config.exception;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice(basePackages = "mz.gov.bau")
@Slf4j
public class ControllerExceptionHandler {

    @ResponseBody
    @ExceptionHandler(ResponseException.class)
    public ResponseEntity<ErrorResponse> handleResponseException(ResponseException exception) {
        log.error("{} SERVER ERROR {} - {}", "\u001B[31m", "\u001B[0m", exception.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(exception.getMessage(), exception.getTimestamp());
        return ResponseEntity.status(exception.getStatusCode()).body(errorResponse);
    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneralException() {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage("internal-error");
        errorResponse.setTimestamp(LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(exception.getAllErrors().stream().
                map(DefaultMessageSourceResolvable::getDefaultMessage).findFirst().orElse("validation-error"));
        errorResponse.setTimestamp(LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

}
