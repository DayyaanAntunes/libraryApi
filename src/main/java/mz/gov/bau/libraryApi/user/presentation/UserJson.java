package mz.gov.bau.libraryApi.user.presentation;

import lombok.Getter;
import lombok.Setter;
import mz.gov.bau.libraryApi.user.domain.enums.Role;

@Getter
@Setter
public class UserJson {
    private Long id;
    private String name;
    private String email;
    private Role role;
}
