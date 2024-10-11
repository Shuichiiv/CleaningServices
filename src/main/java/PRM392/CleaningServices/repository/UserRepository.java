package PRM392.CleaningServices.repository;

import PRM392.CleaningServices.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    List<User> findByRole_RoleName(String roleName);

    boolean existsByEmail(String email);
}
