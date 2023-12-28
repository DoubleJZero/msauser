package msauser.api.kafka;

import msacore.dto.Message;

public interface KafkaMessageProducer {
    <V> void send(String topic, V payload);
    <V> Message buildMessage(String topic, V payload);
}
