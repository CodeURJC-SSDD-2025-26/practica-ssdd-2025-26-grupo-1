package codeurjc.ssdd.grupo1.trainfyre.data.repository;

import codeurjc.ssdd.grupo1.trainfyre.data.model.Line;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;


@Repository
public interface LineRepository extends JpaRepository<Line, Long> {
    Optional<Line> findByName(String name);
}
