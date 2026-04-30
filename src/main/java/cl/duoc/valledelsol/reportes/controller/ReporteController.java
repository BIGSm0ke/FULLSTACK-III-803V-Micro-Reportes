package cl.duoc.valledelsol.reportes.controller;

import cl.duoc.valledelsol.reportes.dto.ReporteDTO;
import cl.duoc.valledelsol.reportes.entity.ReporteEntity;
import cl.duoc.valledelsol.reportes.service.IReporteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reportes")
public class ReporteController {

    private final IReporteService reporteService;

    public ReporteController(IReporteService reporteService) {
        this.reporteService = reporteService;
    }

    @PostMapping
    public ResponseEntity<ReporteEntity> crear(@Valid @RequestBody ReporteDTO reporteDTO) {
        return new ResponseEntity<>(reporteService.crearReporte(reporteDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ReporteEntity>> listar() {
        return ResponseEntity.ok(reporteService.listarTodos());
    }
}