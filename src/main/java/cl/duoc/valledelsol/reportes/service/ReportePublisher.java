package cl.duoc.valledelsol.reportes.service;

import cl.duoc.valledelsol.reportes.entity.ReporteEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class ReportePublisher {

    private final KafkaTemplate<String, ReporteEntity> kafkaTemplate;
    private final String TOPIC = "nuevo-reporte-incendio";

    public ReportePublisher(KafkaTemplate<String, ReporteEntity> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publicarEvento(ReporteEntity reporte) {
        // Enviamos el objeto completo para que el MS de Monitoreo tenga toda la info
        this.kafkaTemplate.send(TOPIC, reporte);
        System.out.println("Evento publicado en Kafka: " + reporte.getTipo());
    }
}