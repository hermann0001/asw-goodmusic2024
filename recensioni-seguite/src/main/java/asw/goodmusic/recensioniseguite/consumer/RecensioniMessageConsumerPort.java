package asw.goodmusic.recensioniseguite.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;

public interface RecensioniMessageConsumerPort {

    public void consume(String message);

    @KafkaListener(topics = "", groupId = "")
    void consume(ConsumerRecord<String, String> record);
}
