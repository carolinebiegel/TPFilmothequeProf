package fr.eni.filmotheque.service.impl.dev;

import java.util.ArrayList;
import java.util.List;

import fr.eni.filmotheque.service.MembreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import fr.eni.filmotheque.bo.Genre;
import fr.eni.filmotheque.bo.Membre;

@Service // obligatoire pour pouvoir injecter ce service avec @Autowired
public class MembreServiceDevImpl implements MembreService {
	
	// je gère en interne une liste de membres
	private List<Membre> listeMembres = new ArrayList<>();
	
	/*
	 * Il y a un @Bean présent dans le contexte Spring pour le type 
	 * PasswordEncoder ( fait dans WebSecurityConfig)
	 * Je vais donc pouvoir l'injecter dans ma classe
	 */
	@Autowired
	PasswordEncoder passwordEncoder; //utilitaire d'encodage des mots de passe 
	
	/**
	 * J'initialise quelques membres dans le constructeur
	 * @throws Exception 
	 */
	public MembreServiceDevImpl() throws Exception {
	}

	@Override
	public void addMembre(Membre membre) throws Exception {
		
		// 1. Phase de validation fonctionelle
		if (listeMembres.contains(membre)) {
			throw new Exception("membre déjà existant");
		}
		
		// pas besoin d'affecter un id à mon membre car c'est son pseudo
		
		// 2. par contre, je dois encoder son mot de passe avant de l'ajouter à ma liste
		// Si on ne le fait pas on ne POURRA PAS SE CONNECTER car Spring Boot decrypte automatiquement les mots de passe avant de comparer
		String motDePasseEncode = passwordEncoder.encode(membre.getPassword());
		membre.setPassword(motDePasseEncode);
		
		// 3. j'ajoute le membre à ma liste de membres
		listeMembres.add(membre);
	}

	/**
	 * Doit me retourner la liste des membres
	 */
	@Override
	public List<Membre> listeMembres() {
		return listeMembres;
	}
	
	/**
	 * Doit me renvoyer le membre qui correspond à l'id passé en paramère
	 */
	@Override
	public Membre getMembreByPseudo(String pseudo) {
		// je parcours la liste des membres jusqu'à trouvber celui qui correspond
		for (Membre membre : listeMembres) {
			if (membre.getPseudo().equals(pseudo)) {
				return membre;
			}
		}
		// si pas trouvé, je retourne null
		return null;
	}

}
