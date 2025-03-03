package mz.gov.bau.libraryApi.book.service;

import lombok.RequiredArgsConstructor;
import mz.gov.bau.libraryApi.book.domain.command.BookCommand;
import mz.gov.bau.libraryApi.book.domain.mapper.BookMapper;
import mz.gov.bau.libraryApi.book.domain.model.Book;
import mz.gov.bau.libraryApi.book.presistence.BookRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository repository;

    @Override
    public Book save(BookCommand bookCommand) {
        Book book = BookMapper.INSTANCE.toModel(bookCommand);
        return repository.save(book);
    }
}
