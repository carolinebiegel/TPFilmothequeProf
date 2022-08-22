package fr.eni.filmotheque.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import fr.eni.filmotheque.bo.Participant;
import fr.eni.filmotheque.service.ParticipantService;

/**
 * Converter => permet de specifier à Spring comment convertir les champs de formulaire de type Date
 * Pour qu'il soit reconnu par Spring, il faut le rajouter à son contexte
 */
@Component
public class ParticipantConverter implements Converter<String, Participant> {

	@Autowired
	private ParticipantService participantService;
	
	/**
	 * On doit coder explicitement comment on passe d'une date au format texte => format Participant
	 */
	@Override
	public Participant convert(String idAuFormatTexte) {
		long id = Integer.parseInt(idAuFormatTexte);
		return participantService.getParticipantById(id);
	}
}
