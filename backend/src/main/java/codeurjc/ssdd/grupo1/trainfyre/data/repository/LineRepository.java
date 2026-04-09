package codeurjc.ssdd.grupo1.trainfyre.data.repository;

import codeurjc.ssdd.grupo1.trainfyre.data.model.Line;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LineRepository extends JpaRepository<Line, Long> {
}
