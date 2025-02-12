package asw.goodmusic.recensioniseguite.listener;

import java.util.logging.Logger;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import asw.goodmusic.common.api.event.DomainEvent;
import asw.goodmusic.recensioniseguite.domain.ConnessioneEventConsumerPortImpl;

@Component
public class ConnessioneEventKafkaListener {
    
    private final Logger logger = Logger.getLogger(this.getClass().toString());

    @Autowired
    private ConnessioneEventConsumerPortImpl connessioneEventConsumer;

    @KafkaListener(topics = "${asw.kafka.channels.in.connessioni}")
    public void listen(ConsumerRecord<String, DomainEvent> record) throws Exception {
        logger.info("ConnessioneEventKafkaListener: " + record.toString());
        DomainEvent event = record.value();
        connessioneEventConsumer.onEvent(event);
    }
}