package mz.gov.bau.libraryApi.book.domain.command;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.validation.constraints.*;
import java.time.Year;

@Data
public class BookCommand {
    @NotBlank(message = "book/missing-args")
    private String title;
    @NotBlank(message = "book/missing-args")
    private String author;
    @NotNull(message = "book/missing-args")
    private Integer year;
    @NotNull(message = "book/missing-args")
    @Min(1)
    private Integer pages;
    @NotNull(message = "book/missing-args")
    @DecimalMin(value = "1.0", message = "book/invalid-price")
    private Double price;

    @JsonIgnore
    @AssertTrue(message = "book/missing-args")
    public boolean isYearValid() {
        return year != null && year <= Year.now().getValue() && year >= 1;
    }

}
