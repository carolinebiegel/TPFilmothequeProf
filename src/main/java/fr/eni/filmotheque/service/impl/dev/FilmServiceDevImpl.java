package fr.eni.filmotheque.service.impl.dev;

import java.util.ArrayList;
import java.util.List;

import fr.eni.filmotheque.service.FilmService;
import org.springframework.stereotype.Service;

import fr.eni.filmotheque.bo.Film;
import fr.eni.filmotheque.bo.Genre;

@Service
public class FilmServiceDevImpl implements FilmService {

	private List<Film> listeFilms = new ArrayList<>();
	private int compteur = 0; // je gère un compteur pour affecter les ids

	@Override
	public void addFilm(Film film) {
		compteur++;
		film.setId(compteur);

		// 3. j'ajoute le genre à ma liste de genres
		listeFilms.add(film);

	}

	@Override
	public List<Film> getFilms() {
		return listeFilms;
	}

	@Override
	public Film getFilmById(long id) {
		// je parcours la liste des films jusqu'à trouvber celui qui correspond
		for (Film film : listeFilms) {
			if (film.getId() == id) {
				return film;
			}
		}
		// si pas trouvé, je retourne null
		return null;
	}

}
