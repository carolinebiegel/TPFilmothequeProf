package fr.eni.filmotheque.service;

import java.util.List;

import fr.eni.filmotheque.bo.Genre;
import fr.eni.filmotheque.bo.Membre;

/**
 * Interface
 * Ce qui sert à specifier les fonctionnalités de notre service
 */
public interface MembreService {
	
	/**
	 * 1. On va avoir besoin de créer des membres
	 */
	public void addMembre(Membre membre) throws Exception;
	
	/**
	 * 2. On va avoir besoind de lister les membres	 * 
	 */
	public List<Membre> listeMembres();
	
	/**
	 * 3. On va avoir besoin de recupérer un membre à partir de son pseudo 
	 */
	public Membre getMembreByPseudo(String pseudo);
}
