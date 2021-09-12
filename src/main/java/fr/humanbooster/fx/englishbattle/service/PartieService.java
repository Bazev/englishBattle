package fr.humanbooster.fx.englishbattle.service;

import java.util.List;

import fr.humanbooster.fx.englishbattle.business.Joueur;
import fr.humanbooster.fx.englishbattle.business.Partie;
import fr.humanbooster.fx.englishbattle.business.Verbe;

public interface PartieService {

	Partie ajouterPartie(Joueur joueur);
	
	Partie recupererPartie(Long id);
	
	List<Partie> recupererParties();
	
	public boolean estPresent(Partie partie, Verbe verbe);
	
	public boolean supprimerPartie(Long id);
}
