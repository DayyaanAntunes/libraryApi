package mz.gov.bau.libraryApi.book.domain.model;

import lombok.Getter;
import lombok.Setter;
import mz.gov.bau.libraryApi.book.domain.enums.Status;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "books")
@SQLDelete(sql = "UPDATE books SET deleted_at = NOW() WHERE id = ?")
@Where(clause = "deleted_at IS NULL")
@Getter
@Setter
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String author;
    @Column(name = "publish_year")
    private Integer publishYear;
    @Column(name = "page_number")
    private Integer pageNumber;
    private BigDecimal price;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;
    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

}
