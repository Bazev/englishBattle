package fr.humanbooster.fx.englishbattle.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.humanbooster.fx.englishbattle.business.Joueur;
import fr.humanbooster.fx.englishbattle.business.Partie;
import fr.humanbooster.fx.englishbattle.dao.ConnexionBdd;
import fr.humanbooster.fx.englishbattle.dao.JoueurDao;
import fr.humanbooster.fx.englishbattle.dao.PartieDao;
import fr.humanbooster.fx.englishbattle.dao.QuestionDao;
import fr.humanbooster.fx.englishbattle.dao.Requetes;

public class PartieDaoImpl implements PartieDao {

	private static Connection connexion;
	private JoueurDao joueurDao;

	// Connexion à la BDD
	public PartieDaoImpl() {
		try {
			connexion = ConnexionBdd.getConnection();
			joueurDao = new JoueurDaoImpl();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	// Création d'une partie
	public Partie create(Partie partie) throws SQLException {
		PreparedStatement ps = connexion.prepareStatement(Requetes.AJOUT_PARTIE, Statement.RETURN_GENERATED_KEYS);
		ps.setLong(1, partie.getJoueur().getId());
		ps.executeUpdate();
		ResultSet rs = ps.getGeneratedKeys();
		rs.next();
		partie.setId(rs.getLong(1));
		return partie;
	}

	@Override
	// Recupération d'une partie unique en fonction de son ID
	public Partie findOne(Long id) throws SQLException {
		PreparedStatement ps = connexion.prepareStatement(Requetes.PARTIE_PAR_ID);

		ps.setLong(1, id);

		ResultSet rs = ps.executeQuery();

		if (rs.next()) {
			Partie partie = new Partie(joueurDao.findOne(rs.getLong("joueur_id")));
			partie.setId(rs.getLong("id"));
			return partie;
		}
		return null;
	}

	@Override
	public List<Partie> findByJoueur(Joueur joueur) throws SQLException {
		List<Partie> parties = new ArrayList<>();

		PreparedStatement ps = connexion.prepareStatement(Requetes.TOUTES_LES_PARTIES_DU_JOUEUR);
		ps.setLong(1, joueur.getId());
		ResultSet rs = ps.executeQuery();
		QuestionDao questionDao = new QuestionDaoImpl();
		while (rs.next()) {
			Partie partie = new Partie(joueur);
			partie.setId(rs.getLong("id"));
			partie.setQuestions(questionDao.findByPartie(partie));
			parties.add(partie);
		}
		return parties;
	}

	@Override
	public List<Partie> findAll() throws SQLException {
		List<Partie> parties = new ArrayList<>();

		PreparedStatement ps = connexion.prepareStatement(Requetes.TOUTES_LES_PARTIES);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Partie partie = new Partie(joueurDao.findOne(rs.getLong("joueur_id")));
			partie.setId(rs.getLong("id"));
			parties.add(partie);
		}
		return parties;
	}

	@Override
	public boolean update(Long id, Joueur joueur) throws SQLException {
		Partie partie = findOne(id);
		if (partie == null) {
			return false;
		}
		PreparedStatement ps = connexion.prepareStatement(Requetes.UPDATE_PARTIE);
		ps.setLong(2, id);
		ps.executeUpdate();

		return true;
	}

	@Override
	public boolean delete(Long id) throws SQLException {
		Partie partie = findOne(id);
		if (partie == null) {
			return false;
		}
		PreparedStatement ps = connexion.prepareStatement(Requetes.SUPPRESSION_PARTIE);
		ps.setLong(1, id);
		ps.executeUpdate();
		return true;
	}

}