package asw.goodmusic.connessioni.eventpublisher;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import asw.goodmusic.common.api.event.DomainEvent;
import asw.goodmusic.connessioni.domain.ConnessioniEventPublisher;

@Component
public class ConnessioniEventKafkaPublisher implements ConnessioniEventPublisher {

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
