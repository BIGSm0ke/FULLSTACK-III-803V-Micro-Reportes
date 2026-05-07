package cl.duoc.valledelsol.reportes.service;

import cl.duoc.valledelsol.reportes.entity.ReporteEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "kafka.enabled", havingValue = "true")
public class ReportePublisher {

    @Autowired(required = false)
    private KafkaTemplate<String, ReporteEntity> kafkaTemplate;

    private final String TOPIC = "nuevo-reporte-incendio";

    public void publicarEvento(ReporteEntity reporte) {
        if (kafkaTemplate != null) {
            this.kafkaTemplate.send(TOPIC, reporte);
            System.out.println("Evento publicado en Kafka: " + reporte.getFireType());
        }
    }
}