package fr.eni.filmotheque.converter;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import fr.eni.filmotheque.bo.Genre;
import fr.eni.filmotheque.service.GenreService;

/**
 * Converter => permet de specifier à Spring comment convertir les champs de formulaire de type Date
 * Pour qu'il soit reconnu par Spring, il faut le rajouter à son contexte
 */
@Component
public class GenreConverter implements Converter<String, Genre> {

	@Autowired
	private GenreService genreService;
	
	/**
	 * On doit coder explicitement comment on passe d'une date au format texte => format Genre
	 */
	@Override
	public Genre convert(String idAuFormatTexte) {
		long id = Integer.parseInt(idAuFormatTexte);
		return genreService.getGenreById(id);
	}
}
