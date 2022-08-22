package fr.eni.filmotheque.service.impl.jpa;

import fr.eni.filmotheque.bo.Genre;
import fr.eni.filmotheque.repository.GenreRepository;
import fr.eni.filmotheque.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Profile("prod")
public class GenreServiceJpaImpl implements GenreService {

    @Autowired
    private GenreRepository genreRepository;

    @Override
    public void addGenre(Genre genre) throws Exception {
        // sauvegarde un objet genre en bdd
        genreRepository.save(genre);
    }

    @Override
    public List<Genre> listeGenres() {
        return genreRepository.findAll();
    }

    @Override
    public Genre getGenreById(long id) {
        return genreRepository.getReferenceById(id);
    }
}
