package cl.duoc.valledelsol.reportes.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
@ConditionalOnProperty(name = "kafka.enabled", havingValue = "true")
public class KafkaTopicConfig {

    @Bean
    public NewTopic reporteTopic() {
        return TopicBuilder.name("nuevo-reporte-incendio")
                .partitions(1)
                .replicas(1)
                .build();
    }
}