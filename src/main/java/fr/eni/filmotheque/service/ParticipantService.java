package fr.eni.filmotheque.service;

import java.util.List;

import fr.eni.filmotheque.bo.Genre;
import fr.eni.filmotheque.bo.Participant;

/**
 * Interface
 * Ce qui sert à specifier les fonctionnalités de notre service
 */
public interface ParticipantService {
	
	/**
	 * 1. On va avoir besoin de créer des participants
	 */
	public void addParticipant(Participant participant) throws Exception;
	
	/**
	 * 2. On va avoir besoind de lister les participants	 * 
	 */
	public List<Participant> listeParticipants();
	
	/**
	 * 3. On va avoir besoin de recupérer un participant à partir de son id 
	 */
	public Participant getParticipantById(long id);
}
