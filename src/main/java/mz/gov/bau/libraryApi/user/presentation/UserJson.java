package mz.gov.bau.libraryApi.user.presentation;

import lombok.Getter;
import lombok.Setter;
import mz.gov.bau.libraryApi.user.domain.enums.Role;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class UserJson {
    private Long id;
    private String name;
    private String email;
    private Role role;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

}
