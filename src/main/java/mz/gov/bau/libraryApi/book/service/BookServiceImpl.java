package mz.gov.bau.libraryApi.book.service;

import lombok.RequiredArgsConstructor;
import mz.gov.bau.libraryApi.book.domain.command.BookCommand;
import mz.gov.bau.libraryApi.book.domain.mapper.BookMapper;
import mz.gov.bau.libraryApi.book.domain.model.Book;
import mz.gov.bau.libraryApi.book.domain.query.BookQuery;
import mz.gov.bau.libraryApi.book.presistence.BookRepository;
import mz.gov.bau.libraryApi.config.exception.ResponseException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository repository;
    private final BookSpecification bookSpecification;

    @Override
    public Book save(BookCommand bookCommand) {
        Book book = BookMapper.INSTANCE.toModel(bookCommand);
        return repository.save(book);
    }

    @Override
    public Page<Book> findAll(BookQuery query, Pageable pageable, boolean unpaged) {
        return !unpaged ? bookSpecification.executeQuery(query, pageable)
                : new PageImpl<>(bookSpecification.executeQuery(query));
    }

    @Override
    public Book findById(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new ResponseException("book/not-found", HttpStatus.NOT_FOUND));
    }
}
