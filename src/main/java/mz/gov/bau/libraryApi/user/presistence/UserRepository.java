package mz.gov.bau.libraryApi.user.presistence;

import mz.gov.bau.libraryApi.user.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT COUNT(*) > 0 " +
            "FROM users u " +
            "WHERE u.email = :email", nativeQuery = true)
    boolean existsByEmailIgnoreStatus(@Param("email") String email);
}
