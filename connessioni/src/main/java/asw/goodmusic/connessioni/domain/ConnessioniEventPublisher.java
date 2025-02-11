package asw.goodmusic.connessioni.domain;

import asw.goodmusic.common.api.event.DomainEvent;

public interface ConnessioniEventPublisher {

    public void publish(DomainEvent event);
    
}
