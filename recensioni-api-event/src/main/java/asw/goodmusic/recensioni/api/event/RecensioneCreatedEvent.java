package asw.goodmusic.recensioni.api.event;

import asw.goodmusic.common.api.event.DomainEvent;
import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor
public class RecensioneCreatedEvent implements DomainEvent {

    private Long id; 
	/* chi ha scritto la recensione */ 
	private String recensore; 
	/* album oggetto della recensione */ 
	private String album; 
	/* artista autore dell'album */ 
	private String artista; 
	/* genere dell'album */ 
	private String genere; 
	/* sunto del testo della recensione */ 
	private String sunto; 

}
