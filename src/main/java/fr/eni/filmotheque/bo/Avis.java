package fr.eni.filmotheque.bo;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter  @Setter @NoArgsConstructor
public class Avis {
	private long id;
	@Min(0) @Max(5)
	private int note;
	private String commentaire;
	private Membre membre;
	private Film film;
}
