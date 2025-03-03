package mz.gov.bau.libraryApi.book.presistence;

import mz.gov.bau.libraryApi.book.domain.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    Page<Book> findAll(Specification<Book> specification, Pageable pageable);
    List<Book> findAll(Specification<Book> specification);
}