package asw.goodmusic.recensioni.eventpublisher;

import asw.goodmusic.common.api.event.DomainEvent;
import asw.goodmusic.recensioni.domain.RecensioniEventPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class RecensioniEventKafkaPublisher implements RecensioniEventPublisher {

    private final Logger logger = Logger.getLogger(this.getClass().toString());
    
    @Value("${asw.kafka.channel.out}")
    private String channel;

    @Autowired
    private KafkaTemplate<String, DomainEvent> template;

    @Override
    public void publish(DomainEvent event) {
        logger.info("EVENT PUBLISHER: " + event.toString() + " ON CHANNEL: " + channel);
        template.send(channel, event);
    }
}
