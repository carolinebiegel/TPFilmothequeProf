package fr.eni.filmotheque.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.eni.filmotheque.bo.Avis;
import fr.eni.filmotheque.bo.Film;
import fr.eni.filmotheque.security.Utilisateur;
import fr.eni.filmotheque.service.AvisService;
import fr.eni.filmotheque.service.FilmService;

@Controller
@RequestMapping("/prive/avis")
public class AvisController {
	
	//pour pouvoir communiquer le service, on crée un attribut qui va contenir une instance de celui-ci
	@Autowired  // va chercher dans le contexte Spring un bean qui correspond au type AvisService et il va l'injecter AUTOMATIQUEMENT
	private AvisService avisService;
	
	@Autowired
	private FilmService filmService;
	
	
	@GetMapping
	private String getAvis(long filmId, Model model) { // filmId : correspond au paramètre de ma requête : http://localhost:8080/prive/avis?filmId=...
		// afin que mon template remplisse un objet avis, je lui passe en attribut du modèle
		model.addAttribute("avis", new Avis());
		
		// en plus, j'ajoute au modèle l'objet film qui correspond à l'id passé en paramètre de requête
		this.addFilmAuModele(model, filmId);
		return "avis";
	}
	
	@PostMapping
	private String postAvis(long filmId, @AuthenticationPrincipal Utilisateur utilisateurConnecte, @Valid Avis avis, BindingResult br, Model model) {
		
		Film film; 
		
		// si on a des erreurs de validations, on retourne  le template pour les afficher
		if (br.hasErrors()) {
			this.addFilmAuModele(model, filmId);
			return "avis";
		}
		
		// Sinon, creer le avis via avisService
		try {
			/* On va compléter l'avis envoyé par le formulaire avec :
			 * - le film correspond à l'id
			 * - le membre correspondant à l'utilisateur connecté
			 */
			
			/* 1 - faire correspondre film et avis
				Comme c'est une relation BIDIRECTIONNELLE, on doit le faire dans les 2 sens
			*/
			
			film = filmService.getFilmById(filmId);
			
			// 1.1 - ajouter le film à l'avis
			avis.setFilm(film);
			
			// 1.2 - ajouter l'avis à la liste des avis du film
			film.getAvis().add(avis);
			
			/* 2 - faire correspondre membre et avis
			  On va recupérer le membre grace à l'utilisateur connecté accessible via le paramètre annoté avec @AuthenticationPrincipal
			 */
			avis.setMembre(utilisateurConnecte.getMembre());
			avisService.addAvis(avis);
		} 
		// si jamais ca se passe mal
		catch (Exception e) {
			// on ajoute un attribut "erreur" au modèle
			model.addAttribute("erreur", e.getMessage());
			this.addFilmAuModele(model, filmId);
			return "avis";
		}
		// si ca se passe bien, je redirige sur la page de détail du film
		return "redirect:/filmDetail?id=" + film.getId();
	}
	
	/**
	 * Méthode utilitaire pour rajouter le film au modèle (afin de ne pas avoir à répeter la même ligne)
	 */
	private void addFilmAuModele(Model model, long filmId) {
		model.addAttribute("film", filmService.getFilmById(filmId));
	}
}
