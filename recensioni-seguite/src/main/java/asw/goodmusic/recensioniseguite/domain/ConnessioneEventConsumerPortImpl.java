package asw.goodmusic.recensioniseguite.domain;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import asw.goodmusic.common.api.event.DomainEvent;
import asw.goodmusic.connessioni.api.event.ConnessioneCreatedEvent;
import asw.goodmusic.connessioni.api.event.ConnessioneDeletedEvent;

@Service
public class ConnessioneEventConsumerPortImpl implements ConnessioneEventConsumerPort{

    private final Logger logger = Logger.getLogger(this.getClass().toString());

    @Autowired
    private ConnessioniService connessioniService;

    @Override
    public void onEvent(DomainEvent event) {
        if (event instanceof ConnessioneCreatedEvent evt) {
            this.onConnessioneCreated(evt);
        } else if (event instanceof ConnessioneDeletedEvent evt) {
            this.onConnessioneDeleted(evt);
        }
    }

    private void onConnessioneCreated(ConnessioneCreatedEvent event) {
        Connessione connessione = new Connessione(event.getId(), event.getUtente(), event.getSeguito(), event.getSeguito());
        this.connessioniService.createConnessione(connessione);
        logger.info("CREATED CONNESSIONE: " + connessione.toString());
    }

    private void onConnessioneDeleted(ConnessioneDeletedEvent event) {
        this.connessioniService.deleteConnessione(event.getId());
        logger.info("CREATED CONNESSIONE: " + event.toString());
    }
}
