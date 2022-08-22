package fr.eni.filmotheque.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.eni.filmotheque.bo.Genre;
import fr.eni.filmotheque.service.GenreService;

@Controller
@RequestMapping("/admin/genres")
public class GenreController {
	
	//pour pouvoir communiquer le service, on crée un attribut qui va contenir une instance de celui-ci
	@Autowired  // va chercher dans le contexte Spring un bean qui correspond au type GenreService et il va l'injecter AUTOMATIQUEMENT
	 GenreService genreService;
	
	
	@GetMapping
	private String getGenres(Model model) {
		// afin que mon template remplisse un objet genre, je lui passe en attribut du modèle
		model.addAttribute("genre", new Genre());
		// je passe également la liste des genres dans le modèle afin qu'elle puiise être affichée dans le template
		// j'appelle mon service pour récupérer la liste des genres 
		model.addAttribute("listeGenre", genreService.listeGenres());
		return "genres";
	}
	
	@PostMapping
	private String postGenre(@Valid Genre genre, BindingResult br, Model model) {
		
		// si on a des erreurs de validations, on retourne  le template pour les afficher
		if (br.hasErrors()) {
			model.addAttribute("listeGenre", genreService.listeGenres());
			return "genres";
		}
		
		// creer le genre via genreService
		try {
			genreService.addGenre(genre);
		} 
		// si jamais ca se passe mal
		catch (Exception e) {
			// on ajoute un attribut "erreur" au modèle
			model.addAttribute("erreur", e.getMessage());
			model.addAttribute("listeGenre", genreService.listeGenres());
			return "genres";
		}
		return "redirect:/admin/genres";
	}
}
