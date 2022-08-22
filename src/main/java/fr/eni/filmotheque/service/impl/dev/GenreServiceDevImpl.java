package fr.eni.filmotheque.service.impl.dev;

import java.util.ArrayList;
import java.util.List;

import fr.eni.filmotheque.service.GenreService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import fr.eni.filmotheque.bo.Genre;

@Service // obligatoire pour pouvoir injecter ce service avec @Autowired
@Profile("dev")
public class GenreServiceDevImpl implements GenreService {
	
	// je gère en interne une liste de genres
	private List<Genre> listeGenres = new ArrayList<>();
	private int compteur = 0; // je gère un compteur pour affecter les ids


	/**
	 * J'initialise quelques genres dans le constructeur
	 * @throws Exception 
	 */
	public GenreServiceDevImpl() throws Exception {
		this.addGenre(new Genre("Science-Fiction"));
		this.addGenre(new Genre("Comédie"));
		this.addGenre(new Genre("Aventure"));
	}

	@Override
	public void addGenre(Genre genre) throws Exception {
		
		// 1. Phase de validation fonctionelle
		if (listeGenres.contains(genre)) {
			throw new Exception("genre déjà existant");
		}
		
		// 2. j'affecte un id à mon genre (sera fait automatiquement avec la base de donnée)
		compteur++;
		genre.setId(compteur);
		
		// 3. j'ajoute le genre à ma liste de genres
		listeGenres.add(genre);
	}

	/**
	 * Doit me retourner la liste des genres
	 */
	@Override
	public List<Genre> listeGenres() {
		return listeGenres;
	}


	/**
	 * Doit me renvoyer le genre qui correspond à l'id passé en paramère
	 */
	@Override
	public Genre getGenreById(long id) {
		// je parcours la liste des genres jusqu'à trouvber celui qui correspond
		for (Genre genre : listeGenres) {
			if (genre.getId() == id) {
				return genre;
			}
		}
		// si pas trouvé, je retourne null
		return null;
	}


}
