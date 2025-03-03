package mz.gov.bau.libraryApi.book.presentation;

import lombok.RequiredArgsConstructor;
import mz.gov.bau.libraryApi.book.domain.command.BookCommand;
import mz.gov.bau.libraryApi.book.domain.mapper.BookMapper;
import mz.gov.bau.libraryApi.book.domain.query.BookQuery;
import mz.gov.bau.libraryApi.book.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService service;

    @PostMapping
    public ResponseEntity<BookJson> createBook(@RequestBody @Valid BookCommand bookCommand) {
        return ResponseEntity.status(HttpStatus.CREATED).body(BookMapper.INSTANCE.toJson(service.save(bookCommand)));
    }

    @GetMapping
    public ResponseEntity<Page<BookJson>> findAllBooks(BookQuery query, @PageableDefault Pageable pageable,
                                                       @RequestParam(defaultValue = "false") boolean unpaged) {
        return ResponseEntity.ok(BookMapper.INSTANCE.toJsonPage(service.findAll(query, pageable, unpaged)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookJson> findBookById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(BookMapper.INSTANCE.toJson(service.findById(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookJson> updateBook(@PathVariable("id") Long id, @RequestBody @Valid BookCommand bookCommand) {
        return ResponseEntity.ok(BookMapper.INSTANCE.toJson(service.update(id, bookCommand)));
    }

}
