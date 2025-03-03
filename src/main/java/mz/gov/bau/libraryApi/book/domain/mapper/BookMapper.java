package mz.gov.bau.libraryApi.book.domain.mapper;

import mz.gov.bau.libraryApi.book.domain.command.BookCommand;
import mz.gov.bau.libraryApi.book.domain.model.Book;
import mz.gov.bau.libraryApi.book.presentation.BookJson;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface BookMapper {
    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    Book toModel(BookCommand bookCommand);
    BookJson toJson(Book book);
    void toModel(BookCommand bookCommand, @MappingTarget Book book);
    default List<BookJson> toJson(List<Book> books) {
        return books.stream().map(this::toJson).collect(Collectors.toList());
    }
    default Page<BookJson> toJsonPage(Page<Book> books) {
        List <Book> bookList = books.getContent();
        List<BookJson> bookJsonList = toJson(bookList);
        return new PageImpl<>(bookJsonList, books.getPageable(), books.getTotalElements());
    }
}
