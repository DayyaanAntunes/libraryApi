package mz.gov.bau.libraryApi.book.service;

import mz.gov.bau.libraryApi.book.domain.command.BookCommand;
import mz.gov.bau.libraryApi.book.domain.model.Book;

public interface BookService {
    Book save(BookCommand bookCommand);
}
