package mz.gov.bau.libraryApi.book.presentation;

import lombok.RequiredArgsConstructor;
import mz.gov.bau.libraryApi.book.domain.command.BookCommand;
import mz.gov.bau.libraryApi.book.domain.mapper.BookMapper;
import mz.gov.bau.libraryApi.book.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService service;

    public ResponseEntity<BookJson> createBook(@RequestBody @Valid BookCommand bookCommand) {
        return ResponseEntity.status(HttpStatus.CREATED).body(BookMapper.INSTANCE.toJson(service.save(bookCommand)));
    }
}
