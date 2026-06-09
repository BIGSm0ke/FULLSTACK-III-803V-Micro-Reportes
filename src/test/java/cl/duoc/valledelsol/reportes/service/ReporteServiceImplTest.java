package cl.duoc.valledelsol.reportes.service;

import cl.duoc.valledelsol.reportes.dto.ReporteDTO;
import cl.duoc.valledelsol.reportes.entity.ReporteEntity;
import cl.duoc.valledelsol.reportes.repository.ReporteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReporteServiceImplTest {

    @Mock
    private ReporteRepository repository;

    @Mock
    private JdbcTemplate jdbcTemplate;

    @Mock
    private ReportePublisher publisher;

    @InjectMocks
    private ReporteServiceImpl reporteService;

    private ReporteDTO dtoValido;
    private ReporteEntity entityGuardada;

    @BeforeEach
    void setUp() {
        dtoValido = new ReporteDTO();
        dtoValido.setFireType("FORESTAL");
        dtoValido.setSeverity("alta");
        dtoValido.setVisible("humo_denso");
        dtoValido.setAddress("Av. Central 123");
        dtoValido.setName("Test User");
        dtoValido.setPhone("+56912345678");
        dtoValido.setLat(-33.4489);
        dtoValido.setLng(-70.6693);
        dtoValido.setUserId(1L);

        entityGuardada = new ReporteEntity();
        entityGuardada.setId(1L);
        entityGuardada.setFireType("FORESTAL");
        entityGuardada.setSeverity("alta");
        entityGuardada.setPrioridad(1);

        ReflectionTestUtils.setField(reporteService, "publisher", publisher);
    }

    @Test
    void crearReporte_DebeGuardarYRetornarEntidad() {
        when(repository.save(any(ReporteEntity.class))).thenReturn(entityGuardada);

        ReporteEntity resultado = reporteService.crearReporte(dtoValido);

        assertNotNull(resultado);
        assertEquals("FORESTAL", resultado.getFireType());
        assertEquals(1, resultado.getPrioridad());
        verify(repository, times(1)).save(any(ReporteEntity.class));
        verify(jdbcTemplate, times(2)).update(anyString(), any(Object[].class));
        verify(publisher, times(1)).publicarEvento(any(ReporteEntity.class));
    }

    @Test
    void crearReporte_SinPublisher_NoDebePublicarEvento() {
        ReporteServiceImpl serviceSinPublisher = new ReporteServiceImpl(repository, jdbcTemplate);
        when(repository.save(any(ReporteEntity.class))).thenReturn(entityGuardada);

        ReporteEntity resultado = serviceSinPublisher.crearReporte(dtoValido);

        assertNotNull(resultado);
        verify(repository, times(1)).save(any(ReporteEntity.class));
    }

    @Test
    void crearReporte_FireTypeUrbano_DebeAsignarPrioridad2() {
        dtoValido.setFireType("URBANO");
        when(repository.save(any(ReporteEntity.class))).thenReturn(entityGuardada);

        reporteService.crearReporte(dtoValido);

        verify(repository).save(argThat(e -> e.getPrioridad() == 2));
    }

    @Test
    void listarTodos_DebeRetornarLista() {
        when(repository.findAll()).thenReturn(List.of(entityGuardada));

        List<ReporteEntity> lista = reporteService.listarTodos();

        assertFalse(lista.isEmpty());
        assertEquals(1, lista.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    void listarTodos_CuandoNoHay_DebeRetornarListaVacia() {
        when(repository.findAll()).thenReturn(List.of());

        List<ReporteEntity> lista = reporteService.listarTodos();

        assertTrue(lista.isEmpty());
    }
}
