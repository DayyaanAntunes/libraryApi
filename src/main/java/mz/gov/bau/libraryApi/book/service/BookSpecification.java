package mz.gov.bau.libraryApi.book.service;

import lombok.RequiredArgsConstructor;
import mz.gov.bau.libraryApi.book.domain.model.Book;
import mz.gov.bau.libraryApi.book.domain.query.BookQuery;
import mz.gov.bau.libraryApi.book.presistence.BookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BookSpecification {
    private final BookRepository repository;

    public Page<Book> executeQuery(BookQuery query, Pageable pageable) {
        return repository.findAll(toSpecification(query), pageable);
    }

    public List<Book> executeQuery(BookQuery query) {
        return repository.findAll(toSpecification(query));
    }

    private Specification<Book> toSpecification(BookQuery query) {
        return findByAuthor(query.getAuthor())
                .and(findByYear(query.getYear()));
    }

    private Specification<Book> findByAuthor(String author) {
        return (root, cq, cb) -> author == null ? cb.and() : cb.equal(root.get("author"), author);
    }

    private Specification<Book> findByYear(Integer year) {
        return (root, cq, cb) -> year == null ? cb.and() : cb.equal(root.get("year"), year);
    }
}
