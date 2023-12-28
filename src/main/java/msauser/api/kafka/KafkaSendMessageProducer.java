package msauser.api.kafka;

import lombok.RequiredArgsConstructor;
import msacore.dto.Message;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Component
@RequiredArgsConstructor
public class KafkaSendMessageProducer implements KafkaMessageProducer {

    private final KafkaTemplate<String, Message<?>> kafkaTemplate;

    @Override
    public <V> void send(String topic, V payload) {
        try {
            kafkaTemplate.send(topic, buildMessage(topic, payload)).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new KafkaSendMessageException(e);
        }
    }

    @Override
    public <V> Message<V> buildMessage(String topic, V payload) {
        Message.Headers headers = new Message.Headers();
        headers.setId(String.valueOf(UUID.randomUUID()));
        headers.setTimestamp(System.currentTimeMillis());
        headers.setTopic(topic);
        return new Message<>(headers, payload);
    }
}
