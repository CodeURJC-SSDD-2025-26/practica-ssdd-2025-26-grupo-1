package codeurjc.ssdd.grupo1.trainfyre.data.repository;

import codeurjc.ssdd.grupo1.trainfyre.data.repository.projection.UserAlertsCountView;
import org.springframework.data.jpa.repository.JpaRepository;
import codeurjc.ssdd.grupo1.trainfyre.data.model.AppUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByUsername(String username);

    @Query("""
        SELECT u.id AS userId, u.username AS username, u.email AS email, COUNT(a.id) AS totalAlerts
        FROM AppUser u
        JOIN u.alerts a
        JOIN a.line l
        WHERE l.name IN :lineNames
          AND a.startDate <= :incDate
          AND (a.endDate IS NULL OR a.endDate = '' OR a.endDate >= :incDate)
          AND a.startHour <= :incHour
          AND a.endHour >= :incHour
          AND EXISTS (
                SELECT 1
                FROM Incidence i
                JOIN i.affectedLines il
                WHERE i.id = :incidenceId
                  AND il = l
          )
        GROUP BY u.id, u.username
        ORDER BY u.username
    """)
    List<UserAlertsCountView> findUsersWithMatchingAlertsGrouped(
            @Param("incidenceId") Long incidenceId,
            @Param("lineNames") List<String> lineNames,
            @Param("incDate") String incDate,   // yyyy-MM-dd
            @Param("incHour") String incHour    // HH:mm
    );
}
