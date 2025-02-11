package asw.goodmusic.recensioniseguite.domain;

import asw.goodmusic.common.api.event.DomainEvent;
import asw.goodmusic.recensioni.api.event.RecensioneCreatedEvent;

import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class RecensioneEventConsumer {

    private final Logger logger = Logger.getLogger(this.getClass().toString());

    public void onEvent(DomainEvent event) {
        if (event instanceof RecensioneCreatedEvent evt) {
            onRecensioneCreated(evt);
        } else {
            logger.info("UNKNOWN EVENT: " + event.toString());
        }
    }

    private void onRecensioneCreated(RecensioneCreatedEvent event) {
        RecensioneBreve recensione = new RecensioneBreve(event.getId(), event.getRecensore(), event.getAlbum(), event.getArtista(),event.getGenere(), event.getSunto());
        logger.info("CREATED RECENSIONE: " + recensione.toString());
    }
}
