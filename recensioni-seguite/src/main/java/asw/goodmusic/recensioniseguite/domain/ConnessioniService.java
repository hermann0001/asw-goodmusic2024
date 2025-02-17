package asw.goodmusic.recensioniseguite.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConnessioniService {

    @Autowired
    private ConnessioniRepository connessioniRepository;

    public Connessione createConnessione(Long id, String utente, String seguito, String ruolo) {
        return this.connessioniRepository.save(new Connessione(id, utente, seguito, ruolo));
    }

    public void deleteConnessione(Long id) {
        this.connessioniRepository.deleteById(id);
    }
}
