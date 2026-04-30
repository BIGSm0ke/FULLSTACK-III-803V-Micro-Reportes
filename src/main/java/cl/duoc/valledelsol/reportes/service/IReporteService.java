package cl.duoc.valledelsol.reportes.service;

import cl.duoc.valledelsol.reportes.dto.ReporteDTO;
import cl.duoc.valledelsol.reportes.entity.ReporteEntity;
import java.util.List;

public interface IReporteService {
    ReporteEntity crearReporte(ReporteDTO reporteDTO);
    List<ReporteEntity> listarTodos();
}