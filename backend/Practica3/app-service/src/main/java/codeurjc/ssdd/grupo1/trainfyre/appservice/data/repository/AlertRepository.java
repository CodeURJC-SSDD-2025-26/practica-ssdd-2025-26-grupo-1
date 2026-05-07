package codeurjc.ssdd.grupo1.trainfyre.appservice.data.repository;

import codeurjc.ssdd.grupo1.trainfyre.appservice.data.model.Alert;
import codeurjc.ssdd.grupo1.trainfyre.appservice.data.model.AppUser;
import codeurjc.ssdd.grupo1.trainfyre.appservice.data.model.Line;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AlertRepository extends JpaRepository<Alert, Long> {
    Optional<Alert> findByLineAndStartDateAndEndDate(Line lines, String start, String end);

    Optional<Alert> findById(Long id);
    
    Page<Alert> findAll(Pageable page);
    Page<Alert> findByUserOrderByLine(AppUser appUser, Pageable page);
    List<Alert> findByUser(AppUser appUser);
}