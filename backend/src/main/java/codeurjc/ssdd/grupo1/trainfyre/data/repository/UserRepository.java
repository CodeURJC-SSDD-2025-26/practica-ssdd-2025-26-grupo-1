package codeurjc.ssdd.grupo1.trainfyre.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import codeurjc.ssdd.grupo1.trainfyre.data.model.AppUser;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByUsername(String username);
}
