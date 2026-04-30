package cl.duoc.valledelsol.reportes.factory;

public class ReporteFactory {
    public static Incendio crearIncendio(String tipo) {
        if (tipo == null) {
            throw new IllegalArgumentException("El tipo no puede ser nulo");
        }
        
        if (tipo.equalsIgnoreCase("FORESTAL")) {
            return new IncendioForestal();
        } else if (tipo.equalsIgnoreCase("URBANO")) {
            return new IncendioUrbano();
        } else {
            throw new IllegalArgumentException("Tipo de incendio no reconocido: " + tipo);
        }
    }
}