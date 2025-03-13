package mz.gov.bau.libraryApi.loan.domain.mapper;

import mz.gov.bau.libraryApi.book.domain.model.Book;
import mz.gov.bau.libraryApi.book.service.BookService;
import mz.gov.bau.libraryApi.loan.domain.command.LoanCommand;
import mz.gov.bau.libraryApi.loan.domain.model.Loan;
import mz.gov.bau.libraryApi.loan.presentation.LoanJson;
import mz.gov.bau.libraryApi.user.domain.model.User;
import mz.gov.bau.libraryApi.user.service.UserService;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface LoanMapper {
    LoanMapper INSTANCE = Mappers.getMapper(LoanMapper.class);

    @Mapping(target = "book", source = "bookId", qualifiedByName = "mapBook")
    @Mapping(target = "client", source = "clientId", qualifiedByName = "mapUser")
    Loan toModel(LoanCommand loanCommand, @Context BookService bookService, @Context UserService userService);

    LoanJson toJson(Loan loan);

    default Page<LoanJson> toJson(Page<Loan> loans) {
        return loans.map(this::toJson);
    }
    @Named("mapBook")
    default Book mapBook(Long bookId, @Context BookService bookService) {
        return bookService.findById(bookId);
    }

    @Named("mapUser")
    default User mapUser(Long clientId, @Context UserService userService) {
        return userService.findById(clientId);
    }
}