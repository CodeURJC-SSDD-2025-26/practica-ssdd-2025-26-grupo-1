package codeurjc.ssdd.grupo1.trainfyre.data.model;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "station")
public class Station {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String address;

}
