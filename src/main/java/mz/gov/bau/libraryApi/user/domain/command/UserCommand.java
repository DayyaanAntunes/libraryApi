package mz.gov.bau.libraryApi.user.domain.command;

import lombok.Getter;
import lombok.Setter;
import mz.gov.bau.libraryApi.user.domain.enums.Role;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UserCommand {
    @NotBlank(message = "user/missing-args")
    private String name;
    @NotBlank(message = "user/missing-args")
    @Email(message = "user/invalid-email")
    private String email;
    @NotBlank(message = "user/missing-args")
    private String password;
    @NotNull(message = "user/missing-args")
    private Role role;
}
