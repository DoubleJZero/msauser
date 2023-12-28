package msauser.api.kafka;

import msacore.dto.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.KafkaTemplate;

@EnableKafka
@ConditionalOnProperty("kafka.bootstrap-servers")
@Configuration
public class KafkaProducerConfig {
    @Value("${kafka.consumer.group-id}")
    private String groupId;

    @Value("${kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Bean
    @ConditionalOnMissingBean
    public KafkaTemplate<String, Message> kafkaTemplate() {
        return KafkaFactoryBuilder.kafkaTemplate(bootstrapServers);
    }

    @Bean
    @ConditionalOnMissingBean
    public KafkaMessageProducer kafkaMessageProducer(KafkaTemplate kafkaTemplate) {
        return new KafkaSendMessageProducer(kafkaTemplate);
    }

}

