package mz.gov.bau.libraryApi.book.domain.query;

import lombok.Data;

@Data
public class BookQuery {
    private String author;
    private Integer year;
}
