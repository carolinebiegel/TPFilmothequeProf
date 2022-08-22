package fr.eni.filmotheque.bo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class Film {

	private long id;
	
	@NotEmpty
	private String titre;
	private LocalDate dateSortie;
	@Min(0)
	private int duree;
	private String synopsis;
	private Genre genre;
	private Participant realisateur;
	private List<Participant> acteurs;
	private List<Avis> avis;
	
	
	/**
	 * On initialise la liste des avis dans le constructeur
	 */
	public Film() {
		avis = new ArrayList<>();
	}
	
	
}
