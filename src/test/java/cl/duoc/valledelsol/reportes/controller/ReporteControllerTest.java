package cl.duoc.valledelsol.reportes.controller;

import cl.duoc.valledelsol.reportes.dto.ReporteDTO;
import cl.duoc.valledelsol.reportes.entity.ReporteEntity;
import cl.duoc.valledelsol.reportes.service.IReporteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ReporteController.class)
class ReporteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private IReporteService reporteService;

    @Test
    void crear_DebeRetornar201() throws Exception {
        ReporteDTO dto = new ReporteDTO();
        dto.setFireType("FORESTAL");
        dto.setSeverity("alta");
        dto.setVisible("humo");
        dto.setAddress("Direccion 123");
        dto.setLat(-33.4489);
        dto.setLng(-70.6693);

        ReporteEntity entity = new ReporteEntity();
        entity.setId(1L);
        entity.setFireType("FORESTAL");

        when(reporteService.crearReporte(any(ReporteDTO.class))).thenReturn(entity);

        mockMvc.perform(post("/api/reportes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.fireType").value("FORESTAL"));
    }

    @Test
    void crear_CuandoDtoInvalido_DebeRetornar400() throws Exception {
        ReporteDTO dtoInvalido = new ReporteDTO();

        mockMvc.perform(post("/api/reportes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dtoInvalido)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void listar_DebeRetornar200ConLista() throws Exception {
        ReporteEntity entity = new ReporteEntity();
        entity.setId(1L);
        entity.setFireType("FORESTAL");

        when(reporteService.listarTodos()).thenReturn(List.of(entity));

        mockMvc.perform(get("/api/reportes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].fireType").value("FORESTAL"));
    }

    @Test
    void listar_CuandoNoHay_DebeRetornar200ListaVacia() throws Exception {
        when(reporteService.listarTodos()).thenReturn(List.of());

        mockMvc.perform(get("/api/reportes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isEmpty());
    }
}
