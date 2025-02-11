package asw.goodmusic.recensioniseguite.domain;

import java.util.logging.Logger;

import asw.goodmusic.common.api.event.DomainEvent;
import asw.goodmusic.connessioni.api.event.ConnessioneCreatedEvent;

public class ConnessioneEventConsumer {

    private final Logger logger = Logger.getLogger(this.getClass().toString());

    public void onEvent(DomainEvent event) {
        if (event instanceof ConnessioneCreatedEvent evt) {
            onConnessioneCreated(evt);
        } else {
            logger.info("UNKNOWN EVENT: " + event.toString());
        }
    }

    private void onConnessioneCreated(ConnessioneCreatedEvent event) {
        Connessione connessione = new Connessione(event.getId(), event.getUtente(), event.getSeguito(), event.getSeguito());
        logger.info("CREATED CONNESSIONE: " + connessione.toString());
    }
}
