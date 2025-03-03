package mz.gov.bau.libraryApi.book.service;

import mz.gov.bau.libraryApi.book.domain.command.BookCommand;
import mz.gov.bau.libraryApi.book.domain.model.Book;
import mz.gov.bau.libraryApi.book.domain.query.BookQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookService {
    Book save(BookCommand bookCommand);
    Page<Book> findAll(BookQuery query, Pageable pageable, boolean unpaged);
    Book findById(Long id);
}
