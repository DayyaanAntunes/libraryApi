package mz.gov.bau.libraryApi.user.service;

import lombok.RequiredArgsConstructor;
import mz.gov.bau.libraryApi.config.exception.ResponseException;
import mz.gov.bau.libraryApi.user.domain.command.UserCommand;
import mz.gov.bau.libraryApi.user.domain.mapper.UserMapper;
import mz.gov.bau.libraryApi.user.domain.model.User;
import mz.gov.bau.libraryApi.user.presistence.UserRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository repository;

    @Override
    public User save(UserCommand userCommand) {
        User user = UserMapper.INSTANCE.toModel(userCommand);

        if (repository.existsByEmailIgnoreStatus(userCommand.getEmail()))
            throw new ResponseException("user/already-exists", HttpStatus.CONFLICT);

        return repository.save(user);
    }

    @Override
    public User update(Long id, UserCommand userCommand) {
        try {
            User user = findById(id);
            UserMapper.INSTANCE.toModel(userCommand, user);
            return repository.save(user);

        } catch (DataIntegrityViolationException e) {
            if (e.getCause() instanceof ConstraintViolationException)
                throw new ResponseException("user/already-exists", HttpStatus.CONFLICT);
            throw new ResponseException("internal-error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) throw new ResponseException("user/not-found", HttpStatus.NOT_FOUND);
        repository.deleteById(id);
    }

    @Override
    public User findById(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new ResponseException("user/not-found", HttpStatus.NOT_FOUND));
    }

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }
}
