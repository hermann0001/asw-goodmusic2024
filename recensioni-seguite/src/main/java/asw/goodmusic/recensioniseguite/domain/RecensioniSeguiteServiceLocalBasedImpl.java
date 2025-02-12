package asw.goodmusic.recensioniseguite.domain;

import java.util.Collection;
import java.util.Map;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class RecensioniSeguiteServiceLocalBasedImpl implements RecensioniSeguiteService{

    @Autowired
    private RecensioneBreveRepository recensioneBreveRepository;

    @Autowired
    private ConnessioniRepository connessioniRepository;

    @Override
    public Collection<RecensioneBreve> getRecensioniSeguite(String utente) {
        Collection<RecensioneBreve> recensioniSeguite = new TreeSet<>();
        Collection<Connessione> connessioni = connessioniRepository.findByUtente(utente); 

        Map<String, Function<Collection<String>, Collection<RecensioneBreve>>> fetchMethods = Map.of(
            "ARTISTA", this.recensioneBreveRepository::findByArtistaIn,
            "RECENSORE", this.recensioneBreveRepository::findByRecensoreIn,
            "GENERE", this.recensioneBreveRepository::findByGenereIn
        );

        for (var entry : fetchMethods.entrySet()) {
            Collection<String> followedEntities = getFollowedEntities(connessioni, entry.getKey());
            if (!followedEntities.isEmpty()) {
                recensioniSeguite.addAll(entry.getValue().apply(followedEntities));
            }
        }

		return recensioniSeguite;
    }

    private Collection<String> getFollowedEntities(Collection<Connessione> connessioni, String role) {
        return connessioni.stream()
            .filter(c -> role.equals(c.getRuolo()))
            .map(Connessione::getSeguito)
            .collect(Collectors.toSet());
    }

}
