package mz.gov.bau.libraryApi.config.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
public class ErrorResponse {
    private String message;
    private LocalDateTime timestamp;
}
