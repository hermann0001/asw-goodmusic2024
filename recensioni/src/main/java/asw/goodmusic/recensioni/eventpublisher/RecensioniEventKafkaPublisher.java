package asw.goodmusic.recensioni.eventpublisher;

import asw.goodmusic.common.api.event.DomainEvent;
import asw.goodmusic.recensioni.api.event.RecensioniEventChannel;
import asw.goodmusic.recensioni.domain.RecensioniEventPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class RecensioniEventKafkaPublisher implements RecensioniEventPublisher {

    private final Logger logger = Logger.getLogger(this.getClass().toString());

    @Autowired
    private KafkaTemplate<String, DomainEvent> template;

    private final String channel = RecensioniEventChannel.CHANNEL_NAME;

    @Override
    public void publish(DomainEvent event) {
        logger.info("EVENT PUBLISHER: " + event.toString() + " ON CHANNEL: " + channel);
        template.send(channel, event);
        // template.flush();
    }
}
