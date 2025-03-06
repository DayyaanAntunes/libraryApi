package mz.gov.bau.libraryApi.book.presentation;

import lombok.Data;
import mz.gov.bau.libraryApi.book.domain.enums.Status;

@Data
public class BookJson {
    private long id;
    private String title;
    private String author;
    private Integer year;
    private Integer pages;
    private Double price;
    private Status status;
}
