package asw.goodmusic.recensioniseguite.listener;

import asw.goodmusic.common.api.event.DomainEvent;
import asw.goodmusic.recensioni.api.event.RecensioniEventChannel;
import asw.goodmusic.recensioniseguite.domain.RecensioneEventConsumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class RecensioneEventKafkaListener {

    private final Logger logger = Logger.getLogger(this.getClass().toString());

    @Autowired
    private RecensioneEventConsumer recensioneEventConsumer;

    @KafkaListener(topics = RecensioniEventChannel.CHANNEL_NAME)
    public void listen(ConsumerRecord<String, DomainEvent> record) throws Exception {
        logger.info("RecensioneEventKafkaListener: " + record.toString());
        DomainEvent event = record.value();
        recensioneEventConsumer.onEvent(event);
    }
}
