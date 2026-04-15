package codeurjc.ssdd.grupo1.trainfyre.data.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import codeurjc.ssdd.grupo1.trainfyre.data.model.Incidence;
import codeurjc.ssdd.grupo1.trainfyre.data.model.Line;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IncidenceRepository extends JpaRepository<Incidence, Long> {
    List<Incidence> findByAffectedLinesContaining(Line line);
    Optional<Incidence> findByIncidenceID(String incidenceID);
    Page<Incidence> findAll(Pageable page);
}
