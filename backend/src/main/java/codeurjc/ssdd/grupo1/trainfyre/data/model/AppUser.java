package codeurjc.ssdd.grupo1.trainfyre.data.model;


import codeurjc.ssdd.grupo1.trainfyre.dto.Role;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
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

    //Todo recordar terminar la columna de imagenes y configurar para que almacene una imagen por defecto
    @Lob
    @Column(name = "profile_picture")
    private byte[] image;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "alert_id")
    private List<Alert> alerts;
}
