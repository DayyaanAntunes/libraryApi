package mz.gov.bau.libraryApi.user.service;

import mz.gov.bau.libraryApi.user.domain.command.UserCommand;
import mz.gov.bau.libraryApi.user.domain.model.User;

import java.util.List;

public interface UserService {
    User save (UserCommand userCommand);
    User update (Long id, UserCommand userCommand);
    void delete (Long id);
    User findById(Long id);
    List<User> findAll ();
}
