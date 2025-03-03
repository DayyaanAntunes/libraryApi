package mz.gov.bau.libraryApi.book.presistence;

import mz.gov.bau.libraryApi.book.domain.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> { }