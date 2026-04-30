package cl.duoc.valledelsol.reportes.service;

import cl.duoc.valledelsol.reportes.dto.ReporteDTO;
import cl.duoc.valledelsol.reportes.entity.ReporteEntity;
import cl.duoc.valledelsol.reportes.factory.Incendio;
import cl.duoc.valledelsol.reportes.factory.ReporteFactory;
import cl.duoc.valledelsol.reportes.repository.ReporteRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ReporteServiceImpl implements IReporteService {

    private final ReporteRepository repository;
    private final ReportePublisher publisher;

    public ReporteServiceImpl(ReporteRepository repository, ReportePublisher publisher) {
        this.repository = repository;
        this.publisher = publisher;
    }

    @Override
    public ReporteEntity crearReporte(ReporteDTO dto) {
        // Uso del Patrón Factory
        Incendio incendioInfo = ReporteFactory.crearIncendio(dto.getTipo());

        ReporteEntity entity = new ReporteEntity();
        entity.setTipo(incendioInfo.getTipo());
        entity.setPrioridad(incendioInfo.getPrioridad());
        entity.setDescripcion(dto.getDescripcion());
        entity.setLatitud(dto.getLatitud());
        entity.setLongitud(dto.getLongitud());

        // Guardar en MySQL
        ReporteEntity guardado = repository.save(entity);

        // Notificar a Kafka (MS Monitoreo)
        publisher.publicarEvento(guardado);

        return guardado;
    }

    @Override
    public List<ReporteEntity> listarTodos() {
        return repository.findAll();
    }
}