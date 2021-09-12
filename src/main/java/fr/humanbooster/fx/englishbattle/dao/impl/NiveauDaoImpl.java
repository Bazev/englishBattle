package fr.humanbooster.fx.englishbattle.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.humanbooster.fx.englishbattle.business.Niveau;
import fr.humanbooster.fx.englishbattle.dao.ConnexionBdd;
import fr.humanbooster.fx.englishbattle.dao.NiveauDao;
import fr.humanbooster.fx.englishbattle.dao.Requetes;

public class NiveauDaoImpl implements NiveauDao {

	private static Connection connection;

	public NiveauDaoImpl() {
		try {
			connection = ConnexionBdd.getConnection();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public Niveau create(Niveau niveau) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(Requetes.AJOUT_NIVEAU, Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, niveau.getNom());
		ps.executeUpdate();
		ResultSet rs = ps.getGeneratedKeys();
		if (rs.next()) {
			niveau.setId(rs.getLong(1));
		}
		return niveau;
	}

	@Override
	public List<Niveau> findAll() throws SQLException {
		List<Niveau> niveaux = new ArrayList<>();
		PreparedStatement ps = connection.prepareStatement(Requetes.TOUS_LES_NIVEAUX);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Niveau niveau = new Niveau(rs.getString("nom"));
			niveau.setId(rs.getLong("id"));
			niveaux.add(niveau);
		}
		return niveaux;
	}

	@Override
	public Niveau findOne(Long id) throws SQLException {
		Niveau niveau = null;
		PreparedStatement ps = connection.prepareStatement(Requetes.NIVEAU_PAR_ID);
		ps.setLong(1, id);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			niveau = new Niveau(rs.getString("nom"));
			niveau.setId(rs.getLong("id"));
		}
		return niveau;
	}

}
