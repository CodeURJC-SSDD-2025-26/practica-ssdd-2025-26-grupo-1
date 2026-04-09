package codeurjc.ssdd.grupo1.trainfyre.data.model;


import codeurjc.ssdd.grupo1.trainfyre.dto.INCIDENCE_LEVEL;
import codeurjc.ssdd.grupo1.trainfyre.dto.INCIDENCE_STATUS;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "incidence")
public class Incidence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private INCIDENCE_LEVEL incidenceLevel;

    @Column(nullable = true)
    private String description;

    @Column(nullable = false)
    private LocalDateTime date;

    @Column(nullable = false)
    private INCIDENCE_STATUS status;

    @ManyToMany
    @JoinTable(
            name = "incidence_line",
            joinColumns = @JoinColumn(name = "incidence_id"),
            inverseJoinColumns = @JoinColumn(name = "line_id")
    )
    private List<Line> affectedLines;
}
