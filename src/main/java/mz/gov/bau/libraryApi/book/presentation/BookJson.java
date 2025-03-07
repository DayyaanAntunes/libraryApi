package mz.gov.bau.libraryApi.book.presentation;

import lombok.Data;
import mz.gov.bau.libraryApi.book.domain.enums.Status;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class BookJson {
    private long id;
    private String title;
    private String author;
    private Integer publishYear;
    private Integer pageNumber;
    private Double price;
    private Status status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

}
