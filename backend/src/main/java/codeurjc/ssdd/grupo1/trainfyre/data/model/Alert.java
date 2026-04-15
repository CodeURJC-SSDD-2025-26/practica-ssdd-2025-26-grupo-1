package codeurjc.ssdd.grupo1.trainfyre.data.model;


import jakarta.persistence.*;
import lombok.Data;

import codeurjc.ssdd.grupo1.trainfyre.dto.IncidencesDTOs.INCIDENCE_LEVEL;

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
    private String startDate;

    @Column(nullable = true)
    private String endDate; //if this value is null it will be treated as there is no end date (infinite)

    @Column(nullable = false)
    private String startHour;

    @Column(nullable = false)
    private String endHour;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private AppUser user;
}
