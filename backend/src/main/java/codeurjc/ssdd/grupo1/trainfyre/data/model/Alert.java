package codeurjc.ssdd.grupo1.trainfyre.data.model;


import codeurjc.ssdd.grupo1.trainfyre.dto.INCIDENCE_LEVEL;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
@Table(name = "alert")
public class Alert {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "line_id")
    private Line line;

    private INCIDENCE_LEVEL minimun_incidence_level;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = true)
    private LocalDate endDate; //if this value is null it will be treated as there is no end date (infinite)

    @Column(nullable = false)
    private LocalTime starHour;

    @Column(nullable = false)
    private LocalTime endHour;
}
