package asw.goodmusic.recensioni.domain;


import asw.goodmusic.common.api.event.DomainEvent;


public interface RecensioniEventPublisher {

    public void publish(DomainEvent event);
}
