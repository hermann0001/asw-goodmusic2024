package asw.goodmusic.recensioniseguite.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecensioniSeguiteConsumerService {



        public void consume(String message) {
            recensioniMessageConsumer.consume(message);
        }
}
