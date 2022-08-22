package fr.eni.filmotheque.bo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Setter @Getter @NoArgsConstructor
public class Participant {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String prenom;
	private String nom;
	private boolean acteur;
	private boolean realisateur;
	
	
	/**
	 * Constructeur utilisé pour initialiser les genres dans le service
	 */
	public Participant(String prenom, String nom, boolean acteur, boolean realisateur) {
		super();
		this.prenom = prenom;
		this.nom = nom;
		this.acteur = acteur;
		this.realisateur = realisateur;
	}
	
	
}
