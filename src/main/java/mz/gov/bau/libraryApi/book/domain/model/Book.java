package mz.gov.bau.libraryApi.book.domain.model;

import lombok.Getter;
import lombok.Setter;
import mz.gov.bau.libraryApi.book.domain.enums.Status;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "books")
@SQLDelete(sql = "UPDATE books SET is_deleted = true WHERE id = ?")
@Where(clause = "is_deleted = false")
@Getter
@Setter
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String author;
    private Integer publishYear;
    private Integer pageNumber;
    private BigDecimal price;
    @Enumerated(EnumType.STRING)
    private Status status;
    private boolean isDeleted;

}
