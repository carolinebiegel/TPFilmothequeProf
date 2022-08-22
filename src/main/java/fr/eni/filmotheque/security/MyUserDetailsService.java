package fr.eni.filmotheque.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fr.eni.filmotheque.bo.Membre;
import fr.eni.filmotheque.service.MembreService;

/**
 * En créant une classe qui implémente l'interface de Spring Security : UserDetailsService
 * , Spring Security va comprendre que c'est à partir de cette classe (via sa méthode loadUserByUsername())
 * qu'il doit aller chercher les utilisateurs de l'application
 *
 */
@Service
public class MyUserDetailsService implements UserDetailsService {
	
	/*
	 * Plutôt que d'avoir une gestion des utilisateurs dans mon service MyUserDetailsService,
	 * Je vais utiliser MembreService pour recupérer les membres que j'ai ajouté via l'appli
	 */
	
	// va être injecté par le constructeur (lorsqu'on définit un constructeur avec un argument qui correspond à un bean dans le contexte Spring, il est AUTOMATIQUEMENT injecté)
	// cet type d'injection permet de garantir l'ordre d'instantiation des services
	private MembreService membreService;
	
	
	

	/**
	 * Au démarrage de l'application, je crée un utilisateur "admin"
	 */
	public MyUserDetailsService(MembreService membreService) {
		this.membreService = membreService;
		try {
			membreService.addMembre(new Membre("admin", "admin", true));
		} catch (Exception e) {
			System.out.println("Utilisateur admin déjà existant");
		}
	}




	/**
	 * loadUserByUsername(String username) 
	 * => explique à Spring comment on lui retourne un utilisateur à partir d'un username utilisé dans la page de login
	 */
	@Override
	public UserDetails loadUserByUsername(String username) {
		
		// 1. Je recupère le membre qui correspond au username
		Membre membre = membreService.getMembreByPseudo(username);
		
		// Si jamais le membre est null, je lance une exception
		if (membre == null) {
			throw new UsernameNotFoundException(username);
		}
		// Sinon, je renvoie une instance de Utilisateur à partir de cet objet Membre
		else {
			return new Utilisateur(membre);
		}
	}
}