package fr.eni.filmotheque.service;

import java.util.List;

import fr.eni.filmotheque.bo.Genre;

/**
 * Interface
 * Ce qui sert à specifier les fonctionnalités de notre service
 */
public interface GenreService {
	
	/**
	 * 1. On va avoir besoin de créer des genres
	 */
	public void addGenre(Genre genre) throws Exception;
	
	/**
	 * 2. On va avoir besoin de lister les genres	 * 
	 */
	public List<Genre> listeGenres();

	/**
	 * 3. On va avoir besoin de recupérer un genre à partir de son id 
	 */
	public Genre getGenreById(long id);
}
