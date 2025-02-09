package asw.goodmusic.recensioniseguite.domain;

import jakarta.persistence.*;
import lombok.*;

/* Connessione tra un utente e un seguito (con un ruolo). */
@Entity
@Data @NoArgsConstructor @AllArgsConstructor
@Table(uniqueConstraints = { @UniqueConstraint(name = "UniqueUtenteSeguitoRuolo", columnNames = { "utente", "seguito", "ruolo" }) })
public class Connessione {

	@Id
	@GeneratedValue
	/* id della connessione */ 
	private Long id; 
	/* utente che segue */ 
	private String utente; 
	/* chi o cosa è seguito (un artista o uno che scrive recensioni oppure un genere musicale) */ 
	private String seguito; 
	/* ruolo del seguito: può essere ARTISTA oppure RECENSORE oppure GENERE */ 
	private String ruolo; 
	
}
