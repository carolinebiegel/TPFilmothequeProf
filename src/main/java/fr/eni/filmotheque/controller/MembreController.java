package fr.eni.filmotheque.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.eni.filmotheque.bo.Membre;
import fr.eni.filmotheque.service.MembreService;

@Controller
@RequestMapping("/admin/membres")
public class MembreController {
	
	//pour pouvoir communiquer le service, on crée un attribut qui va contenir une instance de celui-ci
	@Autowired  // va chercher dans le contexte Spring un bean qui correspond au type MembreService et il va l'injecter AUTOMATIQUEMENT
	private MembreService membreService;
	
	
	@GetMapping
	private String getMembres(Model model) {
		// afin que mon template remplisse un objet membre, je lui passe en attribut du modèle
		model.addAttribute("membre", new Membre());
		// je passe également la liste des membres dans le modèle afin qu'elle puiise être affichée dans le template
		// j'appelle mon service pour récupérer la liste des membres 
		model.addAttribute("listeMembre", membreService.listeMembres());
		return "membres";
	}
	
	@PostMapping
	private String postMembre(@Valid Membre membre, BindingResult br, Model model) {
		
		// si on a des erreurs de validations, on retourne  le template pour les afficher
		if (br.hasErrors()) {
			model.addAttribute("listeMembre", membreService.listeMembres());
			return "membres";
		}
		
		// creer le membre via membreService
		try {
			membreService.addMembre(membre);
		} 
		// si jamais ca se passe mal
		catch (Exception e) {
			// on ajoute un attribut "erreur" au modèle
			model.addAttribute("erreur", e.getMessage());
			model.addAttribute("listeMembre", membreService.listeMembres());
			return "membres";
		}
		return "redirect:/admin/membres";
	}
}
