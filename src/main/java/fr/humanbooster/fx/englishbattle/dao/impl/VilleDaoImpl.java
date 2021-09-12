package fr.humanbooster.fx.englishbattle.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.humanbooster.fx.englishbattle.business.Ville;
import fr.humanbooster.fx.englishbattle.dao.ConnexionBdd;
import fr.humanbooster.fx.englishbattle.dao.Requetes;
import fr.humanbooster.fx.englishbattle.dao.VilleDao;

public class VilleDaoImpl implements VilleDao {

	private static Connection connection;

	public VilleDaoImpl() {
		try {
			connection = ConnexionBdd.getConnection();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Ville create(Ville ville) throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement(Requetes.AJOUT_VILLE,
				Statement.RETURN_GENERATED_KEYS);
		preparedStatement.setString(1, ville.getNom());
		preparedStatement.executeUpdate();
		ResultSet rs = preparedStatement.getGeneratedKeys();
		rs.next();
		ville.setId(rs.getLong(1));

		return ville;
	}

	@Override
	public List<Ville> findAll() throws SQLException {
		List<Ville> villes = new ArrayList<>();
		PreparedStatement ps = connection.prepareStatement(Requetes.TOUTES_LES_VILLES);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Ville ville = new Ville(rs.getString("nom"));
			ville.setId(rs.getLong("id"));
			villes.add(ville);
		}
		return villes;
	}

	@Override
	public Ville findOne(Long id) throws SQLException {
		Ville ville = null;
		PreparedStatement ps = connection.prepareStatement(Requetes.VILLE_PAR_ID);
		ps.setLong(1, id);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			ville = new Ville(rs.getString("nom"));
			ville.setId(rs.getLong("id"));
		}
		return ville;
	}

	@Override
	public boolean delete(Long id) throws SQLException {
		Ville ville = findOne(id);
		if (ville == null) {
			return false;
		}
		PreparedStatement ps = connection.prepareStatement(Requetes.SUPPRESSION_VILLE);
		ps.setLong(1, id);
		ps.executeUpdate();
		return true;
	}

}
