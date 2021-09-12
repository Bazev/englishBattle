package fr.humanbooster.fx.englishbattle.service;

import java.util.List;

import fr.humanbooster.fx.englishbattle.business.Ville;



public interface VilleService {

	
	Ville ajouterVille(String nom);
	
	List<Ville> recupererVilles();
	
	Ville recupererVille(Long id);
	
	boolean supprimerVille(Long id);
	
}
