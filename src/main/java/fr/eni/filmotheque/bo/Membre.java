package fr.eni.filmotheque.bo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @ToString
public class Membre {

	@Id
	private String pseudo;
	private String password;
	private boolean admin;
	
	/**
	 * On override equals pour pouvoir utiliser contains
	 * Ici on dit qu'on considère que 2 membres sont égaux si ils ont leurs 2 pseudos égaux 
	 */
	@Override
	public boolean equals(Object membre) {
		String pseudo = ((Membre) membre).getPseudo();
		return this.pseudo.equals(pseudo);
	}
}
