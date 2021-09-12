package fr.humanbooster.fx.englishbattle.util;

import java.util.Comparator;

import fr.humanbooster.fx.englishbattle.business.Joueur;

public class ComparateurDeJoueurSurVille implements Comparator<Joueur> {

	@Override
	public int compare(Joueur joueur1, Joueur joueur2) {
		return joueur1.getVille().getNom().compareTo(joueur2.getVille().getNom());
	}

}
