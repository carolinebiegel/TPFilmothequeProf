package fr.eni.filmotheque.service.impl.dev;

import java.util.ArrayList;
import java.util.List;

import fr.eni.filmotheque.service.AvisService;
import org.springframework.stereotype.Service;

import fr.eni.filmotheque.bo.Avis;

@Service
public class AvisServiceDevImpl implements AvisService {
	
	List<Avis> listeAVis = new ArrayList<>();
	int compteur = 0;

	@Override
	public void addAvis(Avis avis) {
		compteur++;
		avis.setId(compteur);
		listeAVis.add(avis);
	}
	
	/*
	 * On n'a pas besoin de renvoyer la liste de tous les avis, on y accédera via les films de notre liste de films
	 */

}
