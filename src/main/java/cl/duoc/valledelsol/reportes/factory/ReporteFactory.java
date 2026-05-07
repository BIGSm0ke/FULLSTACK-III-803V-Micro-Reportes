package cl.duoc.valledelsol.reportes.factory;

public class ReporteFactory {
    public static Incendio crearIncendio(String fireType) {
        if (fireType == null) {
            return new IncendioUrbano();
        }

        if (fireType.equalsIgnoreCase("FORESTAL")) {
            return new IncendioForestal();
        } else {
            return new IncendioUrbano();
        }
    }
}
