package cl.duoc.valledelsol.reportes.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "reportes")
public class ReporteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fireType;
    private String severity;
    private String visible;
    private String address;
    private String name;
    private String phone;
    private Double lat;
    private Double lng;
    private int prioridad;
    private Long userId;
    private LocalDateTime fechaCreacion = LocalDateTime.now();
}
