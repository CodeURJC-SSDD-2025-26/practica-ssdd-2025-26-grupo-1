package codeurjc.ssdd.grupo1.trainfyre.data.model;


import codeurjc.ssdd.grupo1.trainfyre.config.DefaultImageLoader;
import codeurjc.ssdd.grupo1.trainfyre.dto.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Role role;

    @Column(nullable = true)
    private String email;

    @Lob
    @Builder.Default
    @Column(name = "profile_picture")
    private byte[] image = DefaultImageLoader.getDefaultProfileImage();

    @PrePersist
    public void ensureDefaultImage() {
        if (this.image == null) {
            this.image = DefaultImageLoader.getDefaultProfileImage();
        }
    }

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "alert_id")
    private List<Alert> alerts;
}
