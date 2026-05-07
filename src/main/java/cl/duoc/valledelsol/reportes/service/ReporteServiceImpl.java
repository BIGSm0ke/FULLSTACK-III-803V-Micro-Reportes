package cl.duoc.valledelsol.reportes.service;

import cl.duoc.valledelsol.reportes.dto.ReporteDTO;
import cl.duoc.valledelsol.reportes.entity.ReporteEntity;
import cl.duoc.valledelsol.reportes.factory.Incendio;
import cl.duoc.valledelsol.reportes.factory.ReporteFactory;
import cl.duoc.valledelsol.reportes.repository.ReporteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class ReporteServiceImpl implements IReporteService {

    private final ReporteRepository repository;
    private final JdbcTemplate jdbcTemplate;

    @Autowired(required = false)
    private ReportePublisher publisher;

    public ReporteServiceImpl(ReporteRepository repository, JdbcTemplate jdbcTemplate) {
        this.repository = repository;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public ReporteEntity crearReporte(ReporteDTO dto) {
        Incendio incendioInfo = ReporteFactory.crearIncendio(dto.getFireType());

        ReporteEntity entity = new ReporteEntity();
        entity.setFireType(dto.getFireType());
        entity.setPrioridad(incendioInfo.getPrioridad());
        entity.setSeverity(dto.getSeverity());
        entity.setVisible(dto.getVisible());
        entity.setAddress(dto.getAddress());
        entity.setName(dto.getName());
        entity.setPhone(dto.getPhone());
        entity.setLat(dto.getLat());
        entity.setLng(dto.getLng());
        if (dto.getUserId() != null) entity.setUserId(dto.getUserId());

        ReporteEntity guardado = repository.save(entity);

        String statusHistory = "[{\"status\":\"recibido\",\"time\":\"" +
                LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm")) + "\"}]";
        LocalDateTime now = LocalDateTime.now();

        jdbcTemplate.update(
            "INSERT INTO monitoreo (lat, lng, severity, fire_type, visible, address, name, phone, timestamp, status, status_history, reporte_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
            dto.getLat(), dto.getLng(), dto.getSeverity(), dto.getFireType(), dto.getVisible(),
            dto.getAddress(), dto.getName(), dto.getPhone(), now, "recibido", statusHistory, guardado.getId()
        );

        jdbcTemplate.update(
            "INSERT INTO alertas (severity, fire_type, visible, address, timestamp, reporte_id) VALUES (?, ?, ?, ?, ?, ?)",
            dto.getSeverity(), dto.getFireType(), dto.getVisible(), dto.getAddress(), now, guardado.getId()
        );

        if (publisher != null) {
            publisher.publicarEvento(guardado);
        }

        return guardado;
    }

    @Override
    public List<ReporteEntity> listarTodos() {
        return repository.findAll();
    }
}
