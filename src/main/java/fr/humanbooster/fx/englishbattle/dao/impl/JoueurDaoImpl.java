package fr.humanbooster.fx.englishbattle.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.humanbooster.fx.englishbattle.business.Joueur;
import fr.humanbooster.fx.englishbattle.dao.ConnexionBdd;
import fr.humanbooster.fx.englishbattle.dao.JoueurDao;
import fr.humanbooster.fx.englishbattle.dao.NiveauDao;
import fr.humanbooster.fx.englishbattle.dao.PartieDao;
import fr.humanbooster.fx.englishbattle.dao.Requetes;
import fr.humanbooster.fx.englishbattle.dao.VilleDao;

public class JoueurDaoImpl implements JoueurDao{
	
	private static Connection connexion ;
	private NiveauDao niveauDao = new NiveauDaoImpl();
	private VilleDao villeDao = new VilleDaoImpl();
	
	public JoueurDaoImpl() {
		try {
			connexion = ConnexionBdd.getConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	 
	@Override
	public Joueur create(Joueur joueur) throws SQLException {
		PreparedStatement preparedStatement = connexion.prepareStatement(Requetes.AJOUT_JOUEUR, Statement.RETURN_GENERATED_KEYS);
		
		preparedStatement.setString(1, joueur.getEmail());
		preparedStatement.setString(2, joueur.getMotDePasse());
		preparedStatement.setString(3, joueur.getNom());
		preparedStatement.setString(4, joueur.getPrenom());
		preparedStatement.setLong(5, joueur.getNiveau().getId());
		preparedStatement.setLong(6, joueur.getVille().getId());
		preparedStatement.executeUpdate();
		ResultSet rs = preparedStatement.getGeneratedKeys();
		if (rs.next()) {
			// On affecte à la compagnie donnée en paramètre l'id choisi par MySql
			joueur.setId(rs.getLong(1));			
			}
		return joueur;
	}

	@Override
	public Joueur findOne(Long id) throws SQLException {
		PreparedStatement preparedStatement = connexion.prepareStatement(Requetes.JOUEUR_PAR_ID, Statement.RETURN_GENERATED_KEYS);
		preparedStatement.setLong(1, id);
		ResultSet rs = preparedStatement.executeQuery();
		if (rs .next()) {
			PartieDao partieDao = new PartieDaoImpl();
			Joueur joueur = new Joueur();
			joueur.setId(rs .getLong("id"));
			joueur.setNom(rs .getString("nom"));
			joueur.setPrenom(rs .getString("prenom"));
			joueur.setEmail(rs.getString("email"));
			joueur.setMotDePasse(rs.getString("motDePasse"));
			joueur.setVille(villeDao.findOne(rs.getLong("ville_id")));
			joueur.setNiveau(niveauDao.findOne(rs.getLong("niveau_id")));
			joueur.setEstEnLigne(rs.getBoolean("estEnLigne"));
			joueur.setParties(partieDao.findByJoueur(joueur));
			return joueur;
			}
		return null;
	}

	
	@Override
	public List<Joueur> findAll() throws SQLException {
		List<Joueur> joueurs = new ArrayList<>();
		PreparedStatement ps = connexion.prepareStatement(Requetes.TOUS_LES_JOUEURS);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Joueur joueur = findOne(rs.getLong("id"));
			joueurs.add(joueur);
		}
		return joueurs;
	}


	@Override
	public boolean update(Long id , Joueur joueur) throws SQLException {	
		PreparedStatement ps = connexion.prepareStatement(Requetes.UPDATE_JOUEUR);
		ps.setString(1,joueur.getEmail());
		ps.setString(2, joueur.getMotDePasse());
		ps.setString(3, joueur.getNom());
		ps.setString(4, joueur.getPrenom());
		//On recupere l'id du niveau du joueur, cette id me sert à remplacer le 4eme "?"
		ps.setLong(5,joueur.getNiveau().getId());
		ps.setLong(6, joueur.getVille().getId());
		ps.setBoolean(7, joueur.isEstEnLigne());
		ps.setLong(8, id);
		ps.executeUpdate();		
		return true;
	}

	
	@Override
	public boolean delete(Long id) throws SQLException {
		Joueur joueurAEffacer =  findOne(id);
		//si l'id du joueur est different de null alors on execute la requete
		if (joueurAEffacer != null) {
			PreparedStatement ps = connexion.prepareStatement(Requetes.SUPPRESSION_JOUEUR);
			ps.setLong(1, id);
			ps.executeUpdate();
			return true;
		}
		else {
			return false;
		}
	}

}
