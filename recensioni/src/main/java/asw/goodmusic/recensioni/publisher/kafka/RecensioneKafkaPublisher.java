package asw.goodmusic.recensioni.publisher.kafka;

import asw.goodmusic.recensioni.publisher.RecensioniMessagePublisherPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class RecensioneKafkaPublisher implements RecensioniMessagePublisherPort {

    //@Value(   )
    private String topic;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public void publish(String message) {
        kafkaTemplate.send(topic, message);
    }

}
