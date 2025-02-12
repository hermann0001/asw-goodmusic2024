package asw.goodmusic.recensioniseguite.domain;

import asw.goodmusic.common.api.event.DomainEvent;

public interface ConnessioneEventConsumerPort {

    public void onEvent(DomainEvent event);

}
