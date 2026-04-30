package cl.duoc.valledelsol.reportes.factory;

public class IncendioUrbano implements Incendio {
    @Override 
    public String getTipo() { 
        return "URBANO"; 
    }

    @Override 
    public int getPrioridad() { 
        return 2; // Prioridad media
    }
}