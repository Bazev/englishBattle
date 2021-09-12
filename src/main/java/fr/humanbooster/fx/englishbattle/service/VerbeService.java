package fr.humanbooster.fx.englishbattle.service;

import java.util.List;

import fr.humanbooster.fx.englishbattle.business.Partie;
import fr.humanbooster.fx.englishbattle.business.Verbe;
import fr.humanbooster.fx.englishbattle.exceptions.AucunVerbeException;

public interface VerbeService {
	
    Verbe ajouterVerbe(String baseVerbale, String preterit, String participePasse, String traduction);
	Verbe ajouterVerbe(Verbe verbe);
	List<Verbe> recupererVerbes();
	Verbe recupererVerbe(Long id);
	Verbe recupererVerbeAleatoire();
	Verbe recupererVerbeAleatoire(Partie partie) throws AucunVerbeException;
	Verbe modifierVerbe(Long idVerbe, String baseVerbale, String preterit, String participePasse, String traduction);
	boolean supprimerVerbe(Long id);
	boolean importerVerbes();
	int recupererNbVerbes();

}
