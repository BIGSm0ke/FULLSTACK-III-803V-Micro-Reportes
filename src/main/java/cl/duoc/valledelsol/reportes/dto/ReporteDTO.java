package cl.duoc.valledelsol.reportes.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReporteDTO {

    @NotBlank(message = "El tipo de incendio es obligatorio")
    private String fireType;

    @NotBlank(message = "La severidad es obligatoria")
    private String severity;

    @NotBlank(message = "Indica qué se observa")
    private String visible;

    @NotBlank(message = "La dirección es obligatoria")
    private String address;

    private String name;

    private String phone;

    @NotNull(message = "La latitud es obligatoria")
    private Double lat;

    @NotNull(message = "La longitud es obligatoria")
    private Double lng;

    private Long userId;
}
