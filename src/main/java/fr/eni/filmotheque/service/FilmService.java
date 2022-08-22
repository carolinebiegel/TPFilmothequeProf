package fr.eni.filmotheque.service;

import java.util.List;

import fr.eni.filmotheque.bo.Film;
import fr.eni.filmotheque.bo.Genre;

public interface FilmService {
	
	public void addFilm(Film film);
	
	public List<Film> getFilms();
	
	public Film getFilmById(long id);

}
