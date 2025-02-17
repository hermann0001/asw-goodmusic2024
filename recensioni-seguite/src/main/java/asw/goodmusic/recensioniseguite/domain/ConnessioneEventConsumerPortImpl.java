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
            logger.info("create event");
            this.onConnessioneCreated(evt);
        } else if (event instanceof ConnessioneDeletedEvent evt) {
            logger.info("delete event");
            this.onConnessioneDeleted(evt);
        }
    }

    private void onConnessioneCreated(ConnessioneCreatedEvent event) {
        Connessione c = this.connessioniService.createConnessione(
            event.getId(),
            event.getUtente(),
            event.getSeguito(),
            event.getRuolo()
        );
        logger.info("CREATED CONNESSIONE: " + c.toString());
    }

    private void onConnessioneDeleted(ConnessioneDeletedEvent event) {
        this.connessioniService.deleteConnessione(event.getId());
        logger.info("DELETED CONNESSIONE: " + event.toString());
    }
}
