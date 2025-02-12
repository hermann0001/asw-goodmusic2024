package asw.goodmusic.recensioniseguite.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConnessioniService {

    @Autowired
    private ConnessioniRepository connessioniRepository;

    public void createConnessione(Connessione c) {
        this.connessioniRepository.save(c);
    }

    public void deleteConnessione(Long id) {
        this.connessioniRepository.deleteById(id);
    }
}
