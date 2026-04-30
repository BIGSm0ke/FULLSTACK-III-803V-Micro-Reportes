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
    
    private String tipo;
    private int prioridad;
    private String descripcion;
    private Double latitud;
    private Double longitud;
    private LocalDateTime fechaCreacion = LocalDateTime.now();
}