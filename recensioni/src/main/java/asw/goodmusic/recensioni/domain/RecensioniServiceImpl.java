package asw.goodmusic.recensioni.domain;

import asw.goodmusic.common.api.event.DomainEvent;
import asw.goodmusic.recensioni.api.event.RecensioneCreatedEvent;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.util.logging.Logger;

@Service
public class RecensioniServiceImpl implements RecensioniService {

	Logger logger = Logger.getLogger(RecensioniServiceImpl.class.getName());

	@Autowired
	private RecensioniRepository recensioniRepository;

	@Autowired
	private RecensioniEventPublisher recensioniEventPublisher;

	/* Crea una nuova recensione, a partire dai suoi dati. */ 
 	public Recensione createRecensione(String recensore, String album, String artista, String genere, String testo, String sunto) {
		Recensione recensione = new Recensione(recensore, album, artista, genere, testo, sunto); 
		recensione = recensioniRepository.save(recensione);
		DomainEvent event = new RecensioneCreatedEvent(recensione.getId(), recensione.getRecensore(), recensione.getAlbum(), recensione.getArtista(), recensione.getGenere(), recensione.getSunto());
		logger.info("Recensione creato!");
		recensioniEventPublisher.publish(event);
		return recensione;
	}

	/* Trova una recensione, dato l'id. */ 
 	public Recensione getRecensione(Long id) {
        return recensioniRepository.findById(id).orElse(null);
	}

	/* Trova tutte le recensioni. */ 
	public Collection<Recensione> getRecensioni() {
        return recensioniRepository.findAll();
	}

	/* Trova tutte le recensioni di un album. */ 
	public Collection<Recensione> getRecensioniByAlbum(String album) {
        return recensioniRepository.findByAlbum(album);
	}

	/* Trova tutte le recensioni scritte da un recensore. */ 
	public Collection<Recensione> getRecensioniByRecensore(String recensore) {
        return recensioniRepository.findByRecensore(recensore);
	}

	/* Trova tutte le recensioni scritte da un insieme di recensori. */ 
	public Collection<Recensione> getRecensioniByRecensori(Collection<String> recensori) {
        return recensioniRepository.findByRecensoreIn(recensori);
	}

	/* Trova tutte le recensioni degli album di un artista. */ 
	public Collection<Recensione> getRecensioniByArtista(String artista) {
        return recensioniRepository.findByArtista(artista);
	}

	/* Trova tutte le recensioni degli album di un insieme di artisti. */ 
	public Collection<Recensione> getRecensioniByArtisti(Collection<String> artisti) {
        return recensioniRepository.findByArtistaIn(artisti);
	}

	/* Trova tutte le recensioni degli album di un certo genere. */ 
	public Collection<Recensione> getRecensioniByGenere(String genere) {
        return recensioniRepository.findByGenere(genere);
	}

	/* Trova tutte le recensioni degli album di un insieme di generi. */ 
	public Collection<Recensione> getRecensioniByGeneri(Collection<String> generi) {
        return recensioniRepository.findByGenereIn(generi);
	}

}
