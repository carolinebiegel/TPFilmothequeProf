package fr.eni.filmotheque.converter;

import java.time.LocalDate;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Converter => permet de specifier à Spring comment convertir les champs de formulaire de type Date
 * Pour qu'il soit reconnu par Spring, il faut le rajouter à son contexte
 */
@Component
public class LocalDateConverter implements Converter<String, LocalDate> {

	/**
	 * On doit coder explicitement comment on passe d'une date au format texte => format LocalDate
	 */
	@Override
	public LocalDate convert(String dateAuFormatTexte) {
		return LocalDate.parse(dateAuFormatTexte);
	}
}
