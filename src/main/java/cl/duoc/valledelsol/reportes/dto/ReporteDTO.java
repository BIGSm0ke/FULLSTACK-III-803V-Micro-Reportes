package cl.duoc.valledelsol.reportes.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReporteDTO {
    
    @NotBlank(message = "El tipo de incendio es obligatorio (FORESTAL/URBANO)")
    private String tipo;
    
    @NotBlank(message = "La descripción no puede estar vacía")
    private String descripcion;
    
    @NotNull(message = "La latitud es obligatoria")
    private Double latitud;
    
    @NotNull(message = "La longitud es obligatoria")
    private Double longitud;
}