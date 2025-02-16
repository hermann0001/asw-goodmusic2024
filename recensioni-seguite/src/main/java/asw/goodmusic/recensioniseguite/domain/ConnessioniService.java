package asw.goodmusic.recensioniseguite.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConnessioniService {

    @Autowired
    private ConnessioniRepository connessioniRepository;

    public Connessione createConnessione(Long id, String utente, String seguito, String ruolo) {
        Connessione c = new Connessione(id, utente, seguito, ruolo);
        return this.connessioniRepository.save(c);
    }

    public void deleteConnessione(Long id) {
        this.connessioniRepository.deleteById(id);
    }
}
