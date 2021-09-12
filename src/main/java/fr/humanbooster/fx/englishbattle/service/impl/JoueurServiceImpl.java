package fr.humanbooster.fx.englishbattle.service.impl;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import fr.humanbooster.fx.englishbattle.business.Joueur;
import fr.humanbooster.fx.englishbattle.business.Niveau;
import fr.humanbooster.fx.englishbattle.business.Ville;
import fr.humanbooster.fx.englishbattle.dao.JoueurDao;
import fr.humanbooster.fx.englishbattle.dao.impl.JoueurDaoImpl;
import fr.humanbooster.fx.englishbattle.service.JoueurService;
import fr.humanbooster.fx.englishbattle.service.NiveauService;
import fr.humanbooster.fx.englishbattle.service.VilleService;
import fr.humanbooster.fx.englishbattle.util.ComparateurDeJoueurSurScore;

public class JoueurServiceImpl implements JoueurService {

	private JoueurDao joueurDao = new JoueurDaoImpl();
	private NiveauService niveauService = new NiveauServiceImpl();
	private VilleService villeService = new VilleServiceImpl();

	@Override
	public Joueur ajouterJoueur(String email, String nom, String prenom, String motDePasse, Long idNiveau,
			Long idVille) {
		Niveau niveau = niveauService.recupererNiveau(idNiveau);
		Ville ville = villeService.recupererVille(idVille);
		Joueur joueur = ajouterJoueur(email, nom, prenom, motDePasse, ville, niveau);
		return joueur;
	}

	@Override
	public Joueur recupererJoueur(Long id) {
		try {
			return joueurDao.findOne(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Joueur> recupererJoueurs() {
		try {
			return joueurDao.findAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Joueur ajouterJoueur(String email, String nom, String prenom, String motDePasse, Ville ville,
			Niveau niveau) {
		Joueur joueur = new Joueur();
		joueur.setEmail(email);
		joueur.setMotDePasse(motDePasse);
		joueur.setNiveau(niveau);
		joueur.setNom(nom);
		joueur.setPrenom(prenom);
		joueur.setVille(ville);
		try {
			joueurDao.create(joueur);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return joueur;
	}

	@Override
	public boolean supprimerJoueur(Long id) {
		try {
			joueurDao.delete(id);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean mettreAJourJoueur(Long idDuJoueurAModifier, String email, String nom, String prenom,
			String motDePasse, Ville ville, Niveau niveau, boolean estEnLigne) {
		Joueur joueur = new Joueur();
		joueur.setEmail(email);
		joueur.setMotDePasse(motDePasse);
		joueur.setNiveau(niveau);
		joueur.setNom(nom);
		joueur.setPrenom(prenom);
		joueur.setVille(ville);
		joueur.setEstEnLigne(estEnLigne);
		try {
			joueurDao.update(idDuJoueurAModifier, joueur);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Joueur recupererJoueurParEmailEtMotDePasse(String email, String motDePasse) {
		Joueur monjoueur = null;
		for (Joueur joueur : recupererJoueurs()) {
			if (joueur.getEmail().equals(email)) {
				if (joueur.getMotDePasse().equals(motDePasse)) {
					monjoueur = joueur;
					break;
				}
			}
		}
		return monjoueur;
	}

	@Override
	public List<Joueur> recupererJoueursDuHallOfFame() {
		List<Joueur> joueurs = recupererJoueurs();
		Collections.sort(joueurs, new ComparateurDeJoueurSurScore());
		return joueurs;
	}

	@Override
	public Joueur definirJoueurEnLigne(Joueur joueur, boolean estEnLigne) {
		joueur.setEstEnLigne(estEnLigne);
		mettreAJourJoueur(joueur.getId(), joueur.getEmail(), joueur.getNom(), joueur.getPrenom(), joueur.getMotDePasse(), joueur.getVille(), joueur.getNiveau(), joueur.isEstEnLigne());
		return joueur;
	}

}
