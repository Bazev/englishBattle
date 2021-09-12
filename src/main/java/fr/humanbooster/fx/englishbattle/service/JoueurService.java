package fr.humanbooster.fx.englishbattle.service;

import java.util.List;

import fr.humanbooster.fx.englishbattle.business.Joueur;
import fr.humanbooster.fx.englishbattle.business.Niveau;
import fr.humanbooster.fx.englishbattle.business.Ville;

public interface JoueurService {

	Joueur ajouterJoueur(String email, String nom, String prenom, String motDePasse, Long idNiveau, Long idVille);

	/**
	 * 
	 * @param email
	 * @param nom
	 * @param prenom
	 * @param motDePasse
  	 * @param ville
	 * @param niveau 
	 * @return un Object joueur complet si l'insertion en base s'est effectué, null sinon
	 */
	Joueur ajouterJoueur(String email, String nom, String prenom, String motDePasse, Ville ville, Niveau niveau);

	/**
	 * 
	 * @param id l'ID du joueur à récuperer
	 * @return un joueur si l'ID est trouvé, null si le joueur n'existe pas dans la base
	 */
	Joueur recupererJoueur(Long id);

	/**
	 * 
	 * @return la liste des joueurs. La liste est vide si il n'y a aucun joueur en BDD. Retourne null si il y a une erreur
	 */
	List<Joueur> recupererJoueurs();

	List<Joueur> recupererJoueursDuHallOfFame();

	/**
	 * 
	 * @param id
	 * @param email
	 * @param nom
	 * @param prenom
	 * @param motDePasse
	 * @param ville
	 * @param niveau
	 * @return true si le joueur est mis à jour / false si la methode update n'a pas aboutie
	 */
	boolean mettreAJourJoueur(Long id, String email, String nom, String prenom, String motDePasse, Ville ville,Niveau niveau, boolean estEnLigne);
	
	/**
	 * 
	 * @param id du joueur à supprimer
	 * @return true si le joueur est supprimer de la base de donnée, false sinon
	 */
	boolean supprimerJoueur(Long id);

	public Joueur recupererJoueurParEmailEtMotDePasse(String email, String motDePasse);

	Joueur definirJoueurEnLigne(Joueur joueur, boolean estEnLigne);

}
