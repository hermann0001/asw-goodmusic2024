package asw.goodmusic.recensioniseguite.domain;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;

import java.util.*; 
import java.util.stream.*; 

@Service  
public class RecensioniSeguiteServiceRestBasedImpl implements RecensioniSeguiteService {

	@Autowired 
	private ConnessioniClientPort connessioniClient;

	@Autowired 
	private RecensioniClientPort recensioniClient;

	@Autowired
	private RecensioneBreveRepository recensioneBreveRepository;

	public void createRecensioneBreve(RecensioneBreve recensioneBreve) {
		this.recensioneBreveRepository.save(recensioneBreve);
	}
	/* Trova le recensioni seguite da un utente, 
	 * ovvero le recensioni degli album degli artisti, dei recensori e dei generi musicali seguiti da quell'utente. */ 
	public Collection<RecensioneBreve> getRecensioniSeguite(String utente) {
		Collection<RecensioneBreve> recensioniSeguite = new TreeSet<>(); 
		
		Collection<Connessione> connessioni = connessioniClient.getConnessioniByUtente(utente); 

		Collection<String> artistiSeguiti = 
			connessioni
				.stream()
				.filter(c -> c.getRuolo().equals("ARTISTA"))
				.map(Connessione::getSeguito)
				.collect(Collectors.toSet());
		if (!artistiSeguiti.isEmpty()) {
			Collection<RecensioneBreve> recensioniDiArtisti = recensioniClient.getRecensioniByArtisti(artistiSeguiti);
			recensioniSeguite.addAll(recensioniDiArtisti); 
		}
		
		Collection<String> recensoriSeguiti = 
			connessioni
				.stream()
				.filter(c -> c.getRuolo().equals("RECENSORE"))
				.map(Connessione::getSeguito)
				.collect(Collectors.toSet()); 
		if (!recensoriSeguiti.isEmpty()) {
			Collection<RecensioneBreve> recensioniDiRecensori = recensioniClient.getRecensioniByRecensori(recensoriSeguiti);
			recensioniSeguite.addAll(recensioniDiRecensori); 
		}

		Collection<String> generiSeguiti =
			connessioni
				.stream()
				.filter(c -> c.getRuolo().equals("GENERE"))
				.map(Connessione::getSeguito)
				.collect(Collectors.toSet()); 
		if (!generiSeguiti.isEmpty()) {
			Collection<RecensioneBreve> recensioniDiGeneri = recensioniClient.getRecensioniByGeneri(generiSeguiti);
			recensioniSeguite.addAll(recensioniDiGeneri); 
		}
		return recensioniSeguite; 
	}
}
