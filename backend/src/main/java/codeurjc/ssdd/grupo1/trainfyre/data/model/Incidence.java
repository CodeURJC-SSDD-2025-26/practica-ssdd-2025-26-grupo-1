package codeurjc.ssdd.grupo1.trainfyre.data.model;


import codeurjc.ssdd.grupo1.trainfyre.config.DefaultImageLoader;
import codeurjc.ssdd.grupo1.trainfyre.dto.IncidencesDTOs.INCIDENCE_LEVEL;
import codeurjc.ssdd.grupo1.trainfyre.dto.IncidencesDTOs.INCIDENCE_STATUS;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "incidence")
public class Incidence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private INCIDENCE_LEVEL incidenceLevel;

    @Column(nullable = true)
    private String description;

    @Column(nullable = false)
    private LocalDateTime date;

    @Column(nullable = false)
    private INCIDENCE_STATUS status;

    @Lob
    @Builder.Default
    @Column(name = "incidence_image")
    private byte[] image = DefaultImageLoader.getDefaultIncidenceImage();

    @PrePersist
    public void ensureDefaultImage() {
        if (this.image == null) {
            this.image = DefaultImageLoader.getDefaultIncidenceImage();
        }
    }

    @ManyToMany
    @JoinTable(
            name = "incidence_line",
            joinColumns = @JoinColumn(name = "incidence_id"),
            inverseJoinColumns = @JoinColumn(name = "line_id")
    )
    private List<Line> affectedLines;
}
