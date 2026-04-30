package cl.duoc.valledelsol.reportes.service;

import cl.duoc.valledelsol.reportes.factory.Incendio;
import cl.duoc.valledelsol.reportes.factory.ReporteFactory;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

// IMPORTANTE: Sin anotaciones de Spring aquí para que sea rápido y no pida DB
class ReporteServiceTest {

    @Test
    void testFactoryForestal() {
        Incendio incendio = ReporteFactory.crearIncendio("FORESTAL");
        assertEquals("FORESTAL", incendio.getTipo());
        assertEquals(1, incendio.getPrioridad());
    }

    @Test
    void testFactoryUrbano() {
        Incendio incendio = ReporteFactory.crearIncendio("URBANO");
        assertEquals("URBANO", incendio.getTipo());
        assertEquals(2, incendio.getPrioridad());
    }
}