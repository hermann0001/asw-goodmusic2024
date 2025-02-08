package asw.goodmusic.recensioni.publisher;

import asw.goodmusic.recensioni.domain.Recensione;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecensioniPublisherService {

    @Autowired
    private RecensioniMessagePublisherPort recensioniMessagePublisher;

    public void publish(Recensione r) {
        String message = (r.getGenere()+" "+r.getArtista()+" "+r.getAlbum()+" "+r.getRecensore()+"  "+r.getSunto());
        recensioniMessagePublisher.publish(message);
    }

}