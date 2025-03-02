package mz.gov.bau.libraryApi.user.presentation;

import lombok.RequiredArgsConstructor;
import mz.gov.bau.libraryApi.user.domain.command.UserCommand;
import mz.gov.bau.libraryApi.user.domain.mapper.UserMapper;
import mz.gov.bau.libraryApi.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService service;

    @GetMapping
    public ResponseEntity<List<UserJson>> findAllUsers(){
        return ResponseEntity.ok(UserMapper.INSTANCE.toJson(service.findAll()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserJson> updateUser(@PathVariable Long id,@RequestBody @Valid UserCommand userCommand){
        return ResponseEntity.status(HttpStatus.OK).body(UserMapper.INSTANCE.toJson(service.update(id,userCommand)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<UserJson> createUser(@RequestBody @Valid UserCommand userCommand){
        return ResponseEntity.status(HttpStatus.CREATED).body(UserMapper.INSTANCE.toJson(service.save(userCommand)));
    }

}
