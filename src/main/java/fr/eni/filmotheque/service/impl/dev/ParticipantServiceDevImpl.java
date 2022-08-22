package fr.eni.filmotheque.service.impl.dev;

import java.util.ArrayList;
import java.util.List;

import fr.eni.filmotheque.service.ParticipantService;
import org.springframework.stereotype.Service;

import fr.eni.filmotheque.bo.Genre;
import fr.eni.filmotheque.bo.Participant;

@Service // obligatoire pour pouvoir injecter ce service avec @Autowired
public class ParticipantServiceDevImpl implements ParticipantService {
	
	// je gère en interne une liste de participants
	private List<Participant> listeParticipants = new ArrayList<>();
	private int compteur = 0; // je gère un compteur pour affecter les ids
	
	/**
	 * J'initialise quelques participants dans le constructeur
	 * @throws Exception 
	 */
	public ParticipantServiceDevImpl() throws Exception {
		this.addParticipant(new Participant("Cyril", "Mace", true, true));
		this.addParticipant(new Participant("Tom", "Hanks", true, true));
		this.addParticipant(new Participant("Michele", "Pfff", false, true));
		this.addParticipant(new Participant("Bruce", "Wilis", true, false));;
	}

	@Override
	public void addParticipant(Participant participant) throws Exception {
		
		// 1. Phase de validation fonctionelle
		if (listeParticipants.contains(participant)) {
			throw new Exception("participant déjà existant");
		}
		
		// 2. j'affecte un id à mon participant (sera fait automatiquement avec la base de donnée)
		compteur++;
		participant.setId(compteur);
		
		// 3. j'ajoute le participant à ma liste de participants
		listeParticipants.add(participant);
	}

	/**
	 * Doit me retourner la liste des participants
	 */
	@Override
	public List<Participant> listeParticipants() {
		return listeParticipants;
	}
	
	/**
	 * Doit me renvoyer le participant qui correspond à l'id passé en paramère
	 */
	@Override
	public Participant getParticipantById(long id) {
		// je parcours la liste des participants jusqu'à trouvber celui qui correspond
		for (Participant participant : listeParticipants) {
			if (participant.getId() == id) {
				return participant;
			}
		}
		// si pas trouvé, je retourne null
		return null;
	}

}
