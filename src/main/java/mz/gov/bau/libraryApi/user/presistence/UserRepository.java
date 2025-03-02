package mz.gov.bau.libraryApi.user.presistence;

import mz.gov.bau.libraryApi.user.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    boolean existsByEmail(String email);
}
