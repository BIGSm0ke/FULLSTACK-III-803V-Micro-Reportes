package cl.duoc.valledelsol.reportes.factory;

public class IncendioForestal implements Incendio {
    @Override public String getTipo() { return "FORESTAL"; }
    @Override public int getPrioridad() { return 1; } // Máxima prioridad
}