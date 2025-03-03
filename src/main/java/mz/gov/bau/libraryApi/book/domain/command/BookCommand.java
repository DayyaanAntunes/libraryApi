package mz.gov.bau.libraryApi.book.domain.command;

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
    @Min(1)
    private Integer year;
    @NotNull(message = "book/missing-args")
    @Min(1)
    private Integer pages;
    @NotNull(message = "book/missing-args")
    @Positive(message = "book/missing-args")
    private Double price;

    @AssertTrue(message = "book/missing-args")
    public boolean isYearValid() {
        return year != null && year <= Year.now().getValue();
    }

}
