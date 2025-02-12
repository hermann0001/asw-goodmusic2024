package asw.goodmusic.recensioniseguite.domain;

import asw.goodmusic.common.api.event.DomainEvent;
import asw.goodmusic.recensioni.api.event.RecensioneCreatedEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class RecensioneEventConsumerPortImpl implements RecensioneEventConsumerPort{

    @Autowired
    private RecensioneBreveService recensioneBreveService;

    private final Logger logger = Logger.getLogger(this.getClass().toString());

    @Override
    public void onEvent(DomainEvent event) {
        if (event instanceof RecensioneCreatedEvent evt) {
            onRecensioneCreated(evt);
        }
    }

    private void onRecensioneCreated(RecensioneCreatedEvent event) {
        RecensioneBreve recensione = new RecensioneBreve(event.getId(), event.getRecensore(), event.getAlbum(), event.getArtista(),event.getGenere(), event.getSunto());
        this.recensioneBreveService.createRecensioneBreve(recensione);
        logger.info("CREATED RECENSIONE: " + recensione.toString());
    }
}
