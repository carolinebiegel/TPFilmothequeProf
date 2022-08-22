package fr.eni.filmotheque.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.eni.filmotheque.bo.Participant;
import fr.eni.filmotheque.service.ParticipantService;

@Controller
@RequestMapping("/admin/participants")
public class ParticipantController {
	
	//pour pouvoir communiquer le service, on crée un attribut qui va contenir une instance de celui-ci
	@Autowired  // va chercher dans le contexte Spring un bean qui correspond au type ParticipantService et il va l'injecter AUTOMATIQUEMENT
	private ParticipantService participantService;
	
	
	@GetMapping
	private String getParticipants(Model model) {
		// afin que mon template remplisse un objet participant, je lui passe en attribut du modèle
		model.addAttribute("participant", new Participant());
		// je passe également la liste des participants dans le modèle afin qu'elle puiise être affichée dans le template
		// j'appelle mon service pour récupérer la liste des participants 
		model.addAttribute("listeParticipant", participantService.listeParticipants());
		return "participants";
	}
	
	@PostMapping
	private String postParticipant(@Valid Participant participant, BindingResult br, Model model) {
		
		// si on a des erreurs de validations, on retourne  le template pour les afficher
		if (br.hasErrors()) {
			model.addAttribute("listeParticipant", participantService.listeParticipants());
			return "participants";
		}
		
		// creer le participant via participantService
		try {
			participantService.addParticipant(participant);
		} 
		// si jamais ca se passe mal
		catch (Exception e) {
			// on ajoute un attribut "erreur" au modèle
			model.addAttribute("erreur", e.getMessage());
			model.addAttribute("listeParticipant", participantService.listeParticipants());
			return "participants";
		}
		return "redirect:/admin/participants";
	}
}
