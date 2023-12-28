package msauser.api.kafka;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class KafkaFactoryBuilder {
    public static <T> KafkaTemplate<String, T> kafkaTemplate(String bootstrapServers) {
        return kafkaTemplate(producerFactory(bootstrapServers));
    }

    public static <T> KafkaTemplate<String, T> kafkaTemplate(ProducerFactory<String, T> producerFactory) {
        return new KafkaTemplate<>(producerFactory);
    }

    public static <T> ProducerFactory<String, T> producerFactory(String bootstrapServers) {
        return new DefaultKafkaProducerFactory<>(producerConfigs(bootstrapServers));
    }

    public static Map<String, Object> producerConfigs(String bootstrapServers) {
        Map<String, Object> configurations = new HashMap<>();
        configurations.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, Arrays.asList(bootstrapServers.split(",")));
        configurations.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configurations.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return configurations;
    }
}
